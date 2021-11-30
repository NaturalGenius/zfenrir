package com.zfenrir.mq.rocket.common;

import org.apache.rocketmq.common.message.Message;

public interface ZfenrirTransactionMessageComfirm {

    /**
     * 确认消息
     * @param msg
     * @param arg
     */
    public void comfirmMessage(Message msg, Object arg);
}
