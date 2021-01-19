package com.shan.yellowpages.base.model.paging;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果对象 应用场景：后台分页查询
 *
 * @param <T>
 * @author bruce
 */
public class KhPagingResult<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认 page no
	 */
	public static final int DEFAULT_PAGE_NO = 1;

	/**
	 * 默认 page size
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 默认的空对象
	 */
	public static final KhPagingResult EMPTY_PAGING_RESULT =  new KhPagingResult(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE, 0, null);



	/**
	 * 当前页数
	 */
	private int pageNo = DEFAULT_PAGE_NO;

	/**
	 * 每页数量
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 总记录条数
	 */
	private int totalCount = 0;

	/**
	 * 总页数
	 */
	private int totalPage = 0;

	private List<T> dataList;

	/**
	 * 默认构造器，dubo序列化必须有默认构造器
	 */
	private KhPagingResult() {
		super();
	}

	/**
	 * 含参版本构造器
	 */
	public KhPagingResult(int pageNo, int pageSize, int totalCount, List<T> dataList) {
		this.pageNo = pageNo <= 0 ? 1 : pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		// 构造totalPage（考虑到对于nodejs的调用客户端，无法使用get属性获取）
		this.totalPage = getTotalPage();

		this.dataList = dataList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * 计算总页数
	 */
	public int getTotalPage() {
		if (pageSize <= 0) {// 不合法
			totalPage = 0;
		} else {
			totalPage = (int) Math.ceil(totalCount / Double.parseDouble(String.valueOf(pageSize)));
		}

		return totalPage;
	}

	// @Deprecated
	// public List<T> getPageData() {
	// return dataList;
	// }

	/**
	 *
	 * @param pagingResult
	 * @return
	 */
	public static boolean isNotEmpty(KhPagingResult pagingResult){
		boolean result = false;
		if(pagingResult!=null && pagingResult.getDataList()!=null && pagingResult.getDataList().size()>0){
			result =true;
		}
		return result;
	}


	/**
	 * 构造空对象
	 */
	public static KhPagingResult buildEmptyResult(int pageSize) {
		KhPagingResult result = null;
		if(pageSize == DEFAULT_PAGE_SIZE){
			result = EMPTY_PAGING_RESULT;
		}else{
			result = new KhPagingResult(DEFAULT_PAGE_NO, pageSize,0,null);;
		}
		return result;
	}

	/**
	 * 判断是否为空
	 */
	public static boolean isValid(KhPagingResult pagingResult) {
		return pagingResult!=null;
	}





	@Override public String toString() {
		return "KhPagingResult{" + "pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", dataList=" + dataList + '}';
	}
}
