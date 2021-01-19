package com.shan.yellowpages.security.model.struct;


import com.shan.yellowpages.security.model.KhAdminUserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户dto
 * @author bruce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserStruct implements Serializable {

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;


    private Integer id;

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
     * 头像url
     * 表字段 : kh_admin_user.avatar_url
     */
    private String avatarUrl;


    /**
     * 最后一次登录时间
     * 表字段 : kh_admin_user.last_login_time
     */
    private Date lastLoginTime;



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
     * 判断实体是否合法, AdminUserStruct
     *
     * @param entity
     */
    public static boolean isValid(AdminUserStruct entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }

    /**
     *
     * @param entity
     * @return
     */
    public static AdminUserStruct convert(KhAdminUserEntity entity) {

        AdminUserStruct result = null;
        if(KhAdminUserEntity.isValid(entity)){
            result = new AdminUserStruct();

            result.setAvatarUrl(entity.getAvatarUrl());
            result.setId(entity.getId());
            result.setNickname(entity.getNickname());
            result.setUsername(entity.getUsername());
            result.setStatus(entity.getStatus());
            result.setCreateTime(entity.getCreateTime());
            result.setLastLoginTime(entity.getLastLoginTime());

        }

        return result;
    }



}