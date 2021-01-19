package com.shan.yellowpages.base.model.tree.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树状结构的基础模型
 *
 * 需要使用梳妆结构的各业务模型，可继承此
 *
 * 建议root的id使用int0（， root的parentId使用int的-1（无父节点）
 *
 * @author bruce
 */
public class KhTreeStruct<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 父id */
	private T parentId;
	/** 当前节点id */
	private T id;
	/** 子节点列表*/
	private List<KhTreeStruct<T>> children = new ArrayList<>();

	public KhTreeStruct() {
	}

	public KhTreeStruct(T parentId, T id, List<KhTreeStruct<T>> children) {
		this.parentId = parentId;
		this.id = id;
		this.children = children;
	}

	public T getParentId() {
		return parentId;
	}

	public void setParentId(T parentId) {
		this.parentId = parentId;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

//	public List<KhTreeStruct<T>> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<KhTreeStruct<T>> children) {
//		this.children = children;
//	}

	public List<KhTreeStruct<T>> getChildren() {
		return children;
	}

	public void setChildren(List<KhTreeStruct<T>> children) {
		this.children = children;
	}

	/**
	 * 添加子节点
	 * @param child
	 */
	public void addChild(KhTreeStruct child) {
		if(children==null){
			children = new ArrayList<>();
		}
		children.add(child);
	}


	/**
	 * 判断是否还有子节点
	 */
	public boolean hasChildren() {
		boolean result = false;
		if(children !=null && children.size()>0 ){
			result = true;
		}
		return result;
	}


	@Override public String toString() {
		return "TreeStruct{" + "parentId=" + parentId + ", id=" + id + '}';
	}

}