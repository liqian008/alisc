package com.shan.yellowpages.security.model.struct;

import com.shan.yellowpages.security.model.KhActivityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * dto
 * @author bruce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityStruct implements Serializable {

    /** 显示指定serialVersionUID生成方式 */
    private static final long serialVersionUID = 1L;

    /**
     * 构造一个空对象，目前用于击穿缓存查询DB不得时返回，避免频繁缓存
     */
    public static final ActivityStruct EMPTY_DATA = new ActivityStruct();

    /** 最后操作人 */
    private AdminUserStruct lastModifyUser;


    private Integer id;

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private Integer lastModUid;

    private Short status;

    private Date createTime;

    private Date updateTime;



    /**
     * 判断实体是否合法,kh_contact
     *
     * @param entity
     */
    public static boolean isValid(ActivityStruct entity) {
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
    public static ActivityStruct convert(KhActivityEntity entity) {

        ActivityStruct result = null;
        if(KhActivityEntity.isValid(entity)){
            result = new ActivityStruct();

            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setDescription(entity.getDescription());
            result.setLastModUid(entity.getLastModUid());
            result.setStatus(entity.getStatus());
            result.setCreateTime(entity.getCreateTime());
            result.setUpdateTime(entity.getUpdateTime());
        }
        return result;
    }


}