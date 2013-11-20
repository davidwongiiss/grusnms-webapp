/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.common.impl;

import java.util.Date;

import com.device.common.BusinessEvent;


/**
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UsersEvent implements BusinessEvent {

	private String userName;
	private String mobileNo ;
	private Integer isAdmin ;
	private String beginTime ;
	private String endTime ;
	

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	private int pageNO;

	private int pageCount;



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
	public UsersEvent(){
		
	}
	
}