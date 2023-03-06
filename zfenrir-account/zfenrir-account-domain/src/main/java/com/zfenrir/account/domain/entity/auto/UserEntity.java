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
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String telphone;

    /**
     * 邮箱
     */
    private String email;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static final String USER_NAME = "user_name";

    public static final String NICK_NAME = "nick_name";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String TELPHONE = "telphone";

    public static final String EMAIL = "email";

    public static final String STATUS = "status";

    public static final String CREATE_ID = "create_id";

    public static final String CREATE_NAME = "create_name";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_ID = "update_id";

    public static final String UPDATE_NAME = "update_name";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "UserEntity{" +
        "id=" + id +
        ", userName=" + userName +
        ", nickName=" + nickName +
        ", account=" + account +
        ", password=" + password +
        ", telphone=" + telphone +
        ", email=" + email +
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
