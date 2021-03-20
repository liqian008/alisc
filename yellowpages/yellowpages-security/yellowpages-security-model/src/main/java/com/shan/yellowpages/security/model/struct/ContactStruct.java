package com.shan.yellowpages.security.model.struct;

import com.shan.yellowpages.security.model.KhContactEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactStruct implements Serializable {

    /** 显示指定serialVersionUID生成方式 */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final ContactStruct EMPTY_DATA = new ContactStruct();

    /** 最后操作人 */
    private AdminUserStruct lastModifyUser;

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
     * 身份证
     */
    private String identity;
    /**
     * 个人简介
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


    private String nationalityName;

    private String provinceName;

    private String cityName;


    /**
     * 性别，1男2女0未知
     * 表字段 : kh_contact.gender
     */
    private Short gender;

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
     * 判断实体是否合法,kh_contact
     *
     * @param entity
     */
    public static boolean isValid(ContactStruct entity) {
        boolean result = false;
        if (entity != null && entity.getId() != null && entity.getId() > 0) {
            result = true;
        }
        return result;
    }

    /** 默认头像 */
    public static final String AVATAR_DEFAULT = "/static/images/demo/users/default_avatar_2.jpg";

    /**
     *
     * @param entity
     * @return
     */
    public static ContactStruct convert(KhContactEntity entity) {

        ContactStruct result = null;
        if(KhContactEntity.isValid(entity)){
            result = new ContactStruct();

            String avatar = entity.getAvatar();
            if(StringUtils.isBlank(avatar)){
                avatar = AVATAR_DEFAULT;
            }
            result.setAvatar(avatar);
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setEmail(entity.getEmail());
            result.setMobile(entity.getMobile());
            result.setFax(entity.getFax());
            result.setTelphone(entity.getTelphone());
            result.setAddress(entity.getAddress());
            result.setPostcode(entity.getPostcode());
            result.setCompany(entity.getCompany());
            result.setCompanyEn(entity.getCompanyEn());
            result.setCompanyId(entity.getCompanyId());
            result.setCompanyWebsite(entity.getCompanyWebsite());
            result.setLastModUid(entity.getLastModUid());
            result.setTitle(entity.getTitle());
            result.setIndustry(entity.getIndustry());
            result.setDepartment(entity.getDepartment());
            result.setRemark(entity.getRemark());
            result.setIdentity(entity.getIdentity());
            result.setResume(entity.getResume());
            result.setGender(entity.getGender());
            result.setNationality(entity.getNationality());
            result.setNationalityName(entity.getNationalityName());
            result.setProvinceName(entity.getProvinceName());
            result.setCityName(entity.getCityName());
            result.setStatus(entity.getStatus());
            result.setCreateTime(entity.getCreateTime());
            result.setUpdateTime(entity.getUpdateTime());
        }
        return result;
    }




}