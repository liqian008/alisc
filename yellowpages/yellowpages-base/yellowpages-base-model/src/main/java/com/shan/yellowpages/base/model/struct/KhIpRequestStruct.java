package com.shan.yellowpages.base.model.struct;

import lombok.Data;

import java.io.Serializable;

/**
 * ip查询请求的对象
 *
 * api介绍： http://whois.pconline.com.cn/#tabs-1
 *
 * @author bruce
 */
@Data
public class KhIpRequestStruct implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ip */
	private String ip;
	/** level， =1/=2/=3分别代表只输出省名称/输出省市名称/输出省市区名称 */
	private short level;
	/** 输出格式，指定默认为json */
	private boolean json = true;

	private KhIpRequestStruct(Builder builder) {
		setIp(builder.ip);
		setLevel(builder.level);
		setJson(builder.json);
	}

	public static final class Builder {
		private String ip;
		private short level;
		private boolean json = true;

		public Builder() {
		}

		public Builder ip(String val) {
			ip = val;
			return this;
		}

		public Builder level(short val) {
			level = val;
			return this;
		}

		public Builder json(boolean val) {
			json = val;
			return this;
		}

		public KhIpRequestStruct build() {
			return new KhIpRequestStruct(this);
		}
	}
}
