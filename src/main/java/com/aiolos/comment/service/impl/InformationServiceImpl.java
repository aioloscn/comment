package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.ConcernRelationModelMapper;
import com.aiolos.comment.dal.InformationModelMapper;
import com.aiolos.comment.dal.InformationThumbsUpModelMapper;
import com.aiolos.comment.model.InformationModel;
import com.aiolos.comment.model.InformationThumbsUpModel;
import com.aiolos.comment.service.InformationService;
import com.aiolos.comment.utils.DateUtils;
import com.aiolos.comment.vo.CommonPageVO;
import com.aiolos.comment.vo.InformationVO;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aiolos
 * @date 2019-11-28 15:27
 */
@Slf4j
@Service
public class InformationServiceImpl implements InformationService {

    @Value("${image.url}")
    private String imageUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ConcernRelationModelMapper concernRelationModelMapper;

    @Autowired
    private InformationModelMapper informationModelMapper;

    @Autowired
    private InformationThumbsUpModelMapper informationThumbsUpModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse releaseContent(InformationModel informationModel) {

        int affected = informationModelMapper.insertSelective(informationModel);
        if (affected > 0) {
            return CommonResponse.ok("已发布", informationModel.getId());
        }
        return CommonResponse.error(EnumError.INFORMATION_RELEASE_FAIL.getErrCode(), EnumError.INFORMATION_RELEASE_FAIL.getErrMsg());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse deleteInformation(int id) {
        int affected = informationModelMapper.deleteByPrimaryKey(id);
        if (affected > 0) {
            return CommonResponse.ok("已删除, null)");
        }
        return CommonResponse.error(EnumError.DELETE_FAIL.getErrCode(), EnumError.DELETE_FAIL.getErrMsg());
    }

    @Override
    public CommonResponse selectAllInformation(int topicId, Integer userId, int pageIndex, int pageCount) {

        long startTime = System.currentTimeMillis();
        List<InformationVO> list = informationModelMapper.selectAllInformation(topicId, pageIndex, pageCount);

        int rowsTotal = 0;
        List<String> imageList = null;
        if (list != null && list.size() > 0) {
            rowsTotal = list.get(0).getRowsTotal();
            for (InformationVO informationVO : list) {
                String images = informationVO.getImages();
                // 拼接图片地址
                if (StringUtils.isNotBlank(images)) {
                    imageList = Arrays.asList(images.split(","));
                    for (int i = 0; i < imageList.size(); i++) {
                        imageList.set(i, imageUrl + imageList.get(i));
                    }
                    informationVO.setImageList(imageList);
                }
                // 将字符还原成emoji
                if (StringUtils.isNotEmpty(informationVO.getContent())) {
                    String content = EmojiParser.parseToUnicode(informationVO.getContent());
                    informationVO.setContent(content);
                }
                // 转换时间
                informationVO.setDateInterval(DateUtils.toToday(informationVO.getGmtCreate()));

                // 赋值是否已关注
                if (userId != null && redisTemplate.opsForSet().isMember(Constant.FOLLOWREDISKEY + userId, informationVO.getFromUid())) {
                    informationVO.setFollowed(true);
                }

                // 赋值是否已点赞
                if (userId != null && redisTemplate.opsForSet().isMember(Constant.THUMBSUPREDISKEY + userId, informationVO.getId())) {
                    informationVO.setPraised(true);
                }
            }
        }

        int pages = rowsTotal == 0 ? 0 : new Double(Math.ceil(Double.valueOf(rowsTotal) / pageCount)).intValue();
        CommonPageVO<List<InformationVO>> commonPageVO = new CommonPageVO<>(pages, list);
        log.info("function selectAllInformation execution time: {}", System.currentTimeMillis() - startTime);
        return CommonResponse.ok(commonPageVO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse thumbsUp(InformationThumbsUpModel informationThumbsUpModel) throws CustomizeException {

        // TODO 后续引入消息队列，先更新redis再异步更新到数据库
        int affected = 0;
        try {
            affected = informationThumbsUpModelMapper.insertSelective(informationThumbsUpModel);
        } catch (DuplicateKeyException e) {
            log.error("违反information_thumbs_up唯一约束");
            return CommonResponse.error(EnumError.CONSTRAINT_VIOLATION.getErrCode(), EnumError.CONSTRAINT_VIOLATION.getErrMsg());
        }
        affected += informationModelMapper.increaseThumbsUp(informationThumbsUpModel.getInformationId());
        if (affected == 2) {

            // 将被点赞的信息ID添加到当前用户的redis集合中
            redisTemplate.opsForSet().add(Constant.THUMBSUPREDISKEY + informationThumbsUpModel.getFromUid(), informationThumbsUpModel.getInformationId());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.THUMBS_UP_FAIL.getErrMsg());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public CommonResponse cancelThumbsUp(InformationThumbsUpModel informationThumbsUpModel) throws CustomizeException {

        // TODO 后续引入消息队列，先更新redis再异步更新到数据库
        int resultCount = informationThumbsUpModelMapper.selectByModel(informationThumbsUpModel);
        if (resultCount == 0)
            return CommonResponse.error(EnumError.DUPLICATION_OPERATION.getErrCode(), EnumError.DUPLICATION_OPERATION.getErrMsg());

        int affected = informationThumbsUpModelMapper.cancelThumbsUp(informationThumbsUpModel);
        affected += informationModelMapper.decreaseThumbsUp(informationThumbsUpModel.getInformationId());
        if (affected == 2) {

            // 将取消点赞的信息ID从当前用户的redis集合中移除
            redisTemplate.opsForSet().remove(Constant.THUMBSUPREDISKEY + informationThumbsUpModel.getFromUid(), informationThumbsUpModel.getInformationId());
            return CommonResponse.ok(null);
        } else {
            throw new RuntimeException(EnumError.CANCEL_THUMBS_UP_FAIL.getErrMsg());
        }
    }
}
