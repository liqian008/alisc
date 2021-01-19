package com.shan.yellowpages.base.exception;

/**
 * unchecked异常
 * 
 * @author bruce
 *
 */
public class KhException extends Exception {

	private static final long serialVersionUID = 1L;

	/** 错误码 */
	private int errorCode;
	/** 用于在前端展示的错误码 */
	private String errorMessage;

	/**
	 * 异常消息，不应当序列化交给前端（用于记录真实错误原因，供服务内部查错）
	 *
	 * 上面定义的 errorCode 和 errorMessage 主要用于给前端呈现，有些错误（如数据库等）不会暴露到前端。
	 */
	private String message;

	/**
	 * 构造器
	 * 
	 * @param errorCode 错误码
	 */
	public KhException(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 构造器
	 * 
	 * @param errorCode 错误码
	 * @param errorMessage 错误信息
	 */
	public KhException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 *
	 * @param errorCode 错误码
	 * @param errorMessage 错误信息
	 * @param message 异常信息
	 */
	public KhException(int errorCode, String errorMessage, String message) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.message = message;
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

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
