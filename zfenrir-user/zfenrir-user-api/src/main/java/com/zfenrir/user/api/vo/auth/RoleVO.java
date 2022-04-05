package com.zfenrir.user.api.vo.auth;

import java.time.LocalDateTime;

public class RoleVO {

   // @ApiModelProperty("角色id")
    private Integer id;

  //  @ApiModelProperty("角色名称")
    private String name;

  //  @ApiModelProperty("角色code")
    private String code;

  //  @ApiModelProperty("类型 1 普通用户 2 系统角色 3 系统管理员 4 超级管理员")
    private Integer type;

  //  @ApiModelProperty("所属系统ID")
    private String appId;

  //  @ApiModelProperty("描述")
    private String desc;

  //  @ApiModelProperty("状态 1正常 2 停用")
    private Integer status;

  //  @ApiModelProperty("创建人id")
    private Integer createId;

  //  @ApiModelProperty("创建人名称")
    private String createName;

  //  @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

  //  @ApiModelProperty("更新人id")
    private Integer updateId;

  //  @ApiModelProperty("更新人名称")
    private String updateName;

 //   @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
