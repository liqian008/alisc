package com.bruce.alisc.common.rpc.fallback;

import com.bruce.alisc.common.rpc.ICommonRpcService;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service public class CommonRpcFallbackService implements ICommonRpcService {

	@Override public String commonTest(String name) {
		return "get fallback";
	}

	@Override public String post(String name) {
		return "post fallback";
	}

}
