package com.bruce.alisc.common.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author bruce
 */
@FeignClient(name = "common-server")
//@FeignClient(name = "common-server", fallback = CommonRpcFallbackService.class)
public interface ICommonRpcService {

	/**
	 * 方法测试
	 *
	 * @return
	 */
	@RequestMapping(value = "/common/test", method = RequestMethod.GET) String commonTest(
			@RequestParam(value = "name", required = false) String name);

	/**
	 * post方法测试
	 *
	 * @return
	 */
	@RequestMapping(value = "/common/testPost", method = RequestMethod.POST) String post(
			@RequestParam(value = "name", required = false) String name);

}
