package com.shan.yellowpages.controller;

import com.shan.yellowpages.base.enumeration.StatusEnum;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.enumeration.KhSecurityEnum;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhAdminUserEntityCriteria;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuejw
 */
@Controller
@RequestMapping("/sys/user")
public class KhAdminUserController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhAdminUserController.class);

	private static final int pageSize = 50;

	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;


	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}


	/**
	 * 分页方式查询
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "100") int pageSize,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("pageNo", pageNo);

		KhAdminUserEntityCriteria criteria = new KhAdminUserEntityCriteria();
		// 倒序排列
		criteria.setOrderByClause(" id desc");

		KhAdminUserEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		// 用户名筛选字段
		String userName = request.getParameter("userName");

		if (StringUtils.isNotBlank(userName)) {
			subCriteria.andUsernameLike("%" + userName + "%");
			model.addAttribute("userName", userName);
		}

		// 用户类型筛选字段
		String userTypeStr = request.getParameter("userType");

		if (StringUtils.isNotBlank(userTypeStr)) {
			short userType = Short.parseShort(userTypeStr);
			subCriteria.andUserTypeEqualTo(userType);
		}

		subCriteria.andUserTypeNotEqualTo(KhSecurityEnum.UserTypeEnum.SUPER_ADMIN.getValue());

		Map<Short, String> userTypeMap = getUserTypeMap(true);
		model.addAttribute("userTypeMap", userTypeMap);
		model.addAttribute("userType", userTypeStr);

//		KhResponseResult<KhPagingResult<KhAdminUserEntity>> khResponseResult = khAdminUserEntityService.pagingByCriteria(pageNo, pageSize, criteria);
//		if (KhResponseResult.isSuccess(khResponseResult)) {
//			model.addAttribute("pageData", khResponseResult.getData());
//		}
		return "authority/userList";
	}

	private Map<Short, String> getUserTypeMap(boolean isSuperAdmin) {
		Stream<KhSecurityEnum.UserTypeEnum> stream = Arrays.stream(KhSecurityEnum.UserTypeEnum.values());
		if (!isSuperAdmin) {
			stream = stream.filter(a -> !a.equals(KhSecurityEnum.UserTypeEnum.SUPER_ADMIN));
		}
		return stream.collect(Collectors.toMap(KhSecurityEnum.UserTypeEnum::getValue, KhSecurityEnum.UserTypeEnum::getName, (k1, k2) -> k1));
	}

	@RequestMapping("/add")
	public String userAdd(Model model, KhAdminUserEntity adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		adminUser.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("adminUser", adminUser);
//		Map<Short, String> userTypeMap = getUserTypeMap(KhAdminUserEntity.isSupperAdminUser(getUserInfo()));
//		model.addAttribute("userTypeMap", userTypeMap);
		return "authority/userEdit";
	}

	@RequestMapping("/edit")
	public String edit(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		KhAdminUserEntity adminUser = khAdminUserEntityService.loadById(id);
//		KhAdminUserEntity adminUser = khResponseResult.getData();
		model.addAttribute("adminUser", adminUser);

//		Map<Short, String> userTypeMap = getUserTypeMap(KhAdminUserEntity.isSupperAdminUser(getUserInfo()));
//		model.addAttribute("userTypeMap", userTypeMap);
		return "authority/userEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, KhAdminUserEntity adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

//		KhResponseResult<Integer> result;
		int result =0;

		String username = adminUser.getUsername();
		if (StringUtils.isBlank(username)) {
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/home/operationResult";
		}

		// 过滤非法字符
//		username = KhValidatorUtil.filterUnSafeChar(username).trim();
//		adminUser.setUsername(username);

		Date currentTime = new Date();
		adminUser.setUpdateTime(currentTime);
		if (adminUser.getId() != null && adminUser.getId() > 0) {
			adminUser.setUsername(null);
			// 只有管理员可以修改用户密码
			if (StringUtils.isNotBlank(adminUser.getPassword())) {
				String password = adminUser.getPassword();
				adminUser.setPassword(DigestUtils.md5Hex(password));
				adminUser.setUpdateTime(currentTime);
			} else {
				adminUser.setPassword(null);
			}
			result = khAdminUserEntityService.updateById(adminUser);
			logger.info("[saveUser]result:{},adminUser:{}", result, adminUser);
		} else {
//			adminUser.setCreatorId(getUserId());

			// 创建新用户时对密码进行加密
			String password = adminUser.getPassword();
			adminUser.setPassword(DigestUtils.md5Hex(password));
			adminUser.setCreateTime(currentTime);
			result = khAdminUserEntityService.save(adminUser);

//			if (KhResponseResult.isSuccess(result) && result.getData() > 0) {
//				// 关联每个用户角色，以保证可以正常登录个人中心
//				Integer[] roleIds = { 2 };
//				List<Integer> roleIdList = Arrays.asList(roleIds);
//				// TODO khAdminUserEntityService.updateUserRoles(adminUser.getId(),
//				// roleIdList);
//			}
		}
		model.addAttribute("redirectUrl", "./list");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping(value = "/del")
	public String del(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = khAdminUserEntityService.deleteById(id);
		// 删除单个+TODO 用户关联资源
		// khAdminUserEntityService.deleteById(id);
		model.addAttribute("redirectUrl", "./list");
		return "forward:/home/operationRedirect";
	}

//	/**
//	 * 根据 id 删除对应的数据
//	 *
//	 * @param id id
//	 * @return 操作结果
//	 */
//	@DeleteMapping("/del.json")
//	public ModelAndView deleteByAjax(@RequestParam("id") int id) {
//		logger.debug("[deleteByAjax] enter, id = {}", id);
//		KhResponseResult<Integer> khResponseResult = khAdminUserEntityService.deleteById(id);
//		if (KhResponseResult.isSuccess(khResponseResult) && khResponseResult.getData() >0) {
//			KhAdminUserRoleEntityCriteria criteria = new KhAdminUserRoleEntityCriteria();
//			criteria.createCriteria().andUserIdEqualTo(id);
//			khAdminUserRoleEntityService.deleteByCriteria(criteria);
//		}
//		return KhWebResponseBuilderUtil.buildJsonView(khResponseResult);
//	}
	
