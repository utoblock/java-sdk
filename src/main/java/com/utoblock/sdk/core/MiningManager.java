package com.utoblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.utoblock.sdk.config.UtoBlockConfig;
import com.utoblock.sdk.core.base.BaseManager;
import com.utoblock.sdk.response.MiningResponse;
import com.utoblock.sdk.enums.ResponseCode;
import com.utoblock.sdk.utils.HttpTool;
import com.utoblock.sdk.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：挖矿核心类
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class MiningManager extends BaseManager {

    private String extras;
    private String account;
    private String key;
    private String description;

    public MiningManager() {
        super();
    }

    public MiningManager key(String key) {
        this.key = key;
        putSign("key", key);
        return this;
    }

    public MiningManager account(String account) {
        this.account = account;
        putSign("account", account);
        return this;
    }

    public MiningManager description(String description) {
        if (StringUtils.notNull(description)) {
            this.description = description;
            putSign("description", description);
        }
        return this;
    }

    public MiningManager extras(String extras) {
        if (StringUtils.notNull(extras)) {
            this.extras = extras;
            putSign("extras", extras);
        }
        return this;
    }

    public MiningManager extras(Map<String, Object> extras) {
        return extras(JSON.toJSONString(extras));
    }

    public MiningResponse start() {
        try {
            // 1.校验参数
            checkParam();
            // 2.参数签名
            String signStr = generateSign();
            // 3.构建请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>(signParamMap);
            paramMap.put("sign", signStr);
            // 4.使用httpClient发送请求到超级部落后台服务器
            String response = HttpTool.post(UtoBlockConfig.getGateway() + UtoBlockConfig.getMiningPath(), JSON.toJSONString(paramMap), UtoBlockConfig.getCharset());
            // 5.格式化服务器响应
            return JSON.parseObject(response, MiningResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MiningResponse.response(ResponseCode.CODE_500.code, ResponseCode.CODE_500.message);
    }

    @Override
    protected String assetParam() {
        if (StringUtils.isNull(key)) {
            return "key";
        }
        if (StringUtils.isNull(account)) {
            return "account";
        }

        return null;
    }
}
