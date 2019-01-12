package com.utoblock.sdk.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utoblock.sdk.config.SuperBlockConfig;
import com.utoblock.sdk.core.base.BaseManager;
import com.utoblock.sdk.response.ConsumeResultResponse;
import com.utoblock.sdk.utils.ECC;
import com.utoblock.sdk.utils.HttpTool;
import com.utoblock.sdk.utils.JsonUtils;
import com.utoblock.sdk.utils.StringUtils;

import java.io.InputStream;

/**
 * 描述：消费回调结果处理类
 * 创建人：yoan
 * 创建日期：2018/12/5
 */
public class ConsumeResultManager extends BaseManager {

    private InputStream inputStream;

    public ConsumeResultManager inputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public ConsumeResultResponse start() {
        // 1.检查参数
        checkParam();
        try {
            // 2.从inputStream获取数据
            String response = HttpTool.getStringFromInputSteam(this.inputStream);
            if (StringUtils.notNull(response)) {
                JSONObject resultObject = JSON.parseObject(response);
                // 3.获取签名
                String sign = resultObject.getString("sign");
                resultObject.remove("sign");
                // 4.检查签名
                String signParamStr = StringUtils.paramString(JsonUtils.toMap(resultObject), SuperBlockConfig.getCharset());
                boolean verifySignResult = ECC.verifySign(signParamStr, sign, SuperBlockConfig.getPlatformPubKey(), SuperBlockConfig.getCharset());
                if (verifySignResult) {
                    ConsumeResultResponse resultResponse = resultObject.toJavaObject(ConsumeResultResponse.class);
                    resultResponse.setCode(200);
                    resultResponse.setMessage("成功");
                    return resultResponse;
                }
                return new ConsumeResultResponse(406, "签名校验失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ConsumeResultResponse(500, "失败");
    }

    @Override
    protected String assetParam() {
        if (inputStream == null) {
            return "inputStream";
        }
        return null;
    }
}
