package com.utoblock.sdk.response;

import com.utoblock.sdk.response.base.SuperBlockResponse;

import java.math.BigDecimal;

/**
 * 描述：消费回调结果对象
 * 创建人：yoan
 * 创建日期：2018/12/5
 */
public class ConsumeResultResponse extends SuperBlockResponse {

    private String tradeNo;
    private String mobile;
    private String description;
    private String extras;
    private String notifyId;
    private BigDecimal value;
    private Long timestamp;

    public ConsumeResultResponse(){

    }

    public ConsumeResultResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
