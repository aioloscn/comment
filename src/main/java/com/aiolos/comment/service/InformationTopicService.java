package com.aiolos.comment.service;

import com.aiolos.comment.model.InformationTopicModel;

import java.util.List;

/**
 * @author Aiolos
 * @date 2019-11-27 13:25
 */
public interface InformationTopicService {

    List<InformationTopicModel> getTopics();
}
