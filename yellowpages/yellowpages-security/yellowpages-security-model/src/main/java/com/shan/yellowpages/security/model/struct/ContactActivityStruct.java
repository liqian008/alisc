package com.shan.yellowpages.security.model.struct;

import com.shan.yellowpages.security.model.KhActivityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 联系人对应的活动vo
 * @author bruce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactActivityStruct implements Serializable {

    /** 显示指定serialVersionUID生成方式 */
    private static final long serialVersionUID = 1L;

    private boolean checked = false;

//    private String checkedStr = "";

    private Integer activityId;

    private String name;


    public void setChecked(boolean checked) {
        this.checked = checked;
//        checkedStr = "checked";
    }

    /**
     * 转换
     * @param activityEntity
     * @return
     */
    public static ContactActivityStruct convert(KhActivityEntity activityEntity){

        ContactActivityStruct result = null;
        if(KhActivityEntity.isValid(activityEntity)){
            result = new ContactActivityStruct();
            result.setActivityId(activityEntity.getId());
            result.setName(activityEntity.getName());
        }

        return result;

    }


}