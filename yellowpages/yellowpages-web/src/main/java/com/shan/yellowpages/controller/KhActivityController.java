package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.*;
import com.shan.yellowpages.security.model.struct.ActivityStruct;
import com.shan.yellowpages.security.model.struct.ContactStruct;
import com.shan.yellowpages.security.service.IKhActivityEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.shan.yellowpages.controller.KhContactController.ADMIN_USERNAME_ARRAY;

/**
 * 活动controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/activity")
public class KhActivityController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhActivityController.class);

//	public static final String[] ADMIN_USERNAME_ARRAY = {"liqian", "shanhm"};

	@Autowired
	private IKhActivityEntityService khActivityEntityService;

	@Autowired
	private IKhContactEntityService khContactEntityService;


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khActivityEntityService, "khActivityEntityService can't be null");
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
	}


	/**
	 * 分页方式查询
	 */
	@RequestMapping("/paging")
	public String paging(Model model, @RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "30") int pageSize,  HttpServletRequest req) {

//		pageSize = 5;
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		//构造查询参数
		buildParams(req);

		model.addAttribute("pageNo", pageNo);
		KhActivityEntityCriteria criteria = buildQueryCriteria(model, req);

		KhPagingResult<ActivityStruct> pagingResult = khActivityEntityService.pagingDtoByCriteria(pageNo, pageSize, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "activity/activityPaging";
	}

	@RequestMapping("/add")
	public String add(Model model, KhActivityEntity activity, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		activity.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("activity", activity);
		return "activity/activityEdit";
	}


	@RequestMapping("/edit")
	public String edit(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhActivityEntity activity = khActivityEntityService.loadById(id);
		model.addAttribute("activity", activity);

		return "activity/activityEdit";
	}


	@RequestMapping("/info")
	public String info(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhActivityEntity activity = khActivityEntityService.loadById(id);
		model.addAttribute("activity", activity);
		return "activity/activityInfo";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, KhActivityEntity activity, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
//		model.addAttribute("servletPath", servletPath);

		int result =0;
		// 过滤非法字符
//		username = KhValidatorUtil.filterUnSafeChar(username).trim();
//		activity.setActivityname(username);

		Date currentTime = new Date();
		activity.setLastModUid(getLoginUser(req).getId());
		activity.setUpdateTime(currentTime);
		if (activity.getId() != null && activity.getId() > 0) {
			//更新信息
			result = khActivityEntityService.updateById(activity);
		} else {
			// 创建新用户
			activity.setCreateTime(currentTime);
			result = khActivityEntityService.save(activity);
		}

		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = khActivityEntityService.deleteById(id);
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	/**
	 *
	 * @param model
	 * @param req
	 * @return
	 */
	private KhActivityEntityCriteria buildQueryCriteria(Model model, HttpServletRequest req) {
		KhActivityEntityCriteria criteria = new KhActivityEntityCriteria();
		// 倒序排列
		criteria.setOrderByClause(" id desc");

		KhActivityEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		// 名称筛选字段
		String name = req.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			subCriteria.andNameLike("%" + StringUtils.trim(name) + "%");
			model.addAttribute("name", name);
		}

		// 名称筛选字段
		String description = req.getParameter("description");
		if (StringUtils.isNotBlank(description)) {
			subCriteria.andDescriptionLike("%" + StringUtils.trim(description) + "%");
			model.addAttribute("description", description);
		}
		criteria.setOrderByClause("  name asc");
		return criteria;
	}




	/**
	 * 分页方式查询
	 */
	@RequestMapping("/contactPaging")
	public String paging(Model model,
			@RequestParam int activityId,
			String name,
			@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "30") int pageSize,  HttpServletRequest req) {

		//pageSize = 5;
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		boolean exportEnable = false;
		//hardcode FIXME
		KhAdminUserEntity userEntity = getLoginUser(req);
		if(Arrays.stream(ADMIN_USERNAME_ARRAY).anyMatch((item)->StringUtils.equalsIgnoreCase(userEntity.getUsername(), item))){
			exportEnable = true;
		}
		model.addAttribute("exportEnable", exportEnable);


		model.addAttribute("activityId", activityId);

		//构造查询参数
		String param = buildParams(req);
		param = param + "&" + "activityId="+activityId;
		req.setAttribute("params", param);

		KhActivityEntity activity = khActivityEntityService.loadById(activityId);
		model.addAttribute("activity", activity);

		model.addAttribute("pageNo", pageNo);
		KhContactEntityCriteria criteria = new KhContactEntityCriteria();

		if(StringUtils.isBlank(name)){
			name = null;
		}else{
			model.addAttribute("name", name);
			name = "%" + name + "%";
		}

		KhPagingResult<ContactStruct> pagingResult = khContactEntityService.pagingDtoByActivity(pageNo, pageSize, activityId, name, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "activity/contactPaging";
	}


	/**
	 * 数据导出excel
	 */
	@RequestMapping("/exportContacts")
	public void exportContacts(
			@RequestParam int activityId,
			HttpServletRequest req, HttpServletResponse res) {

		//FIXME
		KhAdminUserEntity userEntity = getLoginUser(req);
		if(!Arrays.stream(ADMIN_USERNAME_ARRAY).anyMatch((item)->StringUtils.equalsIgnoreCase(userEntity.getUsername(), item))){
			throw new RuntimeException("没有导出权限");
		}

		KhActivityEntity activity = khActivityEntityService.loadById(activityId);

		KhContactEntityCriteria criteria = new KhContactEntityCriteria();
		List<KhContactEntity> dataList = khContactEntityService.listDtoByActivity(activityId, criteria);

		String[] exportFieldArray = req.getParameterValues("exportFields");

		try {
			export("【"+activity.getName()+"】联系人导出", exportFieldArray, dataList, req, res);
		} catch (UnsupportedEncodingException e) {
		}


//		KhContactEntityCriteria criteria = new KhContactEntityCriteria();
//		if(StringUtils.isBlank(name)){
//			name = null;
//		}else{
//			name = "%" + name + "%";
//		}
//		List<ContactStruct> dataList = khContactEntityService.listDtoByActivity(activityId, name, criteria);
//
//
//		String[] exportFieldArray = req.getParameterValues("exportFields");
//
//		try {
//			export("联系人导出", exportFieldArray, dataList, req, res);
//		} catch (UnsupportedEncodingException e) {
//
//		}
	}



}
