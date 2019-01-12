package com.superblock.sdk.response;

import com.alibaba.fastjson.JSONObject;
import com.superblock.sdk.response.base.SuperBlockResponse;
import com.superblock.sdk.enums.ResponseCode;

import java.math.BigDecimal;

/**
 * 描述：查询余额响应结果
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class BalanceResponse extends SuperBlockResponse {

    private String address;
    private BigDecimal balance;

    public static BalanceResponse response(int code, String message) {
        BalanceResponse response = new BalanceResponse();
        response.code = code;
        response.message = message;
        return response;
    }

    public static BalanceResponse response(String responseStr) {
        JSONObject responseObject = JSONObject.parseObject(responseStr);
        if(responseObject.getIntValue("code") == ResponseCode.CODE_200.code){
            JSONObject dataObject = responseObject.getJSONObject("data");
            BalanceResponse response = response(responseObject.getInteger("code"), responseObject.getString("message"));
            response.setAddress(dataObject.getString("address"));
            response.setBalance(dataObject.getBigDecimal("balance"));
            return response;
        }else{
            return response(responseObject.getInteger("code"), responseObject.getString("message"));
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
