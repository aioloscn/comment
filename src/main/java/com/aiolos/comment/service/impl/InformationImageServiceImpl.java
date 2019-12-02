package com.aiolos.comment.service.impl;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dal.InformationImagesModelMapper;
import com.aiolos.comment.model.InformationImagesModel;
import com.aiolos.comment.service.InformationImageService;
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
public class InformationImageServiceImpl implements InformationImageService {

    @Autowired
    private InformationImagesModelMapper informationImagesModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResponse insertImage(InformationImagesModel informationImagesModel) {

        log.info("implement function insertImage");
        int affected = informationImagesModelMapper.insertSelective(informationImagesModel);
        log.info("affected: {}", affected);
        if (affected > 0) {
            return CommonResponse.ok(null);
        }
        return CommonResponse.error(EnumError.INFORMATION_IMAGE_RELEASE_FAIL.getErrCode(), EnumError.INFORMATION_IMAGE_RELEASE_FAIL.getErrMsg());
    }
}
