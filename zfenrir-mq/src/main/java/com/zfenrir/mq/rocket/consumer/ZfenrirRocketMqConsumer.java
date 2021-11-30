package com.zfenrir.mq.rocket.consumer;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zfenrir.mq.rocket.common.ConsumerModel;
import com.zfenrir.mq.rocket.common.ZfenrirMqClient;
import com.zfenrir.mq.rocket.exception.ZfenrirRocketMqException;

public class ZfenrirRocketMqConsumer {

    /**
     * 消费者组
     */
    private String consumerGroup;
    //服务地址
    private String namesrvAddr;
    /**
     * 回调类
     */
    private Map<String, ZfenrirConsumerCallBack> consumerCallBackMap;
    private Map<String, DefaultLitePullConsumer> pullConsumerMap;
    private Map<String, DefaultMQPushConsumer> pushConsumerMap;
    private Map<DefaultLitePullConsumer, Thread> pullConsumerThreadMap;
    /**
     * 启动线程池
     */
    private ExecutorService executorService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    public ZfenrirRocketMqConsumer(String consumerGroup, String namesrvAddr) {
        this.consumerGroup = consumerGroup;
        this.namesrvAddr = namesrvAddr;
        this.pullConsumerMap = new HashMap<>();
        this.pushConsumerMap = new HashMap<>();
        this.pullConsumerThreadMap = new HashMap<>();
        this.executorService  = new ThreadPoolExecutor(10, 15, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2000), new ThreadFactoryImpl("ZfenrirRocketMqConsume client "));
    }
    
    public void initConsume() {
        if (MapUtils.isEmpty(consumerCallBackMap)) {
            throw new ZfenrirRocketMqException("没有消费回调类");
        }
        consumerCallBackMap.forEach((name, consumerCallBack) -> {
            try {
                Method method = consumerCallBack.getClass().getMethod("consumeMessage", MessageExt.class);
                ZfenrirMqClient mqClient = method.getAnnotation(ZfenrirMqClient.class);
               if (mqClient == null) {
                   throw new ZfenrirRocketMqException(MessageFormat.format("{0} ZfenrirMqClient 注解不存在", name));
               }
               executorService.execute(() -> {
                    ConsumerModel consumerModel = mqClient.consumerModel();
                    try {
                        if (consumerModel == ConsumerModel.PULL) {
                            pullConsumerMap.put(mqClient.consumerGroup(), createDefaultLitePullConsumer(mqClient, consumerCallBack));
                        }else {
                            pushConsumerMap.put(mqClient.consumerGroup(), createDefaultLitePushConsumer(mqClient, consumerCallBack));
                        }
                    } catch (MQClientException e) {
                        logger.error(MessageFormat.format("{0} create consumer error", name), e);
                    }
               });
            } catch (NoSuchMethodException | SecurityException e) {
                throw new ZfenrirRocketMqException(MessageFormat.format("{0} method 异常", name), e);
            }
        });
    }
    
    public void start() {
        pushConsumerMap.forEach((groupName, consumer) -> {
            try {
                consumer.start();
            } catch (MQClientException e) {
                throw new ZfenrirRocketMqException(MessageFormat.format("{0} faild start", groupName), e);
            }
        });
        pullConsumerMap.forEach((groupName, consumer) -> {
            try {
                consumer.start();
                pullConsumerThreadMap.get(consumer).start();
            } catch (MQClientException e) {
                throw new ZfenrirRocketMqException(MessageFormat.format("{0} faild start", consumer), e);
            }
        });
    }
    
    public void shutdowm() {
        pushConsumerMap.forEach((groupName, consumer) -> {
            consumer.shutdown();
        });
        pullConsumerMap.forEach((groupName, consumer) -> {
            consumer.shutdown();
            pullConsumerThreadMap.get(consumer).interrupt();
        });
        executorService.shutdown();
    }
    
    private DefaultLitePullConsumer createDefaultLitePullConsumer(ZfenrirMqClient mqClient, ZfenrirConsumerCallBack consumerCallBack) throws MQClientException {
        DefaultLitePullConsumer pullConsumer = new DefaultLitePullConsumer(mqClient.consumerGroup());
        pullConsumer.setNamesrvAddr(namesrvAddr);
        pullConsumer.setInstanceName(consumerGroup);
        pullConsumer.subscribe(mqClient.topic(), mqClient.tag());
        pullConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pullConsumer.setAutoCommit(false);
        Thread thread = new Thread(() -> {
            while (true) {
                List<MessageExt> messageExts = pullConsumer.poll();
                for (MessageExt messageExt : messageExts) {
                    try {
                        consumerCallBack.consumeMessage(messageExt);
                    } catch (Exception e) {
                        logger.error(MessageFormat.format("{0} deal faild", messageExt.toString()), e);
                    }
                }
                pullConsumer.commitSync();
            }
       }, "ZfenrirRocketMqConsume client ");
       pullConsumerThreadMap.put(pullConsumer, thread);
        return pullConsumer;
    }
    
    public void setConsumerCallBackMap(Map<String, ZfenrirConsumerCallBack> consumerCallBackMap) {
        this.consumerCallBackMap = consumerCallBackMap;
    }

    private DefaultMQPushConsumer createDefaultLitePushConsumer(ZfenrirMqClient mqClient, ZfenrirConsumerCallBack consumerCallBack) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(mqClient.consumerGroup());
        pushConsumer.setNamesrvAddr(namesrvAddr);
        pushConsumer.setInstanceName(consumerGroup);
        pushConsumer.subscribe(mqClient.topic(), mqClient.tag());
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pushConsumer.setConsumeThreadMin(mqClient.consumeThreadMin());
        pushConsumer.setConsumeThreadMax(mqClient.consumeThreadMax());
        MessageListener messageListener = createMessageListener(mqClient, consumerCallBack);
        if (mqClient.orderly()) {
            pushConsumer.registerMessageListener((MessageListenerOrderly) messageListener);
        }else {
            pushConsumer.registerMessageListener((MessageListenerConcurrently) messageListener);
        }
        return pushConsumer;
    }

    private MessageListener createMessageListener(ZfenrirMqClient mqClient, ZfenrirConsumerCallBack consumerCallBack) {
        if (mqClient.orderly()) {
          return  new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    for (MessageExt messageExt : msgs) {
                        try {
                            consumerCallBack.consumeMessage(messageExt);
                        } catch (Exception e) {
                            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                        }
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            };
        }
        return new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : msgs) {
                    try {
                        consumerCallBack.consumeMessage(messageExt);
                    } catch (Exception e) {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };
    }
}
