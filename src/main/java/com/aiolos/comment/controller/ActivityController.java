package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.model.ActivityImagesModel;
import com.aiolos.comment.model.ActivityModel;
import com.aiolos.comment.model.ActivityThumbsUpModel;
import com.aiolos.comment.model.ActivityTopicModel;
import com.aiolos.comment.request.ActivityImageReq;
import com.aiolos.comment.request.ActivityReq;
import com.aiolos.comment.request.ActivityThumbsUpReq;
import com.aiolos.comment.service.ActivityImageService;
import com.aiolos.comment.service.ActivityService;
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
import java.util.List;

/**
 * @author Aiolos
 * @date 2019-12-03 04:52
 */
@Slf4j
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private ActivityImageService activityImageService;

    @PostMapping("/release/content")
    @ResponseBody
    public CommonResponse releaseContent(@Valid @RequestBody ActivityReq activityReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function releaseContent, activityReq: {}", activityReq.toString());
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        // 将包含emoji的消息转码
        String title = StringUtils.EMPTY;
        String content = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(activityReq.getContent())) {
            title = EmojiParser.parseToAliases(activityReq.getTitle(), EmojiParser.FitzpatrickAction.PARSE);
            content = EmojiParser.parseToAliases(activityReq.getContent(), EmojiParser.FitzpatrickAction.PARSE);
        }

        ActivityModel activityModel = new ActivityModel();
        activityModel.setFromUid(activityReq.getFromUid());
        activityModel.setTitle(title);
        activityModel.setContent(content);
        activityModel.setTopicId(activityReq.getTopicId());
        activityModel.setProvince(activityReq.getProvince());
        activityModel.setCity(activityReq.getCity());
        activityModel.setCounty(activityReq.getCounty());
        return activityService.releaseContent(activityModel);
    }

    @PostMapping("/release/image")
    @ResponseBody
    public CommonResponse releaseImage(ActivityImageReq activityImageReq) throws Exception {

        String base64Data = activityImageReq.getImage();
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
        String imgPath = imgFile + File.separator + ((int) (Math.random() * 100000)) + "activity#" + activityImageReq.getActivityId() + suffix;
        log.info("imageUrl: {}", imgPath);
        FileUtils.base64ToFile(imgPath, base64Data);
        log.info("Fileutils.base64ToFile()");
        MultipartFile multipartFile = FileUtils.fileToMultipart(imgPath, imgPath, contentType);
        log.info("FileUtils.fileToMultipart()");
        String url = fastDFSClient.uploadFile(multipartFile);

        log.info("url: {}", url);
        ActivityImagesModel activityImagesModel = new ActivityImagesModel();
        activityImagesModel.setActivityId(activityImagesModel.getActivityId());
        activityImagesModel.setImage(url);
        return activityImageService.insertImage(activityImagesModel);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse deleteActivity(@PathVariable Integer id) {

        log.info("implement function delete, activity id: {}", id);
        if (id == null)
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), EnumError.BIND_EXCEPTION_ERROR.getErrMsg());
        return activityService.deleteActivity(id);
    }

    @GetMapping("/get")
    @ResponseBody
    public CommonResponse getActivity(@RequestParam(value = "topicId", required = false, defaultValue = "0") int topicId,
                                         @RequestParam(value = "userId", required = false) Integer userId,
                                         @RequestParam(value = "pageIndex") int pageIndex) {

        log.info("implement function get, topicId: {}, userId: {}, pageIndex: {}", topicId, userId, pageIndex);
        return activityService.getActivity(topicId, userId, pageIndex, Constant.PAGECOUNT);
    }

    @PostMapping("/thumbsUp")
    @ResponseBody
    public CommonResponse thumbsUp(@Valid @RequestBody ActivityThumbsUpReq activityThumbsUpReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function thumbsUp, activityThumbsUpReq body: {}", JSONObject.valueToString(activityThumbsUpReq));
        ActivityThumbsUpModel activityThumbsUpModel = transformationActivityThumbsUpModel(activityThumbsUpReq, bindingResult);
        return activityService.thumbsUp(activityThumbsUpModel);
    }

    @DeleteMapping("/thumbsUp/cancel")
    @ResponseBody
    public CommonResponse cancelThumbsUp(@Valid @RequestBody ActivityThumbsUpReq activityThumbsUpReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function cancel cancelThumbsUp, activityThumbsUp body: {}", JSONObject.valueToString(activityThumbsUpReq));
        ActivityThumbsUpModel activityThumbsUpModel = transformationActivityThumbsUpModel(activityThumbsUpReq, bindingResult);
        return activityService.cancelThumbsUp(activityThumbsUpModel);
    }

    private ActivityThumbsUpModel transformationActivityThumbsUpModel(ActivityThumbsUpReq activityThumbsUpReq, BindingResult bindingResult) throws CustomizeException {
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        ActivityThumbsUpModel activityThumbsUpModel = new ActivityThumbsUpModel();
        activityThumbsUpModel.setActivityId(activityThumbsUpReq.getActivityId());
        activityThumbsUpModel.setFromUid(activityThumbsUpReq.getFromUid());
        return activityThumbsUpModel;
    }

    @GetMapping("/topic/get")
    @ResponseBody
    public CommonResponse<List<ActivityTopicModel>> getTopics() {
        List<ActivityTopicModel> res = activityService.getTopics();
        return CommonResponse.ok(res);
    }
}
