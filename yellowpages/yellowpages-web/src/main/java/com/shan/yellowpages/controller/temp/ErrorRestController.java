package com.shan.yellowpages.controller.temp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author bruce
 */
@RequestMapping
@RestController
public class ErrorRestController implements InitializingBean {



	@ResponseStatus
	@GetMapping("/exception") public String exception(Model model) {

//		System.out.println(model.getAttribute("name"));

		Map<String, Object> map = model.asMap();
		System.out.println(map);

		throw new RuntimeException();
	}

	@GetMapping("/exception1") public String exception1() {
		String name = null;
		System.out.println(name.length());
		return "";
	}

	@GetMapping("/exception2") public String exception2() {
		throw new IllegalArgumentException();
	}

	@ResponseStatus(reason = "这是在方法上通过@ResponseStatus注解定义的信息", value = HttpStatus.NOT_FOUND)
	@GetMapping("/exception3") public String exception3(Model model) {
		return "exception3";
	}





	@Override public void afterPropertiesSet() throws Exception {
	}
}
