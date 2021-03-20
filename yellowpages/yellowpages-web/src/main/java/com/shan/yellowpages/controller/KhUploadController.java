package com.shan.yellowpages.controller;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.model.data.KhResponseResult;
import com.shan.yellowpages.base.model.upload.struct.KhUploadResult;
import com.shan.yellowpages.base.utils.encrypt.Md5Utils;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * 上传controller
 * @author bruce
 */
@Controller
@AuthorizeConfig
@RequestMapping("/upload")
public class KhUploadController extends AbstractBaseController implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhUploadController.class);

	@Value("${upload.path.local}")
	private String uploadBasePath;

	@Value("${upload.url.suffix}")
	private String uploadUrlSuffix;


	//	@Autowired
//	private IKhUploadEntityService khUploadEntityService;


	@Override public void afterPropertiesSet() throws Exception {
//		Assert.notNull(khUploadEntityService, "khUploadEntityService can't be null");
	}


	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest req) {
		return "upload/upload";
	}


	//将byte数组写入文件
//	public String createFile(String filePath, byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream(filePath);
//		fos.write(bytes);
//		fos.close();
//		return filePath;
//	}

	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/uploadFile.json")
	public @ResponseBody KhResponseResult<KhUploadResult> uploadFileJson(@RequestParam("file") MultipartFile[] files)
			throws IOException {


		KhUploadResult uploadResult = new KhUploadResult();

		String fileOriginalName = files[0].getOriginalFilename();

		byte[] bytes = files[0].getBytes();
//		if(bytes.length > 1024*2){
		if(bytes.length > 1024*1024*2){
			throw new KhRuntimeException(IKhErrorCode.UPLOAD_FILE_ERROR, "上传文件不得超过2M", "上传文件不得超过2M");
		}

		String resUrl = uploadFile(fileOriginalName, bytes);
		uploadResult.setResUrl(resUrl);

		return KhResponseResult.buildSuccessResult(uploadResult);


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



	}

	/**
	 *
	 * @return
	 */
	private String uploadFile(String fileOriginalName, byte[] bytes) throws IOException {

		Calendar calc = Calendar.getInstance();
		int year = calc.get(Calendar.YEAR);
		int month = calc.get(Calendar.MONTH) + 1;

		int randomNum = RandomUtils.nextInt(0, 1000);

		String extName = StringUtils.substring(fileOriginalName, fileOriginalName.lastIndexOf("."), fileOriginalName.length());

		String fileName = Md5Utils.md5Encode(System.currentTimeMillis() + "_"+randomNum)+extName;

		String resDirPath = "/"+ year+"/"+(month<10?"0"+month:month);
		String localDirPath = uploadBasePath + resDirPath;

		File file = new File(localDirPath);
		file.mkdirs();


		FileOutputStream fos = new FileOutputStream(localDirPath + "/" + fileName);
		fos.write(bytes);
		fos.close();

		String result = uploadUrlSuffix + resDirPath + "/"+fileName;
		return result;
	}



}
