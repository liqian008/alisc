//package com.shan.yellowpages.security.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 管理后台权限表
// * KhAdminPermissionEntity
// * 数据库表：kh_admin_permission
// */
//public class KhAdminPermissionEntity implements Serializable {
//    /**
//     *
//     * 表字段 : kh_admin_permission.id
//     */
//    private Integer id;
//
//    /**
//     * 分组id
//     * 表字段 : kh_admin_permission.category_id
//     */
//    private Integer categoryId;
//
//    /**
//     * 权限名称
//     * 表字段 : kh_admin_permission.permission_name
//     */
//    private String permissionName;
//
//    /**
//     * 权限类型，0数据请求，10路由
//     * 表字段 : kh_admin_permission.type
//     */
//    private Short type;
//
//    /**
//     * 访问类型，0默认角色，10超级权限，2admin权限
//     * 表字段 : kh_admin_permission.access_type
//     */
//    private Short accessType;
//
//    /**
//     * 权限代码（与前端约定），普通类型为业务代码，如： user.delete; 路由类型值为uri，如： /user/list
//     * 表字段 : kh_admin_permission.permission_code
//     */
//    private String permissionCode;
//
//    /**
//     * 拦截的url
//     * 表字段 : kh_admin_permission.match_url
//     */
//    private String matchUrl;
//
//    /**
//     * url的路径层级，用于处理url的匹配顺序（从大到小进行匹配），层级步长为10，精准url为9999
//     * 表字段 : kh_admin_permission.match_url_level
//     */
//    private Short matchUrlLevel;
//
//    /**
//     * 备注
//     * 表字段 : kh_admin_permission.remark
//     */
//    private String remark;
//
//    /**
//     * 排序
//     * 表字段 : kh_admin_permission.sort
//     */
//    private Integer sort;
//
//    /**
//     * 状态
//     * 表字段 : kh_admin_permission.status
//     */
//    private Short status;
//
//    /**
//     *
//     * 表字段 : kh_admin_permission.create_time
//     */
//    private Date createTime;
//
//    /**
//     *
//     * 表字段 : kh_admin_permission.update_time
//     */
//    private Date updateTime;
//
//    /**
//     * 显示指定serialVersionUID生成方式
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
//     */
//    public static final KhAdminPermissionEntity EMPTY_DATA = new KhAdminPermissionEntity();
//
//    /**
//     * 获取  字段:kh_admin_permission.id
//     *
//     * @return kh_admin_permission.id,
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 设置  字段:kh_admin_permission.id
//     *
//     * @param id the value for kh_admin_permission.id,
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * 获取 分组id 字段:kh_admin_permission.category_id
//     *
//     * @return kh_admin_permission.category_id, 分组id
//     */
//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    /**
//     * 设置 分组id 字段:kh_admin_permission.category_id
//     *
//     * @param categoryId the value for kh_admin_permission.category_id, 分组id
//     */
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    /**
//     * 获取 权限名称 字段:kh_admin_permission.permission_name
//     *
//     * @return kh_admin_permission.permission_name, 权限名称
//     */
//    public String getPermissionName() {
//        return permissionName;
//    }
//
//    /**
//     * 设置 权限名称 字段:kh_admin_permission.permission_name
//     *
//     * @param permissionName the value for kh_admin_permission.permission_name, 权限名称
//     */
//    public void setPermissionName(String permissionName) {
//        this.permissionName = permissionName;
//    }
//
//    /**
//     * 获取 权限类型，0数据请求，10路由 字段:kh_admin_permission.type
//     *
//     * @return kh_admin_permission.type, 权限类型，0数据请求，10路由
//     */
//    public Short getType() {
//        return type;
//    }
//
//    /**
//     * 设置 权限类型，0数据请求，10路由 字段:kh_admin_permission.type
//     *
//     * @param type the value for kh_admin_permission.type, 权限类型，0数据请求，10路由
//     */
//    public void setType(Short type) {
//        this.type = type;
//    }
//
//    /**
//     * 获取 访问类型，0默认角色，10超级权限，2admin权限 字段:kh_admin_permission.access_type
//     *
//     * @return kh_admin_permission.access_type, 访问类型，0默认角色，10超级权限，2admin权限
//     */
//    public Short getAccessType() {
//        return accessType;
//    }
//
//    /**
//     * 设置 访问类型，0默认角色，10超级权限，2admin权限 字段:kh_admin_permission.access_type
//     *
//     * @param accessType the value for kh_admin_permission.access_type, 访问类型，0默认角色，10超级权限，2admin权限
//     */
//    public void setAccessType(Short accessType) {
//        this.accessType = accessType;
//    }
//
//    /**
//     * 获取 权限代码（与前端约定），普通类型为业务代码，如： user.delete; 路由类型值为uri，如： /user/list 字段:kh_admin_permission.permission_code
//     *
//     * @return kh_admin_permission.permission_code, 权限代码（与前端约定），普通类型为业务代码，如： user.delete; 路由类型值为uri，如： /user/list
//     */
//    public String getPermissionCode() {
//        return permissionCode;
//    }
//
//    /**
//     * 设置 权限代码（与前端约定），普通类型为业务代码，如： user.delete; 路由类型值为uri，如： /user/list 字段:kh_admin_permission.permission_code
//     *
//     * @param permissionCode the value for kh_admin_permission.permission_code, 权限代码（与前端约定），普通类型为业务代码，如： user.delete; 路由类型值为uri，如： /user/list
//     */
//    public void setPermissionCode(String permissionCode) {
//        this.permissionCode = permissionCode;
//    }
//
//    /**
//     * 获取 拦截的url 字段:kh_admin_permission.match_url
//     *
//     * @return kh_admin_permission.match_url, 拦截的url
//     */
//    public String getMatchUrl() {
//        return matchUrl;
//    }
//
//    /**
//     * 设置 拦截的url 字段:kh_admin_permission.match_url
//     *
//     * @param matchUrl the value for kh_admin_permission.match_url, 拦截的url
//     */
//    public void setMatchUrl(String matchUrl) {
//        this.matchUrl = matchUrl;
//    }
//
//    /**
//     * 获取 url的路径层级，用于处理url的匹配顺序（从大到小进行匹配），层级步长为10，精准url为9999 字段:kh_admin_permission.match_url_level
//     *
//     * @return kh_admin_permission.match_url_level, url的路径层级，用于处理url的匹配顺序（从大到小进行匹配），层级步长为10，精准url为9999
//     */
//    public Short getMatchUrlLevel() {
//        return matchUrlLevel;
//    }
//
//    /**
//     * 设置 url的路径层级，用于处理url的匹配顺序（从大到小进行匹配），层级步长为10，精准url为9999 字段:kh_admin_permission.match_url_level
//     *
//     * @param matchUrlLevel the value for kh_admin_permission.match_url_level, url的路径层级，用于处理url的匹配顺序（从大到小进行匹配），层级步长为10，精准url为9999
//     */
//    public void setMatchUrlLevel(Short matchUrlLevel) {
//        this.matchUrlLevel = matchUrlLevel;
//    }
//
//    /**
//     * 获取 备注 字段:kh_admin_permission.remark
//     *
//     * @return kh_admin_permission.remark, 备注
//     */
//    public String getRemark() {
//        return remark;
//    }
//
//    /**
//     * 设置 备注 字段:kh_admin_permission.remark
//     *
//     * @param remark the value for kh_admin_permission.remark, 备注
//     */
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    /**
//     * 获取 排序 字段:kh_admin_permission.sort
//     *
//     * @return kh_admin_permission.sort, 排序
//     */
//    public Integer getSort() {
//        return sort;
//    }
//
//    /**
//     * 设置 排序 字段:kh_admin_permission.sort
//     *
//     * @param sort the value for kh_admin_permission.sort, 排序
//     */
//    public void setSort(Integer sort) {
//        this.sort = sort;
//    }
//
//    /**
//     * 获取 状态 字段:kh_admin_permission.status
//     *
//     * @return kh_admin_permission.status, 状态
//     */
//    public Short getStatus() {
//        return status;
//    }
//
//    /**
//     * 设置 状态 字段:kh_admin_permission.status
//     *
//     * @param status the value for kh_admin_permission.status, 状态
//     */
//    public void setStatus(Short status) {
//        this.status = status;
//    }
//
//    /**
//     * 获取  字段:kh_admin_permission.create_time
//     *
//     * @return kh_admin_permission.create_time,
//     */
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    /**
//     * 设置  字段:kh_admin_permission.create_time
//     *
//     * @param createTime the value for kh_admin_permission.create_time,
//     */
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    /**
//     * 获取  字段:kh_admin_permission.update_time
//     *
//     * @return kh_admin_permission.update_time,
//     */
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    /**
//     * 设置  字段:kh_admin_permission.update_time
//     *
//     * @param updateTime the value for kh_admin_permission.update_time,
//     */
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    /**
//     * ,kh_admin_permission
//     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", categoryId=").append(categoryId);
//        sb.append(", permissionName=").append(permissionName);
//        sb.append(", type=").append(type);
//        sb.append(", accessType=").append(accessType);
//        sb.append(", permissionCode=").append(permissionCode);
//        sb.append(", matchUrl=").append(matchUrl);
//        sb.append(", matchUrlLevel=").append(matchUrlLevel);
//        sb.append(", remark=").append(remark);
//        sb.append(", sort=").append(sort);
//        sb.append(", status=").append(status);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append("]");
//        return sb.toString();
//    }
//
//    /**
//     * 判断实体是否合法,kh_admin_permission
//     *
//     * @param entity
//     */
//    public static boolean isValid(KhAdminPermissionEntity entity) {
//        boolean result = false;
//        if (entity != null && entity.getId() != null && entity.getId() > 0) {
//            result = true;
//        }
//        return result;
//    }
//}