package com.zfenrir.mq.rocket.consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import com.zfenrir.mq.rocket.common.ConsumerModel;
import com.zfenrir.mq.rocket.common.ZfenrirMqClient;
import com.zfenrir.mq.rocket.producer.ZfenrirDefaultMQProducer;

public class ZfenrirRocketMqTest {
    public final static String TOPIC = "";
    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        String namesrvAddr = "";
        String group = "";
        ZfenrirRocketMqConsumer mqConsumer = new ZfenrirRocketMqConsumer(group, namesrvAddr);
        Map<String, ZfenrirConsumerCallBack> consumerCallBackMap = new HashMap<>();
        consumerCallBackMap.put("Test1", new Test1());
        consumerCallBackMap.put("Test2", new Test2());
        mqConsumer.setConsumerCallBackMap(consumerCallBackMap );
        mqConsumer.initConsume();
        mqConsumer.start();
        
        ZfenrirDefaultMQProducer mqProducer = new ZfenrirDefaultMQProducer(group, namesrvAddr);
        mqProducer.initDefaultMQProducer();
        mqConsumer.start();
        DefaultMQProducer defaultMQProducer = mqProducer.getDefaultMQProducer();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message(TOPIC, String.valueOf(i).getBytes());
            defaultMQProducer.send(msg);
        }
        TimeUnit.SECONDS.sleep(30);
        mqConsumer.shutdowm();
        mqProducer.shutdown();
    }
}


class Test1 implements ZfenrirConsumerCallBack{

    @Override
    @ZfenrirMqClient(consumerGroup = "Test1", topic = ZfenrirRocketMqTest.TOPIC)
    public void consumeMessage(MessageExt messageExt) {
        String message = new String(messageExt.getBody());
        System.out.println("Test1-" + message);
    }
    
}
class Test2 implements ZfenrirConsumerCallBack{
    
    @Override
    @ZfenrirMqClient(consumerGroup = "Test2", topic = ZfenrirRocketMqTest.TOPIC,
    consumerModel = ConsumerModel.PULL)
    public void consumeMessage(MessageExt messageExt) {
        String message = new String(messageExt.getBody());
        System.out.println("Test2-" + message);
    }
    
}