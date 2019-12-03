package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.dysms.Dysms;
import com.aiolos.comment.model.ConcernRelationModel;
import com.aiolos.comment.model.UserModel;
import com.aiolos.comment.request.ConcernRelationReq;
import com.aiolos.comment.request.LoginReq;
import com.aiolos.comment.request.RegisterReq;
import com.aiolos.comment.service.UserService;
import com.aiolos.comment.utils.CommonUtil;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Aiolos
 * @date 2019-10-11 23:10
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/get")
    @ResponseBody
    public CommonResponse<UserModel> getUser(@RequestParam("id") Integer id) throws CustomizeException {

        log.info("implement function get user, id: {}", id);
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
            throw new CustomizeException(EnumError.USER_NOT_LOGGED_IN);
        }
        return CommonResponse.ok(userModel);
    }

    @PostMapping("/sendsms")
    @ResponseBody
    public CommonResponse sendSms(@RequestParam("telphone") String telphone) throws CustomizeException {

        log.info("implement function send sms, telphone: {}", telphone);
        if (StringUtils.isBlank(telphone) || telphone.length() != 11) {
            return CommonResponse.error(EnumError.PHONE_INCORRECT.getErrCode(), EnumError.PHONE_INCORRECT.getErrMsg());
        }

        return Dysms.sendSms(telphone);
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonResponse<UserModel> register(
            @Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws UnsupportedEncodingException,
            CustomizeException, NoSuchAlgorithmException {

        log.info("implement function register");
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        String nickname = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(registerReq.getNickname())) {
            nickname = EmojiParser.parseToAliases(registerReq.getNickname(), EmojiParser.FitzpatrickAction.PARSE);
        }

        UserModel userModel = new UserModel();
        userModel.setNickname(nickname);
        userModel.setHeadPortrait(registerReq.getHeadPortrait());
        userModel.setTelphone(registerReq.getTelphone());
        userModel.setPassword(registerReq.getPassword());

        return userService.register(userModel);
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResponse<UserModel> login(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult)
            throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException {

        log.info("implement function login, login body: {}", JSONObject.valueToString(loginReq));
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = userService.login(loginReq.getTelphone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);
        return CommonResponse.ok("登录成功", userModel);
    }

    @PostMapping("/loginOrRegister")
    @ResponseBody
    public CommonResponse<UserModel> loginOrRegister(@RequestParam("telphone") String telphone,
                                         @RequestParam("code") String code) throws CustomizeException {

        log.info("implement function login or register, telphone: {}, code: {}", telphone, code);
        if (StringUtils.isBlank(telphone) || telphone.length() != 11) {
            return CommonResponse.error(EnumError.PHONE_INCORRECT.getErrCode(), EnumError.PHONE_INCORRECT.getErrMsg());
        }

        Object cacheCode = redisTemplate.opsForValue().get(telphone);
        if (cacheCode == null) {
            return CommonResponse.error(EnumError.SMS_CODE_EXPIRED.getErrCode(), EnumError.SMS_CODE_EXPIRED.getErrMsg());
        } else if (!StringUtils.equals(cacheCode.toString(), code)) {
            return CommonResponse.error(EnumError.SMS_CODE_INCORRECT.getErrCode(), EnumError.SMS_CODE_INCORRECT.getErrMsg());
        }

        UserModel userModel = userService.loginOrRegister(telphone);
        return CommonResponse.ok(userModel);
    }

    @PostMapping("/logout")
    @ResponseBody
    public CommonResponse logout() {

        log.info("implement function logout");
        httpServletRequest.getSession().invalidate();
        return CommonResponse.ok(null);
    }

    @GetMapping("/getcurrentuser")
    @ResponseBody
    public CommonResponse<UserModel> getCurrentUser() {

        log.info("implement function get current user");
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonResponse.ok(userModel);
    }

    @PostMapping("/follow")
    @ResponseBody
    public CommonResponse follow(@Valid @RequestBody ConcernRelationReq concernRelationReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function follow, concernRelation body: {}", JSONObject.valueToString(concernRelationReq));
        ConcernRelationModel concernRelationModel = transformationConcernRelationModel(concernRelationReq, bindingResult);
        return userService.follow(concernRelationModel);
    }

    @DeleteMapping("/follow/cancel")
    @ResponseBody
    public CommonResponse cancelFollow(@Valid @RequestBody ConcernRelationReq concernRelationReq, BindingResult bindingResult) throws CustomizeException {

        log.info("implement function cancel follow, concernRelation body: {}", JSONObject.valueToString(concernRelationReq));
        ConcernRelationModel concernRelationModel = transformationConcernRelationModel(concernRelationReq, bindingResult);
        return userService.cancelFollow(concernRelationModel);
    }

    private ConcernRelationModel transformationConcernRelationModel(ConcernRelationReq concernRelationReq, BindingResult bindingResult) throws CustomizeException {
        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        ConcernRelationModel concernRelationModel = new ConcernRelationModel();
        concernRelationModel.setFollowUid(concernRelationReq.getFollowUid());
        concernRelationModel.setConcernUid(concernRelationReq.getConcernUid());
        return concernRelationModel;
    }
}
