package com.shan.yellowpages.controller.login;

import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.struct.NavMenuStruct;
import com.shan.yellowpages.security.service.IKhAdminNavMenuEntityService;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户登录Controller，主要完成用户名和密码校验
 *
 * @author xuejw
 */
@Slf4j
@Controller
@RequestMapping("/passport")
public class PassportController extends AbstractBaseController implements InitializingBean {

//	private static final Logger log = LoggerFactory.getLogger(PassportController.class);
	
	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;
	@Autowired
	private IKhAdminNavMenuEntityService khAdminNavMenuEntityService;

	/**
	 * 是否需要验证码
	 */
	private boolean checkValidateCode = true;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
		Assert.notNull(khAdminNavMenuEntityService, "khAdminNavMenuEntityService can't be null");
	}

	/**
	 * login页面
	 */
	@RequestMapping("/login")
	public String login(
	        Model model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		log.debug("[login] entering req:{},res:{},username:{},password:{}", req, res, username, password);
		
		boolean isLogin = isLogin(req);
		if(isLogin){
			return "redirect:/home/index";
		}

		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return "passport/login";
	}

	/**
	 * 简单设计的login验证流程
	 */
	@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) throws KhException, KhException {
		log.debug("[doLogin] entering req:{},res:{},username:{},password:{}", req, res, username, password);
		String target = "/passport/login";

		if (!StringUtils.isAllBlank(username, password)) {
			// 判断用户名密码是否正确
			KhAdminUserEntity khAdminUserEntity =  khAdminUserEntityService.userAuthentication(username, password);
			if (KhAdminUserEntity.isValid(khAdminUserEntity)) {
				// TODO 获取最近一次url请求，跳转到该地址
				target = "/home/index";
				addSession(req, khAdminUserEntity);

			}
		}
		return "redirect:" + target;
	}

	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		removeSession(req);
		return "redirect:/passport/login";
	}

//	/**
//	 * 错误页面
//	 */
//	@RequestMapping("/failure")
//	public String error(Model model, HttpServletRequest req, HttpServletResponse res,
//						@RequestParam(value = "message", required = false) String message,
//						@RequestParam(value = "errorMessageData", required = false) String errorMessageData,
//						@RequestParam(value = "errorCode", required = false) String errorCode) {
//		log.debug("[error] entering req:{},res:{},message:{},errorCode:{}", req, res, message, errorCode);
//		model.addAttribute("message", message);
//		model.addAttribute("errorMessageData", errorMessageData);
//		model.addAttribute("errorCode", errorCode);
//		return "failure";
//	}


	/**
	 * 登录信息加入session
	 *
	 * @param req {@link HttpServletRequest}
	 * @param khAdminUserEntity 当前登录用户信息
	 */
	private void addSession(HttpServletRequest req, KhAdminUserEntity khAdminUserEntity) {
		HttpSession session = req.getSession();

		//写入已登录用户对象
		session.setAttribute(SESSION_USER_KEY, khAdminUserEntity);

		// 获取当前用户导航栏数据,用于展示（读取menu表）
		List<NavMenuStruct> navResourceList = khAdminNavMenuEntityService.getMenuList(NavMenuStruct.NAV_TYPE_SIDEBAR_LEFT, null,  false);
		session.setAttribute(SESSION_NAV_MENU, navResourceList);

	}

	/**
	 * 登录信息从session中移除
	 *
	 * @param req {@link HttpServletRequest}
	 */
	public static void removeSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(SESSION_USER_KEY);
	}

}