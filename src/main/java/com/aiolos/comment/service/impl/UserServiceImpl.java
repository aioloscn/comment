package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.ConcernRelationModelMapper;
import com.aiolos.comment.dal.UserModelMapper;
import com.aiolos.comment.model.ConcernRelationModel;
import com.aiolos.comment.model.UserModel;
import com.aiolos.comment.service.UserService;
import com.aiolos.comment.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2019-10-13 11:05
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserModelMapper userModelMapper;

    @Autowired
    private ConcernRelationModelMapper concernRelationModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse register(UserModel userModel) throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 验证手机号是否已存在
        int resultCount = userModelMapper.getUserCountByTelphone(userModel);
        if (resultCount > 0) {
            return CommonResponse.error(EnumError.REGISTER_DUP_FAIL.getErrCode(), EnumError.REGISTER_DUP_FAIL.getErrMsg());
        }

        try {
            Long accountId = 100000L;
            if (redisTemplate.hasKey(Constant.ACCOUNTID)) {
                Long redisRes = redisTemplate.opsForValue().increment(Constant.ACCOUNTID, 1L);
                log.info("redis increment account id result: {}", redisRes);
            } else {
                redisTemplate.opsForValue().set(Constant.ACCOUNTID, accountId);
            }
            userModel.setAccountId(accountId.intValue());
        } catch (Exception e) {
            log.error(e.getMessage());
            userModel.setAccountId(1000000);
            redisTemplate.opsForValue().set(Constant.ACCOUNTID, 1000000);
        }
        userModel.setPassword(CommonUtil.encodeByMD5(userModel.getPassword()));
        userModel.setGmtCreate(new Date());
        userModel.setGmtModified(new Date());

        try {
            int affectedCount = userModelMapper.insertSelective(userModel);
            if (affectedCount == 0) {
                throw new CustomizeException(EnumError.UNKNOWN_ERROR, "注册失败");
            }
        } catch (DuplicateKeyException e) {
            throw new CustomizeException(EnumError.REGISTER_DUP_FAIL);
        }

        UserModel user = getUser(userModel.getId());
        if (user != null) {

            user.setPassword(StringUtils.EMPTY);
            try {
                redisTemplate.opsForValue().set(Constant.USERREDISKEY  + user.getTelphone(), user);
                redisTemplate.expire(Constant.USERREDISKEY  + user.getTelphone(), 7, TimeUnit.DAYS);
            } catch (Exception e) {
                log.error("注册时缓存redis出现异常，{}", e.getMessage());
                throw new CustomizeException(EnumError.REDIS_ERROR);
            }
        }
        return CommonResponse.ok(user);
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, CustomizeException {

        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone, CommonUtil.encodeByMD5(password));
        if (userModel == null) {
            throw new CustomizeException(EnumError.LOGIN_FAIL);
        }

        try {
            long expirationTime = redisTemplate.getExpire(Constant.USERREDISKEY  + telphone, TimeUnit.MILLISECONDS);
            redisTemplate.opsForValue().set(Constant.USERREDISKEY  + telphone, userModel);
            if (expirationTime > 0) {
                redisTemplate.expire(Constant.USERREDISKEY  + telphone, expirationTime, TimeUnit.MILLISECONDS);
            } else {
                redisTemplate.expire(Constant.USERREDISKEY  + telphone, 7, TimeUnit.DAYS);
            }
        } catch (Exception e) {
            log.error("登录时缓存redis出现异常，{}", e.getMessage());
            throw new CustomizeException(EnumError.REDIS_ERROR);
        }
        return userModel;
    }

    @Override
    public UserModel loginOrRegister(String telphone) throws CustomizeException {

        UserModel userModel = userModelMapper.getUserByTelphone(telphone);
        if (userModel != null) {

            userModel.setPassword(StringUtils.EMPTY);
            redisTemplate.opsForValue().set(Constant.USERREDISKEY + telphone, userModel);
            redisTemplate.expire(Constant.USERREDISKEY  + telphone, 7, TimeUnit.DAYS);
        }
        return userModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse follow(ConcernRelationModel concernRelationModel) throws CustomizeException {

        int affected = 0;
        try {
            affected = concernRelationModelMapper.insertSelective(concernRelationModel);
        } catch (DuplicateKeyException e) {
            log.error("违反concern_relation唯一约束");
            return CommonResponse.error(EnumError.CONSTRAINT_VIOLATION.getErrCode(), EnumError.CONSTRAINT_VIOLATION.getErrMsg());
        }
        affected += userModelMapper.increaseFollows(concernRelationModel.getConcernUid());
        affected += userModelMapper.increaseConcerns(concernRelationModel.getFollowUid());
        if (affected == 3) {

            // 将被关注人添加到当前用户的redis集合中
            redisTemplate.opsForSet().add(Constant.FOLLOWREDISKEY + concernRelationModel.getConcernUid(), concernRelationModel.getFollowUid());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.FOLLOW_FAIL.getErrMsg());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse cancelFollow(ConcernRelationModel concernRelationModel) throws CustomizeException {

        int resultCount = concernRelationModelMapper.selectByModel(concernRelationModel);
        if (resultCount == 0)
            return CommonResponse.error(EnumError.DUPLICATION_OPERATION.getErrCode(), EnumError.DUPLICATION_OPERATION.getErrMsg());

        int affected = concernRelationModelMapper.cancelFollow(concernRelationModel);
        affected += userModelMapper.decreaseFollows(concernRelationModel.getConcernUid());
        affected += userModelMapper.decreaseConcerns(concernRelationModel.getFollowUid());
        if (affected == 3) {

            // 将被关注人从redis集合中移除
            redisTemplate.opsForSet().remove(Constant.FOLLOWREDISKEY + concernRelationModel.getConcernUid(), concernRelationModel.getFollowUid());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.CANCEL_FOLLOW_FAIL.getErrMsg());
        }
    }

}
