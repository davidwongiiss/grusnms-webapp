package com.device.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.GroupsNodesBean;
import com.device.bean.UsersBean;
import com.device.common.LoginResult;
import com.device.po.Users;
import com.device.util.ParamUtil;

public class StatisticsAction {
	private static Log log = LogFactory.getLog(StatisticsAction.class);
	//查询跳转到下个页面
	public String goGroup(){
		return "goGroup";
	}
	
	public String goipList(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String[] idArray = gids.split(",");
		List ipList = GroupsNodesBean.getInstance().getIpsByGroupIds(idArray);
		request.setAttribute("ipList", ipList);
		return "goipList";
	}
	
	public String goDeviceipList(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		List ipList = GroupsNodesBean.getInstance().getGroupIps(groupId);
		request.setAttribute("ipList", ipList);
		return "goipList2";
	}
	
	public String goFrist(){
		return "goFrist";
	}
	
	//统计查询
	public String getStatics(){
		
		return "";
	}
	
	
	private String cycle ;
	private String beginTime ;
	private String endTime ;
	private String type ;
	private String gids ;
	private String groupId ;
	
	
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


	
	

}
