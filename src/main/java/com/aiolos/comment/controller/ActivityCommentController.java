package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.model.ActivityCommentModel;
import com.aiolos.comment.request.ActivityCommentReq;
import com.aiolos.comment.service.ActivityCommentService;
import com.aiolos.comment.utils.CommonUtil;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Aiolos
 * @date 2019-12-03 06:07
 */
@Slf4j
@RestController
@RequestMapping("/activity/comment")
public class ActivityCommentController {

    @Autowired
    private ActivityCommentService activityCommentService;

    @PostMapping("/release")
    @ResponseBody
    public CommonResponse release(@Valid @RequestBody ActivityCommentReq activityCommentReq, BindingResult bindingResult) {

        log.info("implement function activity comment release");

        if (bindingResult.hasErrors()) {
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), CommonUtil.processErrorString(bindingResult));
        }

        // 将包含emoji的消息转码
        String content = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(activityCommentReq.getContent())) {
            content = EmojiParser.parseToAliases(activityCommentReq.getContent(), EmojiParser.FitzpatrickAction.PARSE);
        }

        ActivityCommentModel activityCommentModel = new ActivityCommentModel();
        activityCommentModel.setActivityId(activityCommentReq.getActivityId());
        activityCommentModel.setContent(content);
        activityCommentModel.setFromUid(activityCommentReq.getFromUid());
        return activityCommentService.release(activityCommentModel);
    }


    @GetMapping("/get")
    @ResponseBody
    public CommonResponse getComments(@RequestParam("id") Integer id,
                                      @RequestParam(value = "userId", required = false) Integer userId,
                                      @RequestParam(value = "pageIndex") int pageIndex) {

        log.info("implement function get activity comment");

        if (id == null)
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), EnumError.BIND_EXCEPTION_ERROR.getErrMsg());
        return activityCommentService.getComments(id, userId, pageIndex, Constant.PAGECOUNT);
    }
}
