package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntityCriteria;
import com.shan.yellowpages.security.service.IKhActivityContactRelationEntityService;
import com.shan.yellowpages.security.service.IKhActivityEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/activityContact")
public class KhActivityContactController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhActivityContactController.class);

	@Autowired
	private IKhActivityEntityService khActivityEntityService;
	@Autowired
	private IKhContactEntityService khContactEntityService;
	@Autowired
	private IKhActivityContactRelationEntityService khActivityContactRelationEntityService;


	@RequestMapping(value = "/delete")
	public String delete(Model model, int activityId, int contactId, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhActivityContactRelationEntityCriteria criteria = new KhActivityContactRelationEntityCriteria();
		criteria.createCriteria().andActivityIdEqualTo(activityId).andContactIdEqualTo(contactId);

		int result = khActivityContactRelationEntityService.deleteByCriteria(criteria);
		req.setAttribute("redirectUrl", "../activity/contactPaging?activityId="+activityId);
		return "forward:/home/operationRedirect";
	}


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khActivityEntityService, "khActivityEntityService can't be null");
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
		Assert.notNull(khActivityContactRelationEntityService, "khActivityContactRelationEntityService can't be null");
	}


}
