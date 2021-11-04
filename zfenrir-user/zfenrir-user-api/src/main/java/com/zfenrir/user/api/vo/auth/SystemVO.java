package com.zfenrir.user.api.vo.auth;

import io.swagger.annotations.ApiModelProperty;

public class SystemVO {
    @ApiModelProperty("自增id")
    private Integer id;
    @ApiModelProperty("系统id")
    private String appId;
    @ApiModelProperty("系统名称")
    private String name;

    @ApiModelProperty("系统描述")
    private String desc;
    @ApiModelProperty("首页地址")
    private String homeUrl;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getHomeUrl() {
        return homeUrl;
    }
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }
}
