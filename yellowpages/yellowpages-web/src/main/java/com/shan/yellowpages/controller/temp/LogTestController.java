package com.shan.yellowpages.controller.temp;

import com.shan.yellowpages.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bruce
 */
@Slf4j
@RequestMapping
@Controller
public class LogTestController implements InitializingBean {

	@Autowired private IUserService userService;

	@GetMapping("/logTest") public @ResponseBody String test(String name) {

		//注意 spring 默认日志输出级别为 info 所以默认情况下 这句不会打印到控制台
		log.debug("This is a debug message");
		log.info("This is an info message");
		log.warn("This is a warn message");
		log.error("This is an error message");
		return "logTest";
	}



	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService can't be null");
	}
}
