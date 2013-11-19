
package com.device.util;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * ����˵��:Jackson�ļ򵥷�װ. 
 * ����ʱ��: 2011 12 1 11:14:27
 * @author ͯ��
 */
@SuppressWarnings("deprecation")
public class JsonBinder {

	private ObjectMapper mapper;
	
	/**
	 * ���캯��,֧���������л�������
	 * @param inclusion
	 */
	public JsonBinder(Inclusion inclusion) {
		mapper = new ObjectMapper();
		//�����������������
		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		//��������ʱ����JSON�ַ����д��ڶ�Java����ʵ��û�е�����
		mapper.getDeserializationConfig().set(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 
	 * ����˵��:�������ȫ�����Ե�Json�ַ�����Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:ͯ��
	 * @date: 2011 12 1 11:15:53
	 */
	public static JsonBinder buildNormalBinder() {
		return new JsonBinder(Inclusion.ALWAYS);
	}

	/**
	 * 
	 * ����˵��:����ֻ����ǿ����Ե�Json�ַ�����Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:ͯ��
	 * @date: 2011 12 1 11:16:10
	 */
	public static JsonBinder buildNonNullBinder() {
		return new JsonBinder(Inclusion.NON_NULL);
	}

	/**
	 * 
	 * ����˵��:����ֻ�����ʼֵ���ı�����Ե�Json�ַ�����Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:ͯ��
	 * @date: 2011 12 1 11:16:25
	 */
	public static JsonBinder buildNonDefaultBinder() {
		return new JsonBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * 
	 * ����˵��: 
	 * ���JSON�ַ���ΪNull��"null"�ַ���,����Null.
	 * ���JSON�ַ���Ϊ"[]",���ؿռ���.
	 * �����ȡ������List/Map,�Ҳ���List<String>���ּ�����ʱʹ���������:
	 * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});   
	 * @param: @param <T>
	 * @param: @param jsonString
	 * @param: @param clazz
	 * @param: @return      
	 * @return: T     
	 * @author:ͯ�� 
	 * @date: 2011 12 1 11:16:46
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 
	 * ����˵��:
	 * �������ΪNull,����"null".
	 * �������Ϊ�ռ���,����"[]".   
	 * @param: @param object
	 * @param: @return      
	 * @return: String     
	 * @author:ͯ��
	 * @date: 2011 12 1 11:17:14
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 
	 * ����˵��:����ת���������͵�format pattern,���������Ĭ�ϴ�ӡTimestamp������.   
	 * @param: @param pattern      
	 * @return: void     
	 * @author:ͯ��
	 * @date: 2011 12 1 11:17:44
	 */
	public void setDateFormat(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			mapper.getSerializationConfig().setDateFormat(df);
			mapper.getDeserializationConfig().setDateFormat(df);
		}
	}

	/**
	 * 
	 * ����˵��:ȡ��Mapper����һ�������û�ʹ���������л�API.   
	 * @param: @return      
	 * @return: ObjectMapper     
	 * @author:tb 
	 * @date: 2011 12 1 11:17:59
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
