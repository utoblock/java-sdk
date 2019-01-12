package com.utoblock.sdk.response.base;

/**
 * 描述：接口响应对象
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public abstract class SuperBlockResponse {

    protected int code;
    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
