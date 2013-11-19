/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.common.impl;

import com.device.common.BusinessEvent;


/**
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NodesListEvent implements BusinessEvent {
	private String deviceType;

	private String name;
	
	private int pageNO;

	private int pageCount;

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

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

	public String toString() {
		String eventString = "";
		return eventString;
	}

	public void parse(String str) {

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
	public NodesListEvent(){
		
	}
	/**
	 * @param pattern
	 * @param name
	 * @param pageNO
	 * @param pageCount
	 */
	public NodesListEvent(String deviceType, String name, int pageNO, int pageCount) {
		super();
		this.deviceType = deviceType;
		this.name = name;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}
}