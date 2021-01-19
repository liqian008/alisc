//package com.shan.yellowpages.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author bruce
// */
//@Controller
//public class AppErrorController implements ErrorController {
//
//	@Override public String getErrorPath() {
//		return "/error";
//	}
//
//	/**
//	 *
//	 * @param request
//	 * @return
//	 */
//	@GetMapping("/error") public String error(HttpServletRequest request) {
//		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
////		if(statusCode == 401){
////			return "/401";
////		}else if(statusCode == 404){
////			return "/404";
////		}else if(statusCode == 403){
////			return "/403";
////		}else{
////			return "/500";
////		}
//		if(statusCode >=400 && statusCode<500){
//			return "/40x";
//		}else{
//			return "/50x";
//		}
//	}
//
//
//}
