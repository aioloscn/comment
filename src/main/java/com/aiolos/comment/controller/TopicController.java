package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.model.InformationTopicModel;
import com.aiolos.comment.service.InformationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aiolos
 * @date 2019-11-27 13:20
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private InformationTopicService informationTopicService;

    @GetMapping("/get")
    @ResponseBody
    public CommonResponse<List<InformationTopicModel>> getTopics() {
        List<InformationTopicModel> res = informationTopicService.getTopics();
        return CommonResponse.ok(res);
    }
}
