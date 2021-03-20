package com.shan.yellowpages.config;

import com.shan.yellowpages.web.interceptor.LoginInterceptor;
import com.shan.yellowpages.web.interceptor.RequestTypeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author bruce
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override public void addInterceptors(InterceptorRegistry registry) {

		//request类型拦截器
		registry.addInterceptor(new RequestTypeInterceptor())
				.addPathPatterns("/**");

		//登录拦截器
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/passport/login", "/static/**");

	}

	@Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//第一个方法设置访问路径前缀，第二个方法设置资源路径
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
