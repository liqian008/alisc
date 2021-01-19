package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhCompanyEntity;
import com.shan.yellowpages.security.model.KhCompanyEntityCriteria;
import com.shan.yellowpages.security.service.IKhCompanyEntityService;
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
 * 公司controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/company")
public class KhCompanyController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhCompanyController.class);

	@Autowired
	private IKhCompanyEntityService khCompanyEntityService;


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khCompanyEntityService, "khCompanyEntityService can't be null");
	}


	/**
	 * 分页方式查询
	 */
	@RequestMapping("/paging")
	public String paging(Model model, @RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "100") int pageSize,  HttpServletRequest req) {

//		pageSize = 5;
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		//构造查询参数
		buildParams(req);

		model.addAttribute("pageNo", pageNo);
		KhCompanyEntityCriteria criteria = buildQueryCriteria(model, req);

		KhPagingResult<KhCompanyEntity> pagingResult = khCompanyEntityService.pagingByCriteria(pageNo, pageSize, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "company/companyPaging";
	}



	@RequestMapping("/add")
	public String add(Model model, KhCompanyEntity company, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		company.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("company", company);
		return "company/companyEdit";
	}

	@RequestMapping("/edit")
	public String edit(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhCompanyEntity company = khCompanyEntityService.loadById(id);
		model.addAttribute("company", company);

		return "company/companyEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, KhCompanyEntity company, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
//		model.addAttribute("servletPath", servletPath);

		int result =0;

//		TODO 检查
//		String username = company.getCompanyname();
//		if (StringUtils.isBlank(username)) {
//			model.addAttribute("message", "用户信息输入有误，请检查！");
//			return "forward:/home/operationResult";
//		}

		// 过滤非法字符
//		username = KhValidatorUtil.filterUnSafeChar(username).trim();
//		company.setCompanyname(username);

		Date currentTime = new Date();
		company.setUpdateTime(currentTime);
		if (company.getId() != null && company.getId() > 0) {
			//更新信息
			result = khCompanyEntityService.updateById(company);
		} else {
			// 创建新用户
			company.setCreateTime(currentTime);
			result = khCompanyEntityService.save(company);
		}
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = khCompanyEntityService.deleteById(id);
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	/**
	 *
	 * @param model
	 * @param req
	 * @return
	 */
	private KhCompanyEntityCriteria buildQueryCriteria(Model model, HttpServletRequest req) {
		KhCompanyEntityCriteria criteria = new KhCompanyEntityCriteria();
		// 倒序排列
		criteria.setOrderByClause(" id desc");

		KhCompanyEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		// 公司筛选字段
		String name = req.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			subCriteria.andNameLike("%" + StringUtils.trim(name) + "%");
			model.addAttribute("name", name);
		}

		// 公司筛选字段
		String website = req.getParameter("website");
		if (StringUtils.isNotBlank(website)) {
			subCriteria.andNameLike("%" + StringUtils.trim(website) + "%");
			model.addAttribute("website", website);
		}

		// 公司筛选字段
		String address = req.getParameter("address");
		if (StringUtils.isNotBlank(address)) {
			subCriteria.andNameLike("%" + StringUtils.trim(address) + "%");
			model.addAttribute("address", address);
		}

		return criteria;
	}


}
