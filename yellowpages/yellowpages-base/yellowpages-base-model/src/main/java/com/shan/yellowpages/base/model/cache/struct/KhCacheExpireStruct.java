package com.shan.yellowpages.base.model.cache.struct;

import java.io.Serializable;

/**
 * 缓存过期对象
 * 
 * @author bruce
 *
 */
public class KhCacheExpireStruct implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 永久有效 */
	public final static short EXPIRE_TYPE_NEVER = 0; 

	/** 类型 倒计时 */
	public final static short EXPIRE_TYPE_TIMEOUT = 1; 
	
	/** 类型 截至时间 */
	public final static short EXPIRE_TYPE_AT = 2; 

	/**  key失效类型，默认为timeout类型 */
	private short expireType = EXPIRE_TYPE_TIMEOUT;
	/** 时间戳， 单位毫秒 */
	private long timeMillis; 
	
	public KhCacheExpireStruct() {
		super();
	}
	

	public KhCacheExpireStruct(short expireType, long timeMillis) {
		super();
		this.expireType = expireType;
		this.timeMillis = timeMillis;
	}

	public short getExpireType() {
		return expireType;
	}

	public void setExpireType(short expireType) {
		this.expireType = expireType;
	}

	public long getTimeMillis() {
		return timeMillis;
	}

	public void setTimeMillis(long timeMillis) {
		this.timeMillis = timeMillis;
	}

	@Override
	public String toString() {
		return "KhCacheExpireStruct [expireType=" + expireType + ", timeMillis=" + timeMillis + "]";
	}

}
