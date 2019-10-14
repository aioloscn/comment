package com.aiolos.comment.common;

import java.io.Serializable;

/**
 * @author Aiolos
 * @date 2019-10-12 21:48
 */
public class CommonResponse<T> implements Serializable {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    private CommonResponse() {
        this.code = 200;
        this.msg = "OK";
    }

    private CommonResponse(T data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static CommonResponse ok(Object data) {
        return new CommonResponse(data);
    }

    public static CommonResponse ok(String msg, Object data) {
        CommonResponse res = new CommonResponse();
        res.msg = msg;
        res.data = data;
        return res;
    }

    public static CommonResponse error(Integer errCode, String errMsg) {
        CommonResponse res = new CommonResponse();
        res.code = errCode;
        res.msg = errMsg;
        return res;
    }

    public static CommonResponse error(Integer errCode, String errMsg, Object data) {
        CommonResponse res = new CommonResponse(data);
        res.code = errCode;
        res.msg = errMsg;
        res.data = data;
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
