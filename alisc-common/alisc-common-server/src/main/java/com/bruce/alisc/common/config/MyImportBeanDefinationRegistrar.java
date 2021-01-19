package com.bruce.alisc.common.config;

import com.bruce.alisc.common.temp.TempBean2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 *
 * @author bruce
 */
public class MyImportBeanDefinationRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

	private BeanFactory beanFactory;

	@Override public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
			BeanDefinitionRegistry registry) {

		Map<String, Object > dataMap = importingClassMetadata.getAnnotationAttributes(EnableMyStarter.class.getName());
		Object object = dataMap.get("value");
		boolean isTrue=object==null?false:Boolean.parseBoolean(object.toString());
		if (isTrue){
			registry.registerBeanDefinition("tempBean2", new RootBeanDefinition(TempBean2.class));
//			System.out.println();
		}

	}

	//	@Override public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//
//
////		importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
//
////		System.out.println(beanFactory);
//
//		AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(
//				importingClassMetadata.getAnnotationAttributes(EnableMyStarter.class.getName())
//		);
//
//		//在这里可以拿到所有注解的信息，可以根据不同注解的和注解的属性来返回不同的class,
//		Object object = annotationAttributes.get("value");
//		boolean isTrue=object==null?false:Boolean.parseBoolean(object.toString());
//		if (isTrue){
//			System.out.println();
////			return new String[]{TestService1.class.getName()};
//		}
//		// 从而达到开启不同功能的目的
//		return new String[0];
//
//
////		return new String[0];
//	}

	@Override public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
