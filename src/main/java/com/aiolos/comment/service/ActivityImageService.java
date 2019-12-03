package com.aiolos.comment.service;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.model.ActivityImagesModel;
import com.aiolos.comment.model.InformationImagesModel;

/**
 * @author Aiolos
 * @date 2019-11-28 18:34
 */
public interface ActivityImageService {

    CommonResponse insertImage(ActivityImagesModel activityImagesModel);
}
