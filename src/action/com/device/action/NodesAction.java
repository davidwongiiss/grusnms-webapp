/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.action;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.GroupsNodesBean;
import com.device.bean.NodeGroupsBean;
import com.device.bean.NodesBean;
import com.device.common.impl.NodesListEvent;
import com.device.common.impl.NodesListResult;
import com.device.po.NodeGroups;
import com.device.po.Nodes;
import com.device.util.LoginUtil;
import com.device.util.ParamUtil;
import com.device.util.Struts2Utils;
import com.device.util.UUIDGenerator;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodesAction {
	private static Log log = LogFactory.getLog(NodesAction.class);
	private Nodes node;
	//添加结点
	public String addNodes() {
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		node.setId(UUIDGenerator.generate());
		node.setCreator(LoginUtil.getUserId(request));
		node.setCreateTime(new Date(System.currentTimeMillis()));
		node.setUpdater(LoginUtil.getUserId(request));
		node.setUpdateTime(new Date(System.currentTimeMillis()));
		NodesBean.getInstance().saveNode(node);
		return "ok";
	}
	//修改结点
	public String updateNodes() {
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		node.setUpdater(LoginUtil.getUserId(request));
		node.setUpdateTime(new Date(System.currentTimeMillis()));
		NodesBean.getInstance().updateNode(node);
		return "ok";
	}
	//删除结点
	public void deleteNode() {
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String nodeId = ParamUtil.getString(request, "id");
		NodesBean.getInstance().deleteNode(nodeId);
		Struts2Utils.renderText(nodeId);
	}
	//查询bean
	public String queryNodeBean(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String nodeId = ParamUtil.getString(request, "id");
		node = NodesBean.getInstance().getNodeById(nodeId);
		return "updateNode";
	}
	
	public void checkNodeExit(){
		Struts2Utils.renderText("0");
	}
	
	//设备管理界面，进行设备查询
	public String searchNodes(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		NodesListEvent event = new NodesListEvent();
		Integer pageNo = ParamUtil.getInt(request, "pagination.pageNO", 1).intValue();
		event.setPageNO(pageNo);
		Integer pageCount = ParamUtil.getInt(request, "pageCount", 10).intValue();
		event.setPageCount(pageCount);
		String ip = ParamUtil.getString(request, "ip"); 
		event.setIp(ip);
		String name = ParamUtil.getString(request, "name");
		event.setName(name);
		String deviceSn = ParamUtil.getString(request, "deviceSn");
		event.setDeviceSn(deviceSn);
		NodesListResult result = NodesBean.getInstance().list(event , LoginUtil.getUserId(request) , LoginUtil.checkAdmin(request));
		request.setAttribute("result", result);
		request.setAttribute("pagination", result.getPagination());
		request.setAttribute("event", event);
		return "nodesHandle";
	}
	
	
	//忘记在哪里用了。
	public String listNodes() {
		try {
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			String name = ParamUtil.getString(request, "name");
			NodesListEvent event = new NodesListEvent( name ,
					ParamUtil.getInt(request, "pagination.pageNO", 1).intValue(),
					ParamUtil.getInt(request, "pageCount", 10).intValue());
			NodesListResult result = NodesBean.getInstance().listUnallocat(event);
			request.setAttribute("result", result);
			request.setAttribute("name", name);
			request.setAttribute("pagination", result.getPagination());
			if (result.getStatus() == 0)
				return "listNodes";
			else
				return "failure";
		} catch (Exception e) {
			log.error(e);
			return "failure";
		}
	}
	/**
	 * 查询列表,提供分组页面2
	 * @return
	public String queryNodes(){
		try{
			//查询列表
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			NodesListEvent event = new NodesListEvent(ParamUtil.getString(request, "deviceType"),
					ParamUtil.getString(request, "name"),
					ParamUtil.getInt(request, "pagination.pageNO", 1).intValue(),
					ParamUtil.getInt(request, "pageCount", 10).intValue());
			NodesListResult result = NodesBean.getInstance().list(event);
			//页面分组数据
			Collection<NodeGroups> nodeGroups = NodeGroupsBean.getInstance().getGroupType();
			request.setAttribute("nodeGroups", nodeGroups);
			request.setAttribute("result", result);
			request.setAttribute("pagination", result.getPagination());
			request.setAttribute("event", event);
			return "nodeGroup";
		}catch(Exception e){
			log.error(e);
			return "failure";
		}
	}
	 */
	//提供查询设备查询分组列表
	public String queryNodes(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String groupId = ParamUtil.getString(request, "groupId");
		String groupName = ParamUtil.getString(request, "groupName");
		String ip = ParamUtil.getString(request, "ip");
		NodesListResult result = NodesBean.getInstance().listAllNodes(groupId , ip);
		request.setAttribute("result", result);
		request.setAttribute("groupId", groupId);
		request.setAttribute("ip", ip);
		request.setAttribute("groupName", groupName);
		return "nodeGroup";
	}
	/**
	 * 修改结点所在组
	 * @return
	 */
	public String insertNodeToGroup(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = ParamUtil.getString(request, "ids");
		String gid = ParamUtil.getString(request, "groupId");
		if( !StringUtils.isEmpty(gid))
		{
			String[] idArray = null ;
			if(!StringUtils.isEmpty(ids)){
				idArray = ids.split(",");
			}
			GroupsNodesBean.getInstance().batchInsert(gid, idArray);
		}
		return "_forward"; 
	}
	
	
	

	public Nodes getNode() {
		return node;
	}
	public void setNode(Nodes node) {
		this.node = node;
	}
	public String success() {
		return "success";
	}

	public String failure() {
		return "failure";
	}

}
