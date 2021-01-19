package com.shan.yellowpages.base.model.struct;

import java.io.Serializable;

/**
 * KhCriteria基础父类，供crud base 接口使用，提取了分页、排序所需的参数
 * 
 * @author xuejw
 *
 */
//@Data
public class KhCriteriaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 排序字段
	 */
	protected String orderByClause;

	/**
	 * 是否过滤重复数据
	 */
	protected boolean distinct;

	/**
	 * 分页偏移页数
	 */
	protected Integer limitOffset;

	/**
	 * 分页条数
	 */
	protected Integer limitRows;

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public Integer getLimitOffset() {
		return limitOffset;
	}

	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	public Integer getLimitRows() {
		return limitRows;
	}

	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}
}
