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
	private String deviceSn;
	private String ip ;
	private String name ;
	private String beginTime ;
	private String endTime ;	
	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
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
	public NodesListEvent(String name, int pageNO, int pageCount) {
		super();
		this.name = name;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}
}