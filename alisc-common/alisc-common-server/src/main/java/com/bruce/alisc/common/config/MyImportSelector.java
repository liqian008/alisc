package com.bruce.alisc.common.config;

import com.bruce.alisc.common.temp.TempBean1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 *
 * @author bruce
 */
public class MyImportSelector implements ImportSelector, BeanFactoryAware {

	private BeanFactory beanFactory;


	@Override public String[] selectImports(AnnotationMetadata importingClassMetadata) {


//		importingClassMetadata.getAnnotationTypes().forEach(System.out::println);

//		System.out.println(beanFactory);


		Map<String, Object > dataMap = importingClassMetadata.getAnnotationAttributes(EnableMyStarter.class.getName());
		AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(dataMap);

		//在这里可以拿到所有注解的信息，可以根据不同注解的和注解的属性来返回不同的class,
		Object object = annotationAttributes.get("value");
		boolean isTrue=object==null?false:Boolean.parseBoolean(object.toString());
		if (isTrue){
//			System.out.println();
			return new String[]{ TempBean1.class.getName()};
		}
		// 从而达到开启不同功能的目的
		return new String[0];


//		return new String[0];
	}

	@Override public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	//	@Override public Predicate<String> getExclusionFilter() {
//		return null;
//	}
}
