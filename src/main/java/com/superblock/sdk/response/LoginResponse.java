package com.superblock.sdk.response;

import com.superblock.sdk.response.base.SuperBlockResponse;

/**
 * 描述：登录响应
 * 创建人：yoan
 * 创建日期：2018/12/20
 */
public class LoginResponse extends SuperBlockResponse {

    private String key;

    public static LoginResponse response(int code, String message) {
        return response(code, message, null);
    }

    public static LoginResponse response(int code, String message, String key) {
        LoginResponse response = new LoginResponse();
        response.setCode(code);
        response.setMessage(message);
        response.key = key;
        return response;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
