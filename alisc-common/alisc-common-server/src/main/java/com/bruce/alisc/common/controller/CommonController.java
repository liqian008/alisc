package com.bruce.alisc.common.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.bruce.alisc.common.rpc.ICommonRpcService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RefreshScope @Data @RequestMapping("/common") @RestController public class CommonController
		implements ICommonRpcService, InitializingBean {

	@NacosValue(value = "${user.name:defval}", autoRefreshed = true) private String nacosUsername;

	@Value("${user.name}") private String username;

	@Value("${server.port}") private int serverPort;

	@GetMapping("/test") @Override public String commonTest(String name) {
		return "hello " + username + " " + name;
	}

	@RequestMapping(value = "/testPost", method = RequestMethod.POST) @Override public String post(String name) {
		return "post " + username + " " + name;
	}

	@Override public void afterPropertiesSet() throws Exception {
		System.err.println("=====" + username + ", " + nacosUsername);
	}
}