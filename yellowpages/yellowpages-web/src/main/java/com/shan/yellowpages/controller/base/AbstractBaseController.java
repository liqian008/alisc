package com.shan.yellowpages.controller.base;

import com.shan.yellowpages.controller.ExcelExportData;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author bruce
 */
@Slf4j
public abstract class AbstractBaseController {

	private static Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);

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





	public static final Map<String, String> keyMap = new LinkedHashMap<>();
	static {

		keyMap.put("name", "姓名");
		keyMap.put("mobile", "手机");
		keyMap.put("telphone", "电话");
		keyMap.put("identity", "身份证");
		keyMap.put("remark", "备注");
		keyMap.put("resume", "简介");

		keyMap.put("company", "公司名");
		keyMap.put("companyEn", "公司英文名");
		keyMap.put("companyWebsite", "公司网址");
		keyMap.put("title", "职位");
		keyMap.put("department", "部门");
		keyMap.put("email", "邮箱");
		keyMap.put("fax", "传真");


		keyMap.put("address", "地址");
		keyMap.put("postcode", "邮编");
		keyMap.put("industry", "所在行业");

		//"生日",  "国籍",  "性别",

	}



	/**
	 *
	 * @param businessName
	 * @param exportFieldKeyArray
	 * @param dataList
	 * @param res
	 * @throws UnsupportedEncodingException
	 */
	public static <T> void  export(String businessName, String[] exportFieldKeyArray, List<T> dataList,
			HttpServletRequest req, HttpServletResponse res) throws
			UnsupportedEncodingException {
		String fileName = businessName + ".xlsx";
		// 设置响应头
		setExcelExportResponseHeader(res, fileName);
		try {
			ExcelExportData exportData = new ExcelExportData();
			exportData.setSheetName("数据");
			exportData.setRowTitle(businessName);


			Collection<String> fieldKeyList = null;

			List<String> headerNames = null;
			if(ArrayUtils.isEmpty(exportFieldKeyArray)){
				//TODO filter invalid key
				fieldKeyList = keyMap.keySet().stream().collect(Collectors.toList());
			}else{
				fieldKeyList = Arrays.stream(exportFieldKeyArray).filter(keyMap::containsKey).collect(Collectors.toList());
				//double check
				if(CollectionUtils.isEmpty(fieldKeyList)){
					fieldKeyList = keyMap.keySet().stream().collect(Collectors.toList());
				}
			}

			headerNames = fieldKeyList.stream().map(keyMap::get).collect(Collectors.toList());

			exportData.setKeyList(headerNames);

			if (CollectionUtils.isNotEmpty(dataList)) {
				// 构造数据集
				List<List<String>> excelDataList = new ArrayList<>();
				for (T entity : dataList) {

					List<String> valueList = new ArrayList<>();
					for (String fieldKey: fieldKeyList){
						Field field = KhContactEntity.class.getDeclaredField(fieldKey);
						field.setAccessible(true);
						Object obj = field.get(entity);

						valueList.add(obj==null?"":obj.toString());
					}
					excelDataList.add(valueList);
				}
				exportData.setDataList(excelDataList);
			}
			// 执行导出操作
			Workbook workbook = ExcelUtil.exportWorkbook(exportData);
			workbook.write(res.getOutputStream());
		} catch (Exception e) {
			logger.warn("[export]error, e", e);
		}
	}
}
