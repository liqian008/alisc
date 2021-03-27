package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhActivityEntity;
import com.shan.yellowpages.security.model.KhActivityEntityCriteria;
import com.shan.yellowpages.security.model.KhContactEntityCriteria;
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
import java.util.Date;

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
			int activityId, String name,
			@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "30") int pageSize,  HttpServletRequest req) {

		//pageSize = 5;
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		//构造查询参数
		buildParams(req);

		model.addAttribute("pageNo", pageNo);
		KhContactEntityCriteria criteria = new KhContactEntityCriteria();


		KhPagingResult<ContactStruct> pagingResult = khContactEntityService.pagingDtoByActivity(pageNo, pageSize, activityId, name, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "activity/contactPaging";
	}
}
