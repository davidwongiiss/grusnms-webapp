package com.device.po;

import java.sql.Timestamp;
import java.util.Date;

public class NodeEvents {
	private String id;   // uuid
	private String nodeId;
	private String eventId;
	private String eventObject;
	private String physIdx;
	private String description;
	private Integer severity;
	private Date createTime;
	private Boolean handled;
	String user; //
	
	private String seqNo;  // 服务器端的整体事件变化序号
	private Timestamp eventTime; // 服务器端事件发生事件
	
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
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getHandled() {
		return handled;
	}
	public void setHandled(Boolean handled) {
		this.handled = handled;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Timestamp getEventTime() {
		return eventTime;
	}
	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getPhysIdx() {
		return physIdx;
	}
	public void setPhysIdx(String physIdx) {
		this.physIdx = physIdx;
	}
}
