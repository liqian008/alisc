package com.shan.yellowpages.base.aspject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shan.yellowpages.base.exception.KhCacheNotExistException;
import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.exception.token.KhTokenIllegalException;
import com.shan.yellowpages.base.exception.token.KhTokenExpireException;

/**
 * 日志切面
 * 
 * @author bruce
 *
 */
public class KhLoggerAspectj {

	private static Logger logger = LoggerFactory.getLogger(KhLoggerAspectj.class);

	public void doAfter(JoinPoint jp) {

	}

	public void doBefore(JoinPoint jp) {

	}

	/**
	 * 记录慢查询日志
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long timeMillis = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		timeMillis = System.currentTimeMillis() - timeMillis;

		if (timeMillis > 1000) {
			// 记录响应大于1s的方法
			logInvoking(pjp, retVal, timeMillis);
		} else {
			logger.trace("[KhLoggerAspectj] [doAround] Service Invoking {} for {} ms", pjp.getSignature(), timeMillis);
		}
//		logger.info("[doAround]timeMillis:{},arguments:{}", timeMillis, buildArgumentsStr(pjp));
		return retVal;
	}

	public void doThrowing(JoinPoint jp, Throwable ex) {
		if (ex instanceof KhCacheNotExistException) {
			logger.info("[doThrowing] CacheNotExistException, exceptionMessage:{},signature:{}", ex.getMessage(),
					jp.getSignature());
		} else if (ex instanceof KhRuntimeException) {
			logger.error("[doThrowing] KhRuntimeException, exceptionMessage:{},signature:{}", ex.getMessage(),
					jp.getSignature());
		} else if (ex instanceof KhTokenIllegalException) {
			logger.info("[doThrowing] KhTokenIllegalException, exception:{},signature:{}", ex.getMessage(),
					jp.getSignature());
		} else if (ex instanceof KhTokenExpireException) {
			logger.info("[doThrowing] KhTokenExpireException, exception:{},signature:{}", ex.getMessage(),
					jp.getSignature());
		} else if (ex instanceof KhException) {
			logger.info("[doThrowing] KhException, exception:{},signature:{}", ex.getMessage(), jp.getSignature());
		} else {
			String argsStr = buildArgumentsStr(jp);
			logger.error("[doThrowing] Exception, signature:{},arguments:{},exception:{}", jp.getSignature(), argsStr,
					ex);
		}
	}

	/**
	 * 构造切面方法参数字符串
	 * 
	 * @param jp 切面
	 * @return 参数字符串
	 */
	private String buildArgumentsStr(JoinPoint jp) {
		StringBuilder str = new StringBuilder();
		Object[] args = jp.getArgs();
		if (null != args && args.length > 0) {
			for (Object arg : args) {
				str.append(arg);
				str.append(",");
			}
		}
		return str.toString();
	}

	/**
	 *
	 * @param pjp 切面
	 * @param result 返回值
	 * @param timeMillis 时间戳
	 */
	private void logInvoking(ProceedingJoinPoint pjp, Object result, long timeMillis) {
		String argsStr = buildArgumentsStr(pjp);
		logger.warn("[KhLoggerAspectj] [logInvoking] Slow Invoking {} for {} ms, arguments:{}, result:{}",
				pjp.getSignature(), timeMillis, argsStr, result);
	}

}
