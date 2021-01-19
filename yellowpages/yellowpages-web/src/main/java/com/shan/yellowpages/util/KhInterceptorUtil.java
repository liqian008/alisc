package com.shan.yellowpages.util;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * KhInterceptorUtil工具类，主要用于获取拦截其方法上的权限
 *
 * @author bruce
 */
public class KhInterceptorUtil {

    private static final Logger logger = LoggerFactory.getLogger(KhInterceptorUtil.class);

    /** 使用 map 作为local缓存, 避免每次通过反射获取 */
    private static Map<HandlerMethod, AuthorizeConfig.AuthorizeStrategy> authorizeStrategyMap = new HashMap<>();

    /**
     * 获取登录限制类型(force强制， optional可选，none不需要)
     *
     * @param request {@link HttpServletRequest}
     * @param handlerMethod {@link HandlerMethod}
     * @return 授权注解
     */
    public static AuthorizeConfig.AuthorizeStrategy getAuthorizeType(HttpServletRequest request,
            HandlerMethod handlerMethod) throws Exception {
        logger.debug("[getAuthorizeType]entering,  requestUri:{}, handlerMethod:{}", request.getRequestURI(),
                handlerMethod.hashCode());

        // 先从缓存中获取
        AuthorizeConfig.AuthorizeStrategy result = authorizeStrategyMap.get(handlerMethod);

        if (result == null) {
            // 缓存中不存在，需要通过反射获取
            logger.debug("[getAuthorizeType]cache not exists");
            // 默认不需要授权，实际不因该出现该值
            AuthorizeConfig.AuthorizeStrategy realNeeded = AuthorizeConfig.AuthorizeStrategy.NONE;


            // 获取方法上注解
            AuthorizeConfig authorizeOnMethod = handlerMethod.getMethodAnnotation(AuthorizeConfig.class);
            // 获取类上的注解
            AuthorizeConfig annotationOnClass = handlerMethod.getBeanType().getAnnotation(AuthorizeConfig.class);



            // 获取类上的注解
            //            Object target = AopTargetUtils.getTarget(handlerMethod.());
            //            AuthorizeConfig annotationOnClass = target.getClass().getAnnotation(AuthorizeConfig.class);


            boolean needAuthorize = annotationOnClass != null || authorizeOnMethod != null;
            if (needAuthorize) {
                // 检查登录授权策略
                boolean isForce = (authorizeOnMethod != null
                        && authorizeOnMethod.authorizeStrategy() == AuthorizeConfig.AuthorizeStrategy.FORCE)
                        || (annotationOnClass != null
                        && annotationOnClass.authorizeStrategy() == AuthorizeConfig.AuthorizeStrategy.FORCE);
                if (isForce) {
                    realNeeded = AuthorizeConfig.AuthorizeStrategy.FORCE;
                } else {
                    boolean isOptional = (authorizeOnMethod != null
                            && authorizeOnMethod.authorizeStrategy() == AuthorizeConfig.AuthorizeStrategy.OPTIONAL)
                            || (annotationOnClass != null && annotationOnClass
                            .authorizeStrategy() == AuthorizeConfig.AuthorizeStrategy.OPTIONAL);
                    if (isOptional) {
                        realNeeded = AuthorizeConfig.AuthorizeStrategy.OPTIONAL;
                    }
                }
            }
            result = realNeeded;
            // 放到缓存中
            authorizeStrategyMap.put(handlerMethod, result);
        }
        logger.debug("[getAuthorizeType]result:{}, requestUri:{}, handlerMethod:{}", result, request.getRequestURI(),
                handlerMethod.hashCode());
        return result;
    }


}
