package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司信息表
 * KhCompanyEntity
 * 数据库表：kh_company
 */
public class KhCompanyEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_company.id
     */
    private Integer id;

    /**
     * 母公司id
     * 表字段 : kh_company.parent_id
     */
    private Integer parentId;

    /**
     * 公司名称
     * 表字段 : kh_company.name
     */
    private String name;

    /**
     * 英文名
     * 表字段 : kh_company.en_name
     */
    private String enName;

    /**
     * 公司地址
     * 表字段 : kh_company.address
     */
    private String address;

    /**
     * 公司网址
     * 表字段 : kh_company.website
     */
    private String website;

    /**
     * 
     * 表字段 : kh_company.status
     */
    private Short status;

    /**
     * 最后操作人
     * 表字段 : kh_company.last_mod_uid
     */
    private Integer lastModUid;

    /**
     * 
     * 表字段 : kh_company.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_company.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhCompanyEntity EMPTY_DATA = new KhCompanyEntity();

    /**
     * 获取  字段:kh_company.id
     *
     * @return kh_company.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_company.id
     *
     * @param id the value for kh_company.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 母公司id 字段:kh_company.parent_id
     *
     * @return kh_company.parent_id, 母公司id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置 母公司id 字段:kh_company.parent_id
     *
     * @param parentId the value for kh_company.parent_id, 母公司id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 公司名称 字段:kh_company.name
     *
     * @return kh_company.name, 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 公司名称 字段:kh_company.name
     *
     * @param name the value for kh_company.name, 公司名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 英文名 字段:kh_company.en_name
     *
     * @return kh_company.en_name, 英文名
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置 英文名 字段:kh_company.en_name
     *
     * @param enName the value for kh_company.en_name, 英文名
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取 公司地址 字段:kh_company.address
     *
     * @return kh_company.address, 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 公司地址 字段:kh_company.address
     *
     * @param address the value for kh_company.address, 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取 公司网址 字段:kh_company.website
     *
     * @return kh_company.website, 公司网址
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置 公司网址 字段:kh_company.website
     *
     * @param website the value for kh_company.website, 公司网址
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 获取  字段:kh_company.status
     *
     * @return kh_company.status, 
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置  字段:kh_company.status
     *
     * @param status the value for kh_company.status, 
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取 最后操作人 字段:kh_company.last_mod_uid
     *
     * @return kh_company.last_mod_uid, 最后操作人
     */
    public Integer getLastModUid() {
        return lastModUid;
    }

    /**
     * 设置 最后操作人 字段:kh_company.last_mod_uid
     *
     * @param lastModUid the value for kh_company.last_mod_uid, 最后操作人
     */
    public void setLastModUid(Integer lastModUid) {
        this.lastModUid = lastModUid;
    }

    /**
     * 获取  字段:kh_company.create_time
     *
     * @return kh_company.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_company.create_time
     *
     * @param createTime the value for kh_company.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_company.update_time
     *
     * @return kh_company.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_company.update_time
     *
     * @param updateTime the value for kh_company.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_company
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", enName=").append(enName);
        sb.append(", address=").append(address);
        sb.append(", website=").append(website);
        sb.append(", status=").append(status);
        sb.append(", lastModUid=").append(lastModUid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_company
     *
     * @param entity
     */
    public static boolean isValid(KhCompanyEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}