package com.zfenrir.account.api.vo;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;

public class DemoVO {

    private Date createTime = new Date();
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime = new Date();
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date testTime = new Date();
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime2 = new Date();
    @JSONField(format = "HH:mm:ss")
    private Date createTime3 = new Date();
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getTestTime() {
        return testTime;
    }
    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }
    public Date getCreateTime2() {
        return createTime2;
    }
    public void setCreateTime2(Date createTime2) {
        this.createTime2 = createTime2;
    }
    public Date getCreateTime3() {
        return createTime3;
    }
    public void setCreateTime3(Date createTime3) {
        this.createTime3 = createTime3;
    }
    
}
