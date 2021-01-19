package com.shan.yellowpages.base.model.paging;

import java.io.Serializable;
import java.util.List;

/**
 * 瀑布流方式加载数据结构
 * 
 * 应用场景：无缝的瀑布流分页
 * 
 * @author bruce
 *
 * @param <T>
 */
public class KhTailFallloadResult<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;


	public static KhTailFallloadResult EMPTY_DATA = new KhTailFallloadResult();

	/**  初始访问 传输为0 */
	public static final long INIT_TAIL_FLAG = 0;
	
	/** 无更多数据时的tailFlag，为-1 */
	public static final long EMPTY_TAIL_FLAG = -1;

	/** 每页数量 */
	private int pageSize;

	/** 是否有下一页 */
	private Boolean hasNext;

	/*
	 * 对于无缝加载瀑布流方式，需要使用第n+1条的tailFlag，作为最后一条数据（tailFlag通常使用下一页的开头数据，因此取需要多取一条， pageSize+1）
	 */
	private long tailFlag;

	/** 数据列表 */
	private List<T> dataList;

	private KhTailFallloadResult() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageSize 每页条数
	 * @param hasNext 是否有下一页
	 * @param tailFlag 下一页的标志
	 * @param dataList 数据集
	 */
	public KhTailFallloadResult(int pageSize, boolean hasNext, long tailFlag, List<T> dataList) {
		this.pageSize = pageSize;
		this.hasNext = hasNext;
		this.tailFlag = tailFlag;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public long getTailFlag() {
		return tailFlag;
	}

	public void setTailFlag(long tailFlag) {
		this.tailFlag = tailFlag;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
