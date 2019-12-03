package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.ActivityCommentModelMapper;
import com.aiolos.comment.dal.ActivityModelMapper;
import com.aiolos.comment.model.ActivityCommentModel;
import com.aiolos.comment.service.ActivityCommentService;
import com.aiolos.comment.utils.DateUtils;
import com.aiolos.comment.vo.ActivityCommentVO;
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
public class ActivityCommentServiceImpl implements ActivityCommentService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityCommentModelMapper activityCommentModelMapper;

    @Autowired
    private ActivityModelMapper activityModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse release(ActivityCommentModel activityCommentModel) {

        int affected = 0;
        affected += activityCommentModelMapper.insertSelective(activityCommentModel);
        affected += activityModelMapper.increaseComment(activityCommentModel.getActivityId());
        if (affected == 2)
            return CommonResponse.ok("发布成功", null);
        return CommonResponse.error(EnumError.RELEASE_FAIL.getErrCode(), EnumError.RELEASE_FAIL.getErrMsg());
    }

    @Override
    public CommonResponse getComments(Integer id, Integer userId, int pageIndex, int pageCount) {

        List<ActivityCommentVO> list = activityCommentModelMapper.getComments(id, pageIndex, pageCount);

        int rowTotal = 0;
        if (list != null && list.size() > 0) {
            rowTotal = list.get(0).getRowsTotal();
            for (ActivityCommentVO activityCommentVO : list) {
                // 将字符还原成emoji
                if (StringUtils.isNotEmpty(activityCommentVO.getContent())) {
                    String content = EmojiParser.parseToUnicode(activityCommentVO.getContent());
                    activityCommentVO.setContent(content);
                }
                // 转换时间
                activityCommentVO.setDateInterval(DateUtils.toToday(activityCommentVO.getGmtCreate()));

                // 赋值是否已点赞
                if (userId != null && redisTemplate.opsForSet().isMember(Constant.THUMBSUPREDISKEY + userId, activityCommentVO.getId())) {
                    activityCommentVO.setPraised(true);
                }
            }
        }
        return CommonResponse.ok(list);
    }
}
