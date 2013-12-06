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
public class EventsListEvent implements BusinessEvent {
	private Integer handle;

	private Integer severity;
	
	private String ip ;
	private String groupId;

	private int pageNO;

	private int pageCount;

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
		this.handle = handle;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String toString() {
		String eventString = "";
		return eventString;
	}

	public void parse(String str) {

	}
	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	public EventsListEvent(){
		
	}

}