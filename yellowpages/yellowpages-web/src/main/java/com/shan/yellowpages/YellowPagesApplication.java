package com.shan.yellowpages;

import com.shan.yellowpages.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author bruce
 */
@SpringBootApplication
//@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class})
//@EnableWebMvc
//@EnableRedisHttpSession
@Slf4j
public class YellowPagesApplication implements CommandLineRunner {

	@Autowired
	private ExcelUtil excelUtil;

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(YellowPagesApplication.class, args);
		log.debug("[main]started!");
	}

	@Override public void run(String... args) throws Exception {
		try {
//			excelUtil.importExcel();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
