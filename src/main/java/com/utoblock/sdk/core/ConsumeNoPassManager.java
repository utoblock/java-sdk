package com.utoblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.utoblock.sdk.config.UtoBlockConfig;
import com.utoblock.sdk.core.base.BaseManager;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.response.ConsumeResponse;
import com.utoblock.sdk.utils.HttpTool;
import com.utoblock.sdk.utils.StringUtils;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：消费核心类
 * 创建人：yoan
 * 创建日期：2018/10/9
 */
public class ConsumeNoPassManager extends BaseManager {

    private String extras;
    private Map<String, Object> extrasMap;
    private String account;
    private BigDecimal value;
    private String tradeNo;
    private String description;//no
    private String name;

    public ConsumeNoPassManager() {
        super();
    }

    public ConsumeNoPassManager name(String name){
        this.name = name;
        signParamMap.put("name", name);
        return this;
    }

    public ConsumeNoPassManager value(BigDecimal value) {
        this.value = value;
        signParamMap.put("value", value.toPlainString());
        return this;
    }

    public ConsumeNoPassManager account(String account) {
        this.account = account;
        signParamMap.put("account", account);
        return this;
    }

    public ConsumeNoPassManager tradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        signParamMap.put("tradeNo", tradeNo);
        return this;
    }

    public ConsumeNoPassManager description(String description) {
        if (StringUtils.notNull(this.description)) {
            this.description = description;
            signParamMap.put("description", description);
        }
        return this;
    }

    public ConsumeNoPassManager extras(String extras) {
        if (StringUtils.notNull(extras)) {
            this.extras = extras;
            signParamMap.put("extras", extras);
        }
        return this;
    }

    public ConsumeNoPassManager extras(Map<String, Object> extras) {
        if (extras != null && !extras.isEmpty()) {
            return extras(JSON.toJSONString(extras));
        }
        return extras("");
    }

    public ConsumeResponse start() {
        try {
            // 1.校验参数
            checkParam();
            // 2.使用pub_key加密参数
            String signStr = generateSign();
            // 3.构建请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>(signParamMap);
            paramMap.put("sign", signStr);
            // 4.发起请求
            String response = HttpTool.post(UtoBlockConfig.getGateway() + UtoBlockConfig.getConsumeNoPassPath(), JSON.toJSONString(paramMap), UtoBlockConfig.getCharset());
            return ConsumeResponse.response(ResponseCode.CODE_200.code, ResponseCode.CODE_200.message, URLEncoder.encode(Base64.encode(JSON.toJSONString(paramMap).getBytes(UtoBlockConfig.getCharset())), UtoBlockConfig.getCharset()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConsumeResponse.response(ResponseCode.CODE_500.code, ResponseCode.CODE_500.message);
    }

    @Override
    protected String assetParam() {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            return "value";
        }
        if (StringUtils.isNull(account)) {
            return "account";
        }
        if (StringUtils.isNull(tradeNo) && tradeNo.length() > 100) {
            return "tradeNo";
        }
        if(StringUtils.isNull(name)){
            return "name";
        }

        return null;
    }
}
