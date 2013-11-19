/*
 * Created on 2004-12-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.device.common;

import com.device.exception.BusinessParseException;


/**
 * 业务逻辑请求的参数描述
 * @author Dingyongxing
 * SOHU-R&D
 * 
 */
public interface BusinessEvent extends java.io.Serializable {
	public String toString();
	public void parse(String str) throws BusinessParseException;	
}
