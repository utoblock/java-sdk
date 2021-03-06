package com.utoblock.sdk.config;

import com.utoblock.sdk.UtoBlock;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述：配置类
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class UtoBlockConfig {

    private final static String DEFAULT_CHARSET = "utf-8";

    public final static String CONFIG_APP_KEY = "com.utoblock.app_key";
    public final static String CONFIG_KEY = "com.utoblock.key";
    public final static String CONFIG_GATEWAY = "com.utoblock.gateway";
    public final static String CONFIG_NOTIFY_URL = "com.utoblock.notify_url";
    public final static String CONFIG_PLATFORM_PUB_KEY = "com.utoblock.platform_pub_key";

    private static String appKey;
    private static String key;
    private static String gateway;
    private static String notifyUrl;
    private static String platformPubKey;
    private static String miningPath = "/sdk/mining";
    private static String consumePath = "/sdk/consume";
    private static String balancePath = "/sdk/balance";
    private static String transactionPath = "/sdk/transaction";
    private static String loginPath = "/sdk/wallet/login";
    private static String consumeNoPassPath = "/sdk/consume/nopass";

    static {
        try {
            Properties properties = new Properties();
            InputStream in = UtoBlock.class.getClassLoader().getResourceAsStream("utoblock.properties");
            properties.load(in);
            loadProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties(Properties properties) {
        appKey = properties.getProperty(CONFIG_APP_KEY);
        key = properties.getProperty(CONFIG_KEY);
        gateway = properties.getProperty(CONFIG_GATEWAY);
        notifyUrl = properties.getProperty(CONFIG_NOTIFY_URL);
        platformPubKey = properties.getProperty(CONFIG_PLATFORM_PUB_KEY);
    }

    public static String getAppKey() {
        return appKey;
    }

    public static String getKey() {
        return key;
    }

    public static String getCharset() {
        return DEFAULT_CHARSET;
    }

    public static String getGateway() {
        return gateway;
    }

    public static String getMiningPath() {
        return miningPath;
    }

    public static String getConsumePath() {
        return consumePath;
    }

    public static String getBalancePath() {
        return balancePath;
    }

    public static String getTransactionPath() {
        return transactionPath;
    }

    public static String getNotifyUrl() {
        return notifyUrl;
    }

    public static String getPlatformPubKey() {
        return platformPubKey;
    }

    public static String getLoginPath() {
        return loginPath;
    }

    public static String getConsumeNoPassPath() {
        return consumeNoPassPath;
    }
}
