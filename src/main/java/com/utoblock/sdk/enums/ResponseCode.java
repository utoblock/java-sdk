package com.utoblock.sdk.enums;

/**
 * 描述：
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public enum ResponseCode {

    CODE_200(200, "成功"),
    CODE_406(406, "签名校验失败"),
    CODE_500(500, "失败");

    public int code;
    public String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
