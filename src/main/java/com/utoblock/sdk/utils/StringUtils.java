package com.utoblock.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 描述：字符串工具
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class StringUtils {

    public static boolean notNull(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isNull(String str) {
        return !notNull(str);
    }

    public static String paramString(Map<String, String> signParamMap, String charset) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<String>(signParamMap.keySet());
        Collections.sort(keys);

        StringBuilder prestr = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = signParamMap.get(key);

            if (StringUtils.notNull(value)) {
                value = URLEncoder.encode(value, charset);
                if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                    prestr.append(key + "=" + value);
                } else {
                    prestr.append(key + "=" + value + "&");
                }
            }
        }

        return prestr.toString();
    }
}
