package com.device.po;

import java.sql.Timestamp;
import java.util.Date;

public class NodeEvents {
	public String id;   // uuid
	public String nodeId;
	public String eventId;
	public String eventObject;
	public String description;
	public String severity;
	public Date createTime;
	public Integer handled;
	String user; //
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
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventObject() {
		return eventObject;
	}
	public void setEventObject(String eventObject) {
		this.eventObject = eventObject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getHandled() {
		return handled;
	}
	public void setHandled(Integer handled) {
		this.handled = handled;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
