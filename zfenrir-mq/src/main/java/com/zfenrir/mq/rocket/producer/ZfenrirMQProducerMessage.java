package com.zfenrir.mq.rocket.producer;

import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import com.zfenrir.mq.rocket.common.ZfenrirMessageDelayLevel;
import com.zfenrir.mq.rocket.common.ZfenrirMqCharset;

/**
 * 封装消息发送
 * 
 * @author zhuliang
 * @Date 2021-11-30
 *
 */
public class ZfenrirMQProducerMessage {
    private DefaultMQProducer defaultMQProducer;
    private TransactionMQProducer transactionMQProducer;

    public ZfenrirMQProducerMessage(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    /**
     * 发送同步消息
     * 
     * @param topic
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendMessage(String topic, String message) throws Exception {
        return sendMessage(topic, null, message);
    }

    /**
     * 发送同步消息
     * 
     * @param topic
     * @param tags
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendMessage(String topic, String tags, String message) throws Exception {
        return sendMessage(topic, tags, null, message);
    }

    /**
     * 发送同步消息
     * 
     * @param topic
     * @param tags
     * @param keys
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendMessage(String topic, String tags, String keys, String message) throws Exception {
        return defaultMQProducer.send(createMessage(topic, tags, keys, message));
    }

    /**
     * 发送异步消息
     * 
     * @param topic
     * @param message
     * @param sendCallback
     * @throws Exception
     */
    public void sendAsyncMessage(String topic, String message, SendCallback sendCallback) throws Exception {
        sendAsyncMessage(topic, null, message, sendCallback);
    }

    /**
     * 发送异步消息
     * 
     * @param topic
     * @param tags
     * @param message
     * @param sendCallback
     * @throws Exception
     */
    public void sendAsyncMessage(String topic, String tags, String message, SendCallback sendCallback)
        throws Exception {
        sendAsyncMessage(topic, tags, null, message, sendCallback);
    }

    /**
     * 发送异步消息
     * 
     * @param topic
     * @param tags
     * @param keys
     * @param message
     * @param sendCallback
     * @throws Exception
     */
    public void sendAsyncMessage(String topic, String tags, String keys, String message, SendCallback sendCallback)
        throws Exception {
        defaultMQProducer.send(createMessage(topic, tags, keys, message), sendCallback);
    }

    /**
     * 发送顺序消息
     * 
     * @param topic
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendOrderMessage(String topic, String message, String orderKey) throws Exception {
        return sendOrderMessage(topic, null, message);
    }

    /**
     * 发送顺序消息
     * 
     * @param topic
     * @param tags
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendOrderMessage(String topic, String tags, String message, String orderKey) throws Exception {
        return sendOrderMessage(topic, tags, null, message);
    }

    /**
     * 发送顺序消息
     * 
     * @param topic
     * @param tags
     * @param keys
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendOrderMessage(String topic, String tags, String keys, String message, String orderKey)
        throws Exception {
        return defaultMQProducer.send(createMessage(topic, tags, keys, message), new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                int hashCode = arg.hashCode();
                int index = hashCode % mqs.size();
                return mqs.get(index);
            }
        }, orderKey);
    }

    /**
     * 发送延迟消息
     * 
     * @param topic
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendDelayMessage(String topic, String message, ZfenrirMessageDelayLevel delayLevel)
        throws Exception {
        return sendDelayMessage(topic, null, message, delayLevel);
    }

    /**
     * 发送延迟消息
     * 
     * @param topic
     * @param tags
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendDelayMessage(String topic, String tags, String message, ZfenrirMessageDelayLevel delayLevel)
        throws Exception {
        return sendDelayMessage(topic, tags, null, message, delayLevel);
    }

    /**
     * 发送延迟消息
     * 
     * @param topic
     * @param tags
     * @param keys
     * @param message
     * @return
     * @throws Exception
     */
    public SendResult sendDelayMessage(String topic, String tags, String keys, String message,
        ZfenrirMessageDelayLevel delayLevel) throws Exception {
        Message msg = createMessage(topic, tags, keys, message);
        msg.setDelayTimeLevel(delayLevel.getDelayLevel());
        return defaultMQProducer.send(msg);
    }

    /**
     * 发送事务消息
     * 
     * @param topic
     * @param message
     * @return
     * @throws Exception
     */
    public TransactionSendResult sendTransactionMessage(String topic, String message) throws Exception {
        return sendTransactionMessage(topic, null, message, null);
    }

    /**
     * 发送事务消息
     * 
     * @param topic
     * @param message
     * @return
     * @throws Exception
     */
    public TransactionSendResult sendTransactionMessage(String topic, String message, Object arg) throws Exception {
        return sendTransactionMessage(topic, null, message, arg);
    }

    /**
     * 发送事务消息
     * 
     * @param topic
     * @param tags
     * @param message
     * @return
     * @throws Exception
     */
    public TransactionSendResult sendTransactionMessage(String topic, String tags, String message, Object arg)
        throws Exception {
        return sendTransactionMessage(topic, tags, null, message, arg);
    }

    /**
     * 发送事务消息
     * 
     * @param topic
     * @param tags
     * @param keys
     * @param message
     * @return
     * @throws Exception
     */
    public TransactionSendResult sendTransactionMessage(String topic, String tags, String keys, String message,
        Object arg) throws Exception {
        return transactionMQProducer.sendMessageInTransaction(createMessage(topic, tags, keys, message), arg);
    }

    private Message createMessage(String topic, String tags, String keys, String message) {
        return new Message(topic, tags, keys, message.getBytes(ZfenrirMqCharset.DEFAULT_CHARSET));
    }
}
