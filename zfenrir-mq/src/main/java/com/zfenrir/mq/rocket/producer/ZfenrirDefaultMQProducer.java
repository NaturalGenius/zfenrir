package com.zfenrir.mq.rocket.producer;

import java.util.Objects;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;


public class ZfenrirDefaultMQProducer {
    
    /**
     * 生成者组
     */
    private String producerGroup;
    //服务地址
    private String namesrvAddr;
    private DefaultMQProducer defaultMQProducer;
    private TransactionMQProducer transactionMQProducer;
    public ZfenrirDefaultMQProducer(String producerGroup, String namesrvAddr) {
        this.producerGroup = producerGroup;
        this.namesrvAddr = namesrvAddr;
    }
    //初始化生成者
    public void initDefaultMQProducer() throws MQClientException {
        createDefaultMQProducer();
        defaultMQProducer.start();
    }
    
    //初始化生成者
    public void initTransactionMQProducer() throws MQClientException {
       createTransactionMQProducer();
       transactionMQProducer.start();
    }
    
    /**
     * 停止生产者
     */
    public void shutdown() {
        if (Objects.nonNull(defaultMQProducer)) {
            defaultMQProducer.shutdown();
        }
        if (Objects.nonNull(transactionMQProducer)) {
            transactionMQProducer.shutdown();
        }
    }
    
    
    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    public void setDefaultMQProducer(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    public TransactionMQProducer getTransactionMQProducer() {
        return transactionMQProducer;
    }

    public void setTransactionMQProducer(TransactionMQProducer transactionMQProducer) {
        this.transactionMQProducer = transactionMQProducer;
    }

    /**
     * 创建默认的生产者
     */
    private void createDefaultMQProducer() {
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
    }
    
    /**
     * 创建事务生成者
     */
    private void createTransactionMQProducer() {
        transactionMQProducer = new TransactionMQProducer(producerGroup  + "-TRANSACTION");
        transactionMQProducer.setNamesrvAddr(namesrvAddr);
    }
}
