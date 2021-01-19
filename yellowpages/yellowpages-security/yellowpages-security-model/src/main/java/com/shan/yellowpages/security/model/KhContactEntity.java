package com.shan.yellowpages.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 联系人信息表
 * KhContactEntity
 * 数据库表：kh_contact
 */
public class KhContactEntity implements Serializable {
    /**
     * 
     * 表字段 : kh_contact.id
     */
    private Integer id;

    /**
     * 姓名
     * 表字段 : kh_contact.name
     */
    private String name;

    /**
     * 头像
     * 表字段 : kh_contact.avatar
     */
    private String avatar;

    /**
     * 手机号
     * 表字段 : kh_contact.mobile
     */
    private String mobile;

    /**
     * 座机电话
     * 表字段 : kh_contact.telphone
     */
    private String telphone;

    /**
     * 出生日期
     * 表字段 : kh_contact.birth_date
     */
    private Date birthDate;

    /**
     * 备注
     * 表字段 : kh_contact.remark
     */
    private String remark;

    /**
     * 个人简介
     * 表字段 : kh_contact.resume
     */
    private String resume;

    /**
     * 公司id
     * 表字段 : kh_contact.company_id
     */
    private Integer companyId;

    /**
     * 公司名称
     * 表字段 : kh_contact.company
     */
    private String company;

    /**
     * 公司英文名称
     * 表字段 : kh_contact.company_en
     */
    private String companyEn;

    /**
     * 公司网址
     * 表字段 : kh_contact.company_website
     */
    private String companyWebsite;

    /**
     * 职位
     * 表字段 : kh_contact.title
     */
    private String title;

    /**
     * 部门
     * 表字段 : kh_contact.department
     */
    private String department;

    /**
     * email
     * 表字段 : kh_contact.email
     */
    private String email;

    /**
     * fax
     * 表字段 : kh_contact.fax
     */
    private String fax;

    /**
     * 地址
     * 表字段 : kh_contact.address
     */
    private String address;

    /**
     * 邮编
     * 表字段 : kh_contact.postcode
     */
    private String postcode;

    /**
     * 所属行业
     * 表字段 : kh_contact.industry
     */
    private String industry;

    /**
     * 国籍，0未知
     * 表字段 : kh_contact.nationality
     */
    private Short nationality;

    /**
     * 性别，1男2女0未知
     * 表字段 : kh_contact.gender
     */
    private Short gender;

    /**
     * 身份证号码
     * 表字段 : kh_contact.identity
     */
    private String identity;

    /**
     * 最后操作人
     * 表字段 : kh_contact.last_mod_uid
     */
    private Integer lastModUid;

    /**
     * 
     * 表字段 : kh_contact.status
     */
    private Short status;

    /**
     * 
     * 表字段 : kh_contact.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : kh_contact.update_time
     */
    private Date updateTime;

    /**
     * 显示指定serialVersionUID生成方式
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final KhContactEntity EMPTY_DATA = new KhContactEntity();

    /**
     * 获取  字段:kh_contact.id
     *
     * @return kh_contact.id, 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置  字段:kh_contact.id
     *
     * @param id the value for kh_contact.id, 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 姓名 字段:kh_contact.name
     *
     * @return kh_contact.name, 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 姓名 字段:kh_contact.name
     *
     * @param name the value for kh_contact.name, 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 头像 字段:kh_contact.avatar
     *
     * @return kh_contact.avatar, 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置 头像 字段:kh_contact.avatar
     *
     * @param avatar the value for kh_contact.avatar, 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取 手机号 字段:kh_contact.mobile
     *
     * @return kh_contact.mobile, 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置 手机号 字段:kh_contact.mobile
     *
     * @param mobile the value for kh_contact.mobile, 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取 座机电话 字段:kh_contact.telphone
     *
     * @return kh_contact.telphone, 座机电话
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设置 座机电话 字段:kh_contact.telphone
     *
     * @param telphone the value for kh_contact.telphone, 座机电话
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * 获取 出生日期 字段:kh_contact.birth_date
     *
     * @return kh_contact.birth_date, 出生日期
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 设置 出生日期 字段:kh_contact.birth_date
     *
     * @param birthDate the value for kh_contact.birth_date, 出生日期
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取 备注 字段:kh_contact.remark
     *
     * @return kh_contact.remark, 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 字段:kh_contact.remark
     *
     * @param remark the value for kh_contact.remark, 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 个人简介 字段:kh_contact.resume
     *
     * @return kh_contact.resume, 个人简介
     */
    public String getResume() {
        return resume;
    }

    /**
     * 设置 个人简介 字段:kh_contact.resume
     *
     * @param resume the value for kh_contact.resume, 个人简介
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * 获取 公司id 字段:kh_contact.company_id
     *
     * @return kh_contact.company_id, 公司id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置 公司id 字段:kh_contact.company_id
     *
     * @param companyId the value for kh_contact.company_id, 公司id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取 公司名称 字段:kh_contact.company
     *
     * @return kh_contact.company, 公司名称
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置 公司名称 字段:kh_contact.company
     *
     * @param company the value for kh_contact.company, 公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取 公司英文名称 字段:kh_contact.company_en
     *
     * @return kh_contact.company_en, 公司英文名称
     */
    public String getCompanyEn() {
        return companyEn;
    }

