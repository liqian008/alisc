package com.shan.yellowpages.annotation;

import java.lang.annotation.*;

/**
 * 授权策略，默认必须登录，减少注解重复添加，添加@Inherited继承注解
 *
 * @author bruce
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthorizeConfig {

    /**
     * 填充策略
     */
    AuthorizeStrategy authorizeStrategy() default AuthorizeStrategy.FORCE;

    enum AuthorizeStrategy {
        // 必须登录
        FORCE,
        // 非必须，可登可不登
        OPTIONAL,
        // 不需要有用户权限, 仅用作为空时拦截器缓存的数据, 不具备任何业务意义, 请勿在业务上使用
        NONE
    }


}
