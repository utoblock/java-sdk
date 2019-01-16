package com.utoblock.sdk;

import com.utoblock.sdk.core.*;

/**
 * 描述：sdk入口
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class UtoBlock {

    /**
     * 挖矿
     *
     * @return
     */
    public static MiningManager mining() {
        return new MiningManager();
    }

    /**
     * 消费
     *
     * @return
     */
    public static ConsumeManager consume() {
        return new ConsumeManager();
    }

    /**
     * 免密消费
     *
     * @return
     */
    public static ConsumeNoPassManager consumeWithoutPass() {
        return new ConsumeNoPassManager();
    }

    /**
     * 查询余额
     *
     * @return
     */
    public static BalanceManager balance() {
        return new BalanceManager();
    }

    /**
     * 查询流水
     *
     * @return
     */
    public static TransactionManager transaction() {
        return new TransactionManager();
    }

    /**
     * 消费回调处理
     *
     * @return
     */
    public static ConsumeResultManager consumeResult() {
        return new ConsumeResultManager();
    }

    /**
     * 钱包登录
     *
     * @return
     */
    public static LoginManager login() {
        return new LoginManager();
    }
}
