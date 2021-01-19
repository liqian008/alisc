package com.shan.yellowpages.base.enumeration;

/**
 * 性别的枚举值
 * @author xuejw
 */
public enum GenderEnum {

	UNKNOWN("未知", (short) 0),
	MALE("男", (short) 1),
	FEMALE("女", (short) 2);

	private String name;

	private short status;

	GenderEnum(String name, short status) {
		this.name = name;
		this.status = status;
	}

	public static String getName(Short status) {
		if (status != null) {
			for (GenderEnum e : GenderEnum.values()) {
				if (e.getStatus() == status) {
					return e.name;
				}
			}
		}
		return UNKNOWN.name;
	}

	public String getName() {
		return name;
	}

	public short getStatus() {
		return status;
	}

}
