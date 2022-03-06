package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动表
 * KhCountryEntity
 * 数据库表：kh_country
 */
public class KhCountryEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_country.id
     */
    private Integer id;

    /**
     * 国家编码，如CN，US
     * 表字段 : kh_country.code
     */
    private String code;

    /**
     * 英文名称
     * 表字段 : kh_country.en_name
     */
    private String enName;

    /**
     * 中文名称
     * 表字段 : kh_country.cn_name
     */
    private String cnName;

    /**
     * 
     * 表字段 : kh_country.status
     */
    private Short status;

    /**
     * 
     * 表字段 : kh_country.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_country.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhCountryEntity EMPTY_DATA = new KhCountryEntity();

    /**
     * 获取  字段:kh_country.id
     *
     * @return kh_country.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_country.id
     *
     * @param id the value for kh_country.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 国家编码，如CN，US 字段:kh_country.code
     *
     * @return kh_country.code, 国家编码，如CN，US
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 国家编码，如CN，US 字段:kh_country.code
     *
     * @param code the value for kh_country.code, 国家编码，如CN，US
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 英文名称 字段:kh_country.en_name
     *
     * @return kh_country.en_name, 英文名称
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置 英文名称 字段:kh_country.en_name
     *
     * @param enName the value for kh_country.en_name, 英文名称
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取 中文名称 字段:kh_country.cn_name
     *
     * @return kh_country.cn_name, 中文名称
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置 中文名称 字段:kh_country.cn_name
     *
     * @param cnName the value for kh_country.cn_name, 中文名称
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 获取  字段:kh_country.status
     *
     * @return kh_country.status, 
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置  字段:kh_country.status
     *
     * @param status the value for kh_country.status, 
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取  字段:kh_country.create_time
     *
     * @return kh_country.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_country.create_time
     *
     * @param createTime the value for kh_country.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_country.update_time
     *
     * @return kh_country.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_country.update_time
     *
     * @param updateTime the value for kh_country.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_country
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", enName=").append(enName);
        sb.append(", cnName=").append(cnName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_country
     *
     * @param entity
     */
    public static boolean isValid(KhCountryEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}