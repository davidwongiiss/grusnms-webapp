package com.device.po;

/**
 * 节点汇总表
 * @author davidwongiiss
 *
 */
public class NodeStatus {
	private String nodeId;
	private Long gbeBitrate;
	private Long gbeService;
	private Long qamBitrate;
	private Long qamService;
	private int eventCount;
	private int alarmCount;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public Long getGbeBitrate() {
		return gbeBitrate;
	}
	public void setGbeBitrate(Long gbeBitrate) {
		this.gbeBitrate = gbeBitrate;
	}
	public Long getGbeService() {
		return gbeService;
	}
	public void setGbeService(Long gbeService) {
		this.gbeService = gbeService;
	}
	public Long getQamBitrate() {
		return qamBitrate;
	}
	public void setQamBitrate(Long qamBitrate) {
		this.qamBitrate = qamBitrate;
	}
	public Long getQamService() {
		return qamService;
	}
	public void setQamService(Long qamService) {
		this.qamService = qamService;
	}
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	public int getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}
};

