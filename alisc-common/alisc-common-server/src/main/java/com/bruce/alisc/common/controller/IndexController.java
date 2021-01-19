package com.bruce.alisc.common.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 首页
 * @author bruce
 */
@Controller
public class IndexController implements  InitializingBean {

	@RequestMapping(value = "/index")
	public String index(Model model) {

		String name = "zhangsan";
		Integer age = 10;
		Date birthDate = new Date();
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("birthDate", birthDate);


		return "index";
	}

	@Override public void afterPropertiesSet() throws Exception {
	}
}