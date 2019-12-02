package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.model.InformationImagesModel;
import com.aiolos.comment.model.InformationModel;
import com.aiolos.comment.model.InformationThumbsUpModel;
import com.aiolos.comment.request.InformationImageReq;
import com.aiolos.comment.request.InformationReq;
import com.aiolos.comment.request.InformationThumbsUpReq;
import com.aiolos.comment.service.InformationImageService;
import com.aiolos.comment.service.InformationService;
import com.aiolos.comment.utils.CommonUtil;
import com.aiolos.comment.utils.FastDFSClient;
import com.aiolos.comment.utils.FileUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

/**
 * @author Aiolos
 * @date 2019-11-28 01:59
 */
@Slf4j
@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private InformationService informationService;

    @Autowired
    private InformationImageService informationImageService;

    @PostMapping("/release/content")
    @ResponseBody
    public CommonResponse releaseContent(@Valid @RequestBody InformationReq informationReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function releaseContent, informationReq: {}", informationReq.toString());
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        // 将包含emoji的消息转码
        String content = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(informationReq.getContent())) {
            content = EmojiParser.parseToAliases(informationReq.getContent(), EmojiParser.FitzpatrickAction.PARSE);
        }

        InformationModel informationModel = new InformationModel();
        informationModel.setFromUid(informationReq.getFromUid());
        informationModel.setNickname(informationReq.getNickname());
        informationModel.setHeadPortrait(informationReq.getHeadPortrait());
        informationModel.setContent(content);
        informationModel.setTopicId(informationReq.getTopicId());
        informationModel.setProvince(informationReq.getProvince());
        informationModel.setCity(informationReq.getCity());
        informationModel.setCounty(informationReq.getCounty());
        return informationService.releaseContent(informationModel);
    }

    @PostMapping("/release/image")
    @ResponseBody
    public CommonResponse releaseImage(InformationImageReq informationImageReq) throws Exception {

        String base64Data = informationImageReq.getImage();
        String dataPrix = StringUtils.EMPTY; // base64格式前头
        String data = StringUtils.EMPTY;//实体部分数据
        if(base64Data == null || "".equals(base64Data)) {
            return CommonResponse.error(EnumError.IMAGE_DATA_EMPTY.getErrCode(), EnumError.IMAGE_DATA_EMPTY.getErrMsg());
        } else {
            String [] d = base64Data.split("base64,");//将字符串分成数组
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                return CommonResponse.error(EnumError.IMAGE_DATA_WRONGFUL.getErrCode(), EnumError.IMAGE_DATA_WRONGFUL.getErrMsg());
            }
        }
        // 后缀
        String suffix = StringUtils.EMPTY;  // 图片后缀，用以识别哪种格式数据
        String contentType = StringUtils.EMPTY;
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
            contentType = "image/jpeg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
            contentType = "image/x-icon";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
            contentType = "image/gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
            contentType = "image/png";
        } else {
            return CommonResponse.error(EnumError.IMAGE_DATA_WRONGFUL.getErrCode(), EnumError.IMAGE_DATA_WRONGFUL.getErrMsg());

        }
        String imgFile = "/developer/comment/images";
        File file = new File(imgFile);
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true);
        }
        String imgPath = imgFile + File.separator + ((int) (Math.random() * 100000)) + "information#" + informationImageReq.getInformationId() + suffix;
        log.info("imageUrl: {}", imgPath);
        FileUtils.base64ToFile(imgPath, base64Data);
        log.info("Fileutils.base64ToFile()");
        MultipartFile multipartFile = FileUtils.fileToMultipart(imgPath, imgPath, contentType);
        log.info("FileUtils.fileToMultipart()");
        String url = fastDFSClient.uploadFile(multipartFile);

        log.info("url: {}", url);
        InformationImagesModel informationImagesModel = new InformationImagesModel();
        informationImagesModel.setInformationId(informationImageReq.getInformationId());
        informationImagesModel.setImage(url);
        return informationImageService.insertImage(informationImagesModel);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse deleteInformation(@PathVariable int id) {

        log.info("implement function delete, information id: {}", id);
        return informationService.deleteInformation(id);
    }

    @GetMapping("/get")
    @ResponseBody
    public CommonResponse getInformation(@RequestParam(value = "topicId", required = false, defaultValue = "0") int topicId,
                                         @RequestParam(value = "userId", required = false) Integer userId,
                                         @RequestParam(value = "pageIndex") int pageIndex) {

        log.info("implement function get, topicId: {}, userId: {}, pageIndex: {}", topicId, userId, pageIndex);
        return informationService.selectAllInformation(topicId, userId, pageIndex, Constant.PAGECOUNT);
    }

    @PostMapping("/thumbsUp")
    @ResponseBody
    public CommonResponse thumbsUp(@Valid @RequestBody InformationThumbsUpReq informationThumbsUpReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function thumbsUp, informationThunbsUp body: {}", JSONObject.valueToString(informationThumbsUpReq));
        InformationThumbsUpModel informationThumbsUpModel = transformationInformationThumbsUpModel(informationThumbsUpReq, bindingResult);
        return informationService.thumbsUp(informationThumbsUpModel);
    }

    @DeleteMapping("/thumbsUp/cancel")
    @ResponseBody
    public CommonResponse cancelThumbsUp(@Valid @RequestBody InformationThumbsUpReq informationThumbsUpReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function cancel cancelThumbsUp, informationThunbsUp body: {}", JSONObject.valueToString(informationThumbsUpReq));
        InformationThumbsUpModel informationThumbsUpModel = transformationInformationThumbsUpModel(informationThumbsUpReq, bindingResult);
        return informationService.cancelThumbsUp(informationThumbsUpModel);
    }

    private InformationThumbsUpModel transformationInformationThumbsUpModel(InformationThumbsUpReq informationThumbsUpReq, BindingResult bindingResult) throws CustomizeException {
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        InformationThumbsUpModel informationThumbsUpModel = new InformationThumbsUpModel();
        informationThumbsUpModel.setInformationId(informationThumbsUpReq.getInformationId());
        informationThumbsUpModel.setFromUid(informationThumbsUpReq.getFromUid());
        return informationThumbsUpModel;
    }
}
