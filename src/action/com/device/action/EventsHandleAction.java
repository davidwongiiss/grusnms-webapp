package com.device.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.EventsBean;
import com.device.bean.GroupsNodesBean;
import com.device.common.impl.EventsListEvent;
import com.device.common.impl.EventsListResult;
import com.device.util.ParamUtil;

public class EventsHandleAction {
	private static Log log = LogFactory.getLog(EventsHandleAction.class);
	
	public String queryIps(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		List ipList = GroupsNodesBean.getInstance().getAllIps();
		request.setAttribute("ipList", ipList);
		return "ipList";
	}
	/**
	 * ��ѯ�б�2
	 * @return
	 */
	public String queryEvents(){
		try{
			//��ѯ�б�
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			EventsListEvent event = new EventsListEvent();
			int severity = ParamUtil.getInt(request, "severity");
			event.setSeverity(severity);
			int pageNo = ParamUtil.getInt(request, "pagination.pageNO", 1).intValue();
			event.setPageNO(pageNo);
			int pageCount = ParamUtil.getInt(request, "pageCount", 10).intValue();
			event.setPageCount(pageCount);
			String handle = request.getParameter("handle");
			if(handle != null){
				event.setHandle(Boolean.getBoolean(handle));
			}
			
			EventsListResult result = EventsBean.getInstance().list(event);
			
			String groupId = ParamUtil.getString(request, "groupId");
			List ipList = GroupsNodesBean.getInstance().getGroupIps(groupId);
			request.setAttribute("ipList", ipList);
			request.setAttribute("result", result);
			request.setAttribute("groupId", groupId);
			request.setAttribute("pagination", result.getPagination());
			request.setAttribute("event", event);
			return "eventsHandle";
		}catch(Exception e){
			log.error(e);
			return "failure";
		}
	}
	
	/**
	 * �޸Ľ��������
	 * @return
	 */
	public String batchHandleEvents(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = ParamUtil.getString(request, "ids");
		if(!StringUtils.isEmpty(ids))
		{
			String[] idArray = ids.split(",");
			EventsBean.getInstance().batchHandle(idArray);
		}
		return "_forward"; 
	}
	/**
	 * ����������
	 */
	public void handleEvent(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String id = ParamUtil.getString(request, "id");
		EventsBean.getInstance().handleEvent(id);
	}
}
