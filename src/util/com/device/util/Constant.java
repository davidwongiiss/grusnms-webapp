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
	
	//��������
	public static Map<String,String> groupTypes = new LinkedHashMap<String,String>();
	//�豸����
	public static Map<String,String> deviceTypes = new LinkedHashMap<String,String>();
	//��������
	public static Map<String,String> deptMaps = new LinkedHashMap<String,String>();
	static {
		groupTypes.put("dept", "����");
		groupTypes.put("area", "����");
		groupTypes.put("define", "�Զ���");
		
		
		deviceTypes.put("device1", "�豸1");
		deviceTypes.put("device2", "�豸2");
		deviceTypes.put("device3", "�豸3");
		deviceTypes.put("device4", "�豸4");
		
		deptMaps.put("dept1", "����һ");
		deptMaps.put("dept2", "���Ŷ�");
	}
	
}
