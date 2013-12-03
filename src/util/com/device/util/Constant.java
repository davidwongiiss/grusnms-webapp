package com.device.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constant {
	public static String AREA = "area";
	public static String DEPT = "dept";
	public static String DEFINE = "define";
	
	public static String DEVICENO = "NSG9000-6G";
	public static String DEVICETYPE = "IPQAM";
	
	//分组类型
	public static Map<String,String> groupTypes = new LinkedHashMap<String,String>();
	//设备类型
	public static Map<String,String> deviceTypes = new LinkedHashMap<String,String>();
	//部门名称
	public static Map<String,String> deptMaps = new LinkedHashMap<String,String>();
	static {
		groupTypes.put("dept", "部门");
		groupTypes.put("area", "地域");
		groupTypes.put("define", "自定义");
		
		
		deviceTypes.put("device1", "设备1");
		deviceTypes.put("device2", "设备2");
		deviceTypes.put("device3", "设备3");
		deviceTypes.put("device4", "设备4");
		
		deptMaps.put("dept1", "部门一");
		deptMaps.put("dept2", "部门二");
	}
	
}
