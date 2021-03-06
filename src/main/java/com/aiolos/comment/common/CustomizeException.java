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

    // 因为ErrorEnum实现了CommonError，所以在抛出异常的时候可以传CommonError进来，在ErrorEnum中setErrMsg()
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

    // 在CommonError的被实现类（ErrorEnum）中替换message
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
