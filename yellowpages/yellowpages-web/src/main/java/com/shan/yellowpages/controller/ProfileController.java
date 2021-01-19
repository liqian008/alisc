package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * home页的Controller
 *
 * @author xuejw
 */
@Controller
@AuthorizeConfig
@RequestMapping("/home")
public class ProfileController extends AbstractBaseController implements InitializingBean {

	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}


	/**
	 * 个人页面
	 */
	@RequestMapping("/profile")
	public String profile(Model model, HttpServletRequest req) {
		KhAdminUserEntity khAdminUserEntity = getLoginUser(req);
//		model.addAttribute("khAdminUserEntity", khAdminUserEntity);
		return "home/profile";
	}

	/**
	 * 修改密码界面
	 */
	@RequestMapping("/password")
	public String password(Model model) {
		return "home/password";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, String oldPassword,  String newPassword, String rePassword, HttpServletRequest request)
			throws KhException {
		model.addAttribute("redirectUrl", "./password");
		model.addAttribute("message", "修改密码失败");
		// 检查oldPassword
		if (StringUtils.isBlank(oldPassword)) {
			// 密码不能为空
			model.addAttribute("message", "旧密码不能为空");
			return "forward:/home/operationRedirect";
		}

		// 检查newPassword和rePassword
		if (StringUtils.isAnyBlank(newPassword, rePassword)) {
			// 密码不能为空
			model.addAttribute("message", "新密码与确认密码均不能为空");
			return "forward:/home/operationRedirect";
		} else if (!newPassword.equals(rePassword)) {
			// 新密码与确认密码必须一致
			model.addAttribute("message", "新密码与确认密码必须一致");
			return "forward:/home/operationRedirect";
		}
		int userId = getLoginUser(request).getId();
		KhAdminUserEntity userEntity = khAdminUserEntityService.loadById(userId);
		if (KhAdminUserEntity.isValid(userEntity)) {
			int result = khAdminUserEntityService.changePassword(userId, oldPassword, newPassword);
			if (result > 0) {
				model.addAttribute("redirectUrl", "/home/index");
				model.addAttribute("message", null);
				return "forward:/home/operationRedirect";
			}
		}
		return "forward:/home/operationRedirect";
	}

}