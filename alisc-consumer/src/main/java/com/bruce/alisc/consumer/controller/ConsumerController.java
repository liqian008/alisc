package com.bruce.alisc.consumer.controller;

import com.bruce.alisc.common.rpc.ICommonRpcService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RequestMapping("/consumer") @RestController public class ConsumerController implements InitializingBean {

	@Autowired private ICommonRpcService commonRpcService;

	@GetMapping("/test") public String test(String name) {

		String result = commonRpcService.commonTest(name);
		return result;
	}

	@GetMapping("/testPost") public String testPost(String name) {
		String result = commonRpcService.post(name);
		return result;
	}

	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(commonRpcService, "commonRpcService can't be null");
	}
}
