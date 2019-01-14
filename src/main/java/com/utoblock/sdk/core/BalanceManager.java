package com.utoblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.utoblock.sdk.config.UtoBlockConfig;
import com.utoblock.sdk.core.base.BaseManager;
import com.utoblock.sdk.response.BalanceResponse;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.utils.HttpTool;
import com.utoblock.sdk.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：查询余额核心类
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class BalanceManager extends BaseManager {

    private String account;

    public BalanceManager() {
        super();
    }

    public BalanceManager account(String account) {
        this.account = account;
        putSign("account", account);
        return this;
    }

    public BalanceResponse start() {
        try {
            // 1.参数检查
            checkParam();
            // 2.构建签名
            String signStr = generateSign();
            // 3.构建请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>(signParamMap);
            paramMap.put("sign", signStr);
            // 4.发送请求
            String response = HttpTool.post(UtoBlockConfig.getGateway() + UtoBlockConfig.getBalancePath(), JSON.toJSONString(paramMap), UtoBlockConfig.getCharset());
            return BalanceResponse.response(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BalanceResponse.response(ResponseCode.CODE_500.code, ResponseCode.CODE_500.message);
    }

    @Override
    protected String assetParam() {
        if (StringUtils.isNull(account)) {
            return "account";
        }
        return null;
    }
}