    /**
     * 设置 公司英文名称 字段:kh_contact.company_en
     *
     * @param companyEn the value for kh_contact.company_en, 公司英文名称
     */
    public void setCompanyEn(String companyEn) {
        this.companyEn = companyEn;
    }

    /**
     * 获取 公司网址 字段:kh_contact.company_website
     *
     * @return kh_contact.company_website, 公司网址
     */
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    /**
     * 设置 公司网址 字段:kh_contact.company_website
     *
     * @param companyWebsite the value for kh_contact.company_website, 公司网址
     */
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    /**
     * 获取 职位 字段:kh_contact.title
     *
     * @return kh_contact.title, 职位
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 职位 字段:kh_contact.title
     *
     * @param title the value for kh_contact.title, 职位
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 部门 字段:kh_contact.department
     *
     * @return kh_contact.department, 部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置 部门 字段:kh_contact.department
     *
     * @param department the value for kh_contact.department, 部门
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 获取 email 字段:kh_contact.email
     *
     * @return kh_contact.email, email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 email 字段:kh_contact.email
     *
     * @param email the value for kh_contact.email, email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取 fax 字段:kh_contact.fax
     *
     * @return kh_contact.fax, fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置 fax 字段:kh_contact.fax
     *
     * @param fax the value for kh_contact.fax, fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取 地址 字段:kh_contact.address
     *
     * @return kh_contact.address, 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 地址 字段:kh_contact.address
     *
     * @param address the value for kh_contact.address, 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取 邮编 字段:kh_contact.postcode
     *
     * @return kh_contact.postcode, 邮编
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置 邮编 字段:kh_contact.postcode
     *
     * @param postcode the value for kh_contact.postcode, 邮编
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取 所属行业 字段:kh_contact.industry
     *
     * @return kh_contact.industry, 所属行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 所属行业 字段:kh_contact.industry
     *
     * @param industry the value for kh_contact.industry, 所属行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取 国籍，0未知 字段:kh_contact.nationality
     *
     * @return kh_contact.nationality, 国籍，0未知
     */
    public Short getNationality() {
        return nationality;
    }

    /**
     * 设置 国籍，0未知 字段:kh_contact.nationality
     *
     * @param nationality the value for kh_contact.nationality, 国籍，0未知
     */
    public void setNationality(Short nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取 性别，1男2女0未知 字段:kh_contact.gender
     *
     * @return kh_contact.gender, 性别，1男2女0未知
     */
    public Short getGender() {
        return gender;
    }

    /**
     * 设置 性别，1男2女0未知 字段:kh_contact.gender
     *
     * @param gender the value for kh_contact.gender, 性别，1男2女0未知
     */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * 获取 身份证号码 字段:kh_contact.identity
     *
     * @return kh_contact.identity, 身份证号码
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置 身份证号码 字段:kh_contact.identity
     *
     * @param identity the value for kh_contact.identity, 身份证号码
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取 最后操作人 字段:kh_contact.last_mod_uid
     *
     * @return kh_contact.last_mod_uid, 最后操作人
     */
    public Integer getLastModUid() {
        return lastModUid;
    }

    /**
     * 设置 最后操作人 字段:kh_contact.last_mod_uid
     *
     * @param lastModUid the value for kh_contact.last_mod_uid, 最后操作人
     */
    public void setLastModUid(Integer lastModUid) {
        this.lastModUid = lastModUid;
    }

    /**
     * 获取  字段:kh_contact.status
     *
     * @return kh_contact.status, 
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置  字段:kh_contact.status
     *
     * @param status the value for kh_contact.status, 
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取  字段:kh_contact.create_time
     *
     * @return kh_contact.create_time, 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置  字段:kh_contact.create_time
     *
     * @param createTime the value for kh_contact.create_time, 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  字段:kh_contact.update_time
     *
     * @return kh_contact.update_time, 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置  字段:kh_contact.update_time
     *
     * @param updateTime the value for kh_contact.update_time, 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * ,kh_contact
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", avatar=").append(avatar);
        sb.append(", mobile=").append(mobile);
        sb.append(", telphone=").append(telphone);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", remark=").append(remark);
        sb.append(", resume=").append(resume);
        sb.append(", companyId=").append(companyId);
        sb.append(", company=").append(company);
        sb.append(", companyEn=").append(companyEn);
        sb.append(", companyWebsite=").append(companyWebsite);
        sb.append(", title=").append(title);
        sb.append(", department=").append(department);
        sb.append(", email=").append(email);
        sb.append(", fax=").append(fax);
        sb.append(", address=").append(address);
        sb.append(", postcode=").append(postcode);
        sb.append(", industry=").append(industry);
        sb.append(", nationality=").append(nationality);
        sb.append(", gender=").append(gender);
        sb.append(", identity=").append(identity);
        sb.append(", lastModUid=").append(lastModUid);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断实体是否合法,kh_contact
     *
     * @param entity
     */
    public static boolean isValid(KhContactEntity entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }
}