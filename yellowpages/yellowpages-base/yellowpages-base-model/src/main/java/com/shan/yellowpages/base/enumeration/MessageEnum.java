package com.shan.yellowpages.base.enumeration;

/**
 * 消息enum
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-10-08 12:30
 */
public class MessageEnum {

	public enum MsgTypeEnum {

		IMAGE("image", "[图片]", "发来一张图片"),
		TEXT("text", "[文本]", "发来一条消息"),

		;

		private String msgType;
		private String senderDiaplay;
		private String receiverDisplay;

		MsgTypeEnum(String msgType, String senderDiaplay, String receiverDisplay) {
			this.msgType = msgType;
			this.senderDiaplay = senderDiaplay;
			this.receiverDisplay = receiverDisplay;
		}

		public String getMsgType() {
			return msgType;
		}

		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public String getSenderDiaplay() {
			return senderDiaplay;
		}

		public void setSenderDiaplay(String senderDiaplay) {
			this.senderDiaplay = senderDiaplay;
		}

		public String getReceiverDisplay() {
			return receiverDisplay;
		}

		public void setReceiverDisplay(String receiverDisplay) {
			this.receiverDisplay = receiverDisplay;
		}
	}


}
