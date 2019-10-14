package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.UserModelMapper;
import com.aiolos.comment.model.UserModel;
import com.aiolos.comment.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author Aiolos
 * @date 2019-10-13 11:05
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserModel register(UserModel userModel) throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException {

        userModel.setPassword(encodeByMD5(userModel.getPassword()));
        userModel.setCreatedAt(new Date());
        userModel.setUpdatedAt(new Date());

        try {
            userModelMapper.insertSelective(userModel);
        } catch (DuplicateKeyException e) {
            throw new CustomizeException(EnumError.REGISTER_DUP_FAIL);
        }
        return getUser(userModel.getId());
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, CustomizeException {

        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone, encodeByMD5(password));
        if (userModel == null) {
            throw new CustomizeException(EnumError.LOGIN_FAIL);
        }
        return userModel;
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
}
