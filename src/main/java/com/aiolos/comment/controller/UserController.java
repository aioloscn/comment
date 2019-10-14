package com.aiolos.comment.controller;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aiolos.comment.model.UserModel;
import com.aiolos.comment.request.LoginReq;
import com.aiolos.comment.request.RegisterReq;
import com.aiolos.comment.service.IUserService;
import com.aiolos.comment.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Aiolos
 * @date 2019-10-11 23:10
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/index")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", "aiolos");
        return modelAndView;
    }

    @GetMapping("/get")
    @ResponseBody
    public CommonResponse<UserModel> getUser(@RequestParam("id") Integer id) throws CustomizeException {

        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
            throw new CustomizeException(EnumError.USER_NOT_LOGGED_IN);
        }
        return CommonResponse.ok(userModel);
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonResponse<UserModel> register(
            @Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws UnsupportedEncodingException,
            CustomizeException, NoSuchAlgorithmException {

        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = new UserModel();
        userModel.setTelphone(registerReq.getTelphone());
        userModel.setPassword(registerReq.getPassword());
        userModel.setNickName(registerReq.getNickName());
        userModel.setGender(registerReq.getGender());

        UserModel resUserModel = userService.register(userModel);
        return CommonResponse.ok(resUserModel);
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResponse<UserModel> login(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult)
            throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (bindingResult.hasErrors()) {
            throw new CustomizeException(EnumError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = userService.login(loginReq.getTelphone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);
        return CommonResponse.ok("登录成功", userModel);
    }

    @PostMapping("/logout")
    @ResponseBody
    public CommonResponse logout() {

        httpServletRequest.getSession().invalidate();
        return CommonResponse.ok(null);
    }

    @GetMapping("/getcurrentuser")
    @ResponseBody
    public CommonResponse<UserModel> getCurrentUser() {

        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonResponse.ok(userModel);
    }
}
