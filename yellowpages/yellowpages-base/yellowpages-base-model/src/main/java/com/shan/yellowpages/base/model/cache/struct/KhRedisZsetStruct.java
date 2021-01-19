package com.shan.yellowpages.base.model.cache.struct;

import java.io.Serializable;

/**
 * redis的zset数据结构
 * @author bruce
 *
 */
public class KhRedisZsetStruct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** reids的key */
	private String key;
	/** zset的member */
	private String member;
	/** zset的score */
	private Double score;
	
//	public KhRedisZsetStruct(String member, Double score) {
//		super();
//		this.member = member;
//		this.score = score;
//	}
	
	public KhRedisZsetStruct(String key, String member, Double score) {
		super();
		this.key = key;
		this.member = member;
		this.score = score;
	}
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "KhRedisZsetStruct [key=" + key + ", member=" + member + ", score=" + score + "]";
	}

	

}
