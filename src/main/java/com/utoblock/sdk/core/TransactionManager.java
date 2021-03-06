package com.utoblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.utoblock.sdk.config.UtoBlockConfig;
import com.utoblock.sdk.core.base.BaseManager;
import com.utoblock.sdk.response.TransactionResponse;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.utils.HttpTool;
import com.utoblock.sdk.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：查询流水核心类
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class TransactionManager extends BaseManager {

    private String account;
    private int page;
    private int size;

    public TransactionManager() {
        super();
    }

    public TransactionManager account(String account) {
        this.account = account;
        putSign("account", account);
        return this;
    }

    public TransactionManager page(int page) {
        this.page = page;
        putSign("page", String.valueOf(page));
        return this;
    }

    public TransactionManager size(int size) {
        this.size = size;
        putSign("size", String.valueOf(size));
        return this;
    }

    public TransactionResponse start() {
        try {
            // 1.参数检查
            checkParam();
            // 2.生成签名
            String signStr = generateSign();
            // 3.构建请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>(signParamMap);
            paramMap.put("sign", signStr);
            // 4.发送请求
            String response = HttpTool.post(UtoBlockConfig.getGateway() + UtoBlockConfig.getTransactionPath(), JSON.toJSONString(paramMap), UtoBlockConfig.getCharset());
            return TransactionResponse.response(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TransactionResponse.response(ResponseCode.CODE_500.code, ResponseCode.CODE_500.message);
    }

    @Override
    protected String assetParam() {
        if (StringUtils.isNull(account)) {
            return "account";
        }
        if (page < 1) {
            return "page";
        }
        if (size < 1 || size > 100) {
            return "size";
        }
        return null;
    }
}
