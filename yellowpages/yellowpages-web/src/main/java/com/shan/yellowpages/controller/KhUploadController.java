package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.model.upload.struct.KhUploadResult;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/upload")
public class KhUploadController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhUploadController.class);

//	@Autowired
//	private IKhUploadEntityService khUploadEntityService;


	@Override public void afterPropertiesSet() throws Exception {
//		Assert.notNull(khUploadEntityService, "khUploadEntityService can't be null");
	}


	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest req) {
		return "upload/upload";
	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/uploadFile.json")
	public @ResponseBody KhUploadResult uploadFileJson(@RequestParam("file") MultipartFile[] files) {
		// public ModelAndView uploadFileJson(@RequestParam("file") MultipartFile file)throws IOException {
//		List<KhUploadPackageResult> khUploadPackageResultList = new ArrayList<>();
//		for (CommonsMultipartFile file : files) {
//			KhUploadPackageResult khUploadPackageResult = new KhUploadPackageResult();
//			// 获取文件名，带后缀
//			String originalFilename = file.getOriginalFilename();
//			String suffix = StringUtils.substringAfterLast(originalFilename, ".");
//
//			// 上传使用了http协议，需显示传入khContext
////			KhContext khContext = KhContextUtil.getKhContext();
//			KhResponseResult<KhUploadResult> khResponseResult = khRpcUploadService.uploadFile(khContext, (short) 0, file.getBytes(), originalFilename);
//
//			if (KhResponseResult.isSuccess(khResponseResult)) {
//				khUploadPackageResult.setResUrl(khResponseResult.getData().getResUrl());
//				khUploadPackageResult.setFileType(suffix);
//				khUploadPackageResult.setFileName(originalFilename);
//				khUploadPackageResultList.add(khUploadPackageResult);
//			}
//		}
//		KhAdminResultBuilder<KhUploadPackageResult> builder = new KhAdminResultBuilder<>();
//		builder.listData(khUploadPackageResultList);
//		Map<String, Object> map = builder.build();
//		KhResponseResult<Map<String, Object>> result = KhResponseResult.buildSuccessResult(map);
//		return KhWebResponseBuilderUtil.buildJsonView(result);

		KhUploadResult result = new KhUploadResult("http://xxxx");
		return result;
	}




}
