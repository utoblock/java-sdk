package com.superblock.sdk.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：json拓展工具类
 * 创建人：yoan
 * 创建日期：2018/12/5
 */
public class JsonUtils {

    /**
     * 将jsonobject解析成map
     * @param jsonObject
     * @return
     */
    public static Map<String, String> toMap(JSONObject jsonObject){
        Map<String, String> resultMap = new HashMap<>(jsonObject.size());
        for(Map.Entry<String, Object> entry : jsonObject.entrySet()){
            resultMap.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return resultMap;
    }
}
