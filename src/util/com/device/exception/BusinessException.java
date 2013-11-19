/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.device.exception;

/**
 * 通过业务处理的异常
 * @author Dingyongxing
 * SOHU-R&D
 * 
 */
public class BusinessException extends Exception{
	private int  errorCode;
    public BusinessException(int errorCode) {
    	this.errorCode=errorCode;
    }

	/**
	 * @return Returns the errorCode.
	 */
	public int getErrorCode() {
		return errorCode;
	}
}
