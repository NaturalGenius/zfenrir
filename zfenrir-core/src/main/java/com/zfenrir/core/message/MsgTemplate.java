package com.zfenrir.core.message;

/**
 * 消息模板
 * 
 * @author zhuliang
 * @Date 2023-3-14
 *
 */
public class MsgTemplate {

    /**
     * 模板id
     */
    private Integer id;
    /**
     * 模板内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
