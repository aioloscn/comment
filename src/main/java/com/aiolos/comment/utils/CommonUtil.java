package com.aiolos.comment.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Aiolos
 * @date 2019-10-13 18:01
 */
public class CommonUtil {

    /**
     * 将数组中的异常信息拼成一个字符串
     * @param bindingResult
     * @return
     */
    public static String processErrorString(BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage() + ",");
        }

        return sb.substring(0, sb.length() - 1);
    }

    public static String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
}
