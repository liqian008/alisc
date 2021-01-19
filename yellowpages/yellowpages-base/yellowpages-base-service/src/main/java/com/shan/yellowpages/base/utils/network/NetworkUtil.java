package com.shan.yellowpages.base.utils.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网络工具类
 * 
 * @author bruce
 * @version 1.0
 */
public class NetworkUtil {

	private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

	/* 内网地址的默认规则 */
	private static final String[] DEFAULT_INTRANET_IPS = { "10", "192", "172" };

	/**
	 * 取本机内网IP
	 * 
	 * @param intranetIpPrefixs 内网地址的开头前缀，不填默认使用（10, 192, 172三个）
	 * @return 内网id
	 */
	public static String getIntranetIp(String... intranetIpPrefixs) {

		if (intranetIpPrefixs == null || intranetIpPrefixs.length <= 0) {
			intranetIpPrefixs = DEFAULT_INTRANET_IPS;
		}

		String result = null;
		Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface ni = networkInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					String ip = ips.nextElement().getHostAddress();
					// System.out.println("server ip: "+ip+", intranetIpPrefix:"+intranetIpPrefix);
					logger.trace("[NetworkUtil][getIntranetIp]serverIp:{}, intranetIpPrefixs:{}", ip,
							intranetIpPrefixs);
					for (String item : intranetIpPrefixs) {
						if (StringUtils.isNotBlank(item) && ip.startsWith(item)) {
							result = ip;

							logger.debug("[NetworkUtil][getIntranetIp]result:{}, intranetIpPrefixs:{}", result,
									intranetIpPrefixs);
							return result;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("[NetworkUtil][getIntranetIp]error:{}, intranetIpPrefix:{}", e, intranetIpPrefixs); //$NON-NLS-1$
		}
		return null;
	}

	// /**
	// * 取本机一个内网IP
	// *
	// * @return
	// */
	// public static String getIntranetIp() {
	//
	// String result = null;
	// try {
	// InetAddress ia = InetAddress.getLocalHost();
	// result = ia.getHostAddress();
	// } catch (UnknownHostException e) {
	// logger.error("[NetworkUtil][getIntranetIp]error:{}", e); //$NON-NLS-1$
	// }
	// return result;
	// }
	//
	//
	//
	// public static void main(String[] args) {
	//// System.out.println(getIntranetIp());
	//// System.out.println(getIntranetIp("192"));
	//
	// System.out.println("ab".startsWith(null));
	//
	// }

	public static void main(String[] args) {
		System.out.println(getIntranetIp("10", "192"));
	}

}
