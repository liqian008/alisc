package com.bruce.alisc.common;

import com.bruce.alisc.common.config.EnableMyStarter;
import com.bruce.alisc.common.temp.TempBean1;
import com.bruce.alisc.common.temp.TempBean2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * @author bruce
 */
@EnableMyStarter(scan="com.bruce", value=true)
@EnableDiscoveryClient @SpringBootApplication public class AliscCommonApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(AliscCommonApplication.class, args);
		//		while(true) {
		//			//当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
		//			String userName = applicationContext.getEnvironment().getProperty("user.name");
		//			System.err.println("user name :" + userName);
		//			try {
		//				TimeUnit.SECONDS.sleep(1);
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}



		System.out.println("==TempBean1=="+applicationContext.getBean(TempBean1.class));
		System.out.println("==TempBean2=="+applicationContext.getBean(TempBean2.class));
	}

	@Override public void run(String... args) throws Exception {

	}
}
