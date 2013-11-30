package com.device.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;


/**
 * 
 * 功能说明: json工具类,采用Jackson对Object,Map,List,数组,枚举,日期类等的持久化.
 * 创建时间: 2011 11 30
 * @author 童贝
 */
@SuppressWarnings("unchecked")
public class JSONUtil {
	private static JsonBinder binder = JsonBinder.buildNormalBinder();
	/**
	 * 
	 * 功能说明:将简单的json字符串解析到List<map>中，并以集合的形式返回
	 * @param: @param <T>
	 * @param: @param json
	 * @param: @return      
	 * @return: List<T>     
	 * @author:童贝 
	 * @date: 2011 11 30 14:47:34
	 */
	public static <T> List<T> parseList(String json) {
		List<T> list = new ArrayList<T>();
		if(StringUtils.isEmpty(json)){
			return list;
		}
		try {
			list = binder.getMapper().readValue(json, new TypeReference<List<T>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 
	 * 功能说明:将简单的json字符串解析到List<自定义对象>中，并以集合的形式返回  
	 * @param: @param <T>
	 * @param: @param json
	 * @param: @param classes  必须以对象数组的形式出现(JSONUtil.parseList(json,VmlWorkFlow[].class))
	 * @param: @return      
	 * @return: List<T>     
	 * @author:童贝
	 * @date: 2011 11 30 15:26:45
	 */
	public static <T> List parseList(String json,Class<T> classes) {
		List list = new ArrayList();
		if(StringUtils.isEmpty(json)){
			return list;
		}
		try {
			T curObj= binder.getMapper().readValue(json, classes);
			if(curObj instanceof Object[]) {
				Object[] newObj=(Object[]) curObj;
				list=Arrays.asList(newObj);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	/**
	 * 
	 * 功能说明:获取单个对象
	 * @param: @param <T>
	 * @param: @param jsonString
	 * @param: @param clazz
	 * @param: @return      
	 * @return: T     
	 * @author:童贝
	 * @date: 2011 11 30 15:37:50
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		return binder.fromJson(jsonString, clazz);
	}
	
	
	/**
	 * 
	 * 功能说明:将Map中的对象序列化成JSON字符串  
	 * @param: @param map
	 * @param: @return      
	 * @return: String     
	 * @author:童贝
	 * @date: 2011 11 30 15:38:32
	 */
	public static String mapToJson(Map map){
		return binder.toJson(map);
	}
	
	/**
	 * 
	 * 功能说明：序列化单个对象  
	 * @param: @param obj
	 * @param: @return      
	 * @return: String     
	 * @author:童贝 
	 * @date: 2011 11 30 15:39:01
	 */
	public static String objectToJson(Object obj){
		return binder.toJson(obj);
	}

	
	/**
	 * 
	 * 功能说明:将集合中的对象按指定字段进行JSON化(可以满足基本需求，对特殊的需求需要进一步进行扩展)
	 * @param: @param obj
	 * @param: @param ignorableFieldNames
	 * @param: @return      
	 * @return: String     
	 * @author:童贝
	 * @date: 2011 12 1 09:50:33
	 */
	public static String listToJson(Collection obj, String[] ignorableFieldNames) {
		String str=null;
		try {     
			FilterProvider filters = new SimpleFilterProvider().addFilter(PropertyFilterMixIn.MODEL_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept(ignorableFieldNames));      
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.DEFAULT); 
			mapper.getSerializationConfig().addMixInAnnotations(Object.class, PropertyFilterMixIn.class);     
			ObjectWriter writer = mapper.writer(filters); 
			str=writer.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String listToJson2(Collection obj, String[] ignorableFieldNames) {
		String str=null;
		try {     
			FilterProvider filters = new SimpleFilterProvider().addFilter(PropertyFilterMixIn.MODEL_FILTER, SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));      
			ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.DEFAULT); 
			mapper.getSerializationConfig().addMixInAnnotations(Object.class, PropertyFilterMixIn.class);     
			ObjectWriter writer = mapper.writer(filters); 
			str=writer.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	

	
	/**
	 * 
	 * 功能说明:将list<map>序列化成json字符串
	 * @param: @param obj
	 * @param: @return      
	 * @return: String     
	 * @author:童贝
	 * @date: 2011 12 1 10:53:02
	 */
	public static String listToJson(List<Map> obj) {
		return objectToJson(obj);
	}
	
	/**
	 * 
	 * 功能说明:取得单个对象的属性值(针对MAP)
	 * @param: @param strJson
	 * @param: @param propertyName
	 * @param: @return      
	 * @return: String     
	 * @author:童贝
	 * @date: 2011 12 1 10:57:48
	 */
	public static String getPropertyValueByJsonObject(String strJson,String propertyName){
		Map<String,String> map=fromJson(strJson, HashMap.class);
		if(map!=null){
			return map.get(propertyName);
		}
		return null;
	}
	
	public static void main(String[] args) {
//		//用来测试fromJson
//		String json="{\"id\":null,\"name\":null,\"desc\":null,\"count\":3,\"nodes\":[{\"id\":\"node_1\",\"name\":\"node_1\",\"type\":\"start\",\"shape\":\"oval\",\"number\":1,\"left\":539,\"top\":93,\"width\":20,\"height\":20,\"property\":null},{\"id\":\"node_2\",\"name\":\"node_2\",\"type\":\"node\",\"shape\":\"rect\",\"number\":2,\"left\":497,\"top\":196,\"width\":100,\"height\":40,\"property\":[{\"id\":\"n_p_id\",\"text\":\"input\",\"value\":\"node_2\"},{\"id\":\"n_p_name\",\"text\":\"input\",\"value\":\"node_2\"},{\"id\":\"n_p_desc\",\"text\":\"textarea\",\"value\":\"\"},{\"id\":\"n_p_group\",\"text\":\"input\",\"value\":\"\"},{\"id\":\"n_p_role\",\"text\":\"input\",\"value\":\"\"},{\"id\":\"n_p_dcur\",\"text\":\"select\",\"value\":\"1\"}]}],\"lines\":[{\"id\":\"line_3\",\"name\":\"line_3\",\"type\":\"line\",\"shape\":\"line\",\"number\":3,\"from\":\"node_1\",\"to\":\"node_2\",\"fromx\":549,\"fromy\":113,\"tox\":547,\"toy\":196,\"polydot\":[],\"property\":null}]}";
//		VmlWorkFlow wf=JSONUtil.fromJson(json,VmlWorkFlow.class);
//		System.out.println(wf.getName());
//		System.out.println(wf.getNodes().size());
//		System.out.println(wf.getLines().size());
//		for(VmlNode vn:wf.getNodes()){
//			if(vn.getProperty()!=null){
//				for(VmlProperty vp:vn.getProperty()){
//					System.out.println(vn.getName()+":"+vp.getId()+":"+vp.getValue());
//				}
//			}else{
//				System.out.println(vn.getName()+":Property=null");
//			}
//		}
//		
//		//用来测试parseList
//		json="[{\"id\":1,\"name\":2,\"desc\":3,\"count\":4},{\"id\":7,\"name\":8,\"desc\":9,\"count\":10}]";
//		List<Map> list=JSONUtil.parseList(json);
//		System.out.println(list.get(0).get("name"));
//		System.out.println(list.get(1).get("name"));
//
//		//用来测试parseList
//		json="[{\"id\":1,\"name\":2,\"desc\":3,\"count\":4,\"nodes\":[{\"id\":\"node_1\"}]}]";
//		List<VmlWorkFlow> wfList=JSONUtil.parseList(json,VmlWorkFlow[].class);
//		System.out.println(wfList.get(0).getName());
//		
//		//用来测试mapToJson
//		Map<String, String> map=new HashMap<String, String>();
//		map.put("id", "10");
//		map.put("name","20");
//		System.out.println(JSONUtil.mapToJson(map));
//		
//		//用来测试objectToJson
//		System.out.println(JSONUtil.objectToJson(wf));
//		
//		//用来测试listToJson
//		JSONUtil.listToJson(wfList, new String[]{"id","nodes","type"});
//		
//		//用来测试objectToJson
//		JSONUtil.objectToJson(wf, new String[]{"id","nodes","type"});
//		
//		//用来测试listToJson
//		System.out.println(JSONUtil.listToJson(list));
	}
}
