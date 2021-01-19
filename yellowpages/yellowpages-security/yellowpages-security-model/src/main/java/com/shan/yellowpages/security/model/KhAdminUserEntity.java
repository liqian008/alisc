package com.shan.yellowpages.security.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 管理后台用户表
 * KhAdminUserEntity
 * 数据库表：kh_admin_user
 */
public class KhAdminUserEntity implements Serializable {
    /**
     * 主键id
     * 表字段 : kh_admin_user.id
     */
    private Integer id;

    /**
     * 用户类型：10-超级用户，20-普通admin，30-普通用户
     * 表字段 : kh_admin_user.user_type
     */
    private Short userType;

    /**
     * 访问类型，0常规用户，10超级admin，20普通admin
     * 表字段 : kh_admin_user.access_type
     */
    private Short accessType;

    /**
     * 用户名
     * 表字段 : kh_admin_user.username
     */
    private String username;

    /**
     * 用户昵称
     * 表字段 : kh_admin_user.nickname
     */
    private String nickname;

    /**
     * 密码
     * 表字段 : kh_admin_user.password
     */
    private String password;

    /**
     * 头像url
     * 表字段 : kh_admin_user.avatar_url
     */
    private String avatarUrl;

    /**
     * 父账户id
     * 表字段 : kh_admin_user.primary_account_id
     */
    private Integer primaryAccountId;

    /**
     * 账号创建者id
     * 表字段 : kh_admin_user.creator_id
     */
    private Integer creatorId;

    /**
     * 最后一次登录时间
     * 表字段 : kh_admin_user.last_login_time
     */
    private Date lastLoginTime;

    /**
     * 排序
     * 表字段 : kh_admin_user.sort
     */
    private Integer sort;

    /**
     * 0:禁用,1:启用
     * 表字段 : kh_admin_user.status
     */
    private Short status;

    /**
     * 创建时间
     * 表字段 : kh_admin_user.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * 表字段 : kh_admin_user.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhAdminUserEntity EMPTY_DATA = new KhAdminUserEntity();

    /**
     * 获取 主键id 字段:kh_admin_user.id
     *
     * @return kh_admin_user.id, 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 主键id 字段:kh_admin_user.id
     *
     * @param id the value for kh_admin_user.id, 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 用户类型：10-超级用户，20-普通admin，30-普通用户 字段:kh_admin_user.user_type
     *
     * @return kh_admin_user.user_type, 用户类型：10-超级用户，20-普通admin，30-普通用户
     */
    public Short getUserType() {
        return userType;
    }

    /**
     * 设置 用户类型：10-超级用户，20-普通admin，30-普通用户 字段:kh_admin_user.user_type
     *
     * @param userType the value for kh_admin_user.user_type, 用户类型：10-超级用户，20-普通admin，30-普通用户
     */
    public void setUserType(Short userType) {
        this.userType = userType;
    }

    /**
     * 获取 访问类型，0常规用户，10超级admin，20普通admin 字段:kh_admin_user.access_type
     *
     * @return kh_admin_user.access_type, 访问类型，0常规用户，10超级admin，20普通admin
     */
    public Short getAccessType() {
        return accessType;
    }

    /**
     * 设置 访问类型，0常规用户，10超级admin，20普通admin 字段:kh_admin_user.access_type
     *
     * @param accessType the value for kh_admin_user.access_type, 访问类型，0常规用户，10超级admin，20普通admin
     */
    public void setAccessType(Short accessType) {
        this.accessType = accessType;
    }

    /**
     * 获取 用户名 字段:kh_admin_user.username
     *
     * @return kh_admin_user.username, 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 用户名 字段:kh_admin_user.username
     *
     * @param username the value for kh_admin_user.username, 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取 用户昵称 字段:kh_admin_user.nickname
     *
     * @return kh_admin_user.nickname, 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置 用户昵称 字段:kh_admin_user.nickname
     *
     * @param nickname the value for kh_admin_user.nickname, 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取 密码 字段:kh_admin_user.password
     *
     * @return kh_admin_user.password, 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码 字段:kh_admin_user.password
     *
     * @param password the value for kh_admin_user.password, 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 头像url 字段:kh_admin_user.avatar_url
     *
     * @return kh_admin_user.avatar_url, 头像url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置 头像url 字段:kh_admin_user.avatar_url
     *
     * @param avatarUrl the value for kh_admin_user.avatar_url, 头像url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取 父账户id 字段:kh_admin_user.primary_account_id
     *
     * @return kh_admin_user.primary_account_id, 父账户id
     */
    public Integer getPrimaryAccountId() {
        return primaryAccountId;
    }

    /**
     * 设置 父账户id 字段:kh_admin_user.primary_account_id
     *
     * @param primaryAccountId the value for kh_admin_user.primary_account_id, 父账户id
     */
    public void setPrimaryAccountId(Integer primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }

    /**
     * 获取 账号创建者id 字段:kh_admin_user.creator_id
     *
     * @return kh_admin_user.creator_id, 账号创建者id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 设置 账号创建者id 字段:kh_admin_user.creator_id
     *
     * @param creatorId the value for kh_admin_user.creator_id, 账号创建者id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取 最后一次登录时间 字段:kh_admin_user.last_login_time
     *
     * @return kh_admin_user.last_login_time, 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置 最后一次登录时间 字段:kh_admin_user.last_login_time
     *
     * @param lastLoginTime the value for kh_admin_user.last_login_time, 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取 排序 字段:kh_admin_user.sort
     *
     * @return kh_admin_user.sort, 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置 排序 字段:kh_admin_user.sort
     *
     * @param sort the value for kh_admin_user.sort, 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取 0:禁用,1:启用 字段:kh_admin_user.status
     *
     * @return kh_admin_user.status, 0:禁用,1:启用
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置 0:禁用,1:启用 字段:kh_admin_user.status
     *
     * @param status the value for kh_admin_user.status, 0:禁用,1:启用
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取 创建时间 字段:kh_admin_user.create_time
     *
     * @return kh_admin_user.create_time, 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 字段:kh_admin_user.create_time
     *
     * @param createTime the value for kh_admin_user.create_time, 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 更新时间 字段:kh_admin_user.update_time
     *
     * @return kh_admin_user.update_time, 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间 字段:kh_admin_user.update_time
     *
     * @param updateTime the value for kh_admin_user.update_time, 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_admin_user
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userType=").append(userType);
        sb.append(", accessType=").append(accessType);
        sb.append(", username=").append(username);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", primaryAccountId=").append(primaryAccountId);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_admin_user
     *
     * @param entity
     */
    public static boolean isValid(KhAdminUserEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }

}