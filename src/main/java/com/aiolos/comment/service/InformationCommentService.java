package com.aiolos.comment.service;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.model.InformationCommentModel;

/**
 * @author Aiolos
 * @date 2019-12-02 21:38
 */
public interface InformationCommentService {

    CommonResponse release(InformationCommentModel informationCommentModel);

    CommonResponse getComments(Integer id, Integer userId, int pageIndex, int pageCount);
}
