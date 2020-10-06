package com.aiolos.comment.service;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.model.ActivityModel;
import com.aiolos.comment.model.ActivityThumbsUpModel;
import com.aiolos.comment.model.ActivityTopicModel;

import java.util.List;

/**
 * @author Aiolos
 * @date 2019-12-03 04:59
 */
public interface ActivityService {

    CommonResponse getActivity(int topicId, Integer userId, int pageIndex, int pageCount);

    CommonResponse releaseContent(ActivityModel activityModel);

    CommonResponse deleteActivity(Integer id);

    CommonResponse thumbsUp(ActivityThumbsUpModel activityThumbsUpModel) throws CustomizeException;

    CommonResponse cancelThumbsUp(ActivityThumbsUpModel activityThumbsUpModel) throws CustomizeException;

    List<ActivityTopicModel> getTopics();

    CommonResponse search(int topicId, Integer userId, String keyword, int pageIndex, int pageCount) throws CustomizeException;
}
