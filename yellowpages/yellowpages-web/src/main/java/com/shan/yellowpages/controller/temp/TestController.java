package com.shan.yellowpages.controller.temp;

import com.shan.yellowpages.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bruce
 */
@RequestMapping
@Controller
public class TestController implements InitializingBean {

	@Autowired private IUserService userService;

	@GetMapping("/testtest") public String test(String name) {

		String result = userService.test(name);
		return "login";

//		throw new RuntimeException();
	}



	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService can't be null");
	}
}
