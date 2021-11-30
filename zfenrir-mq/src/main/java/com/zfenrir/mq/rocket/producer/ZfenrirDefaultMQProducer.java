package com.zfenrir.mq.rocket.producer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;

import com.zfenrir.mq.rocket.common.ZfenrirTransactionMessageComfirm;


public class ZfenrirDefaultMQProducer {
    
    /**
     * 生成者组
     */
    private String producerGroup;
    //服务地址
    private String namesrvAddr;
    private DefaultMQProducer defaultMQProducer;
    private TransactionMQProducer transactionMQProducer;
    private Map<String, ZfenrirTransactionMessageComfirm> messageComfirmMap;
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

    public void setMessageComfirmMap(Map<String, ZfenrirTransactionMessageComfirm> messageComfirmMap) {
        this.messageComfirmMap = messageComfirmMap;
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
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-ZfenrirDefaultMQProducer-thread");
                return thread;
            }
        });
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(new ZfenrirTransactionListenerImpl(messageComfirmMap));
    }
}
