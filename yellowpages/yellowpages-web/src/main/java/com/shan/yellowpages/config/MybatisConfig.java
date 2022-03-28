package com.shan.yellowpages.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce
 */
@Slf4j
@Configuration
//@MapperScan({"com.shan.yellowpages.**.dao.mapper", "com.bruce.shorturl.mapper"})
//@MapperScan({"com.bruce.shorturl.mapper"})
//@MapperScan({"com.shan.yellowpages.**.dao.mapper"})
public class MybatisConfig implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[MybatisConfig][afterPropertiesSet]enter");
    }
}
