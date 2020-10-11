package com.aiolos.comment.advice;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aiolos
 * @date 2019-10-12 21:39
 */
@Slf4j
@RestControllerAdvice   // 所有执行的controller都会被这个切面所包含
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)  // 只要对应的controller抛出了任何Exception或者继承自Exception的异常，都会做一个对应的处理
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerCustomizeException(HttpServletRequest req, HttpServletResponse res, Exception e) {

        log.info("全局异常捕获，异常信息：{}", e);
        if (e instanceof CustomizeException) {
            return CommonResponse.error(((CustomizeException) e).getErrCode(), ((CustomizeException) e).getErrMsg());
        } else if (e instanceof NoHandlerFoundException) {
            return CommonResponse.error(EnumError.NO_HANDLER_FOUND.getErrCode(), EnumError.NO_HANDLER_FOUND.getErrMsg());
        } else if (e instanceof ServletRequestBindingException) {
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), EnumError.BIND_EXCEPTION_ERROR.getErrMsg());
        } else if (e instanceof NullPointerException) {
            return CommonResponse.error(EnumError.NULL_POINT_ERROR.getErrCode(), EnumError.NULL_POINT_ERROR.getErrMsg());
        } else {
            return CommonResponse.error(EnumError.UNKNOWN_ERROR.getErrCode(), e.getMessage());
        }
    }
}
