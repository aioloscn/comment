package com.aiolos.comment.common;

/**
 * @author Aiolos
 * @date 2019-10-12 21:07
 */
public enum EnumError implements CommonError {

    UNKNOWN_ERROR(500, "未知错误"),
    USER_NOT_LOGGED_IN(10001, "用户尚未登录"),
    NO_HANDLER_FOUND(10002, "找不到执行的路径"),
    BIND_EXCEPTION_ERROR(10003, "请求参数错误"),
    ;

    EnumError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private Integer errCode;
    private String errMsg;

    @Override
    public Integer getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
