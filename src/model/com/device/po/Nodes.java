package com.device.po;

import java.util.Date;

public class Nodes implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4271466639093253182L;
	// Fields
	private String id;
	private String deviceSn;
	private String name;
	private String description;
	private String ip;
	private String longitude = "";
	private String latitude = "" ;
	private String deviceType = "IPQAM";
	private String deviceModal = "NSG9000-6G";
	private Date createTime;
	private Date updateTime;
	private String creator;
	private String updater;
	private boolean isSystem = false;
	
	private String ipv6;
	private String loginUser;
	private String loginPassword;
	
	private boolean deleted = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeviceSn() {
		return deviceSn;
	}
	
	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceModal() {
		return deviceModal;
	}
	public void setDeviceModal(String deviceModal) {
		this.deviceModal = deviceModal;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
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

	public boolean isIsSystem() {
		return isSystem;
	}
	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	public String getIpv6() {
		return ipv6;
	}
	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	
}
	