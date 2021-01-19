package com.shan.yellowpages.base.enumeration;

/**
 * 登录配置相关
 */
public class LoginConfigEnum {

    /**
     * 平台类型枚举
     */
    public enum PlatformEnum {

        PC_WINDOWS(1, "PC_WINDOWS"),

        // PC_MAC(2, "PC_MAC"),// 暂时不区分这个类型, 以后可以考虑放开

        MOBILE_ANDROID(3, "MOBILE_ANDROID"),

        MOBILE_IOS(4, "MOBILE_IOS");

        private final int type;

        private final String desc;

        PlatformEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

    }

    /**
     * H5所在的客户端环境
     */
    public enum ClientEnum {

        BROWER(1, "BROWER"),    //浏览器环境（包含pc & 移动端）

        HLMY1758(2, "HLMY1758"), //1758客户端环境

        WECHAT(3, "WECHAT"),    //微信客户端环境

        QQ(4, "QQ"),    //QQ客户端环境

        WEIBO(5, "WEIBO"),  //新浪微博客户端环境

        VSPACE(6, "VSPACE"),    //V次元客户端环境

        OTHER(20, "OTHER");  //其他客户端环境，只是定义，没有使用到

        private final int type;

        private final String desc;

        ClientEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

    }

    /**
     * 登录类型
     */
    public enum LoginTypeEnum {

        WEIXIN(1, "WEIXIN"),    //微信登录

        QQ(2, "QQ"),

        WEIBO(3, "WEIBO"),

        MOBILE(4, "MOBILE");

        private final int type;

        private final String desc;

        LoginTypeEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

    }

    /**
     * 登录类型
     */
    public enum UrlTypeEnum {

        URL(1, "URL"),  //url跳转方式

        NATIVE(2, "NATIVE");    //native方式

        private final int type;

        private final String desc;

        UrlTypeEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

    }

}
