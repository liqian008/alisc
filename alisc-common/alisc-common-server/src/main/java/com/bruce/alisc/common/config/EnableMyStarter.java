package com.bruce.alisc.common.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author bruce
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MyImportSelector.class, MyImportBeanDefinationRegistrar.class})
public @interface EnableMyStarter {

	/**
	 * 是否开启
	 * @return
	 */
	boolean value() default false;

	/**
	 *
	 * @return
	 */
	String scan();

}
