package com.superblock.sdk.response;

import com.superblock.sdk.response.base.SuperBlockResponse;

/**
 * 描述：消费响应
 * 创建人：yoan
 * 创建日期：2018/11/12
 */
public class ConsumeResponse extends SuperBlockResponse {

    private String key;

    public static ConsumeResponse response(int code, String message) {
        return response(code, message, null);
    }

    public static ConsumeResponse response(int code, String message, String key) {
        ConsumeResponse response = new ConsumeResponse();
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
