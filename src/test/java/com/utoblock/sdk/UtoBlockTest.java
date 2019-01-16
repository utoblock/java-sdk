package com.utoblock.sdk;

import com.alibaba.fastjson.JSON;
import com.utoblock.sdk.response.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 描述：
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class UtoBlockTest {

    public void mining() {
        System.out.println("开始测试挖矿");
        MiningResponse response = UtoBlock.mining()
                .account("13480233393")
                .key("1")
                .extras("你好")
                .description("asdfzxcv哈哦啊$#@")
                .start();
        assertEquals(200, response.getCode());
        System.out.println("挖矿完成，结果：" + JSON.toJSONString(response));
    }

    public void consume() {
        System.out.println("开始测试消费");
        ConsumeResponse response = UtoBlock.consume()
                .name("一台手机")
                .account("13710966390")
                .value(BigDecimal.valueOf(0.01))
                .tradeNo("3")
                .extras("你好啊")
                .description("哈哈啊")
                .start();
        assertEquals(200, response.getCode());
        System.out.println("消费完成，结果：" + JSON.toJSONString(response));
    }

    public void consumeWithoutPass() {
        System.out.println("开始测试消费");
        ConsumeNoPassResponse response = UtoBlock.consumeWithoutPass()
                .name("一台手机")
                .account("13710966390")
                .value(BigDecimal.valueOf(0.01))
                .tradeNo("3")
                .extras("你好啊")
                .description("哈哈啊")
                .start();
        assertEquals(200, response.getCode());
        System.out.println("消费完成，结果：" + JSON.toJSONString(response));
    }

    public void balance() {
        System.out.println("开始测试查询余额");
        BalanceResponse response = UtoBlock.balance()
                .account("13710966390")
                .start();
        assertEquals(200, response.getCode());
        System.out.println("查询余额完成,结果：" + JSON.toJSONString(response));
    }

    public void transaction() {
        System.out.println("开始测试查询流水");
        TransactionResponse response = UtoBlock.transaction()
                .account("123")
                .page(1)
                .size(10)
                .start();
        assertEquals(200, response.getCode());
        System.out.println("查询流水完成，结果：" + JSON.toJSONString(response));
    }

    public void login(){
        System.out.println("开始测试登录");
        LoginResponse response = UtoBlock.login()
                .account("13710966390")
                .start();
        assertEquals(200, response.getCode());
        System.out.println("登录结果,结果：" + JSON.toJSONString(response));
    }
}