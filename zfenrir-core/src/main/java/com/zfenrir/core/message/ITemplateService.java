package com.zfenrir.core.message;

public interface ITemplateService {

    /**
     * 跟进模板id获取模板信息
     * 
     * @param templateId
     * @return
     */
    MsgTemplate geMsgTemplateById(Integer templateId);
}
