package com.shan.yellowpages.base.enumeration;

/**
 * 第三方授权配置
 */
public class OpenAppEnum {

	/**
	 * 第三方授权类型
	 */
	public enum OpenAppTypeEnum {

		WEIXIN_WE_CHAT((short)0, "微信公众号"),
        WEIXIN_APP((short)10, "微信APP应用"),
        WEIXIN_PC((short)20, "微信PCWeb应用"), 
        WEIXIN_MINI_PROGRAM((short)30, "微信小程序"), 
        WEIXIN_MINI_GAME((short)40, "微信小游戏"),
		QQ_PC((short)50, "QQ网站应用"), 
        WEIBO_PC((short)60, "微博PC应用"), 
        MOBILE((short)70, "手机"), 
        QQ_MCH((short)100, "QQ钱包");

		private final short value;

		private final String name;

		OpenAppTypeEnum(short value, String name) {
			this.value = value;
			this.name = name;
		}

        public short getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
	}
}
