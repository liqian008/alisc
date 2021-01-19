package com.shan.yellowpages.base.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * 
 * @author bruce
 * @version 1.0
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/** 当前系统文件分隔符 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * 根据byte数组，生成文件
	 * 
	 * @param filePath 文件路径
	 * @param bytes bytes数组
	 * @return 文件
	 */
	public static File byteToFile(String filePath, byte[] bytes) {
		logger.debug("[FileUtil][byteToFile]entering, filePath:{}", filePath);
		File file = new File(filePath);
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			logger.error("[FileUtil][byteToFile] filed, filePath:{}, exception:{}", filePath, e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e1) {
			}
		}
		return file;
	}

	/**
	 * 根据byte数组，生成文件
	 * 
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param bytes bytes数组
	 * @return 文件
	 */
	public static File byteToFile(String filePath, String fileName, byte[] bytes) {
		logger.debug("[FileUtil][byteToFile]entering, filePath:{},fileName:{}", filePath, fileName);
		File dir = new File(filePath);
		if (!dir.exists()) {// 判断文件目录是否存在
			dir.mkdirs();
		}
		return byteToFile(filePath + fileName, bytes);
	}

	/**
	 * 获取文件真实路径
	 *
	 * @param path 文件路径
	 * @return 文件真实路径
	 */
	public static String getRealFilePath(String path) {
		if (path == null) {
			return null;
		}
		return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
	}

	public static void main(String[] args) {
		byte[] bytes = "a".getBytes();
		byteToFile("F:\\Desktop\\test\\", "1.jpg", bytes);
	}

}
