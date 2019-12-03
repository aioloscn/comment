package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.InformationCommentModelMapper;
import com.aiolos.comment.dal.InformationModelMapper;
import com.aiolos.comment.model.InformationCommentModel;
import com.aiolos.comment.service.InformationCommentService;
import com.aiolos.comment.utils.DateUtils;
import com.aiolos.comment.vo.InformationCommentVO;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aiolos
 * @date 2019-12-02 21:40
 */
@Slf4j
@Service
public class InformationCommentServiceImpl implements InformationCommentService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InformationCommentModelMapper informationCommentModelMapper;

    @Autowired
    private InformationModelMapper informationModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse release(InformationCommentModel informationCommentModel) {

        int affected = 0;
        affected += informationCommentModelMapper.insertSelective(informationCommentModel);
        affected += informationModelMapper.increaseComment(informationCommentModel.getInformationId());
        if (affected == 2)
            return CommonResponse.ok("发布成功", null);
        return CommonResponse.error(EnumError.RELEASE_FAIL.getErrCode(), EnumError.RELEASE_FAIL.getErrMsg());
    }

    @Override
    public CommonResponse getComments(Integer id, Integer userId, int pageIndex, int pageCount) {

        List<InformationCommentVO> list = informationCommentModelMapper.getComments(id, pageIndex, pageCount);

        int rowTotal = 0;
        if (list != null && list.size() > 0) {
            rowTotal = list.get(0).getRowsTotal();
            for (InformationCommentVO informationCommentVO : list) {
                // 将字符还原成emoji
                if (StringUtils.isNotEmpty(informationCommentVO.getContent())) {
                    String content = EmojiParser.parseToUnicode(informationCommentVO.getContent());
                    informationCommentVO.setContent(content);
                }
                // 转换时间
                informationCommentVO.setDateInterval(DateUtils.toToday(informationCommentVO.getGmtCreate()));

                // 赋值是否已点赞
                if (userId != null && redisTemplate.opsForSet().isMember(Constant.THUMBSUPREDISKEY + userId, informationCommentVO.getId())) {
                    informationCommentVO.setPraised(true);
                }
            }
        }
        return CommonResponse.ok(list);
    }
}
