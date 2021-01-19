package com.shan.yellowpages.controller.temp;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhAdminUserEntityCriteria;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//import com.shan.yellowpages.security.dao.mapper.UserInfoMapper;
//import com.shan.yellowpages.security.model.UserInfoExample;

/**
 * 用户contoller
 * @author bruce
 */
@RequestMapping
//@Controller
@AuthorizeConfig
@Deprecated
public class UserController implements InitializingBean {

//	@Autowired private IKhAdminUserEntityService khAdminUserEntityService;

//	@Autowired
//	private UserInfoMapper userInfoMapper;
	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}

	@GetMapping("/user") public @ResponseBody Object test(String name) {

		long result = 0;
//		UserInfoExample criteria = new UserInfoExample();
//		result = userInfoMapper.countByExample(criteria);

		KhAdminUserEntityCriteria c = new KhAdminUserEntityCriteria();
		List<KhAdminUserEntity> dataList = khAdminUserEntityService.queryByCriteria(c);
		return dataList;
		//		List<KhAdminUserEntity> dataList = khAdminUserEntityService.queryAll();
//		return result;


//		return "test";
	}




}
