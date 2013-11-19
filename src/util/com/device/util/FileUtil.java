/**
 * <p>Title: FileUtil.java</p>
 * <p>Description: FileUtil.java</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sohu Inc</p>
 * <p>Date:2008-7-25</p>
 * @author 赵士昌<zhaoshichang@gmail.com>
 * @version 1.0
 */
package com.device.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用于文件的读写
 * @author 赵士昌
 */
public class FileUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	public static final String CODE_FILE="/suggest.txt";

	public static String getFileContent(File file, String encode) {
		StringBuffer r_buffer = new StringBuffer();
		String strRet = "";
		try {
//			FileReader fr = new FileReader(file);
//			int r_count = 0;
//			char[] in_buffer = new char[5 * 1024];
//			while ((r_count = fr.read(in_buffer)) != -1)
//				r_buffer.append(in_buffer, 0, r_count);
//			strRet = new String(r_buffer.toString().getBytes(), encode);
			
			InputStreamReader read = new InputStreamReader (new FileInputStream(file),encode);
			BufferedReader reader=new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				r_buffer.append(line);
			}
		} catch (FileNotFoundException e) {
			log.info("文件["+file.getName()+"]未找到",e);
		} catch (IOException e) {
			log.info(e.getMessage(),e);
		}
//		return strRet;
		return r_buffer.toString();
	}
	
	public static String getFileContent(String encode) {
		StringBuffer r_buffer = new StringBuffer();
		String strRet = "";
		try {
			InputStreamReader isr = new InputStreamReader(FileUtil.class.getResourceAsStream("/suggest.txt"));
			int r_count = 0;
			char[] in_buffer = new char[5 * 1024];
			while ((r_count = isr.read(in_buffer)) != -1)
				r_buffer.append(in_buffer, 0, r_count);
			strRet = new String(r_buffer.toString().getBytes(), encode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strRet;
	}
	
	

	public static void main(String[] args) {
		System.out.println(FileUtil.getFileContent(new File(
				"E:/zhao/Desktop/search/suggest.txt"), "GB2312"));
	}

}
