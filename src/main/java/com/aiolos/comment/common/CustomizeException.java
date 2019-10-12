package com.aiolos.comment.common;

/**
 * @author Aiolos
 * @date 2019-10-12 21:18
 */
public class CustomizeException extends Exception implements CommonError {

    private CommonError commonError;

    public CustomizeException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public CustomizeException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public Integer getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
