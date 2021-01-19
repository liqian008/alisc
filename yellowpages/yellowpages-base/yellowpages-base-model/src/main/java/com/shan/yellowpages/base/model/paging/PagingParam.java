package com.shan.yellowpages.base.model.paging;

import java.io.Serializable;
import java.util.Map;


/**
 * 分页的查询参数，用于构造分页的ui
 * 应用场景：后台分页查询
 * @author bruce
 *
 */
public class PagingParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*查询的requestUri*/
	private String requestUri;
	/*查询参数的map*/
	private Map<String, Object> queryMap;
	

	public PagingParam(){
	}
	
	public PagingParam(String requestUri, Map<String, Object> queryMap){
		this.queryMap = queryMap;
		this.requestUri = requestUri;
	}
	
	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}
	
}
