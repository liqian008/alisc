package com.shan.yellowpages.base.exception;

/**
 * 业务的错误码接口
 *
 * 只是为了验证，TODO 实际需要放置在业务包中
 *
 * @author :xuejw
 * @date 2019-03-05 17:46
 */
public interface ITestErrorCode extends IKhErrorCode {

	/** 系统错误 */
	int BUSINESS_SYSTEM_ERROR = 1;
	/** 请求参数不正确 */
	int BUSINESS_SYSTEM_PARAM_ERROR = 2;
	/** 请求签名不正确 */
	int BUSINESS_SYSTEM_SIGN_ERROR = 3;
	/** 验证码不正确 */
	int BUSINESS_SYSTEM_VERIFYCODE_ERROR = 10;
	/** 验证码已过期 */
	int BUSINESS_SYSTEM_VERIFYCODE_EXPIRE_ERROR = 11;




}


