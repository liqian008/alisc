package com.shan.yellowpages.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce
 */
@Configuration
//@MapperScan({"com.shan.yellowpages.**.dao.mapper", "com.bruce.shorturl.mapper"})
//@MapperScan({"com.bruce.shorturl.mapper"})
@MapperScan({"com.shan.yellowpages.security.dao.mapper"})
public class MybatisConfig {


}
