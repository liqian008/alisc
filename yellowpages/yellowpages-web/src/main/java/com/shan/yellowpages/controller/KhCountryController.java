package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhCountryEntity;
import com.shan.yellowpages.security.model.KhCountryEntityCriteria;
import com.shan.yellowpages.security.service.IKhCountryEntityService;
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
 * 国家字典controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/country")
public class KhCountryController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhCountryController.class);

	@Autowired
	private IKhCountryEntityService khCountryEntityService;


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khCountryEntityService, "khCountryEntityService can't be null");
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
		KhCountryEntityCriteria criteria = buildQueryCriteria(model, req);

		KhPagingResult<KhCountryEntity> pagingResult = khCountryEntityService.pagingByCriteria(pageNo, pageSize, criteria);
		model.addAttribute("pagingData", pagingResult);
		return "country/countryPaging";
	}



	@RequestMapping("/add")
	public String add(Model model, KhCountryEntity country, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		country.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("country", country);
		return "country/countryEdit";
	}

	@RequestMapping("/edit")
	public String edit(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhCountryEntity country = khCountryEntityService.loadById(id);
		model.addAttribute("country", country);

		return "country/countryEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, KhCountryEntity country, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
//		model.addAttribute("servletPath", servletPath);

		int result =0;

		Date currentTime = new Date();
		country.setUpdateTime(currentTime);
		if (country.getId() != null && country.getId() > 0) {
			//更新信息
			result = khCountryEntityService.updateById(country);
		} else {
			// 创建新用户
			country.setCreateTime(currentTime);
			result = khCountryEntityService.save(country);
		}
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, int id, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = khCountryEntityService.deleteById(id);
		req.setAttribute("redirectUrl", "./paging");
		return "forward:/home/operationRedirect";
	}

	/**
	 *
	 * @param model
	 * @param req
	 * @return
	 */
	private KhCountryEntityCriteria buildQueryCriteria(Model model, HttpServletRequest req) {
		KhCountryEntityCriteria criteria = new KhCountryEntityCriteria();
		// 倒序排列
		criteria.setOrderByClause(" sort asc, code asc");

		KhCountryEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		// 国家筛选字段
		String code = req.getParameter("code");
		if (StringUtils.isNotBlank(code)) {
			subCriteria.andCodeLike("%" + StringUtils.trim(code) + "%");
			model.addAttribute("code", code);
		}

		// 英文名称筛选字段
		String enName = req.getParameter("enName");
		if (StringUtils.isNotBlank(enName)) {
			subCriteria.andEnNameLike("%" + StringUtils.trim(enName) + "%");
			model.addAttribute("enName", enName);
		}

		// 中文名称筛选字段
		String cnName = req.getParameter("cnName");
		if (StringUtils.isNotBlank(cnName)) {
			subCriteria.andEnNameLike("%" + StringUtils.trim(cnName) + "%");
			model.addAttribute("cnName", cnName);
		}

		return criteria;
	}


}
