package com.aiolos.comment.common;

/**
 * @author Aiolos
 * @date 2019-10-12 21:05
 */
public interface CommonError {

    Integer getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
