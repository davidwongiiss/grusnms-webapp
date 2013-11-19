package com.device.po;

import java.io.Serializable;

public class GNpk implements Serializable{
	private String nodeId;
	private String groupId;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
