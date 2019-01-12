package com.utoblock.sdk.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utoblock.sdk.response.base.SuperBlockResponse;
import com.utoblock.sdk.enums.ResponseCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：查询流水响应结果
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class TransactionResponse extends SuperBlockResponse {

    private List<TransactionItem> list;
    private long totalPage;
    private long totalSize;
    private int page;
    private int size;

    public static TransactionResponse response(int code, String message) {
        TransactionResponse response = new TransactionResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static TransactionResponse response(String responseStr) {
        JSONObject responseObject = JSON.parseObject(responseStr);
        if (ResponseCode.CODE_200.code == responseObject.getIntValue("code")) {
            TransactionResponse response = response(responseObject.getIntValue("code"), responseObject.getString("message"));
            response.totalPage = responseObject.getLongValue("totalPage");
            response.totalSize = responseObject.getLongValue("totalSize");
            response.page = responseObject.getIntValue("page");
            response.size = responseObject.getIntValue("size");

            // 构建列表
            response.list = responseObject.getJSONArray("data").toJavaList(TransactionItem.class);

            return response;
        } else {
            return response(responseObject.getIntValue("code"), responseObject.getString("message"));
        }
    }

    public List<TransactionItem> getList() {
        return list;
    }

    public void setList(List<TransactionItem> list) {
        this.list = list;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static class TransactionItem {

        private String txId;
        private BigDecimal value;
        private String type;
        private String name;
        private long time;

        public String getTxId() {
            return txId;
        }

        public void setTxId(String txId) {
            this.txId = txId;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
