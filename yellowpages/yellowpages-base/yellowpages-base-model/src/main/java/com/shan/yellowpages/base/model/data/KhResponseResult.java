package com.shan.yellowpages.base.model.data;

import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * 接口通用返回数据结构
 *
 * consumer可选择使用合适格式来解释，如json，xml等
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-04-16 17:40
 */
@Data
public class KhResponseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 成功的结果对象，不包含数据体 */
	public final static KhResponseResult SUCCESS_EMPTY_ENTITY = KhResponseResult.buildSuccessResult(null);

	/** 默认错误的结果对象 */
	public final static KhResponseResult ERROR_EMPTY_ENTITY = KhResponseResult.buildFailedResult(IKhErrorCode.SYSTEM_ERROR);

	/** 成功的errorcode */
	private static final int ERRORCODE_SUCCESS = 0;

	/** 错误码：成功为0，失败时需传入特定的错误码 */
	private int errorCode;
	/** 错误信息：只有失败的时候才传入该值 */
	private String errorMessage;
//	/** 当前用户是否已登录 true-已登录；false-未登录 */
//	private Boolean login;
	/** 数据内容, 用于存放返回结果的数据体 */
	private T data;
	/** 当前用户，TODO 搞成范型 */
	private Serializable currentUser;

	@Tolerate
	public KhResponseResult() {
		super();
	}

	/**
	 * 构造器 用于成功的响应
	 * 
	 * @param data
	 */
	public KhResponseResult(T data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Serializable getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Serializable currentUser) {
		this.currentUser = currentUser;
	}

	// public KhResponseResult(int errorCode, String errorMessage, T data) {
	// this.errorCode = errorCode;
	// this.errorMessage = errorMessage;
	// this.data = data;
	// }

	/**
	 * 构造成功的响应
	 * 
	 * @param data 数据
	 * @return KhResponseResult对象
	 */
	public static <T> KhResponseResult<T> buildSuccessResult(T data) {
		return new KhResponseResult<>(data);
	}

	/**
	 * 构造失败的响应
	 * 
	 * @param errorcode 错误码
	 * @param <T> data数据对象
	 * @return KhResponseResult对象
	 */
	public static <T> KhResponseResult<T> buildFailedResult(int errorcode) {
		KhResponseResult<T> result = new KhResponseResult<>();
		result.setErrorCode(errorcode);
		return result;
	}

	/**
	 * 构造失败的响应
	 * 
	 * @param errorcode 错误码
	 * @param <T> data数据对象
	 * @return KhResponseResult对象
	 */
	public static <T> KhResponseResult<T> buildFailedResult(int errorcode, String errorMessage) {
		KhResponseResult<T> result = new KhResponseResult<>();
		result.setErrorCode(errorcode);
		result.setErrorMessage(errorMessage);
		return result;
	}


	/**
	 * 构造失败的响应
	 *
	 * @return KhResponseResult对象
	 */
	public static <T> KhResponseResult<T> buildFailedResult(KhRuntimeException khRuntimeException) {
		return buildFailedResult(khRuntimeException.getErrorCode(),  khRuntimeException.getErrorMessage());
	}

	/**
	 * 构造失败的响应
	 *
	 * @return KhResponseResult对象
	 */
	public static <T> KhResponseResult<T> buildFailedResult(KhException khException) {
		return buildFailedResult(khException.getErrorCode(),  khException.getErrorMessage());
	}


	/**
	 * 判断返回结果是否成功
	 *
	 * @param khResponseResult 响应结果
	 * @return 响应是否成功 : true - 成功; false - 失败
	 */
	public static <T> boolean isSuccess(KhResponseResult<T> khResponseResult) {
		boolean result = false;

		if (khResponseResult != null && khResponseResult.getErrorCode() == ERRORCODE_SUCCESS) {
			result = true;
		}
		return result;
	}

	@Override public String toString() {
		return "KhResponseResult{" + "errorCode=" + errorCode + ", errorMessage='" + errorMessage + '\'' + ", data="
				+ data + ", currentUser=" + currentUser + '}';
	}

}