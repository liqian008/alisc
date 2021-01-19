package com.shan.yellowpages.base.utils.network;

import org.apache.commons.lang3.StringUtils;

import com.shan.yellowpages.base.context.KhAccessInfoStruct;
import com.shan.yellowpages.base.context.KhClientInfo;
import com.shan.yellowpages.base.context.KhDeviceInfoStruct;
import com.shan.yellowpages.base.enumeration.LoginConfigEnum;

/**
 * UserAgent 相关工具类
 *
 * @author bruce
 * @version 1.0
 */
public abstract class UserAgentUtil {

    /**
     * 根据 UA 获取平台信息
     *
     * @param userAgent userAgent
     * @return {@link LoginConfigEnum.PlatformEnum}
     */
    public static LoginConfigEnum.PlatformEnum getPlat(String userAgent) {

        if (userAgent == null) {
            return LoginConfigEnum.PlatformEnum.PC_WINDOWS;
        }

        LoginConfigEnum.PlatformEnum result;
        String userAgentTemp = userAgent.toLowerCase();

        if (userAgentTemp.contains("android")) {
            result = LoginConfigEnum.PlatformEnum.MOBILE_ANDROID;
        } else if (userAgentTemp.contains("iphone") || userAgentTemp.contains("ipad")
                || userAgentTemp.contains("ipod")) {
            result = LoginConfigEnum.PlatformEnum.MOBILE_IOS;
        } else {
            result = LoginConfigEnum.PlatformEnum.PC_WINDOWS;
        }

        return result;
    }

    /**
     * 根据 UA 获取客户端信息
     *
     * @param userAgent userAgent
     * @return {@link LoginConfigEnum.ClientEnum}
     */
    public static LoginConfigEnum.ClientEnum getClient(String userAgent) {
        if (userAgent == null) {
            return LoginConfigEnum.ClientEnum.BROWER;
        }

        LoginConfigEnum.ClientEnum result;
        String userAgentTemp = userAgent.toLowerCase();

        if (userAgentTemp.contains("hlmy_vspace+")) {
            result = LoginConfigEnum.ClientEnum.VSPACE;
        } else if (userAgentTemp.contains("dwjia")) {
            result = LoginConfigEnum.ClientEnum.HLMY1758;
        } else if (userAgentTemp.contains("micromessenger")) {
            result = LoginConfigEnum.ClientEnum.WECHAT;
        } else if (userAgentTemp.contains("qq")) {
            result = LoginConfigEnum.ClientEnum.QQ;
        } else {
            result = LoginConfigEnum.ClientEnum.BROWER;
        }

        return result;
    }

    /**
     * UA demo
     * <p>
     * Mozilla/5.0 (Linux; Android 9; Android SDK built for x86 Build/PSR1.180720.075; wv)
     * AppleWebKit/537.36 (KHTML, like Gecko)
     * Version/4.0
     * Chrome/69.0.3497.100
     * Mobile Safari/537.36
     * hlmy_dwjia+/40000
     * Hlmy_Vgame/40000 (AppKey/4f992c1d186a5ec12d2c21efed4ae858; VersionCode/40000; Chn/self; Brand/google; Model/Android SDK built for x86; ScreenWidth/1080; ScreenHeight/1794; OsVersion/9)
     *
     * @param userAgent ua
     * @return {@link KhClientInfo}
     */
    public static KhClientInfo getHlmyClientInfoFromClientUa(String userAgent) {// TODO 应该重构

        try {
            // 1. 自定义信息以 Hlmy_Vgame/ 开头, 获取 Hlmy_Vgame 之后括号的内容
            String hlmy_vgame = "Hlmy_Vgame/";

            int hlmy_vgame_i = userAgent.indexOf(hlmy_vgame);
            String substring = userAgent.substring(hlmy_vgame_i + hlmy_vgame.length());

            String str = "(";
            int begin = substring.indexOf(str);
            begin += str.length();
            int end = substring.lastIndexOf(")");

            String content = substring.substring(begin, end);

            // 2. 获取括号里面的内容, 获取对应的 key 和 value
            String[] split = content.split("; ");

            KhClientInfo khClientInfo = new KhClientInfo();

            KhAccessInfoStruct accessInfo = new KhAccessInfoStruct();
            KhDeviceInfoStruct deviceInfo = new KhDeviceInfoStruct();

            khClientInfo.setAccessInfo(accessInfo);
            khClientInfo.setDeviceInfo(deviceInfo);

            String searchStr = "/";
            for (String s : split) {
                //
                String[] temp = s.split(searchStr);

                //
                String key = temp[0];

                int i = StringUtils.ordinalIndexOf(s, searchStr, 1);
                i += searchStr.length();
                String value = s.substring(i);

                if ("AppKey".equals(key)) {
                    accessInfo.setClientAppKey(value);
                } else if ("VersionCode".equals(key)) {
                    accessInfo.setClientVersionCode(Integer.valueOf(value));
                } else if ("Chn".equals(key)) {
                    accessInfo.setClientChn(value);
                } else if ("Brand".equals(key)) {
                    deviceInfo.setDeviceBrand(value);
                } else if ("Model".equals(key)) {
                    deviceInfo.setModel(value);
                } else if ("ScreenWidth".equals(key)) {
                    deviceInfo.setScreenWidth(Short.valueOf(value));
                } else if ("ScreenHeight".equals(key)) {
                    deviceInfo.setScreenHeight(Short.valueOf(value));
                } else if ("OsVersion".equals(key)) {
                    deviceInfo.setOsVersion(value);
                }
                // System.out.println("key = " + key + ", value = " + value);
            }
            return khClientInfo;
        } catch (Exception e) {
            // System.out.println();
        }

        return null;
    }

}
