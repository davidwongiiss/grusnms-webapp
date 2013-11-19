/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.GroupsNodesBean;
import com.device.bean.NodeGroupsBean;
import com.device.bean.NodesBean;
import com.device.common.impl.NodesListEvent;
import com.device.common.impl.NodesListResult;
import com.device.po.NodeGroups;
import com.device.util.ParamUtil;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodesAction {
	private static Log log = LogFactory.getLog(NodesAction.class);

	public String addNodes() {
		return "addNodes";
	}

	public String listNodes() {
		try {
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			NodesListEvent event = new NodesListEvent(ParamUtil.getString(request, "deviceType"),
					ParamUtil.getString(request, "name"),
					ParamUtil.getInt(request, "pagination.pageNO", 1).intValue(),
					ParamUtil.getInt(request, "pageCount", 10).intValue());
			NodesListResult result = NodesBean.getInstance().listUnallocat(event);
			request.setAttribute("result", result);
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
	//
	public String queryNodes(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String groupId = ParamUtil.getString(request, "groupId");
		String groupName = ParamUtil.getString(request, "groupName");
		NodesListEvent event = new NodesListEvent();
//		event.setName();
		NodesListResult result = NodesBean.getInstance().listAllNodes(groupId);
//		request.setAttribute("nodeGroups", nodeGroups);
		request.setAttribute("result", result);
		request.setAttribute("groupId", groupId);
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
		String gid = ParamUtil.getString(request, "gid");
		if(!StringUtils.isEmpty(ids) && !StringUtils.isEmpty(gid))
		{
			String[] idArray = ids.split("_");
			GroupsNodesBean.getInstance().batchInsert(gid, idArray);
		}
		return "_forward"; 
	}
	
	
	
	

	public String insertNodes() {
		return "insertNodes";
	}

	public String modifyNodes() {
		return "modifyNodes";
	}
	
	public String updateNodes() {
		return "updateNodes";
	}

	public String deleteNodes() {
		return "deleteNodes";
	}

	public String success() {
		return "success";
	}

	public String failure() {
		return "failure";
	}

}
