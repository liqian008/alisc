package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理后台菜单管理表
 * KhAdminNavMenuEntity
 * 数据库表：kh_admin_nav_menu
 */
public class KhAdminNavMenuEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_admin_nav_menu.id
     */
    private Integer id;

    /**
     * 导航类型，0左导航，10上方导航（暂不使用）
     * 表字段 : kh_admin_nav_menu.nav_type
     */
    private Short navType;

    /**
     * 访问类型，0业务默认权限，10admin管理权限。20业务超级权限，30超级管理权限
     * 表字段 : kh_admin_nav_menu.access_type
     */
    private Short accessType;

    /**
     * 父菜单ID，主要用于构造树形菜单
     * 表字段 : kh_admin_nav_menu.parent_id
     */
    private Integer parentId;

    /**
     * 权限id，菜单文件夹情况下可为空
     * 表字段 : kh_admin_nav_menu.permission_id
     */
    private Integer permissionId;

    /**
     * 菜单标题
     * 表字段 : kh_admin_nav_menu.title
     */
    private String title;

    /**
     * icon图片地址
     * 表字段 : kh_admin_nav_menu.icon_url
     */
    private String iconUrl;

    /**
     * 菜单代码，目前用于映射spa的路由路径
     * 表字段 : kh_admin_nav_menu.menu_code
     */
    private String menuCode;

    /**
     * 备注
     * 表字段 : kh_admin_nav_menu.remark
     */
    private String remark;

    /**
     * 排序
     * 表字段 : kh_admin_nav_menu.sort
     */
    private Integer sort;

    /**
     * 状态
     * 表字段 : kh_admin_nav_menu.status
     */
    private Short status;

    /**
     * 
     * 表字段 : kh_admin_nav_menu.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_admin_nav_menu.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhAdminNavMenuEntity EMPTY_DATA = new KhAdminNavMenuEntity();

    /**
     * 获取  字段:kh_admin_nav_menu.id
     *
     * @return kh_admin_nav_menu.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_admin_nav_menu.id
     *
     * @param id the value for kh_admin_nav_menu.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 导航类型，0左导航，10上方导航（暂不使用） 字段:kh_admin_nav_menu.nav_type
     *
     * @return kh_admin_nav_menu.nav_type, 导航类型，0左导航，10上方导航（暂不使用）
     */
    public Short getNavType() {
        return navType;
    }

    /**
     * 设置 导航类型，0左导航，10上方导航（暂不使用） 字段:kh_admin_nav_menu.nav_type
     *
     * @param navType the value for kh_admin_nav_menu.nav_type, 导航类型，0左导航，10上方导航（暂不使用）
     */
    public void setNavType(Short navType) {
        this.navType = navType;
    }

    /**
     * 获取 访问类型，0业务默认权限，10admin管理权限。20业务超级权限，30超级管理权限 字段:kh_admin_nav_menu.access_type
     *
     * @return kh_admin_nav_menu.access_type, 访问类型，0业务默认权限，10admin管理权限。20业务超级权限，30超级管理权限
     */
    public Short getAccessType() {
        return accessType;
    }

    /**
     * 设置 访问类型，0业务默认权限，10admin管理权限。20业务超级权限，30超级管理权限 字段:kh_admin_nav_menu.access_type
     *
     * @param accessType the value for kh_admin_nav_menu.access_type, 访问类型，0业务默认权限，10admin管理权限。20业务超级权限，30超级管理权限
     */
    public void setAccessType(Short accessType) {
        this.accessType = accessType;
    }

    /**
     * 获取 父菜单ID，主要用于构造树形菜单 字段:kh_admin_nav_menu.parent_id
     *
     * @return kh_admin_nav_menu.parent_id, 父菜单ID，主要用于构造树形菜单
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置 父菜单ID，主要用于构造树形菜单 字段:kh_admin_nav_menu.parent_id
     *
     * @param parentId the value for kh_admin_nav_menu.parent_id, 父菜单ID，主要用于构造树形菜单
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 权限id，菜单文件夹情况下可为空 字段:kh_admin_nav_menu.permission_id
     *
     * @return kh_admin_nav_menu.permission_id, 权限id，菜单文件夹情况下可为空
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 设置 权限id，菜单文件夹情况下可为空 字段:kh_admin_nav_menu.permission_id
     *
     * @param permissionId the value for kh_admin_nav_menu.permission_id, 权限id，菜单文件夹情况下可为空
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取 菜单标题 字段:kh_admin_nav_menu.title
     *
     * @return kh_admin_nav_menu.title, 菜单标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 菜单标题 字段:kh_admin_nav_menu.title
     *
     * @param title the value for kh_admin_nav_menu.title, 菜单标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 icon图片地址 字段:kh_admin_nav_menu.icon_url
     *
     * @return kh_admin_nav_menu.icon_url, icon图片地址
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置 icon图片地址 字段:kh_admin_nav_menu.icon_url
     *
     * @param iconUrl the value for kh_admin_nav_menu.icon_url, icon图片地址
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    /**
     * 获取 菜单代码，目前用于映射spa的路由路径 字段:kh_admin_nav_menu.menu_code
     *
     * @return kh_admin_nav_menu.menu_code, 菜单代码，目前用于映射spa的路由路径
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置 菜单代码，目前用于映射spa的路由路径 字段:kh_admin_nav_menu.menu_code
     *
     * @param menuCode the value for kh_admin_nav_menu.menu_code, 菜单代码，目前用于映射spa的路由路径
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取 备注 字段:kh_admin_nav_menu.remark
     *
     * @return kh_admin_nav_menu.remark, 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 字段:kh_admin_nav_menu.remark
     *
     * @param remark the value for kh_admin_nav_menu.remark, 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 排序 字段:kh_admin_nav_menu.sort
     *
     * @return kh_admin_nav_menu.sort, 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置 排序 字段:kh_admin_nav_menu.sort
     *
     * @param sort the value for kh_admin_nav_menu.sort, 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取 状态 字段:kh_admin_nav_menu.status
     *
     * @return kh_admin_nav_menu.status, 状态
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置 状态 字段:kh_admin_nav_menu.status
     *
     * @param status the value for kh_admin_nav_menu.status, 状态
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取  字段:kh_admin_nav_menu.create_time
     *
     * @return kh_admin_nav_menu.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_admin_nav_menu.create_time
     *
     * @param createTime the value for kh_admin_nav_menu.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_admin_nav_menu.update_time
     *
     * @return kh_admin_nav_menu.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_admin_nav_menu.update_time
     *
     * @param updateTime the value for kh_admin_nav_menu.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_admin_nav_menu
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", navType=").append(navType);
        sb.append(", accessType=").append(accessType);
        sb.append(", parentId=").append(parentId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", title=").append(title);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", remark=").append(remark);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_admin_nav_menu
     *
     * @param entity
     */
    public static boolean isValid(KhAdminNavMenuEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}