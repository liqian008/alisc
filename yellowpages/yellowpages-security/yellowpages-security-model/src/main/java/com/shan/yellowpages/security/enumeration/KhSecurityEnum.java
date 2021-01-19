package com.shan.yellowpages.security.enumeration;

/**
 * 权限管理配置enum
 * 
 * @author xuejw
 */
public class KhSecurityEnum {

	/**
	 * 用户类型型
	 */
	@Deprecated
	public enum UserTypeEnum {

		SUPER_ADMIN((short) 10, "超级管理员"),
        COMMON_ADMIN((short) 20, "普通管理员"),
        COMMON_USER((short) 30, "普通用户");

		private final short value;

		private final String name;

		UserTypeEnum(short value, String name) {
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
