package com.device.action;

import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.grus.nms.report.action.DateUtil;
import com.device.bean.EventsBean;
import com.device.bean.GroupsNodesBean;
import com.device.common.impl.EventsListEvent;
import com.device.common.impl.EventsListResult;
import com.device.util.ConfigManager;
import com.device.util.ParamUtil;
import com.device.util.Struts2Utils;

public class EventsHandleAction {
	private static Log log = LogFactory.getLog(EventsHandleAction.class);
	
	public String queryIps(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		List ipList = GroupsNodesBean.getInstance().getAllIps();
		request.setAttribute("ipList", ipList);
		return "ipList";
	}
	/**
	 * 查询列表2
	 * @return
	 */
	public String queryEvents(){
		try{
			//查询列表
			HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
			EventsListEvent event = new EventsListEvent();
			String groupId = ParamUtil.getString(request, "groupId");
			event.setGroupId(groupId);
			int severity = ParamUtil.getInt(request, "severity");
			event.setSeverity(severity);
			int pageNo = ParamUtil.getInt(request, "pagination.pageNO", 1).intValue();
			event.setPageNO(pageNo);
			int pageCount = ParamUtil.getInt(request, "pageCount", 10).intValue();
			event.setPageCount(pageCount);
			String handle = request.getParameter("handle");
			if(handle != null && !"".equals(handle)){
				event.setHandle(Integer.parseInt(handle));
			}
			String ip = ParamUtil.getString(request, "ip");
			if(ip != null && !"".equals(ip)){
				int index = ip.indexOf("*");
				if(index != -1){
					ip = ip.substring(0 , index);
				}
				event.setIp(ip);
			}
			
			EventsListResult result = EventsBean.getInstance().list(event);
			
			request.setAttribute("ip", ip);
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
	
	public void exportExcel(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		HttpServletResponse reponse = org.apache.struts2.ServletActionContext.getResponse();
		EventsListEvent event = new EventsListEvent();
		String groupId = ParamUtil.getString(request, "groupId");
		event.setGroupId(groupId);
		int severity = ParamUtil.getInt(request, "severity");
		event.setSeverity(severity);
		String handle = request.getParameter("handle");
		if(handle != null && !"".equals(handle)){
			event.setHandle(Integer.parseInt(handle));
		}
		String ip = ParamUtil.getString(request, "ip");
		if(ip != null && !"".equals(ip)){
			int index = ip.indexOf("*");
			if(index != -1){
				ip = ip.substring(0 , index);
			}
			event.setIp(ip);
		}
		List result = (List)EventsBean.getInstance().getResult(event);
		HSSFWorkbook wb = EventsBean.getInstance().exportEvent(result);
		try
		{
			String dirpath = ConfigManager.instance().getProperty("conf", "execldir");
			String name = DateUtil.date2Str(new Date(System.currentTimeMillis()), "yyyyMMddHHmmss");
			String namePath = dirpath +"/"+ name +".xls";
			FileOutputStream fout = new FileOutputStream(namePath);
			wb.write(fout);
			fout.close();
			Struts2Utils.renderText(namePath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Struts2Utils.renderText("fail");
		}	
		
	}
	
	/**
	 * 修改结点所在组
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
	 * 处理单条数据
	 */
	public void handleEvent(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String id = ParamUtil.getString(request, "id");
		EventsBean.getInstance().handleEvent(id);
	}
}
