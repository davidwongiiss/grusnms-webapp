package com.device.po;

public class MonitorBean {
	private String nodeid ;
	private String ip ;
	private Integer status ;
	private boolean alarm ;
	private boolean warning ;
	private Integer gbeb = 0;
	private Integer gben = 0;
	private Integer qamb = 0 ;
	private Integer qamn = 0;
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public boolean isAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public Integer getGbeb() {
		return gbeb;
	}
	public void setGbeb(Integer gbeb) {
		this.gbeb = gbeb;
	}
	public Integer getGben() {
		return gben;
	}
	public void setGben(Integer gben) {
		this.gben = gben;
	}
	public Integer getQamb() {
		return qamb;
	}
	public void setQamb(Integer qamb) {
		this.qamb = qamb;
	}
	public Integer getQamn() {
		return qamn;
	}
	public void setQamn(Integer qamn) {
		this.qamn = qamn;
	}
}
