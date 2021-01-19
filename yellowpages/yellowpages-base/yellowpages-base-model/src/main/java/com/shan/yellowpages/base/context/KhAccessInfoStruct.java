package com.shan.yellowpages.base.context;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Hlmy 客户端接入相关信息
 *
 * @author bruce
 */
public class KhAccessInfoStruct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端请求的 appKey
     * 客户端下, native 和 webview 使用同一个 clientAppKey [靠 fromNative 加以区别]
     */
    private String clientAppKey;

    /**
     * 客户端请求的 secretKey
     */
    private String clientSecretKey;

    /**
     * 客户端请求的 name
     */
    private String clientName;

    /**
     * 是否来自原生客户端请求
     * <p>
     * 默认为 false
     * 客户端原生请求情况下为 true; h5, pc, 及 hybird 的 webview 情况下为 false, 小程序为 false.
     */
    private boolean fromNative = false;

    /**
     * 客户端包的渠道号, 目前仅在客户端中有效
     */
    private String clientChn;

    /**
     * 当前客户端版本号, 格式为 : X.Y.Z, 形如 V3.6.2
     */
    private String clientVersionName;

    /**
     * 当前客户端版本号, int 版本的 version, 如 3.6.2 对应 362
     */
    private int clientVersionCode;

    public KhAccessInfoStruct() {
        super();
    }

    public KhAccessInfoStruct(String clientName, String clientAppKey, String clientSecretKey) {
        super();
        this.clientName = clientName;
        this.clientAppKey = clientAppKey;
        this.clientSecretKey = clientSecretKey;
    }

    public KhAccessInfoStruct(String clientName, String clientAppKey, String clientSecretKey, boolean fromNative) {
        super();
        this.clientName = clientName;
        this.clientAppKey = clientAppKey;
        this.clientSecretKey = clientSecretKey;
        this.fromNative = fromNative;
    }

    private KhAccessInfoStruct(Builder builder) {
        setClientAppKey(builder.clientAppKey);
        setClientChn(builder.clientChn);
        setClientName(builder.clientName);
        setClientSecretKey(builder.clientSecretKey);
        setClientVersionCode(builder.clientVersionCode);
        setClientVersionName(builder.clientVersionName);
        setFromNative(builder.fromNative);
    }

    public String getClientAppKey() {
        return clientAppKey;
    }

    public void setClientAppKey(String clientAppKey) {
        this.clientAppKey = clientAppKey;
    }

    public String getClientSecretKey() {
        return clientSecretKey;
    }

    public void setClientSecretKey(String clientSecretKey) {
        this.clientSecretKey = clientSecretKey;
    }

    public String getClientChn() {
        return clientChn;
    }

    public void setClientChn(String clientChn) {
        this.clientChn = clientChn;
    }

    public String getClientVersionName() {
        return clientVersionName;
    }

    public void setClientVersionName(String clientVersionName) {
        this.clientVersionName = clientVersionName;
    }

    public int getClientVersionCode() {
        return clientVersionCode;
    }

    public void setClientVersionCode(int clientVersionCode) {
        this.clientVersionCode = clientVersionCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean isFromNative() {
        return fromNative;
    }

    public void setFromNative(boolean fromNative) {
        this.fromNative = fromNative;
    }

    @Override
    public String toString() {
        return "KhAccessInfoStruct [clientAppKey=" + clientAppKey + ", clientSecretKey=" + clientSecretKey
                + ", clientName=" + clientName + ", fromNative=" + fromNative + ", clientChn=" + clientChn
                + ", clientVersionName=" + clientVersionName + ", clientVersionCode=" + clientVersionCode + "]";
    }

    /**
     * 判断实体是否可用
     */
    public static boolean isValid(KhAccessInfoStruct entity) {
        boolean result = false;

        if (entity != null && StringUtils.isNotBlank(entity.getClientAppKey())) {
            result = true;
        }

        return result;
    }

    /**
     * Builder
     */
    public static final class Builder {

        private String clientName;

        private String clientAppKey;

        private String clientSecretKey;

        private String clientChn;

        private String clientVersionName;

        private int clientVersionCode;

        private boolean fromNative;

        public Builder() {

        }

        public Builder clientName(String val) {
            clientName = val;
            return this;
        }

        public Builder clientAppKey(String val) {
            clientAppKey = val;
            return this;
        }

        public Builder clientSecretKey(String val) {
            clientSecretKey = val;
            return this;
        }

        public Builder clientChn(String val) {
            clientChn = val;
            return this;
        }

        public Builder clientVersionName(String val) {
            clientVersionName = val;
            return this;
        }

        public Builder clientVersionCode(int val) {
            clientVersionCode = val;
            return this;
        }

        public Builder fromNative(boolean val) {
            fromNative = val;
            return this;
        }

        public KhAccessInfoStruct build() {
            return new KhAccessInfoStruct(this);
        }

    }

}
