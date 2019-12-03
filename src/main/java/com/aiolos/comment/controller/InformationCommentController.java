package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.model.InformationCommentModel;
import com.aiolos.comment.request.InformationCommentReq;
import com.aiolos.comment.service.InformationCommentService;
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
 * @date 2019-12-02 21:02
 */
@Slf4j
@RestController
@RequestMapping("/information/comment")
public class InformationCommentController {

    @Autowired
    private InformationCommentService informationCommentService;

    @PostMapping("/release")
    @ResponseBody
    public CommonResponse release(@Valid @RequestBody InformationCommentReq informationCommentReq, BindingResult bindingResult) {

        log.info("implement function information comment release");

        if (bindingResult.hasErrors()) {
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), CommonUtil.processErrorString(bindingResult));
        }

        // 将包含emoji的消息转码
        String content = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(informationCommentReq.getContent())) {
            content = EmojiParser.parseToAliases(informationCommentReq.getContent(), EmojiParser.FitzpatrickAction.PARSE);
        }

        InformationCommentModel informationCommentModel = new InformationCommentModel();
        informationCommentModel.setInformationId(informationCommentReq.getInformationId());
        informationCommentModel.setContent(content);
        informationCommentModel.setFromUid(informationCommentReq.getFromUid());
        return informationCommentService.release(informationCommentModel);
    }


    @GetMapping("/get")
    @ResponseBody
    public CommonResponse getComments(@RequestParam("id") Integer id,
                                      @RequestParam(value = "userId", required = false) Integer userId,
                                      @RequestParam(value = "pageIndex") int pageIndex) {

        log.info("implement function get information comment");

        if (id == null)
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), EnumError.BIND_EXCEPTION_ERROR.getErrMsg());
        return informationCommentService.getComments(id, userId, pageIndex, Constant.PAGECOUNT);
    }
}
