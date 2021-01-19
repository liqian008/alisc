package com.shan.yellowpages.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hlmy 自定义 runtime 异常，供业务处理使用
 * 
 * @author bruce
 * @version 1.0
 */
public class KhRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(KhRuntimeException.class);


	/** 用于在前端展示的错误码 */
	private int errorCode = IKhErrorCode.SYSTEM_ERROR;
	/** 用于在前端展示的错误码 */
	private String errorMessage;

	/**
	 * 异常消息，不应当序列化交给前端（用于记录真实错误原因，供服务内部查错）
	 *
	 * 上面定义的 errorCode 和 errorMessage 主要用于给前端呈现，有些错误（如数据库等）不会暴露到前端。
	 */
	private String message;

	/** 扩展数据 */
	//	private String extraData;


	public KhRuntimeException() {
	}

	/**
	 * 构造器
	 * @param errorCode
	 */
	public KhRuntimeException(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 构造器
	 * @param errorCode
	 * @param message
	 */
	@Deprecated
	public KhRuntimeException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
		//复制message
		this.errorMessage = message;
	}

	/**
	 *
	 * @param errorCode
	 * @param errorMessage
	 * @param message
	 */
	public KhRuntimeException(int errorCode, String errorMessage, String message) {
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

	@Override public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}


//	private KhRuntimeException() {
//	}
//
//	private int errorCode;
//
//	private String message;
//
//	/* 扩展数据 */
//	private String extraData;
//
//	public KhRuntimeException(int errorCode) {
//		super();
//		this.errorCode = errorCode;
//	}
//
//	public KhRuntimeException(int errorCode, String message) {
//		super();
//		this.errorCode = errorCode;
//		this.message = message;
//	}
//
//	public KhRuntimeException(int errorCode, String message, String extraData) {
//		super();
//		this.errorCode = errorCode;
//		this.message = message;
//		this.extraData = extraData;
//	}
//
//	public KhRuntimeException(ErrorCodeEnum errorCodeEnum) {
//		super();
//		this.errorCode = errorCodeEnum.getCode();
//		this.message = errorCodeEnum.getMessage();
//	}
//
//	public int getErrorCode() {
//		return errorCode;
//	}
//
//	public void setErrorCode(int errorCode) {
//		this.errorCode = errorCode;
//	}
//
//	public String getExtraData() {
//		return extraData;
//	}
//
//	public void setExtraData(String extraData) {
//		this.extraData = extraData;
//	}
//
//	@Override
//	public String getMessage() {
//		String result = null;
//		if (StringUtils.isNotBlank(message )) {
//			// 如果有message的情况下，使用message
//			result = message;
//		} else {
//			// 否则，使用errcode对应的message
//			result = ErrorCodeEnum.getMessage(errorCode);
//		}
//
//		logger.trace("[KhRuntimeException][getMessage]result:{}, errorCode:{}", result, errorCode);
//		return result;
//	}
//
//	public String getMessageAndData() {
//		return getMessage() + ", extraData: " + extraData;
//	}

	/**
	 * HlmyRuntimeException异常供业务判断使用，不需要异常栈信息，所以覆写Throwable中的方法，以提高异常性能
	 * 
	 * @return
	 */
//	@Override
//	public Throwable fillInStackTrace() {
//		return this;
//	}

	public static void main(String[] args) {
		try {
			a();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void a(){
		throw new KhRuntimeException(1, "系统错误", "数据库链接异常");
	}
}
