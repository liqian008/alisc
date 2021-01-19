package com.shan.yellowpages.exception;

import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.model.data.KhResponseResult;
import com.shan.yellowpages.web.interceptor.RequestTypeInterceptor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要考虑@ResponseStatus
 * @author bruce
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ModelAttribute
	public Map<String, Object> modelAttribute(){
		Map<String, Object> data = new HashMap<>();
		data.put("name", "liqian");
		data.put("age", 40);
		return data;
	}


	/**
	 * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及视图的指定
	 * 参数Exception e:会将产生异常对象注入到方法中
	 */
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e){
		e.printStackTrace();

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
			System.out.println("========");
		}



		KhResponseResult khResponseResult = KhResponseResult.ERROR_EMPTY_ENTITY;
		if(e instanceof KhRuntimeException){
			KhRuntimeException khRuntimeException = (KhRuntimeException) e;
			khResponseResult = KhResponseResult.buildFailedResult(khRuntimeException);
		}else if(e instanceof KhException){
			KhException khException = (KhException) e;
			khResponseResult = KhResponseResult.buildFailedResult(khException);
		}else{}

		if(RequestTypeInterceptor.isAjaxRequest(req)){
			return khResponseResult;
		}else{
			ModelAndView mv = new ModelAndView();
			mv.addObject("khResponseResult", khResponseResult);
			mv.setViewName("50x");
			return mv;
		}
	}

	/**
	 * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及视图的指定
	 * 参数Exception e:会将产生异常对象注入到方法中
	 */
	@ExceptionHandler(NullPointerException.class)
	public Object nullPointerExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e){
//		e.printStackTrace();

		KhResponseResult khResponseResult = KhResponseResult.ERROR_EMPTY_ENTITY;
		if(e instanceof KhRuntimeException){
			KhRuntimeException khRuntimeException = (KhRuntimeException) e;
			khResponseResult = KhResponseResult.buildFailedResult(khRuntimeException);
		}else if(e instanceof KhException){
			KhException khException = (KhException) e;
			khResponseResult = KhResponseResult.buildFailedResult(khException);
		}else{}

		if(RequestTypeInterceptor.isAjaxRequest(req)){
			return khResponseResult;
		}else{
			ModelAndView mv = new ModelAndView();
			mv.addObject("khResponseResult", khResponseResult);
			mv.setViewName("50x");
			return mv;
		}
	}
}