//	/**
//	 * 进入用户角色关联界面
//	 */
//	@RequestMapping("/userRoleSet")
//	public String userRoleSet(Model model, int userId, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		// 取用户
//		KhAdminUserEntity adminUser = khAdminUserEntityService.loadById(userId).getData();
//		// 取所有正常的角色(status=1)
//		List<KhAdminRoleEntity> allRoles = khAdminRoleEntityService.queryRoles(PermissionStruct.ACCESS_TYPE_NORMAL, StatusEnum.ENABLE.getStatus())
//				.getData();
//		// 取用户拥有的角色
//		List<KhAdminRoleEntity> userRoles = khAdminRoleEntityService.queryRolesByUserId(userId, null).getData();
//
//		model.addAttribute("adminUser", adminUser);
//
//
//
//		List<KhSecurityRelationStruct> khSecurityRelationStructList = new ArrayList<>();
//		for (KhAdminRoleEntity khAdminRoleEntity : allRoles) {
//			// 非超级权限的内容不展示
//			if (!KhAdminUserEntity.isSupperAdminUser(getUserInfo())) {
//				if (khAdminRoleEntity.getAccessType() == KhSecurityEnum.UserTypeEnum.SUPER_ADMIN.getValue()) {
//					continue;
//				}
//			}
//			KhSecurityRelationStruct khSecurityRelationStruct = new KhSecurityRelationStruct();
//			khSecurityRelationStruct.setId(khAdminRoleEntity.getId());
//			khSecurityRelationStruct.setName(khAdminRoleEntity.getRoleName());
//			khSecurityRelationStruct.setStatus(khAdminRoleEntity.getStatus());
//			khSecurityRelationStruct.setStatusDesc(StatusEnum.getName(khAdminRoleEntity.getStatus()));
//			int check = userRoles != null && userRoles.contains(khAdminRoleEntity) ? 1 : 0;
//			khSecurityRelationStruct.setCheck(check);
//			khSecurityRelationStructList.add(khSecurityRelationStruct);
//		}
////		model.addAttribute("allRoles", allRoles);
////		model.addAttribute("userRoles", userRoles);
//		model.addAttribute("khSecurityRelationStructList", khSecurityRelationStructList);
//
//		return "authority/userRoleSet";
//	}
//
//	/**
//	 * 关联用户&角色
//	 */
//	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
//	public String saveUserRole(Model model, Integer userId, Integer[] roleIds, HttpServletRequest request) {
//		if (userId != null && userId > 0) {
//			if (roleIds != null && roleIds.length > 0) {
//				List<Integer> roleIdList = Arrays.asList(roleIds);
//				KhResponseResult<Integer> result = khAdminUserRoleEntityService.updateUserRoles(userId, roleIdList);
//				logger.info("[saveUserRole]result:{},model:{},userId:{},roleIds:{},request:{}", result, model, userId,
//						roleIds, request);
//			}
//		}
//		model.addAttribute("redirectUrl", "./list");
//		return "forward:/home/operationRedirect";
//	}
}
