package com.aiolos.comment.advice;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
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
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerCustomizeException(HttpServletRequest req, HttpServletResponse res, Exception e) {

        if (e instanceof CustomizeException) {
            return CommonResponse.error(((CustomizeException) e).getErrCode(), ((CustomizeException) e).getErrMsg());
        } else if (e instanceof NoHandlerFoundException) {
            return CommonResponse.error(EnumError.NO_HANDLER_FOUND.getErrCode(), EnumError.NO_HANDLER_FOUND.getErrMsg());
        } else if (e instanceof ServletRequestBindingException) {
            return CommonResponse.error(EnumError.BIND_EXCEPTION_ERROR.getErrCode(), EnumError.BIND_EXCEPTION_ERROR.getErrMsg());
        } else {
            return CommonResponse.error(EnumError.UNKNOWN_ERROR.getErrCode(), e.getMessage());
        }
    }
}
