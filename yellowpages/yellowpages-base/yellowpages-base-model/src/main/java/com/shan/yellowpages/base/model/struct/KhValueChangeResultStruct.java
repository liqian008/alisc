package com.shan.yellowpages.base.model.struct;

import java.io.Serializable;

/**
 * 提交数据变更的结果
 * 
 * @author bruce
 *
 */
public class KhValueChangeResultStruct implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public final static KhValueChangeResultStruct EMPTY_DATA = new KhValueChangeResultStruct(0, null, null);
	

	/** 变更的数值，增加为正，扣减为负 */
	private int changeAmount;
	/** 变更后的数值, 如变更数值为0，则该数值为null */
	private Long postAmount;

	/** 数值变更交易编号 */
	private String txId;



	/* 如果需要，这里还可以扩展变更前的数值 */

	public KhValueChangeResultStruct() {
		super();
	}
	

//	public KhValueChangeResultStruct(int changeAmount, Long postAmount) {
//		super();
//		this.changeAmount = changeAmount;
//		this.postAmount = postAmount;
//	}

	public KhValueChangeResultStruct(int changeAmount, Long postAmount, String txId) {
		this.changeAmount = changeAmount;
		this.postAmount = postAmount;
		this.txId = txId;
	}

	public int getChangeAmount() {
		return changeAmount;
	}


	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public Long getPostAmount() {
		return postAmount;
	}

	public void setPostAmount(Long postAmount) {
		this.postAmount = postAmount;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

//	@Override
//	public String toString() {
//		return "UserGameValueResultStruct [changeAmount=" + changeAmount + ", postAmount=" + postAmount + "]";
//	}

	@Override public String toString() {
		return "KhValueChangeResultStruct{" + "changeAmount=" + changeAmount + ", postAmount=" + postAmount
				+ ", txId='" + txId + '\'' + '}';
	}
}
