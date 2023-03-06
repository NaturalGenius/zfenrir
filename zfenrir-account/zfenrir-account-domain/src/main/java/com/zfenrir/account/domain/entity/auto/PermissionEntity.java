package com.zfenrir.account.domain.entity.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuliang
 * @since 2021-10-31
 */
@TableName("permission")
public class PermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 权限控制器
     */
    private String control;

    /**
     * 所属系统id
     */
    private String appId;

    /**
     * 菜单 1 菜单 2 按钮
     */
    private Integer menu;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方法 多个逗号隔开
     */
    private String method;

    /**
     * 描述
     */
    private String desc;

    /**
     * 状态 1正常 2 删除
     */
    private Integer status;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    private Integer updateId;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String PID = "pid";

    public static final String CONTROL = "control";

    public static final String APP_ID = "app_id";

    public static final String MENU = "menu";

    public static final String URL = "url";

    public static final String METHOD = "method";

    public static final String DESC = "desc";

    public static final String STATUS = "status";

    public static final String CREATE_ID = "create_id";

    public static final String CREATE_NAME = "create_name";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ID = "update_id";

    public static final String UPDATE_NAME = "update_name";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "PermissionEntity{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", pid=" + pid +
        ", control=" + control +
        ", appId=" + appId +
        ", menu=" + menu +
        ", url=" + url +
        ", method=" + method +
        ", desc=" + desc +
        ", status=" + status +
        ", createId=" + createId +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", updateId=" + updateId +
        ", updateName=" + updateName +
        ", updateTime=" + updateTime +
        "}";
    }
}
