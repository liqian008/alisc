package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.service.IKhActivityContactRelationEntityService;
import com.shan.yellowpages.security.service.IKhActivityEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

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


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khActivityEntityService, "khActivityEntityService can't be null");
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
		Assert.notNull(khActivityContactRelationEntityService, "khActivityContactRelationEntityService can't be null");
	}





}
