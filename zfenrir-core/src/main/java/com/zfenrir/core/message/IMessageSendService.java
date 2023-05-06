package com.zfenrir.core.message;

public interface IMessageSendService {

    /**
     * 发送模板消息
     * 
     * @param templateId
     * @param userId
     */
    void sendMessage(Integer templateId, Integer userId);
}
