package com.bruce.alisc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author bruce
 */
@EnableFeignClients("com.bruce.alisc") @SpringBootApplication public class AliscConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AliscConsumerApplication.class, args);
	}

}
