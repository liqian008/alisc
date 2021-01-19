package com.shan.yellowpages.base.context;

import java.io.Serializable;

/**
 * Hlmy 客户端设备信息
 *
 * @author bruce
 */
public class KhDeviceInfoStruct implements Serializable {

    private static final long serialVersionUID = 1L;

//	/**
//	 * 客户端类型，0:web
//	 */
//	public static final short CLIENT_TYPE_WEB = 0;
//
//	/**
//	 * 客户端类型，1:APP
//	 */
//	public static final short CLIENT_TYPE_APP = 10;

    /**
     * 网络类型，未知
     */
    public static final String CLIENT_NETWORK_UNKNOWN = "UNKNOWN";

    /**
     * 网络类型，移动网络
     */
    public static final String CLIENT_NETWORK_MOBILE = "MONET";

    /**
     * 网络类型，WIFI
     */
    public static final String CLIENT_NETWORK_WIFI = "WIFI";

    /**
     * 客户端操作系统类型. 如 : android, iOS
     */
    private String osType;

    /**
     * 客户端操作系统版本. 如 8.0
     */
    private String osVersion;

    /**
     * 网络类型. 未知(UNKNOWN), 移动网络（MONET），wifi网络（WIFI），目前仅在移动客户端下能获取
     */
    private String network = CLIENT_NETWORK_UNKNOWN;

    /**
     * 客户端屏幕分辨率宽度，单位像素（目前仅在移动客户端下有效）
     */
    private Short screenWidth;

    /**
     * 客户端屏幕分辨率高度，单位像素（目前仅在移动客户端下有效）
     */
    private Short screenHeight;

    /**
     * 客户端设备id串号，可能用作识别客户端唯一信息（IOS下，唯一值早就不让用了，，即使用广告id也是需要单独申请权限的，而且应用中必须有明显的广告）
     */
    private String deviceId;

    /**
     * 客户端设备 id 串号. 可能用作识别客户端唯一信息
     */
    private String imei;

    /**
     * 客户端品牌，如 huawei
     */
    private String deviceBrand;

    /**
     * 客户端设备名称，如 mate20（目前仅在移动客户端下有效）
     */
    private String deviceName;

    private String model;

    public KhDeviceInfoStruct() {
        super();
    }

    private KhDeviceInfoStruct(Builder builder) {
        setOsType(builder.osType);
        setOsVersion(builder.osVersion);
        setNetwork(builder.network);
        setScreenWidth(builder.screenWidth);
        setScreenHeight(builder.screenHeight);
        setDeviceId(builder.deviceId);
        setImei(builder.imei);
        setDeviceBrand(builder.deviceBrand);
        setDeviceName(builder.deviceName);
        setModel(builder.model);
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Short getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Short screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Short getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Short screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "KhDeviceInfoStruct{" +
                "osType='" + osType + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", network='" + network + '\'' +
                ", screenWidth=" + screenWidth +
                ", screenHeight=" + screenHeight +
                ", deviceId='" + deviceId + '\'' +
                ", imei='" + imei + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    /**
     * 判断实体是否可用
     */
    public static boolean isValid(KhAccessInfoStruct entity) {
        boolean result = false;
        if (entity != null) {
            result = true;
        }
        return result;
    }

    public static final class Builder {

        private String osType;

        private String osVersion;

        private String network;

        private Short screenWidth;

        private Short screenHeight;

        private String deviceId;

        private String imei;

        private String deviceBrand;

        private String deviceName;

        private String model;

        public Builder() {

        }

        public Builder osType(String val) {
            osType = val;
            return this;
        }

        public Builder osVersion(String val) {
            osVersion = val;
            return this;
        }

        public Builder network(String val) {
            network = val;
            return this;
        }

        public Builder screenWidth(Short val) {
            screenWidth = val;
            return this;
        }

        public Builder screenHeight(Short val) {
            screenHeight = val;
            return this;
        }

        public Builder deviceId(String val) {
            deviceId = val;
            return this;
        }

        public Builder imei(String val) {
            imei = val;
            return this;
        }

        public Builder deviceName(String val) {
            deviceName = val;
            return this;
        }

        public Builder deviceBrand(String val) {
            deviceBrand = val;
            return this;
        }

        public Builder model(String val) {
            model = val;
            return this;
        }

        public KhDeviceInfoStruct build() {
            return new KhDeviceInfoStruct(this);
        }

    }

}