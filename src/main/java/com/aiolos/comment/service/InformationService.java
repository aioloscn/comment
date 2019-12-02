package com.aiolos.comment.service;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.model.InformationModel;
import com.aiolos.comment.model.InformationThumbsUpModel;

/**
 * @author Aiolos
 * @date 2019-11-28 15:15
 */
public interface InformationService {

    CommonResponse releaseContent(InformationModel informationModel);

    CommonResponse deleteInformation(int id);

    CommonResponse selectAllInformation(int topicId, Integer userId, int pageIndex, int pageCount);

    CommonResponse thumbsUp(InformationThumbsUpModel informationThumbsUpModel) throws CustomizeException;

    CommonResponse cancelThumbsUp(InformationThumbsUpModel informationThumbsUpModel) throws CustomizeException;
}
