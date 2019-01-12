package com.superblock.sdk;

import com.superblock.sdk.core.*;

/**
 * 描述：sdk入口
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class SuperBlock {

    public static MiningManager mining(){
        return new MiningManager();
    }

    public static ConsumeManager consume(){
        return new ConsumeManager();
    }

    public static BalanceManager balance(){
        return new BalanceManager();
    }

    public static TransactionManager transaction(){
        return new TransactionManager();
    }

    public static ConsumeResultManager consumeResult(){
        return new ConsumeResultManager();
    }

    public static LoginManager login(){
        return new LoginManager();
    }
}
