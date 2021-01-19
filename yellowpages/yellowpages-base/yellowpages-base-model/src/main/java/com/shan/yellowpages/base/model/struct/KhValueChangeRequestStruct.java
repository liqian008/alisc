package com.shan.yellowpages.base.model.struct;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 提交数据变更的对象
 * 
 * @author bruce
 *
 */
public class KhValueChangeRequestStruct implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 空类型 */
	public static final short TYPE_EMPTY = 0;


	/** 用户id */
	private String userPk;
	/** 变更类型，用于db进行分类查询统计 */
	private short type = TYPE_EMPTY;
	/** 变更的数值，增加为正，扣减为负 */
	private int amount;
	/** 操作时间 */
	private Date actionTime;
	/** 变更操作的备注 */
    private String note;
	/**  便于做操作排重处理 */
	private String sn;
    

	public KhValueChangeRequestStruct() {
		super();
	}


	public KhValueChangeRequestStruct(String userPk, short type, int amount, Date actionTime, String note, String sn) {
		this.userPk = userPk;
		this.type = type;
		this.amount = amount;
		this.actionTime = actionTime;
		this.note = note;
		this.sn = sn;
	}

	public String getUserPk() {
		return userPk;
	}

	public void setUserPk(String userPk) {
		this.userPk = userPk;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 是否合法
	 * @param entity
	 * @return
	 */
	public static boolean isValid(KhValueChangeRequestStruct entity){
		boolean result = false;
		if(entity!=null && StringUtils.isNotBlank(entity.getUserPk()) && entity.getAmount() !=0 && entity.getActionTime()!=null && StringUtils.isNotBlank(entity.getSn())){
			result = true;
		}
		return result;
	}


	@Override public String toString() {
		return "MinigameUserValueChangeStruct{" + "userPk=" + userPk + ", amount=" + amount + ", actionTime="
				+ actionTime + ", note='" + note + '\'' + ", sn='" + sn + '\'' + '}';
	}

}
