package com.device.po;


public class UsersNodes implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -216600877492832677L;
	private String userName ;
	private String nodeId ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
