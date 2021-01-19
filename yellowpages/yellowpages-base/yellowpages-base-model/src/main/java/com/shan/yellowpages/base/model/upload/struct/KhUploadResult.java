//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shan.yellowpages.base.model.upload.struct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author bruce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhUploadResult implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final short FILE_TYPE_DEFAULT = 0;

    public static final short FILE_TYPE_IMAGE = 10;
    public static final short FILE_TYPE_VIDEO = 20;
    private String resUrl;


    public static boolean isValid(KhUploadResult entity) {
        boolean result = false;
        if (entity != null && !StringUtils.isBlank(entity.getResUrl())) {
            result = true;
        }

        return result;
    }

//    public String toString() {
//        return "KhUploadResult{resUrl='" + this.resUrl + '\'' + '}';
//    }
}
