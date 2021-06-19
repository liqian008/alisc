package com.shan.yellowpages.controller;

import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.service.IKhActivityContactRelationEntityService;
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
 * 联系人controller
 * @author bruce
 */
@Controller
@RequestMapping("/aaa")
public class KhInputController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhInputController.class);

	public static final String[] ADMIN_USERNAME_ARRAY = {"liqian", "shanhm"};

	@Autowired
	private IKhContactEntityService khContactEntityService;
	@Autowired
	private IKhActivityContactRelationEntityService khActivityContactRelationEntityService;



	@Override public void afterPropertiesSet() throws Exception {
		Assert.notNull(khContactEntityService, "khContactEntityService can't be null");
		Assert.notNull(khActivityContactRelationEntityService, "khActivityContactRelationEntityService can't be null");
	}


//	/**
//	 * 分页方式查询
//	 */
//	@RequestMapping("/paging")
//	public String paging(Model model, @RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "30") int pageSize,  HttpServletRequest req) {
//
////		pageSize = 5;
//		String servletPath = req.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//
//
//		boolean exportEnable = false;
//		//hardcode FIXME
//		KhAdminUserEntity userEntity = getLoginUser(req);
//		if(Arrays.stream(ADMIN_USERNAME_ARRAY).anyMatch((item)->StringUtils.equalsIgnoreCase(userEntity.getUsername(), item))){
//			exportEnable = true;
//		}
//		model.addAttribute("exportEnable", exportEnable);
//
//		//构造查询参数
//		buildParams(req);
//
//		model.addAttribute("pageNo", pageNo);
//		KhContactEntityCriteria criteria = buildQueryCriteria(model, req);
//
//		model.addAttribute("exportKeyMap", keyMap);
//
//		KhPagingResult<ContactStruct> pagingResult = khContactEntityService.pagingDtoByCriteria(pageNo, pageSize, criteria);
////		KhPagingResult<KhContactEntity> pagingResult = khContactEntityService.pagingByCriteria(pageNo, pageSize, criteria);
//		model.addAttribute("pagingData", pagingResult);
//		return "contact/contactPaging";
//	}





	@RequestMapping("/input")
	public String input(Model model, HttpServletRequest req) {
		String servletPath = req.getRequestURI();
		model.addAttribute("servletPath", servletPath);

//		KhContactEntity contact = khContactEntityService.loadById(id);
//		model.addAttribute("contact", contact);


//		List<ContactActivityStruct> activityStructList = processActivityList(id);
//		model.addAttribute("activityStructList", activityStructList);
//
//		if(KhContactEntity.isValid(contact) && StringUtils.isBlank(contact.getAvatar())){
//			contact.setAvatar(ContactStruct.AVATAR_DEFAULT);
//		}
		return "contact/input";
	}


}
