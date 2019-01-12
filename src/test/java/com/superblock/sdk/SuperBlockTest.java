package com.superblock.sdk;

import com.alibaba.fastjson.JSON;
import com.superblock.sdk.response.*;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 描述：
 * 创建人：yoan
 * 创建日期：2018/11/15
 */
public class SuperBlockTest {

    public void mining() {
        System.out.println("开始测试挖矿");
        MiningResponse response = SuperBlock.mining()
                .account("13480233393")
                .key("1")
                .extras("你好")
                .description("asdfzxcv哈哦啊$#@")
                .timestamp(System.currentTimeMillis())
                .start();
        assertEquals(200, response.getCode());
        System.out.println("挖矿完成，结果：" + JSON.toJSONString(response));
    }

    public void consume() {
        System.out.println("开始测试消费");
        ConsumeResponse response = SuperBlock.consume()
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
        BalanceResponse response = SuperBlock.balance()
                .account("13710966390")
                .timestamp(System.currentTimeMillis())
                .start();
        assertEquals(200, response.getCode());
        System.out.println("查询余额完成,结果：" + JSON.toJSONString(response));
    }

    @Test
    public void transaction() {
        System.out.println("开始测试查询流水");
        TransactionResponse response = SuperBlock.transaction()
                .account("123")
                .page(1)
                .size(10)
                .start();
        assertEquals(200, response.getCode());
        System.out.println("查询流水完成，结果：" + JSON.toJSONString(response));
    }

    @Test
    public void login(){
        System.out.println("开始测试登录");
        LoginResponse response = SuperBlock.login()
                .account("13710966390")
                .timestamp(System.currentTimeMillis())
                .start();
        assertEquals(200, response.getCode());
        System.out.println("登录结果,结果：" + JSON.toJSONString(response));
    }
}