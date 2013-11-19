
package com.device.util;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * 功能说明:Jackson的简单封装. 
 * 创建时间: 2011 12 1 11:14:27
 * @author 童贝
 */
@SuppressWarnings("deprecation")
public class JsonBinder {

	private ObjectMapper mapper;
	
	/**
	 * 构造函数,支持设置序列化的内容
	 * @param inclusion
	 */
	public JsonBinder(Inclusion inclusion) {
		mapper = new ObjectMapper();
		//设置输出包含的属性
		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig().set(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 
	 * 功能说明:创建输出全部属性到Json字符串的Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:童贝
	 * @date: 2011 12 1 11:15:53
	 */
	public static JsonBinder buildNormalBinder() {
		return new JsonBinder(Inclusion.ALWAYS);
	}

	/**
	 * 
	 * 功能说明:创建只输出非空属性到Json字符串的Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:童贝
	 * @date: 2011 12 1 11:16:10
	 */
	public static JsonBinder buildNonNullBinder() {
		return new JsonBinder(Inclusion.NON_NULL);
	}

	/**
	 * 
	 * 功能说明:创建只输出初始值被改变的属性到Json字符串的Binder.   
	 * @param: @return      
	 * @return: JsonBinder     
	 * @author:童贝
	 * @date: 2011 12 1 11:16:25
	 */
	public static JsonBinder buildNonDefaultBinder() {
		return new JsonBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * 
	 * 功能说明: 
	 * 如果JSON字符串为Null或"null"字符串,返回Null.
	 * 如果JSON字符串为"[]",返回空集合.
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句:
	 * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});   
	 * @param: @param <T>
	 * @param: @param jsonString
	 * @param: @param clazz
	 * @param: @return      
	 * @return: T     
	 * @author:童贝 
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
	 * 功能说明:
	 * 如果对象为Null,返回"null".
	 * 如果集合为空集合,返回"[]".   
	 * @param: @param object
	 * @param: @return      
	 * @return: String     
	 * @author:童贝
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
	 * 功能说明:设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.   
	 * @param: @param pattern      
	 * @return: void     
	 * @author:童贝
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
	 * 功能说明:取出Mapper做进一步的设置或使用其他序列化API.   
	 * @param: @return      
	 * @return: ObjectMapper     
	 * @author:tb 
	 * @date: 2011 12 1 11:17:59
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
