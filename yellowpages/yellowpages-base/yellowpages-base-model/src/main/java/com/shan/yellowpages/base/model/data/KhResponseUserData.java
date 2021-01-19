package com.shan.yellowpages.base.model.data;

import java.io.Serializable;

/**
 * 客户端api的response内容对象
 *
 * 背景：客户端或小程序可能随时需要获取用户的登录态，所以需要在reponse中，增加用户的数据
 *
 * 考虑在KhResponseResult中增加login状态，废弃本类
 *
 * 客户端使用的data数据，均需要继承自该类
 *
 *
 */
@Deprecated
public class KhResponseUserData implements Serializable{

    private static final long serialVersionUID = 1L;


    protected KhResponseUserTokenDataStruct userData;

    public KhResponseUserData() {
    }

    public KhResponseUserData(KhResponseUserTokenDataStruct userData) {
        this.userData = userData;
    }

    public KhResponseUserTokenDataStruct getUserData() {
        return userData;
    }

    public void setUserData(KhResponseUserTokenDataStruct userData) {
        this.userData = userData;
    }

    @Override public String toString() {
        return "KhResponseUserData{" + "userData=" + userData + '}';
    }


    /**
     * 用户身份&token数据
     */
    public static class KhResponseUserTokenDataStruct implements Serializable{

        private static final long serialVersionUID = 1L;

        /** 当前用户id */
        private long userId;

        /** 新token，通常在token即将过期时返回 */
        private String refreshToken;

        public KhResponseUserTokenDataStruct() {
        }

        public KhResponseUserTokenDataStruct(long userId, String refreshToken) {
            this.userId = userId;
            this.refreshToken = refreshToken;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        @Override public String toString() {
            return "KhResponseUserData{" + "userId=" + userId + ", refreshToken='" + refreshToken + '\'' + '}';
        }

    }


}