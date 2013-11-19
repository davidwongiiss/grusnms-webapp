/**
 * <p>Title: ClobUtil.java</p>
 * <p>Description: ClobUtil.java</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Sohu Inc</p>
 * <p>Date:2008-7-11</p>
 * @author 赵士昌<zhaoshichang@gmail.com>
 * @version 1.0
 */
package com.device.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 用于处理Oracle Clob类型读取
 * 
 * @author 赵士昌
 * 
 */
public class ClobUtil {
	
	private static final Log log = LogFactory.getLog(ClobUtil.class);
	
	/**
	 * @param clob
	 * @return
	 */
	public static String toString(Clob clob) {
		String strResult = "";
		try {
			if(clob==null){
				return "";
			}
			Reader clobReader = clob.getCharacterStream();
			StringBuffer r_buffer = new StringBuffer();
			int clob_count = 0;
			char[] in_buffer = new char[5 * 1024];
			while ((clob_count = clobReader.read(in_buffer)) != -1)
				r_buffer.append(in_buffer, 0, clob_count);
			strResult = r_buffer.toString();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return strResult;
	}

}
