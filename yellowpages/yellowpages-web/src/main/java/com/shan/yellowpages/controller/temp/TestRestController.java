package com.shan.yellowpages.controller.temp;

import com.shan.yellowpages.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RequestMapping
@RestController
public class TestRestController implements InitializingBean {

	@Autowired private IUserService userService;

	@GetMapping("/test") public String test(String name) {

		String result = userService.test(name);
		return result;
	}


//	@GetMapping("/login") public String login(String name) {
//
//		return "login";
//	}

//	@GetMapping("/exception") public String exception() {
//		throw new RuntimeException();
//	}


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService can't be null");
	}
}
