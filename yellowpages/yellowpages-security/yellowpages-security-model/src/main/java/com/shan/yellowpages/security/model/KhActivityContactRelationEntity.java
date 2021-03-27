package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动-联系人关联表
 * KhActivityContactRelationEntity
 * 数据库表：kh_activity_contact_relation
 */
public class KhActivityContactRelationEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_activity_contact_relation.id
     */
    private Integer id;

    /**
     * 活动id
     * 表字段 : kh_activity_contact_relation.activity_id
     */
    private Integer activityId;

    /**
     * 联系人id
     * 表字段 : kh_activity_contact_relation.contact_id
     */
    private Integer contactId;

    /**
     * 最后操作人
     * 表字段 : kh_activity_contact_relation.last_mod_uid
     */
    private Integer lastModUid;

    /**
     * 
     * 表字段 : kh_activity_contact_relation.status
     */
    private Short status;

    /**
     * 
     * 表字段 : kh_activity_contact_relation.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_activity_contact_relation.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhActivityContactRelationEntity EMPTY_DATA = new KhActivityContactRelationEntity();

    /**
     * 获取  字段:kh_activity_contact_relation.id
     *
     * @return kh_activity_contact_relation.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_activity_contact_relation.id
     *
     * @param id the value for kh_activity_contact_relation.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 活动id 字段:kh_activity_contact_relation.activity_id
     *
     * @return kh_activity_contact_relation.activity_id, 活动id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置 活动id 字段:kh_activity_contact_relation.activity_id
     *
     * @param activityId the value for kh_activity_contact_relation.activity_id, 活动id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取 联系人id 字段:kh_activity_contact_relation.contact_id
     *
     * @return kh_activity_contact_relation.contact_id, 联系人id
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * 设置 联系人id 字段:kh_activity_contact_relation.contact_id
     *
     * @param contactId the value for kh_activity_contact_relation.contact_id, 联系人id
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    /**
     * 获取 最后操作人 字段:kh_activity_contact_relation.last_mod_uid
     *
     * @return kh_activity_contact_relation.last_mod_uid, 最后操作人
     */
    public Integer getLastModUid() {
        return lastModUid;
    }

    /**
     * 设置 最后操作人 字段:kh_activity_contact_relation.last_mod_uid
     *
     * @param lastModUid the value for kh_activity_contact_relation.last_mod_uid, 最后操作人
     */
    public void setLastModUid(Integer lastModUid) {
        this.lastModUid = lastModUid;
    }

    /**
     * 获取  字段:kh_activity_contact_relation.status
     *
     * @return kh_activity_contact_relation.status, 
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置  字段:kh_activity_contact_relation.status
     *
     * @param status the value for kh_activity_contact_relation.status, 
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取  字段:kh_activity_contact_relation.create_time
     *
     * @return kh_activity_contact_relation.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_activity_contact_relation.create_time
     *
     * @param createTime the value for kh_activity_contact_relation.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_activity_contact_relation.update_time
     *
     * @return kh_activity_contact_relation.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_activity_contact_relation.update_time
     *
     * @param updateTime the value for kh_activity_contact_relation.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_activity_contact_relation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", contactId=").append(contactId);
        sb.append(", lastModUid=").append(lastModUid);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_activity_contact_relation
     *
     * @param entity
     */
    public static boolean isValid(KhActivityContactRelationEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}