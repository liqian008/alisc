package com.shan.yellowpages.base.context;

import java.io.Serializable;

/**
 * hlmy 上下文 主要存储了环境、版本等对象信息
 * 
 * @author bruce
 */
public class KhContext implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 线程对象 */
	private static ThreadLocal<KhContext> khContextThreadLocal = new ThreadLocal<>();

	/**
	 * 空对象
	 */
	public static final KhContext EMPTY_CONTEXT = new KhContext();

	/** 请求用户的 id */
	private int requestUserId;

	/**
	 * 请求日志 id
	 */
	private String traceId;

	/**
	 * 请求时的时间戳
	 */
	private long requestTimeMillis;

	/**
	 * 渠道号
	 */
	private String chn;

	/**
	 * 客户端 UA
	 */
	private String userAgent;

	/**
	 * 客户端 IP
	 */
	private String remoteIp;

	/**
	 * scheme. http or https
	 */
	private String scheme;

	/**
	 * 请求method, GET/POST/PUT/DELETE 等
	 */
	private String requestMethod;

	/**
	 * 请求 host
	 */
	private String host;

	/**
	 * 请求路径. 当 web 服务时有效
	 */
	private String requestURI;

	/**
	 * 传入客户端信息 [如 WIFI, 机型等]. 用法如 : 展示不同质量的图片等
	 */
	private KhClientInfo khClientInfo;

	/**
	 * 原始调用方，不可变更
	 */
	// private HlmyOriginInvokerEnum originInvoker;

	// TODO 程序的调用线路，内容格式为： className.method

	public KhContext() {
		super();
	}

	private KhContext(Builder builder) {
		setRequestUserId(builder.requestUserId);
		setTraceId(builder.traceId);
		setRequestTimeMillis(builder.requestTimeMillis);
		setChn(builder.chn);
		setUserAgent(builder.userAgent);
		setRemoteIp(builder.remoteIp);
		setScheme(builder.scheme);
		setRequestMethod(builder.requestMethod);
		setHost(builder.host);
		setRequestURI(builder.requestURI);
		setKhClientInfo(builder.khClientInfo);
	}

	// public HlmyOriginInvokerEnum getOriginInvoker() {
	// return originInvoker;
	// }
	//
	// public void setOriginInvoker(HlmyOriginInvokerEnum originInvoker) {
	// this.originInvoker = originInvoker;
	// }

	public int getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(int requestUserId) {
		this.requestUserId = requestUserId;
	}

	public String getChn() {
		return chn;
	}

	public void setChn(String chn) {
		this.chn = chn;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getRequestTimeMillis() {
		return requestTimeMillis;
	}

	public void setRequestTimeMillis(long requestTimeMillis) {
		this.requestTimeMillis = requestTimeMillis;
	}

	public KhClientInfo getKhClientInfo() {
		return khClientInfo;
	}

	public void setKhClientInfo(KhClientInfo khClientInfo) {
		this.khClientInfo = khClientInfo;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	@Override
	public String toString() {
		return "KhContext{" + "requestUserId=" + requestUserId + ", traceId='" + traceId + '\'' + ", requestTimeMillis="
				+ requestTimeMillis + ", chn='" + chn + '\'' + ", userAgent='" + userAgent + '\'' + ", remoteIp='"
				+ remoteIp + '\'' + ", scheme='" + scheme + '\'' + ", requestMethod='" + requestMethod + '\''
				+ ", host='" + host + '\'' + ", requestURI='" + requestURI + '\'' + ", khClientInfo=" + khClientInfo
				+ '}';
	}

	/**
	 * 将 khContext 添加到 threadLocal 中
	 */
	public static void setThreadLocalKhContext(KhContext khContext) {
		khContextThreadLocal.set(khContext);
	}

	/**
	 * 从 threadLocal 中获取 khContext
	 */
	public static KhContext getThreadLocalKhContext() {
		return khContextThreadLocal.get();
	}

	public static boolean isValid(KhContext khContext) {
		boolean result = false;

		if (khContext != null && khContext.getKhClientInfo() != null) {
			result = true;
		}

		return result;
	}

	/**
	 * builder
	 */
	public static final class Builder {
		private int requestUserId;
		private String traceId;
		private long requestTimeMillis;
		private String chn;
		private String userAgent;
		private String remoteIp;
		private String scheme;
		private String requestMethod;
		private String host;
		private String requestURI;
		private KhClientInfo khClientInfo;

		public Builder() {
		}

		public Builder requestUserId(int val) {
			requestUserId = val;
			return this;
		}

		public Builder traceId(String val) {
			traceId = val;
			return this;
		}

		public Builder requestTimeMillis(long val) {
			requestTimeMillis = val;
			return this;
		}

		public Builder chn(String val) {
			chn = val;
			return this;
		}

		public Builder userAgent(String val) {
			userAgent = val;
			return this;
		}

		public Builder remoteIp(String val) {
			remoteIp = val;
			return this;
		}

		public Builder scheme(String val) {
			scheme = val;
			return this;
		}

		public Builder requestMethod(String val) {
			requestMethod = val;
			return this;
		}

		public Builder host(String val) {
			host = val;
			return this;
		}

		public Builder requestURI(String val) {
			requestURI = val;
			return this;
		}

		public Builder khClientInfo(KhClientInfo val) {
			khClientInfo = val;
			return this;
		}

		public KhContext build() {
			return new KhContext(this);
		}
	}
}
