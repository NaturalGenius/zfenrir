package com.zfenrir.user.api.form.auth;

import com.zfenrir.common.annotation.WrapperBuild;
import com.zfenrir.common.common.entity.PageParameter;
import com.zfenrir.common.enums.ZfenrirSqlOperateEnum;
import com.zfenrir.user.domain.entity.auto.RoleEntity;

public class RoleForm extends PageParameter{

   // @ApiModelProperty("角色名称")
    @WrapperBuild(columnName = RoleEntity.NAME, operator = ZfenrirSqlOperateEnum.LIKE)
    private String name;
  //  @ApiModelProperty("系统标识")
    @WrapperBuild(columnName = RoleEntity.APP_ID)
    private String appId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
