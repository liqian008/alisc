//package com.shan.yellowpages.base.utils;
//
//import com.shan.yellowpages.base.utils.encrypt.Md5Utils;
//import com.temujin.khaan.constants.DynamicConstConfig;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.*;
//import java.util.Date;
//
//
///**
// * 上传工具类
// *
// */
//public class UploadUtil {
//
//
//	private final static String DATE_FORMAT = "yyyyMMdd";
//
//    public static final String FILE_SEPARTOR = System.getProperty("file.separator");
//
//
//
//	/** 资源链接 */
////	public static final String RES_PATH = "//"+UPLOAD_RES_DOMAIN + FILE_SEPARTOR + UPLOAD_RES_RELATIVE_PATH;
//
//
//	/**
//	 * 获取保存文件的全路径
//	 *
//	 * @return
//	 */
//	public static String getStorageRootPath(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_STORAGE_ROOT);
//		return result;
//	}
//
//
//	/**
//	 * 获取图片的相对路径
//	 *
//	 * @return
//	 */
//	public static String getImageRelativePath(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_IMAGE_DIV_RELATIVE_PATH);
//		return result;
//	}
//
//	/**
//	 * 获取文件的相对路径
//	 *
//	 * @return
//	 */
//	public static String getFileRelativePath(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_FILE_DIR_RELATIVE_PATH);
//		return result;
//	}
//
//	/**
//	 * 获取视频的相对路径
//	 *
//	 * @return
//	 */
//	public static String getVideoRelativePath(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_VIDEO_DIR_RELATIVE_PATH);
//		return result;
//	}
//
//
//
//
//
//	/**
//	 * 获取访问资源的域名
//	 *
//	 * @return
//	 */
//	public static String getResDomain(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_RES_DOMAIN);
//		return result;
//	}
//
//	/**
//	 * 获取访问资源的相对路径
//	 *
//	 * @return
//	 */
//	public static String getResRelativePath(){
//		String result =  StringUtils.trimToEmpty(DynamicConstConfig.UPLOAD_RES_RELATIVE_PATH);
//		return result;
//	}
//
//	/**
//	 * 获取访问资源的基础url path
//	 * 规则： 域名 + 相对路径
//	 *
//	 * @return
//	 */
//	public static String getResUrlPath(){
//		String result =  "//"+ getResDomain() + getResRelativePath();
//		return result;
//	}
//
//
//
//
//
//
//	/**
//     * 返回系统生成的文件名
//     * 格式为： $prefix_$time_$suffix.文件后缀名
//     * @param prefix 前缀
//     * @param fileName
//     * @param suffix 后缀
//     * @param time
//     * @return
//     */
//    public static String getFileName(String prefix, String fileName, String suffix, long time) {
//        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        StringBuffer sb = new StringBuffer();
//        if(StringUtils.isNotBlank(prefix)){
//	        sb.append(prefix);
//	        sb.append("_");
//        }
//        sb.append(Md5Utils.md5Encode(prefix + "_" + time));
//
//        //后缀
//        if (StringUtils.isNotEmpty(suffix)) {
//            sb.append("_");
//            sb.append(suffix);
//        }
//
//        if(StringUtils.isNotBlank(extName)){
//			sb.append(".");
//			sb.append(extName);
//		}
//        return sb.toString();
//    }
//
//    public static String getFileName(String fileName) {
//        return getFileName(null, fileName, null, System.currentTimeMillis());
//    }
//
//
//
////    /**
////   	 * 获取文件保存的基础路径（绝对路径）
////   	 * @return
////   	 */
////   	public static String getBasePath() {
////   		String basePath = DynamicConstConfig.UPLOAD_STORAGE_ROOT;
////   		return basePath;
////   	}
//
////	/**
////	 * 获取文件访问的基础路径（url）
////	 * @return
////	 */
////	public static String getBaseUrl() {
////    	String domain = BASE_RES_DOMAIN;
////    	String contextPath = BASE_RES_CONTEXT_PATH;
////    	String basePath = domain + contextPath + BASE_RES_BASE;
////        return basePath;
////    }
//
//	/**
//	 * 构造目录名称
//	 * @return
//	 */
//	private static String getDictionary() {
//        return getDictionary(System.currentTimeMillis());
//    }
//
//	/**
//	 * 根据时间戳构造目录名称
//	 * @return
//	 */
//   	private static String getDictionary(long timeMillis) {
//   		String result = DateFormatUtils.format(new Date(timeMillis), DATE_FORMAT);
//        return result;
//    }
//
////   	/**
////	 * 获取用户头像保存的相对路径
////	 * @param userId
////	 * @return
////	 */
////	public static String getAvatarPath() {
////		String avatarPath = ConfigUtil.getString("upload_path_avatar") ;
////		return avatarPath;
////	}
//
//
//   /**
//	 * 获取用户图片保存的相对路径
//	 * @param timeMillis
//	 * @return
//	 */
//	public static String getImagePath(long timeMillis) {
//		String imagePath = getImageRelativePath() + UploadUtil.FILE_SEPARTOR + getDictionary(timeMillis) ;
//		return imagePath;
//	}
//
//	/**
//     * 获取用户图片保存的相对路径
//     * @return
//     */
//    public static String getImagePath() {
//        return getImagePath(System.currentTimeMillis());
//    }
//
//    /**
//	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
//	 *
//	 * @param timeMillis
//	 * @return
//	 */
//	public static String getFilePath(long timeMillis) {
//		String filePath = getFileRelativePath() + UploadUtil.FILE_SEPARTOR + getDictionary(timeMillis);
//		return filePath;
//	}
//
//	/**
//	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
//	 *
//	 * @return
//	 */
//	public static String getVideoPath() {
//		return getFilePath(System.currentTimeMillis());
//	}
//
//	/**
//	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
//	 *
//	 * @param timeMillis
//	 * @return
//	 */
//	public static String getVideoPath(long timeMillis) {
//		String filePath = getVideoRelativePath() + UploadUtil.FILE_SEPARTOR + getDictionary(timeMillis);
//		return filePath;
//	}
//
//	/**
//	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
//	 *
//	 * @return
//	 */
//	public static String getFilePath() {
//		return getFilePath(System.currentTimeMillis());
//	}
//
//
//
//	/**
//	 * 保存文件，返回文件
//	 *
//	 * @param data
//	 * @param dirPath 文件夹路径
//	 * @param filename	文件名
//	 * @return
//	 * @throws IOException
//	 */
//	public static File saveFile(byte[] data, String dirPath, String filename) throws IOException {
//		File dir = new File(dirPath);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		File file = new File(dir, filename);
//		FileCopyUtils.copy(data, file);
//		return file;
//	}
//
//
//	/**
//	 * 获取文件的二进制流
//	 * @param file
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] file2bytes(File file) throws IOException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
//		BufferedInputStream in = null;
//		try {
//			in = new BufferedInputStream(new FileInputStream(file));
//			int buf_size = 1024;
//			byte[] buffer = new byte[buf_size];
//			int len = 0;
//			while (-1 != (len = in.read(buffer, 0, buf_size))) {
//				bos.write(buffer, 0, len);
//			}
//			return bos.toByteArray();
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			bos.close();
//		}
//	}
//
//
//
//	//    /**
//	//     * 根据url判断文件是否存在
//	//     * @param fileUrl
//	//     * @return
//	//     */
//	//    public static boolean fileExists(String fileUrl){
//	//    	File file = loadFileByUrl(fileUrl);
//	//    	if(file!=null){
//	//    		return true;
//	//    	}
//	//    	return false;
//	//    }
//	//
//	//    /**
//	//     * 根据url构造file
//	//     * @param fileUrl
//	//     * @return
//	//     */
//	//    public static File loadFileByUrl(String fileUrl){
//	//    	if(fileUrl!=null){
//	//    		String abFilePath = fileUrl.replace(getBaseUrl(), getBasePath());
//	//    		File file = new File(abFilePath);
//	//    		if(file.exists()){
//	//    			return file;
//	//    		}
//	//    	}
//	//    	return null;
//	//    }
//
//}
