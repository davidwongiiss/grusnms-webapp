/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.device.exception;

import org.hibernate.HibernateException;

/**
 * 通过QueryHelper进行查询的异常
 * @author Dingyongxing
 * SOHU-R&D
 * 
 */
public class HibernateHelperException extends HibernateException{

	public static final int NAMDEDQUERY_NOT_EXIST=1;
	public static final int PARAMETERS_NOT_MATCH=2;
	public static final int SQL_EXCEPTION=3;

	private int type;
	private String msg;
    public HibernateHelperException(int type,String message, Throwable cause) {
        super(message, cause);
    	this.type=type;
    }

    public HibernateHelperException(int type,String message) {
        super(message);
    	this.type=type;        
    }
}
