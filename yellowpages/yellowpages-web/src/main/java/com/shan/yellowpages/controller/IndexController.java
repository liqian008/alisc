package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * home页的Controller
 *
 * @author xuejw
 */
@Controller
@RequestMapping("/home")
public class IndexController extends AbstractBaseController implements InitializingBean {

	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}

	/**
	 * index首页
	 */
	@AuthorizeConfig
	@RequestMapping("/index")
	public String index() {
		return "home/index";
	}

	/**
	 * 没有权限访问
	 */
	@RequestMapping("/accessDenied")
	public String accessDenied(Model model, HttpServletRequest request) {
		model.addAttribute("accessDenied", "用户未授权,请登出后选择新用户！！！");
		return "home/accessDenied";
	}

	/**
	 * 数据提交操作中转页，主要是防止POST重复提交
	 *
	 * 注意：外层需使用req.setAttribute("", "")方式写入数据，此处才能拿到req的attribute数据
	 */
	@RequestMapping("/operationRedirect")
	public String operationRedirect(Model model, HttpServletRequest req) {
		return "home/operationRedirect";
	}


//	/**
//	 * 个人页面
//	 */
//	@RequestMapping("/profile")
//	public String profile(Model model, HttpServletRequest req) {
//		KhAdminUserEntity khAdminUserEntity = getLoginUser(req);
////		model.addAttribute("khAdminUserEntity", khAdminUserEntity);
//		return "home/profile";
//	}
//
//	/**
//	 * 修改密码界面
//	 */
//	@RequestMapping("/password")
//	public String password(Model model) {
//		return "home/password";
//	}

	//    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	//	public String changePassword(Model model, HttpServletRequest request, String newPassword, String rePassword) {
	//		model.addAttribute("redirectUrl", "./password");
	//		model.addAttribute("message", "修改密码失败");
	//		// 检查newPassword和rePassword
	//		if (StringUtils.isAnyBlank(newPassword, rePassword)) {
	//			// 密码不能为空
	//			model.addAttribute("message", "新密码与确认密码均不能为空");
	//			return "forward:/home/operationRedirect";
	//		} else if (!newPassword.equals(rePassword)) {
	//			// 新密码与确认密码必须一致
	//			model.addAttribute("message", "新密码与确认密码必须一致");
	//			return "forward:/home/operationRedirect";
	//		}
	//		int userId = getUserId();
	//		KhResponseResult<KhAdminUserEntity> khResponseResult = khAdminUserEntityService.loadById(userId);
	//		if (KhResponseResult.isSuccess(khResponseResult) && khResponseResult.getData() != null) {
	//			int result = khAdminUserEntityService.changeUserPassword(userId, DigestUtils.md5Hex(newPassword)).getData();
	//			if (result > 0) {
	//				model.addAttribute("redirectUrl", "./index");
	//                model.addAttribute("message", null);
	//				return "forward:/home/operationRedirect";
	//			}
	//		}
	//		return "forward:/home/operationRedirect";
	//	}

}