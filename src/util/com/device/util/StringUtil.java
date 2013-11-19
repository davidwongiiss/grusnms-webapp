package com.device.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author weiweichen
 *
 */
public class StringUtil {

	public static String mapValue(String key , Map<String,Map> map){
		String result ="";
		if(key==null)
			return result;
		String[] keys = key.split(",");
		for (int i = 0; i < keys.length; i++) {
			String mapKey = keys[i];
			if(map.containsKey(mapKey)){
				result+= ","+map.get(mapKey).get("name");
			}
		}
		if (result.startsWith(",")) {
			result = result.substring(1);
		}
		return result;
	}
	
	/**
	 * 
	 * ��ʿ����2008.07.21
	 * 
	 * @param str
	 * @return
	 */
	public static String chuckStr(String oldStr) {
		if (oldStr == null || "".equals(oldStr.trim()))
			return "";
		int length = oldStr.length();
		if(oldStr.charAt(length-1)=='��'){
			oldStr = oldStr.substring(0, length-1);
		}
		int index = oldStr.indexOf("��");
		if (index > 20) {
			index = oldStr.indexOf(":");
			if (index > 20) {
				index = -1;
			}
		}
		if (index == -1)
			return oldStr;
		else
			return oldStr.substring(index + 1);
	}

	/**
	 * ȥ�������ڵı���
	 * @param oldStr
	 * @param title
	 * @return
	 */
	public static String removeTitle(String oldStr, String title) {
		if (oldStr == null || "".equals(oldStr.trim()))
			return "";
		if (title == null || "".equals(title)) {
			return oldStr;
		}
		String newStr = chuckStr(oldStr);
		String newTitle = chuckStr(title);

		return StringUtils.replace(newStr, newTitle, "");
		// return newStr.replaceAll(newTitle, "");
	}
	
	/**
	 * ����
	 * @param set
	 * @return
	 */
	public static List reverseSet(Set set){
		if(set==null || set.size()<=0)
			return null;
		List list = new ArrayList();
		for (Object object : set) {
			list.add(object);
		}
		Collections.sort(list);
		
		List reverseList = new ArrayList();
		for (int i = list.size()-1; i >=0 ; i--) {
			Object object = list.get(i);
			reverseList.add(object);
		}

		return reverseList;		
	}
	
	/**
	 * ����
	 * @param set
	 * @return
	 */
	public static List sortSet(Set set){
		if(set==null || set.size()<=0)
			return null;
		List list = new ArrayList();
		for (Object object : set) {
			list.add(object);
		}
		Collections.sort(list);
		return list;		
	}
	
	/**
	 * 20080715
	 * @param str
	 * @return
	 */
	public static String stringDate(String str){
		if(str==null)
			return "";
		String result="";
		if(str.length()==6){
			result = str.substring(0, 4)+"-"+str.substring(4);
		}else if(str.length()==8){
			result = str.substring(0, 4)+"-"+str.substring(4,6)+"-"+str.substring(6);
		}else{
			result = str;
		}
		return result;
	}
	
	public static Double numberFormat(Double number){
		if(number==null){
			return null;
		}
		return number/10000 ;
	}
	
	/**
	 * Ĭ�����ݸ�ʽ
	 * @param number
	 * @return
	 */
	public static String decimalFormat(Object number){
		if(number==null){
			return "--";
		}
		DecimalFormat   df   =   new DecimalFormat("0");   
		return  df.format(number); 
	}
	
	public static String decimal2Format(Object number){
		if(number==null){
			return "0";
		}
		DecimalFormat   df   =   new DecimalFormat("0");   
		return  df.format(number); 
	}
	
	/**
	 * ����С��
	 * @param number
	 * @return
	 */
	public static String decimalFormat(Object number, String style){
		if(number==null)
			return "--";
		if(number instanceof BigDecimal && ((BigDecimal)number).doubleValue() == Double.MIN_VALUE){
			return "--";
		}
		if(style==null)
			return decimalFormat(number);
		
		DecimalFormat   df   =   new DecimalFormat(style);   
		return  df.format(number); 
	}
	
	public static String decimal2Format(Object number, String style){
		if(number==null)
			return 0+"";
		if(style==null)
			return decimalFormat(number);
		
		DecimalFormat   df   =   new DecimalFormat(style);   
		return  df.format(number); 
	}
	
	
	/**
	 * �����������ַ����Ļ��з���� <br/>
	 * 
	 * @param str
	 * @return
	 */
	public static String nl2br(String str) {
		try {
			return str.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>").replaceAll("\r", "<br>");
		} catch (Exception e) {
			return str;
		}
	}
	
	
	/**
	 * ��һ���ַ���ת��Ϊint
	 * ���ת��ʧ���򷵻�0;
	 * @param str
	 * @return
	 */
	public static int parseInt(String str) {
		int value = 0;
		if(str==null){
			return value;
		}
		str= str.trim();
		try {
			value = Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return value;
	}

	/**
	 * ��һ���ַ���ת��Ϊint
	 * ���ת��ʧ���򷵻�defaultValue
	 * @param str
	 * @return
	 */
	public static int parseInt(String str, int defaultValue) {
		int value = defaultValue;
		if(str==null){
			return value;
		}
		str= str.trim();
		try {
			value = Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return value;
	}
	
	public static Long parseLong(String str) {
		Long value = 0L;
		if(str==null){
			return value;
		}
		str= str.trim();
		try {
			value = Long.parseLong(str);
		} catch (NumberFormatException e) {
		}
		return value;
	}
	
	//��ǰ�����ڵ����
	public static int currentYear(){
		 Calendar cld = Calendar.getInstance();
		 cld.setTimeInMillis(System.currentTimeMillis());
		 if(cld.get(Calendar.MONTH)>3){
			 return cld.get(Calendar.YEAR);
		 }
		 return cld.get(Calendar.YEAR)-1 ;
	}
	
	//ͬ�ȱ仯
	public static String compareChange(Double newer, Double older ){
		if(newer==null || older==null || older.doubleValue()==0){
			return "--";
		}
		Double result = ((newer-older)/Math.abs(older)) *100 ;//ͬ�ȱ仯��Ҫ��100�������
		if( result < 0.01 && result >-0.01 )
			return "";
		String str = decimalFormat(result,"0.##") + "%";	
		if(result.doubleValue()>0){
			str = "<span class=red>"+str+"</span>";
		}else if(result.doubleValue()<0){
			str = "<span class=green>"+str+"</span>";
		}
		return 	str;	
	}
	
	/**
	 * ��ʽ���ն���ɣ�"--" ��������ǿգ���ô�ͷ���ԭ����
	 * @param str
	 * @return
	 */
	public static Object nullFormat(Object obj){
		if(obj == null){
			return "--";
		}else if(obj instanceof String && obj.equals("")){
			return "--";
		}else{
			return obj;
		}
	}
	/**
	 * ȥ��null
	 * @param str
	 * @return
	 */
	public static Object killNull(Object obj){
		if(obj == null){
			return "";
		}else if(obj instanceof String && obj.equals("")){
			return "";
		}else{
			return obj;
		}
	}
	
	/**
	 * ��unicode����
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		if(src == null) return null;
		
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	public static void main(String[] args) {
		java.math.BigDecimal eps = new BigDecimal(Double.MIN_VALUE);
		System.out.println(StringUtil.decimalFormat(0.344444444444444, "0.##") + "%");
		System.out.println(compareChange(0.18, -0.15 ));
	}

}
