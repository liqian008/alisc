package com.shan.yellowpages.base.utils.zip;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * gzip工具类
 * @author bruce
 */
public class GZipUtils {

public static final int BUFFER = 1024;


	/** gzip的扩展文件名 */
	public static final String EXT = ".gz";


	/**
	 * 数据压缩
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] compress(byte[] data) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 压缩
		compress(bais, baos);

		byte[] output = baos.toByteArray();

		baos.flush();

		baos.close();

		bais.close();

		return output;

	}

	/**
	 * 文件压缩
	 * @param file
	 * @throws Exception
	 */
	public static void compress(File file) throws Exception {
		compress(file, true);
	}

	/**
	 * 文件压缩
	 * @param file
	 * @param delete 压缩完毕后，是否删除原文件
	 * @throws Exception
	 */
	public static void compress(File file, boolean delete) throws Exception {

		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);
		compress(fis, fos);
		fis.close();
		fos.flush();
		fos.close();

		if (delete) {
			file.delete();
		}
	}

	/**
	 * 数据压缩
	 * @param is 输入流
	 * @param os 输出流
	 * @throws Exception
	 */
	public static void compress(InputStream is, OutputStream os) throws Exception {

		GZIPOutputStream gos = new GZIPOutputStream(os);

		int count;

		byte data[] = new byte[BUFFER];

		while ((count = is.read(data, 0, BUFFER)) != -1) {
			gos.write(data, 0, count);
		}

		gos.finish();
		gos.flush();
		gos.close();
	}

	/**
	 * 文件压缩
	 * @param path
	 * @throws Exception
	 */

	public static void compress(String path) throws Exception {
		compress(path, false);
	}

	/**
	 * 文件压缩
	 * @param path
	 * @param delete
	 * @throws Exception
	 */

	public static void compress(String path, boolean delete) throws Exception {
		File file = new File(path);

		compress(file, delete);
	}

	/**
	 * 数据解压缩
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decompress(byte[] data) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 解压缩
		decompress(bais, baos);
		data = baos.toByteArray();
		baos.flush();
		baos.close();
		bais.close();
		return data;
	}

	/**
	 * 文件解压缩
	 * @param zipFile
	 * @throws Exception
	 */
	public static void decompress(File zipFile) throws Exception {
		decompress(zipFile, false);
	}

	/**
	 * 文件解压缩
	 * @param zipFile
	 * @param delete 解压完毕是否删除压缩文件
	 * @throws Exception
	 */
	public static void decompress(File zipFile, boolean delete) throws Exception {
		FileInputStream fis = new FileInputStream(zipFile);


		FileOutputStream fos = new FileOutputStream(zipFile.getPath().replace(EXT,""));
		decompress(fis, fos);
		fis.close();

		fos.flush();

		fos.close();

		if (delete) {
			zipFile.delete();
		}
	}

	/**
	 * 数据解压缩
	 * @param is
	 * @param os
	 * @throws Exception
	 */

	public static void decompress(InputStream is, OutputStream os) throws Exception {

		GZIPInputStream gis = new GZIPInputStream(is);

		int count;

		byte data[] = new byte[BUFFER];

		while ((count = gis.read(data, 0, BUFFER)) != -1) {
			os.write(data, 0, count);
		}

		gis.close();

	}

	/**
	 * 文件解压缩
	 * @param zipFilePath
	 * @throws Exception
	 */
	public static void decompress(String zipFilePath) throws Exception {
		decompress(zipFilePath, false);
	}

	/**
	 * 文件解压缩
	 * @param zipFilePath
	 * @param delete
	 * @throws Exception
	 */
	public static void decompress(String zipFilePath, boolean delete) throws Exception {
		File zipFile = new File(zipFilePath);
		decompress(zipFile, delete);
	}




	public static void main(String[] args) {

		String dir = "/Users/bruce/Desktop/vanchip/PLM/FT/Data/VC7916-51/AGD910M073-D001";
//		String gzipFilePath = "E3200-1117_VC7916V_P7R_3D_3_LB-AGD-063_065_QSFRYQG_AGD910M073-D001_KGU_36.60_20190322091804.sum.gz";

		File dirFile = new File(dir);


		File[] zipFiles = dirFile.listFiles(new FileFilter() {
			@Override public boolean accept(File pathname) {
				boolean result = false;
				if(StringUtils.endsWithIgnoreCase(pathname.getName(), ".std" +EXT)){
					result = true;
				}
				return result;
			}
		});

		if(zipFiles!=null && zipFiles.length>0){

			for(File zipFile: zipFiles){
				String zipFileName = zipFile.getName();
				System.out.println(zipFileName);
				try {
					decompress(zipFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}


}