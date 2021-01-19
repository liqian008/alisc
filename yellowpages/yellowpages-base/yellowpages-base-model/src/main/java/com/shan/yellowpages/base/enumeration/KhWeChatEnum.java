package com.shan.yellowpages.base.enumeration;

/**
 * 微信枚举
 * 
 * @author bruce
 *
 */
public class KhWeChatEnum {

	/**
	 * 微信接收及回复内容枚举
	 * 
	 * @author bruce
	 */
	public enum WxTypeEnum {

		// 微信交互
		TEXT((short) 0, "text", "文本"),

		NEWS((short) 1, "news", "图文"),

		// 自定义多图文枚举类型
		MULTI_NEWS((short) 2, "multi_news", "多图文"),

		IMAGE((short) 3, "image", "图片"),

		VOICE((short) 4, "voice", "声音"),

		VIDEO((short) 5, "video", "视频"),

		MUSIC((short) 6, "music", "音乐"),

		SUBSCRIBE((short) 10, "subscribe", "关注"),

		UNSUBSCRIBE((short) 11, "unsubscribe", "取消关注"),

		CLICK((short) 12, "click", "点击菜单拉取"),

		VIEW((short) 13, "view", "点击菜单跳转"),

		SCAN((short) 14, "scan", "扫瞄二维码"),

		LOCATION((short) 15, "location", "位置"),

		RESUBSCRIBE((short) 16, "resubscribe", "重复关注");

		private final Short type;

		private final String messageType;

		private final String description;

		WxTypeEnum(Short type, String messageType, String description) {
			this.type = type;
			this.messageType = messageType;
			this.description = description;
		}

		public static WxTypeEnum valueOf(Short type) {
			WxTypeEnum[] enumArray = WxTypeEnum.values();
			for (WxTypeEnum enumData : enumArray) {
				if (type != null && type.equals(enumData.type)) {
					return enumData;
				}
			}
			return null;
		}

		public Short getType() {
			return type;
		}

		public String getMessageType() {
			return messageType;
		}

		public String getDescription() {
			return description;
		}

	}

	public enum WxKeyTypeEnum {

		// 事件类型
		TEXT((short) 0, "text", "文本"),

		SUBSCRIBE((short) 10, "subscribe", "关注"),

		CLICK((short) 12, "click", "点击菜单拉取");

		private final Short keyType;

		private final String messageType;

		private final String description;

		WxKeyTypeEnum(Short keyType, String messageType, String description) {
			this.keyType = keyType;
			this.messageType = messageType;
			this.description = description;
		}

		public static WxKeyTypeEnum valueOf(Short type) {
			WxKeyTypeEnum[] enumArray = WxKeyTypeEnum.values();
			for (WxKeyTypeEnum enumData : enumArray) {
				if (type != null && type.equals(enumData.keyType)) {
					return enumData;
				}
			}
			return null;
		}

		public Short getKeyType() {
			return keyType;
		}

		public String getMessageType() {
			return messageType;
		}

		public String getDescription() {
			return description;
		}

	}

	/**
	 * 消息关键字匹配模式枚举
	 * 
	 * @author bruce
	 *
	 */
	public enum WxMatchModeEnum {

		// 关键字匹配模式
		EQUAL((short) 0, "equal", "完全匹配"),

		CONTAIN((short) 1, "contain", "不完全匹配");

		private final Short type;

		private final String matchMode;

		private final String description;

		WxMatchModeEnum(Short type, String matchMode, String description) {
			this.type = type;
			this.matchMode = matchMode;
			this.description = description;
		}

		public static WxMatchModeEnum valueOf(Short type) {
			WxMatchModeEnum[] enumArray = WxMatchModeEnum.values();
			for (WxMatchModeEnum enumData : enumArray) {
				if (type != null && type.equals(enumData.type)) {
					return enumData;
				}
			}
			return null;
		}

		public Short getType() {
			return type;
		}

		public String getMatchMode() {
			return matchMode;
		}

		public String getDescription() {
			return description;
		}

	}

	/**
	 * 回复内容的模式枚举
	 * 
	 * @author bruce
	 *
	 */
	public enum WxReplyModeEnum {

		REPLY_ALL((short) 0, "reply_all", "全部回复"),

		RANDOM_ONE((short) 1, "random_one", "随机回复");

		private final Short type;

		private final String replyMode;

		private final String description;

		WxReplyModeEnum(Short type, String replyMode, String description) {
			this.type = type;
			this.replyMode = replyMode;
			this.description = description;
		}

		public static WxReplyModeEnum valueOf(Short type) {
			WxReplyModeEnum[] enumArray = WxReplyModeEnum.values();
			for (WxReplyModeEnum enumData : enumArray) {
				if (type != null && type.equals(enumData.type)) {
					return enumData;
				}
			}
			return null;
		}

		public Short getType() {
			return type;
		}

		public String getReplyMode() {
			return replyMode;
		}

		public String getDescription() {
			return description;
		}

	}

}
