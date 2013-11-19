package com.device.po;

import java.sql.Timestamp;

public class EventsAssign {
	private String id;
	private String nodeId;
	private String userId ;
	private Boolean isTip ;
	private Boolean isEmail ;
	private Boolean isShortMessage;
	private Timestamp createTime ;
	private Timestamp updateTime;
	private String creator;
	private String updater ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Boolean getIsTip() {
		return isTip;
	}
	public void setIsTip(Boolean isTip) {
		this.isTip = isTip;
	}
	public Boolean getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(Boolean isEmail) {
		this.isEmail = isEmail;
	}
	public Boolean getIsShortMessage() {
		return isShortMessage;
	}
	public void setIsShortMessage(Boolean isShortMessage) {
		this.isShortMessage = isShortMessage;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	
}
