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

	private String severity;

	private int pageNO;

	private int pageCount;



	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
		this.handle = handle;
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
	public EventsListEvent(){
		
	}
	/**
	 * @param pattern
	 * @param name
	 * @param pageNO
	 * @param pageCount
	 */
	public EventsListEvent(Integer handle, String severity, int pageNO, int pageCount) {
		super();
		this.handle = handle;
		this.severity = severity;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}
}