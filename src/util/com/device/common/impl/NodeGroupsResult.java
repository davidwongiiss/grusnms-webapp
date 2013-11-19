/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.common.impl;

import java.util.Collection;

import com.device.common.BusinessResult;
import com.device.util.Pagination;


/**
 * @author luciali
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NodeGroupsResult implements BusinessResult {
	private String deviceType;

	private String paramName;

	private Collection c;

	private int pageNO;

	private int pageCount;

	private Pagination pagination;

	private long status;

	/**
	 * @return Returns the pattern.
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param pattern
	 *            The pattern to set.
	 */
	public void setDeviceType(String pattern) {
		this.deviceType = pattern;
	}

	/**
	 * @return Returns the c.
	 */
	public Collection getC() {
		return c;
	}

	/**
	 * @param c
	 *            The c to set.
	 */
	public void setC(Collection c) {
		this.c = c;
	}

	/**
	 * @return Returns the pageCount.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            The pageCount to set.
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return Returns the pageNO.
	 */
	public int getPageNO() {
		return pageNO;
	}

	/**
	 * @param pageNO
	 *            The pageNO to set.
	 */
	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}

	/**
	 * @return Returns the pagination.
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            The pagination to set.
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return Returns the paramName.
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName
	 *            The paramName to set.
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @param stauts
	 *            The stauts to set.
	 */
	public void setStatus(long status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sohu.sip.auth.middletier.BusinessResult#getStatus()
	 */
	public long getStatus() {
		return this.status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sohu.sip.auth.middletier.BusinessResult#parse(java.lang.String)
	 */
	public void parse(String str) {

	}

	public String toString() {
		String eventString = "";
		return eventString;
	}

}