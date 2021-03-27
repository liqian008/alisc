package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动表
 * KhActivityEntity
 * 数据库表：kh_activity
 */
public class KhActivityEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_activity.id
     */
    private Integer id;

    /**
     * 活动名称
     * 表字段 : kh_activity.name
     */
    private String name;

    /**
     * 描述
     * 表字段 : kh_activity.description
     */
    private String description;

    /**
     * 活动开始时间
     * 表字段 : kh_activity.start_time
     */
    private Date startTime;

    /**
     * 活动结束时间
     * 表字段 : kh_activity.end_time
     */
    private Date endTime;

    /**
     * 最后操作人
     * 表字段 : kh_activity.last_mod_uid
     */
    private Integer lastModUid;

    /**
     * 
     * 表字段 : kh_activity.status
     */
    private Short status;

    /**
     * 
     * 表字段 : kh_activity.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_activity.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhActivityEntity EMPTY_DATA = new KhActivityEntity();

    /**
     * 获取  字段:kh_activity.id
     *
     * @return kh_activity.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_activity.id
     *
     * @param id the value for kh_activity.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 活动名称 字段:kh_activity.name
     *
     * @return kh_activity.name, 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 活动名称 字段:kh_activity.name
     *
     * @param name the value for kh_activity.name, 活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 描述 字段:kh_activity.description
     *
     * @return kh_activity.description, 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 描述 字段:kh_activity.description
     *
     * @param description the value for kh_activity.description, 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取 活动开始时间 字段:kh_activity.start_time
     *
     * @return kh_activity.start_time, 活动开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 活动开始时间 字段:kh_activity.start_time
     *
     * @param startTime the value for kh_activity.start_time, 活动开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 活动结束时间 字段:kh_activity.end_time
     *
     * @return kh_activity.end_time, 活动结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 活动结束时间 字段:kh_activity.end_time
     *
     * @param endTime the value for kh_activity.end_time, 活动结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 最后操作人 字段:kh_activity.last_mod_uid
     *
     * @return kh_activity.last_mod_uid, 最后操作人
     */
    public Integer getLastModUid() {
        return lastModUid;
    }

    /**
     * 设置 最后操作人 字段:kh_activity.last_mod_uid
     *
     * @param lastModUid the value for kh_activity.last_mod_uid, 最后操作人
     */
    public void setLastModUid(Integer lastModUid) {
        this.lastModUid = lastModUid;
    }

    /**
     * 获取  字段:kh_activity.status
     *
     * @return kh_activity.status, 
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置  字段:kh_activity.status
     *
     * @param status the value for kh_activity.status, 
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取  字段:kh_activity.create_time
     *
     * @return kh_activity.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_activity.create_time
     *
     * @param createTime the value for kh_activity.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_activity.update_time
     *
     * @return kh_activity.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_activity.update_time
     *
     * @param updateTime the value for kh_activity.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_activity
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", lastModUid=").append(lastModUid);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_activity
     *
     * @param entity
     */
    public static boolean isValid(KhActivityEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}