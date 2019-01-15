package com.utoblock.sdk.response;

import com.alibaba.fastjson.JSONObject;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.response.base.SuperBlockResponse;

/**
 * 描述：免密消费结果
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class ConsumeNoPassResponse extends SuperBlockResponse {

    private String txId;

    public static ConsumeNoPassResponse response(int code, String message){
        ConsumeNoPassResponse response = new ConsumeNoPassResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static ConsumeNoPassResponse response(String responseStr) {
        JSONObject responseObject = JSONObject.parseObject(responseStr);
        if(responseObject.getIntValue("code") == ResponseCode.CODE_200.code){
            ConsumeNoPassResponse response = response(responseObject.getInteger("code"), responseObject.getString("message"));
            response.setTxId(responseObject.getString("data"));
            return response;
        }else{
            return response(responseObject.getInteger("code"), responseObject.getString("message"));
        }
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
