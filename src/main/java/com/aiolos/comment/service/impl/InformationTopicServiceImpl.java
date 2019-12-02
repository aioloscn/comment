package com.aiolos.comment.service.impl;

import com.aiolos.comment.dal.InformationTopicModelMapper;
import com.aiolos.comment.model.InformationTopicModel;
import com.aiolos.comment.service.InformationTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aiolos
 * @date 2019-11-27 13:25
 */
@Slf4j
@Service
public class InformationTopicServiceImpl implements InformationTopicService {

    @Autowired
    private InformationTopicModelMapper informationTopicModelMapper;

    @Override
    public List<InformationTopicModel> getTopics() {
        return informationTopicModelMapper.selectAllTopics();
    }
}
