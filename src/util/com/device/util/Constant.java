package com.device.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static String AREA = "area";
	public static String DEPT = "dept";
	public static String DEFINE = "define";
	//分组类型
	public static Map<String,String> groupTypes = new HashMap<String,String>();
	//设备类型
	public static Map<String,String> deviceTypes = new HashMap<String,String>();
	//部门名称
	public static Map<String,String> deptMaps = new HashMap<String,String>();
	static {
		groupTypes.put("area", "地域");
		groupTypes.put("dept", "部门");
		groupTypes.put("define", "自定义");
		
		
		deviceTypes.put("device1", "设备1");
		deviceTypes.put("device2", "设备2");
		deviceTypes.put("device3", "设备3");
		deviceTypes.put("device4", "设备4");
		
		deptMaps.put("dept1", "部门一");
		deptMaps.put("dept2", "部门二");
	}
	
}
