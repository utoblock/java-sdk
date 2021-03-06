package com.utoblock.sdk.response;

import com.alibaba.fastjson.JSONObject;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.response.base.SuperBlockResponse;

/**
 * 描述：挖矿返回结果
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class MiningResponse extends SuperBlockResponse {

    private String txId;

    public static MiningResponse response(int code, String message){
        MiningResponse response = new MiningResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static MiningResponse response(String responseStr) {
        JSONObject responseObject = JSONObject.parseObject(responseStr);
        if(responseObject.getIntValue("code") == ResponseCode.CODE_200.code){
            MiningResponse response = response(responseObject.getInteger("code"), responseObject.getString("message"));
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
