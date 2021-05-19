package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.*;
import com.shan.yellowpages.security.model.struct.ContactActivityStruct;
import com.shan.yellowpages.security.model.struct.ContactStruct;
import com.shan.yellowpages.security.service.IKhActivityContactRelationEntityService;
import com.shan.yellowpages.security.service.IKhActivityEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import com.shan.yellowpages.util.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 联系人controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/contact")
public class KhContactController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhContactController.class);

//	public static final String[] ADMIN_USERNAME_ARRAY = {"liqian"};
	public static final String[] ADMIN_USERNAME_ARRAY = {"liqian", "shanhm"};

	@Autowired
	private IKhContactEntityService khContactEntityService;
	@Autowired
	private IKhActivityEntityService khActivityEntityService;
	@Autowired
	private IKhActivityContactRelationEntityService khActivityContactRelationEntityService;



	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
		Assert.notNull(khActivityEntityService, "khActivityEntityService can't be null");
		Assert.notNull(khActivityContactRelationEntityService, "khActivityContactRelationEntityService can't be null");
	}


	/**
	 * 分页方式查询
	 */
	@RequestMapping("/paging")
	public String paging(Model model, @RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "30") int pageSize,  HttpServletRequest req) {

//		pageSize = 5;
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);


		boolean exportEnable = false;
		//hardcode FIXME
		KhAdminUserEntity userEntity = getLoginUser(req);
		if(Arrays.stream(ADMIN_USERNAME_ARRAY).anyMatch((item)->StringUtils.equalsIgnoreCase(userEntity.getUsername(), item))){
			exportEnable = true;
		}
		model.addAttribute("exportEnable", exportEnable);

		//构造查询参数
		buildParams(req);

		model.addAttribute("pageNo", pageNo);
		KhContactEntityCriteria criteria = buildQueryCriteria(model, req);

		model.addAttribute("exportKeyMap", keyMap);

		KhPagingResult<ContactStruct> pagingResult = khContactEntityService.pagingDtoByCriteria(pageNo, pageSize, criteria);
