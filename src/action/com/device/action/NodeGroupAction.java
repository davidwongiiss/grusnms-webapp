package com.device.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.NodeGroupsBean;
import com.device.po.NodeGroups;
import com.device.util.JSONUtil;
import com.device.util.LoginUtil;
import com.device.util.ParamUtil;
import com.device.util.StringUtil;
import com.device.util.Struts2Utils;
import com.device.util.UUIDGenerator;

public class NodeGroupAction {
	private static Log log = LogFactory.getLog(NodeGroupAction.class);
	/**
	 * 结点分组类型，构成树形结构
	 */
	public void bulidGroupTree() {
		try {
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			String rootType = ParamUtil.getString(request, "type");
			String result = NodeGroupsBean.getInstance().bulidGroupTree(rootType);
			Struts2Utils.renderText(result);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	
	/**
	 * 结点分组添加
	 */
	public void saveGroup(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String pId = ParamUtil.getString(request, "pId" , "0");
		nodeGroups.setpId(pId);
		nodeGroups.setId(UUIDGenerator.generate());
		nodeGroups.setCreateTime(new Date(System.currentTimeMillis()));
		nodeGroups.setUpdateTime(new Date(System.currentTimeMillis()));
		nodeGroups.setCreator(LoginUtil.getUserId(request));
		String name = nodeGroups.getName();
		nodeGroups.setName(StringUtil.unescape(name));
		NodeGroupsBean.getInstance().saveGroup(nodeGroups);
		String node = JSONUtil.objectToJson(nodeGroups);
		Struts2Utils.renderJson(node);
	}
	/**
	 * 结点组修改
	 * @return
	 */
	public void editGroup(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String pId = ParamUtil.getString(request, "pId");
		nodeGroups.setpId(pId);
		String id = ParamUtil.getString(request, "id");
		nodeGroups.setId(id);
		String name = nodeGroups.getName();
		nodeGroups.setName(StringUtil.unescape(name));
		nodeGroups.setUpdater(LoginUtil.getUserId(request));
		nodeGroups.setUpdateTime(new Date(System.currentTimeMillis()));
		NodeGroupsBean.getInstance().editGroup(nodeGroups);
		String node = JSONUtil.objectToJson(nodeGroups);
		Struts2Utils.renderJson(node);
	}
	/**
	 * 删除结点
	 */
	public void deleteGroup(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String groupId = ParamUtil.getString(request, "groupId");
		NodeGroupsBean.getInstance().deleteGroup(groupId);
		Struts2Utils.renderText("{'groupId':'"+groupId+"'}");
	}

	private NodeGroups nodeGroups;
	public NodeGroups getNodeGroups() {
		return nodeGroups;
	}


	public void setNodeGroups(NodeGroups nodeGroups) {
		this.nodeGroups = nodeGroups;
	}
	

}
