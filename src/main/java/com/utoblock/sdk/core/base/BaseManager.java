package com.utoblock.sdk.core.base;

import com.utoblock.sdk.config.UtoBlockConfig;
import com.utoblock.sdk.utils.ECC;
import com.utoblock.sdk.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：基础类
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public abstract class BaseManager {

    protected Map<String, String> signParamMap;

    protected long timestamp;

    protected BaseManager() {
        signParamMap = new HashMap<String, String>();
        timestamp = System.currentTimeMillis();
    }

    /**
     * 构建签名
     *
     * @return
     * @throws Exception
     */
    protected String generateSign() throws Exception {
        // 添加通用参数参数
        this.signParamMap.put("appKey", UtoBlockConfig.getAppKey());
        this.signParamMap.put("charset", UtoBlockConfig.getCharset());
        this.signParamMap.put("timestamp", String.valueOf(timestamp));
        // 按顺序组装参数
        String paramStr = StringUtils.paramString(this.signParamMap, UtoBlockConfig.getCharset());
        // 使用pub_key加密参数
        return ECC.sign(paramStr, UtoBlockConfig.getKey(), UtoBlockConfig.getCharset());
    }

    protected void putSign(String key, String value) {
        signParamMap.put(key, value);
    }

    /**
     * 校验参数
     *
     * @return
     */
    protected abstract String assetParam();

    protected void checkParam() {
        String errorParam = assetParam();
        if (StringUtils.notNull(errorParam)) {
            throw new IllegalArgumentException(errorParam + "参数错误");
        }
    }
}
