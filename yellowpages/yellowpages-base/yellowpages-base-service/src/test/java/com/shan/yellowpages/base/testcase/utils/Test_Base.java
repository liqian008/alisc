package com.shan.yellowpages.base.testcase.utils;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
//@ContextConfiguration
@ContextConfiguration(locations = {"classpath*:/test_spring/application*.xml"})  

//@ContextConfiguration   
//({"/spring/app*.xml","/spring/service/app*.xml"}) //加载配置文件  
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例  
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！  
//@Transactional  
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！  
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
//------------  

//参考了 http://www.cnblogs.com/wangtj-19/p/5821725.html
//和 http://blog.csdn.net/shan9liang/article/details/40452469


public abstract class Test_Base extends AbstractJUnit4SpringContextTests{
	
	//AbstractJUnit4SpringContextTests 的使用办法参看 http://www.coderli.com/junit-spring-test-applicationcontext/

	// @Resource //自动注入,默认按名称
	// private IBaseDao baseDao;
	
	public <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	protected ApplicationContext getContext() {
		return applicationContext;
	}
	
	
}