//		KhPagingResult<KhContactEntity> pagingResult = khContactEntityService.pagingByCriteria(pageNo, pageSize, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "contact/contactPaging";
	}




	/**
	 * 数据导出excel
	 */
	@RequestMapping("/export")
	public void export(Model model,  HttpServletRequest req, HttpServletResponse res) {
		//构造查询参数
		buildParams(req);

		//hardcode FIXME
		KhAdminUserEntity userEntity = getLoginUser(req);
		if(!Arrays.stream(ADMIN_USERNAME_ARRAY).anyMatch((item)->StringUtils.equalsIgnoreCase(userEntity.getUsername(), item))){
			throw new RuntimeException("没有导出权限");
		}

		KhContactEntityCriteria criteria = buildQueryCriteria(model, req);
		List<KhContactEntity> dataList = khContactEntityService.queryByCriteria(criteria);

		String[] exportFieldArray = req.getParameterValues("exportFields");

		try {
			export("联系人导出", exportFieldArray, dataList, req, res);
		} catch (UnsupportedEncodingException e) {
		}
	}


	@RequestMapping("/add")
	public String add(Model model, KhContactEntity contact, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		contact.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("contact", contact);
		if(KhContactEntity.isValid(contact) && StringUtils.isBlank(contact.getAvatar())){
			contact.setAvatar(ContactStruct.AVATAR_DEFAULT);
		}

		List<ContactActivityStruct> activityStructList = processActivityList(null);
		model.addAttribute("activityStructList", activityStructList);


		model.addAttribute("activityStructList", activityStructList);

		return "contact/contactEdit";
	}


	@RequestMapping("/clone")
	public String clone(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhContactEntity contact = khContactEntityService.loadById(id);

		contact.setId(null);
		contact.setName("姓名");
		contact.setCreateTime(null);
		contact.setUpdateTime(null);
		model.addAttribute("contact", contact);

		boolean clone = true;
		model.addAttribute("clone", clone);
//		if(clone){
//			model.addAttribute("calloutStyle", "callout-danger");
//		}else{
//			model.addAttribute("calloutStyle", "callout-info");
//		}

		if(KhContactEntity.isValid(contact) && StringUtils.isBlank(contact.getAvatar())){
			contact.setAvatar(ContactStruct.AVATAR_DEFAULT);
		}

		List<ContactActivityStruct> activityStructList = processActivityList(id);
		model.addAttribute("activityStructList", activityStructList);

		return "contact/contactEdit";
	}

	@RequestMapping("/edit")
	public String edit(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhContactEntity contact = khContactEntityService.loadById(id);
		model.addAttribute("contact", contact);


		List<ContactActivityStruct> activityStructList = processActivityList(id);
		model.addAttribute("activityStructList", activityStructList);

		if(KhContactEntity.isValid(contact) && StringUtils.isBlank(contact.getAvatar())){
			contact.setAvatar(ContactStruct.AVATAR_DEFAULT);
		}
		return "contact/contactEdit";
	}

	/**
	 * 处理活动列表
	 * @param contactId 联系人id
	 * @return
	 */
	private List<ContactActivityStruct> processActivityList(Integer contactId) {

		List<KhActivityContactRelationEntity> relationEntityList = null;

		//获取联系人关联过的列表
		if(contactId!=null && contactId > 0){
			KhActivityContactRelationEntityCriteria criteria = new KhActivityContactRelationEntityCriteria();
			criteria.createCriteria().andContactIdEqualTo(contactId);
			relationEntityList = khActivityContactRelationEntityService.queryByCriteria(criteria);
		}

		//获取联系人关联过的列表
		List<KhActivityEntity> activityEntityList = khActivityEntityService.queryAll();
		List<ContactActivityStruct> activityStructList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(activityEntityList)){
			for(KhActivityEntity activityEntity: activityEntityList){
				ContactActivityStruct activityStruct = ContactActivityStruct.convert(activityEntity);
				if(CollectionUtils.isNotEmpty(relationEntityList)){
					for(KhActivityContactRelationEntity item: relationEntityList){
						//判断是否匹配
						if(item.getActivityId().equals(activityEntity.getId())){
							activityStruct.setChecked(true);
						}
					}
				}
				activityStructList.add(activityStruct);
			}
		}
		return activityStructList;
	}

	@RequestMapping("/info")
	public String info(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhContactEntity contact = khContactEntityService.loadById(id);
		if(KhContactEntity.isValid(contact) && StringUtils.isBlank(contact.getAvatar())){
			contact.setAvatar(ContactStruct.AVATAR_DEFAULT);
		}
		model.addAttribute("contact", contact);
		return "contact/contactInfo";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, KhContactEntity contact, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
//		model.addAttribute("servletPath", servletPath);

		int result =0;

//		TODO 检查
//		String username = contact.getContactname();
//		if (StringUtils.isBlank(username)) {
//			model.addAttribute("message", "用户信息输入有误，请检查！");
//			return "forward:/home/operationResult";
//		}

		// 过滤非法字符
//		username = KhValidatorUtil.filterUnSafeChar(username).trim();
//		contact.setContactname(username);

		Date currentTime = new Date();
		contact.setLastModUid(getLoginUser(req).getId());
		contact.setUpdateTime(currentTime);
		if (contact.getId() != null && contact.getId() > 0) {
			//更新信息
			result = khContactEntityService.updateById(contact);
		} else {
			// 创建新用户
			contact.setCreateTime(currentTime);
			result = khContactEntityService.save(contact);
		}

		if(StringUtils.isBlank(contact.getAvatar())){
			contact.setAvatar(null);
		}

		//保存联系人和活动的关系
		String[] activityIds = req.getParameterValues("activityIds");
		if(ArrayUtils.isNotEmpty(activityIds)){

			int contactId = contact.getId();

			khActivityContactRelationEntityService.deleteByContactId(contactId);

			for(String activityIdStr: activityIds){
				int activityId = NumberUtils.toInt(activityIdStr);
				if(activityId>0){
					KhActivityContactRelationEntity relationEntity = new KhActivityContactRelationEntity();
					relationEntity.setActivityId(activityId);
					relationEntity.setContactId(contactId);
					relationEntity.setCreateTime(currentTime);
					relationEntity.setUpdateTime(currentTime);
					try{
						khActivityContactRelationEntityService.saveIgnore(relationEntity);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}





		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = khContactEntityService.deleteById(id);
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	/**
	 *
	 * @param model
	 * @param req
	 * @return
	 */
	private KhContactEntityCriteria buildQueryCriteria(Model model, HttpServletRequest req) {
		KhContactEntityCriteria criteria = new KhContactEntityCriteria();
		// 倒序排列
		criteria.setOrderByClause(" id desc");

		KhContactEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		// 用户名筛选字段
		String name = req.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			subCriteria.andNameLike("%" + StringUtils.trim(name) + "%");
			model.addAttribute("name", name);
		}

		//筛选字段
		String mobile = req.getParameter("mobile");
		if (StringUtils.isNotBlank(mobile)) {
			subCriteria.andMobileLike("%" + StringUtils.trim(mobile) + "%");
			model.addAttribute("mobile", mobile);
		}

		//筛选字段
		String telphone = req.getParameter("telphone");
		if (StringUtils.isNotBlank(telphone)) {
			subCriteria.andTelphoneLike("%" + StringUtils.trim(telphone) + "%");
			model.addAttribute("telphone", telphone);
		}

		//筛选字段
		String remark = req.getParameter("remark");
		if (StringUtils.isNotBlank(remark)) {
			subCriteria.andRemarkLike("%" + StringUtils.trim(remark) + "%");
			model.addAttribute("remark", remark);
		}

		//筛选字段
		String email = req.getParameter("email");
		if (StringUtils.isNotBlank(email)) {
			subCriteria.andEmailLike("%" + StringUtils.trim(email) + "%");
			model.addAttribute("email", email);
		}

		//筛选字段
		String address = req.getParameter("address");
		if (StringUtils.isNotBlank(address)) {
			subCriteria.andAddressLike("%" + StringUtils.trim(address) + "%");
			model.addAttribute("address", address);
		}

		//筛选字段
		String title = req.getParameter("title");
		if (StringUtils.isNotBlank(title)) {
			subCriteria.andTitleLike("%" + StringUtils.trim(title) + "%");
			model.addAttribute("title", title);
		}

		//筛选字段
		String company = req.getParameter("company");
		if (StringUtils.isNotBlank(company)) {
			subCriteria.andCompanyLike("%" + StringUtils.trim(company) + "%");
			model.addAttribute("company", company);
		}

		//筛选字段
		String companyEn = req.getParameter("companyEn");
		if (StringUtils.isNotBlank(companyEn)) {
			subCriteria.andCompanyEnLike("%" + StringUtils.trim(companyEn) + "%");
			model.addAttribute("companyEn", companyEn);
		}

		//筛选字段
		String companyWebsite = req.getParameter("companyWebsite");
		if (StringUtils.isNotBlank(companyWebsite)) {
			subCriteria.andCompanyWebsiteLike("%" + StringUtils.trim(companyWebsite) + "%");
			model.addAttribute("companyWebsite", companyWebsite);
		}

		//筛选字段
		String industry = req.getParameter("industry");
		if (StringUtils.isNotBlank(industry)) {
			subCriteria.andIndustryLike("%" + StringUtils.trim(industry) + "%");
			model.addAttribute("industry", industry);
		}

		//筛选字段
		String fax = req.getParameter("fax");
		if (StringUtils.isNotBlank(fax)) {
			subCriteria.andFaxLike("%" + StringUtils.trim(fax) + "%");
			model.addAttribute("fax", fax);
		}


		//筛选字段
		String department = req.getParameter("department");
		if (StringUtils.isNotBlank(department)) {
			subCriteria.andDepartmentLike("%" + StringUtils.trim(department) + "%");
			model.addAttribute("department", department);
		}

		criteria.setOrderByClause("  name asc");

		return criteria;
	}


	public static final Map<String, String> keyMap = new HashMap<>();
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
	public static void export(String businessName, String[] exportFieldKeyArray, List<KhContactEntity> dataList,
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
//				headerNames = fieldKeyList.stream().map(keyMap::get).collect(Collectors.toList());
			}else{
				fieldKeyList = Arrays.stream(exportFieldKeyArray).filter(keyMap::containsKey).collect(Collectors.toList());
				//double check
				if(CollectionUtils.isEmpty(fieldKeyList)){
					fieldKeyList = keyMap.keySet().stream().collect(Collectors.toList());
				}
			}

			headerNames = fieldKeyList.stream().map(keyMap::get).collect(Collectors.toList());


//			List<String> headerNames = Arrays.asList(
//					"姓名", "手机", "电话",  "身份证", "备注", "简介",
//					"公司名", "公司英文名", "公司网址", "职位", "部门", "邮箱", "传真",
//					"地址", "邮编", "所在行业"
//					//"生日",  "国籍",  "性别",
//			);
			exportData.setKeyList(headerNames);

			if (CollectionUtils.isNotEmpty(dataList)) {
				// 构造数据集
				List<List<String>> excelDataList = new ArrayList<>();
				for (KhContactEntity entity : dataList) {

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
