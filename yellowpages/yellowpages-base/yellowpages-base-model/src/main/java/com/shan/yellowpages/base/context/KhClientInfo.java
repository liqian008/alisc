package com.shan.yellowpages.base.context;

import java.io.Serializable;

/**
 * Hlmy 客户端对象
 * 含接入信息对象 & 设备信息对象
 *
 * @author bruce
 */
public class KhClientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 1758 客户端接入对象
     */
    private KhAccessInfoStruct accessInfo;

    /**
     * 客户端设备信息对象
     */
    private KhDeviceInfoStruct deviceInfo;

    public KhClientInfo() {
        super();
    }

    public KhClientInfo(KhAccessInfoStruct accessInfo, KhDeviceInfoStruct deviceInfo) {
        super();
        this.accessInfo = accessInfo;
        this.deviceInfo = deviceInfo;
    }

    private KhClientInfo(Builder builder) {
        setAccessInfo(builder.accessInfo);
        setDeviceInfo(builder.deviceInfo);
    }

    public KhDeviceInfoStruct getDeviceInfo() {
        return deviceInfo;
    }

    public KhAccessInfoStruct getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(KhAccessInfoStruct accessInfo) {
        this.accessInfo = accessInfo;
    }

    public void setDeviceInfo(KhDeviceInfoStruct deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public String toString() {
        return "KhClientInfo [acceessInfo=" + accessInfo + ", deviceInfo=" + deviceInfo + "]";
    }

    /**
     * 判断实体是否可用
     */
    public static boolean isValid(KhClientInfo entity) {
        boolean result = false;

        if (entity != null) {
            result = true;
        }

        return result;
    }

    /**
     * Builder
     */
    public static final class Builder {

        private KhAccessInfoStruct accessInfo;

        private KhDeviceInfoStruct deviceInfo;

        public Builder() {

        }

        public Builder accessInfo(KhAccessInfoStruct val) {
            accessInfo = val;
            return this;
        }

        public Builder deviceInfo(KhDeviceInfoStruct val) {
            deviceInfo = val;
            return this;
        }

        public KhClientInfo build() {
            return new KhClientInfo(this);
        }

    }

}
