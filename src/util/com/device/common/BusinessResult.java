/*
 * Created on 2004-12-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.device.common;

import com.device.exception.BusinessParseException;


/**
 * ҵ���߼�����ķ���ֵ
 * 
 * @author Dingyongxing SOHU-R&D
 * 
 */
public interface BusinessResult extends java.io.Serializable {
    public long getStatus();

    public String toString();

    public void parse(String str) throws BusinessParseException;
}
