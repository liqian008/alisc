package com.shan.yellowpages.base.model.cache.struct;

import java.io.Serializable;

/**
 * redis的hash数据结构
 * @author bruce
 *
 */
public class KhRedisHashStruct  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String key;
	private String field;
	private String value;
	
//	public KhRedisHashStruct(String field, String value) {
//		super();
//		this.field = field;
//		this.value = value;
//	}
	
	public KhRedisHashStruct(String key, String field, String value) {
		super();
		this.key = key;
		this.field = field;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KhRedisHashStruct [key=" + key + ", field=" + field + ", value=" + value + "]";
	}
	

}
