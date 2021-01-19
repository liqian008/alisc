package com.shan.yellowpages.base.exception;

/**
 * 错误码基础街口
 *
 * @author :bruce
 * @date 2019-03-05 17:46
 */
public interface IKhErrorCode{

	/************************************************/
	/************ 系统类错误分配0~100错误码 ************/
	/************************************************/

	/** 系统错误 */
	int SYSTEM_ERROR = 1;
	/** 请求参数不正确 */
	int SYSTEM_PARAM_ERROR = 2;
	/** 请求签名不正确 */
	int SYSTEM_SIGN_ERROR = 3;


	/** 网络异常 */
	int SYSTEM_NETWORK_ERROR = 5;
	/** 网络请求异常 */
	int SYSTEM_NETWORK_REQUEST_ERROR = 6;


	/** 验证码不正确 */
	int SYSTEM_VERIFYCODE_ERROR = 10;
	/** 验证码已过期 */
	int SYSTEM_VERIFYCODE_EXPIRE_ERROR = 11;
	/** 邮件发送错误 */
	int SYSTEM_MAIL_SENDE_EXPIRE_ERROR = 20;


	/************************************************/
	/************ 权限相关分配100~200错误码 ************/
	/************************************************/

	/** 当前操作需要登录 */
	int AUTHORIZE_NEED_LOGIN = 100;
	/** 当前操作权限不足 */
	int AUTHORIZE_NEED_AUTHORIZE = 110;


	/************************************************/
	/************ 微信相关分配300~400错误码 ************/
	/************************************************/


	/** 群发消息失败 */
	int WX_BROADCAST_ERROR = 300;
	/** 非服务号 */
	int WX_BROADCAST_NEED_SENIOR = 301;
	/** 超过每月4次的群发限制 */
	int WX_BROADCAST_OVERLOAD =  302;
	/** 微信支付下单异常 */
	int WX_UNIFIED_ORDER_ERROR =303;


	/************************************************/
	/************ 上传相关分配600~700错误码 ************/
	/************************************************/

	/** 上传错误 */
	int UPLOAD_ERROR = 600;
	/** 文件格式不支持 */
	int UPLOAD_FORMAT_NOT_SUPPORT = 601;
	/** 图片上传未知错误 */
	int UPLOAD_IMAGE_ERROR = 602;
	/** 文件上传未知错误 */
	int UPLOAD_FILE_ERROR = 603;







}


