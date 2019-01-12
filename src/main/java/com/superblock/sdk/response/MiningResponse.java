package com.superblock.sdk.response;

import com.superblock.sdk.response.base.SuperBlockResponse;

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

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
