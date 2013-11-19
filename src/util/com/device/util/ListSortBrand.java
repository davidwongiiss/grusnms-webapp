/**
 * 
 */
package com.device.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author richardzhao
 * 
 */
public class ListSortBrand {

	/**
	 * @param args
	 */
	/**
	private static Comparator<Student> getComparator(String fieldName) {
		if(fieldName.equalsIgnoreCase("id")) {
		 return new StudentIdComparator<Student>();
		}
		if(fieldName.equalsIgnoreCase("name")) {
		return new StudentNameComparator<Student>();
		}
		return null;
	}*/
	public static void main(String[] arg) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(2, "����"));
		list.add(new Student(1, "����"));
		list.add(new Student(4, "����"));
		list.add(new Student(3, "����"));
		// Comparator<Student> comparator = getComparator("id");
		//Comparator<Student> comparator = getComparator("name");
		//Collections.sort(list, comparator);
		
		// ���
		//for(Student stu : list) {
		      // System.out.println(stu.getId()+"-->"+stu.getName();//������}
	}
}

class Student {
	private int id;

	private String name;

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
/**
 * ����ѧ���� id �Ŵ�С��������
 *//**
class StudentIdComparator<T extends Student> implements Comparator<Student> {
	public int compare(Student o1, Student o2) {
       return o1.getId() - o2.getId();
����}
}*/

/**
 * ����ѧ���� name �� GB2312 �������򣨼��ֿ������Ϊ����
 */
/**
class StudentNameComparator<T extends Student> implements Comparator<Student> {
	public int compare(Student o1, Student o2) {
		return getGBK(o1.getName()).compareTo(getGBK(o2.getName()));
����}

	private String getGBK(String str) {
	byte[] bytes = null;
	try {
	bytes = str.getBytes("gb2312");
	} catch (UnsupportedEncodingException e) {
	e.printStackTrace();
	}
	StringBuffer sb = new StringBuffer();
	for(byte b : bytes) {
	sb.append(String.format("%02X", b));
	}
	return sb.toString(); 
����}
}
*/
