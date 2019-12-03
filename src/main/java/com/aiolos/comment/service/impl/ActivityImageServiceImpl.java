package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.ActivityImagesModelMapper;
import com.aiolos.comment.model.ActivityImagesModel;
import com.aiolos.comment.service.ActivityImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aiolos
 * @date 2019-11-28 18:35
 */
@Slf4j
@Service
public class ActivityImageServiceImpl implements ActivityImageService {

    @Autowired
    private ActivityImagesModelMapper activityImagesModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse insertImage(ActivityImagesModel activityImagesModel) {

        log.info("implement function insertImage");
        int affected = activityImagesModelMapper.insertSelective(activityImagesModel);
        log.info("affected: {}", affected);
        if (affected > 0) {
            return CommonResponse.ok(null);
        }
        return CommonResponse.error(EnumError.ACTIVITY_IMAGE_RELEASE_FAIL.getErrCode(), EnumError.ACTIVITY_IMAGE_RELEASE_FAIL.getErrMsg());
    }
}
