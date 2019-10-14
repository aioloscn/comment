package com.aiolos.comment.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Aiolos
 * @date 2019-10-13 18:01
 */
public class CommonUtil {

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
}
