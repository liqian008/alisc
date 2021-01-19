package com.shan.yellowpages.base.model.paging;

import java.io.Serializable;
import java.util.List;

/**
 * 瀑布流方式加载下一页的数据结构
 * 应用场景：后台分页查询，逐一翻页，且无需计算总条数的时候
 *
 * @param <T>
 * @author bruce
 */
public class KhPagingFallloadResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 无更多数据时的nextPage，为-1
     */
    private static final int EMPTY_NEXT_PAGE = -1;

    /**
     * 当前页数
     */
    private int currentPageNo;

    /**
     * 下页数，无下一页时取值-1
     */
    private int nextPageNo = EMPTY_NEXT_PAGE;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 数据列表
     */
    private List<T> dataList;

    /**
     * 默认构造器，dubo序列化必须有默认构造器
     */
    private KhPagingFallloadResult() {
        super();
    }
    
    /**
     * 构造器
     * @param currentPageNo 当前页码
     * @param nextPageNo 下页页码
     * @param pageSize 每页条数
     * @param dataList 数据集
     */
    public KhPagingFallloadResult(int currentPageNo, int nextPageNo, int pageSize, List<T> dataList) {
        super();
        this.currentPageNo = currentPageNo;
        this.nextPageNo = nextPageNo;
        this.pageSize = pageSize;
        this.dataList = dataList;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
