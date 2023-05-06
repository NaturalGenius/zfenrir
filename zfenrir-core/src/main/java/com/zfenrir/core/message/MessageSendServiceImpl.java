package com.zfenrir.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 发送通用消息
 * 
 * @author zhuliang
 * @Date 2023-3-14
 *
 */
@Service
public class MessageSendServiceImpl implements IMessageSendService {

    @Autowired
    private ITemplateService templateService;
    @Autowired
    private ResolveParamaterContent resolveParamaterContent;
    @Override
    public void sendMessage(Integer templateId, Integer userId) {
        // 第一步获取模板信息
        MsgTemplate msgTemplate = templateService.geMsgTemplateById(templateId);
        PlaceholderResolver placeholderResolver = PlaceholderResolver.getDefaultResolver();
        // 第二部解析模板中参数
        List<String> resolvepLaceholders = placeholderResolver.resolvepLaceholders(msgTemplate.getContent());
        // 第三步 解析参数
        Map<String, String> paramValueMap = resolveParamaterContent.resolveParamaters(resolvepLaceholders);
        // 构建发送文本
        String resolveByMap = placeholderResolver.resolveByMap(msgTemplate.getContent(), paramValueMap);
        // 发送消息
        System.out.println("消息发送成功" + resolveByMap);
    }


}
