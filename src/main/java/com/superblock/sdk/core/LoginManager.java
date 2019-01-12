package com.superblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.superblock.sdk.config.SuperBlockConfig;
import com.superblock.sdk.core.base.BaseManager;
import com.superblock.sdk.enums.ResponseCode;
import com.superblock.sdk.response.LoginResponse;
import com.superblock.sdk.utils.StringUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：登录处理类
 * 创建人：yoan
 * 创建日期：2018/12/20
 */
public class LoginManager extends BaseManager {

    private String account;

    public LoginManager account(String account) {
        this.account = account;
        putSign("account", account);
        return this;
    }

    public LoginManager timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public LoginResponse start() {
        try {
            // 1.校验参数
            checkParam();
            // 2.使用pub_key加密参数
            String signStr = generateSign();
            // 3.构建请求参数
            Map<String, Object> paramMap = new HashMap<String, Object>(signParamMap);
            paramMap.put("sign", signStr);
            // 4.构造请求url
            return LoginResponse.response(ResponseCode.CODE_200.code, ResponseCode.CODE_200.message, URLEncoder.encode(Base64.encode(JSON.toJSONString(paramMap).getBytes(SuperBlockConfig.getCharset())), SuperBlockConfig.getCharset()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LoginResponse.response(ResponseCode.CODE_500.code, ResponseCode.CODE_500.message);
    }

    @Override
    protected String assetParam() {
        if (StringUtils.isNull(account)) {
            return "account";
        }
        return null;
    }
}
