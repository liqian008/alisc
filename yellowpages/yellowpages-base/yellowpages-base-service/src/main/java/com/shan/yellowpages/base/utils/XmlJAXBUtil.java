package com.shan.yellowpages.base.utils;

import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml工具类
 * 使用JAXB
 */
public class XmlJAXBUtil {

	private static final Logger logger = LoggerFactory.getLogger(XmlJAXBUtil.class);


	/**
	 * 使用JAXB，将泛型对象直接转换成XML输出
	 *
	 * @param t
	 * @return
	 */
	public static <T> String convertToXml(T t) throws JAXBException {

		if(t==null){
			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR);
		}

		// 创建输出流
		StringWriter sw = new StringWriter();
		// 利用jdk中自带的转换类实现
		JAXBContext context = JAXBContext.newInstance(t.getClass());

		Marshaller marshaller = context.createMarshaller();
		// 格式化xml输出的格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 将对象转换成输出流形式的xml
		marshaller.marshal(t, sw);

		String result = sw.toString();
		logger.debug("[convertToXml]result:{}, xml:{}", result,  t);

		return result;
	}

	/**
	 * 使用JAXB，将XML转换成泛型对象输出
	 * @param xml
	 * @param t
	 * @param <T>
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T convertToJavaBean(String xml, Class<T> t) throws JAXBException {
		if (StringUtils.isEmpty(xml)) {
			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR);
		}

		JAXBContext context = JAXBContext.newInstance(t);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		T result = (T) unmarshaller.unmarshal(new StringReader(xml));

		logger.debug("[convertToJavaBean]result:{}, xml:{}", result,  xml);

		return result;
	}


}
