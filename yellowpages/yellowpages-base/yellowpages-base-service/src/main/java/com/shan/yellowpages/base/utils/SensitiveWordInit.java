package com.shan.yellowpages.base.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 *
 * @author : chenming
 * @date ： 2014年4月20日 下午2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {

	private static final Logger logger = LoggerFactory.getLogger(SensitiveWordInit.class);

	@SuppressWarnings("rawtypes")
	public static Map sensitiveWordMap;

	public SensitiveWordInit() {
		super();
	}

	/**
	 * @author chenming
	 * @date 2014年4月20日 下午2:28:32
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord() {
		try {
			// 读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile();
			// 将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
		} catch (Exception e) {
			logger.error("[initKeyWord],exception", e);
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 *
	 * <pre>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * </pre>
	 *
	 * @author chenming
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet 敏感词库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		Map nowMap;
		Map<String, String> newWorMap;
		// 迭代keyWordSet
		for (String key : keyWordSet) {
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取
				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 *
	 * @author chenming
	 * @date 2014年4月20日 下午2:31:18
	 * @return 获取敏感词库
	 * @throws Exception 异常
	 */
//	@SuppressWarnings("resource")
//	private static Set<String> readSensitiveWordFile() throws Exception {
//
//		String pathname = ClassLoader.getSystemResource("sensi_words.txt").getPath();
//		if (isWindows() && StringUtils.startsWith(pathname, "/")) {
//			pathname = pathname.substring(1);
//		}
//		Path path = Paths.get(pathname);
//		List<String> lines = Files.readAllLines((path));
//		Set<String> set = new HashSet<>(lines);
//		// try (InputStreamReader read = new InputStreamReader(new
//		// FileInputStream(file), ENCODING)) {
//		// if (file.isFile() && file.exists()) { // 文件流是否存在
//		// BufferedReader bufferedReader = new BufferedReader(read);
//		// String txt;
//		// // 读取文件，将文件内容放入到set中
//		// while ((txt = bufferedReader.readLine()) != null) {
//		// set.add(txt);
//		// }
//		// } else { // 不存在抛出异常信息
//		// throw new Exception("敏感词库文件不存在");
//		// }
//		// }
//		return set;
//	}

	private  Set<String> readSensitiveWordFile() {
		Set<String> set = new HashSet<>();
		// 返回读取指定资源的输入流
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("com/temujin/khaan/base/utils/sensi_words.txt");
			 BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))){
			String txt = "";
			while ((txt = br.readLine()) != null) {
				set.add(txt);
			}

		}catch (Exception e){
			logger.error("[readSensitiveWordFile] exception", e);
		}
		return set;
	}

	private static boolean isWindows() {
		return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
	}

	public static void main(String[] args) throws Exception {
		Set<String> strings = new SensitiveWordInit().readSensitiveWordFile();
		System.out.println("strings = " + strings);
	}
}
