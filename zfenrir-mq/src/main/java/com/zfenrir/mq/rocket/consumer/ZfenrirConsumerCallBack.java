package com.zfenrir.mq.rocket.consumer;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * mq消费回调类
 * @author zhuliang
 * @Date  2021-11-29
 *
 */
public interface ZfenrirConsumerCallBack {

    /**
     * 真实的消息消费方法
     * @param messageExt
     */
   void consumeMessage(MessageExt messageExt);
}
