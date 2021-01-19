package com.bruce.alisc.common;

import com.bruce.alisc.common.temp.TempBean2;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class AnnotationStarter {

	public static void main(String[] args) {

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		System.out.println(applicationContext.getBean(TempBean2.class));


		SpringApplication application = new SpringApplication(AppConfig.class);
		ApplicationContext applicationContext = application.run(args);
		System.out.println(applicationContext.getBean(TempBean2.class));

	}
}
