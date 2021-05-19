package com.shan.yellowpages.controller.base;

import com.shan.yellowpages.security.model.KhAdminUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *
 * @author bruce
 */
@Slf4j
public abstract class AbstractBaseController {


	public static final String SESSION_USER_KEY = "_session_user";

	public static final String SESSION_NAV_MENU = "_session_nav_menu";

	/**
	 * 判断是否已登录
	 * @param req
	 * @return
	 */
	protected boolean isLogin(HttpServletRequest req){
		KhAdminUserEntity adminUserEntity = getLoginUser(req);
		boolean result = KhAdminUserEntity.isValid(adminUserEntity);
		return result;

		//		Boolean isLogin = (Boolean) req.getSession().getAttribute(SESSION_USER_KEY);
		//		return isLogin==null?false:isLogin;
	}


	/**
	 * 获取当前登录用户
	 * @param req
	 * @return
	 */
	protected KhAdminUserEntity getLoginUser(HttpServletRequest req){
		KhAdminUserEntity result = (KhAdminUserEntity) req.getSession().getAttribute(SESSION_USER_KEY);
		return result;
	}


	/**
	 * 构造分页链接url参数
	 *
	 * @param req
	 * @return
	 */
	public static String buildParams(HttpServletRequest req) {
		Map<String, String[]> paramMaps = req.getParameterMap();

		StringBuilder sb = new StringBuilder();
		if(!MapUtils.isEmpty(paramMaps)){
			for (Map.Entry<String, String[]> entry : paramMaps.entrySet()) {
				String key = entry.getKey();
				if (StringUtils.equals(key, "pageNo") || StringUtils.equals(key, "pageSize")) {
					continue;
				}

				String[] valueArray = entry.getValue();
				if(valueArray==null || valueArray.length==0){
					continue;
				}
				if(valueArray.length==1){
					String value= valueArray[0];
					if(StringUtils.isNotBlank(value)){
						sb.append(key);
						sb.append("=");
						sb.append(valueArray[0]);
						sb.append("&");
					}
				}else{
					for(String value: valueArray){
						if(StringUtils.isNotBlank(value)){
							sb.append(key);
							sb.append("=");
							sb.append(valueArray[0]);
							sb.append("&");
						}
					}
				}
			}
		}
		String result = sb.toString();
		req.setAttribute("params", result);
		return result;
	}
	/**
	 * 设置导出excel的响应头
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	protected static void setExcelExportResponseHeader(HttpServletResponse response, String fileName)
			throws UnsupportedEncodingException {
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));

		//response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls" );

	}
}
