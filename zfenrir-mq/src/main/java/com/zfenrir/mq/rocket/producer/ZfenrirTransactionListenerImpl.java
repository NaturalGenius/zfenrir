package com.zfenrir.mq.rocket.producer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import com.zfenrir.mq.rocket.common.ZfenrirTransactionMessageComfirm;

public class ZfenrirTransactionListenerImpl implements TransactionListener{

    private Map<String, Integer> localTrans = new ConcurrentHashMap<>();
    /**
     * key 为TOPIC
     */
    private Map<String, ZfenrirTransactionMessageComfirm> messageComfirmMap;
    
    public ZfenrirTransactionListenerImpl(Map<String, ZfenrirTransactionMessageComfirm> messageComfirmMap) {
       this.messageComfirmMap = messageComfirmMap;
    }
    
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        ZfenrirTransactionMessageComfirm messageComfirm = messageComfirmMap.get(msg.getTopic());
        String transactionId = msg.getTransactionId();
        if (messageComfirm != null) {
            try {
                messageComfirm.comfirmMessage(msg, arg);
                localTrans.put(transactionId, 1);
                return LocalTransactionState.COMMIT_MESSAGE;
            } catch (Exception e) {
                localTrans.put(transactionId, 2);
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        localTrans.put(transactionId, 0);
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String transactionId = msg.getTransactionId();
        Integer status = localTrans.get(transactionId);
        localTrans.remove(transactionId);
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
