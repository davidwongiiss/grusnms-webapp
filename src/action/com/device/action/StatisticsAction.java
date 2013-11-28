package com.device.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.GroupsNodesBean;

public class StatisticsAction {
	private static Log logger = LogFactory.getLog(StatisticsAction.class);
	
	//查询跳转到下个页面
	public String goGroup(){
		return "goGroup";
	}
	
	public String goipList(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String[] idArray = gids.split(",");
		@SuppressWarnings("unchecked")
		List<String> ipList = GroupsNodesBean.getInstance().getIpsByGroupIds(idArray);
		request.setAttribute("ipList", ipList);
		return "goipList";
	}
	
	public String goDeviceipList(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		@SuppressWarnings("unchecked")
		List<String> ipList = GroupsNodesBean.getInstance().getGroupIps(groupId);
		request.setAttribute("ipList", ipList);
		return "goipList2";
	}
	
	public String goFrist(){
		return "goFrist";
	}
	
	// 统计查询
	public String getStatics() {
		return "";
	}
	
	
	private String cycle ;
	private String beginTime ;
	private String endTime ;
	private String type ;
	private String gids ;
	private String groupId ;
	private Integer graphType; // 线类型	
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

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


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGids() {
		return gids;
	}

	public void setGids(String gids) {
		this.gids = gids;
	}

	public Integer getGraphType() {
		return graphType;
	}

	public void setGraphType(Integer graphType) {
		this.graphType = graphType;
	}
}
