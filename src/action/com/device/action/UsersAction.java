package com.device.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.NodesBean;
import com.device.bean.UsersBean;
import com.device.common.impl.NodesListResult;
import com.device.common.impl.UsersEvent;
import com.device.common.impl.UsersListResult;
import com.device.po.Users;
import com.device.util.DataFormat;
import com.device.util.LoginUtil;
import com.device.util.ParamUtil;

public class UsersAction {
	private static Log log = LogFactory.getLog(UsersAction.class);
	//��ѯ�û�
	public String queryUsers(){
		try{
			//��ѯ�б�
			HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
			UsersEvent event = new UsersEvent(); 
			event.setUserName(ParamUtil.getString(request, "userName"));
			event.setMobileNo(ParamUtil.getString(request, "mobileNo"));
			event.setIsAdmin(ParamUtil.getInt(request, "isAdmin"));
			String beginTime = ParamUtil.getString(request, "beginTime");
			String endTime = ParamUtil.getString(request, "endTime");
			if(!"".equals(beginTime)&&!"".equals(endTime)){
				event.setBeginTime(DataFormat.parseDate(beginTime));
				event.setEndTime(DataFormat.parseDate(endTime));
			}
			UsersListResult result = UsersBean.getInstance().list(event);
			request.setAttribute("result", result);
			request.setAttribute("pagination", result.getPagination());
			request.setAttribute("event", event);
			return "queryUsers";
		}catch(Exception e){
			log.error(e);
			return "failure";
		}
	}
	//queryNUsers
	//��ѯ�û�
	public String queryNUsers(){
		try{
			//��ѯ�б�
			HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
			UsersEvent event = new UsersEvent(); 
			event.setUserName(ParamUtil.getString(request, "userName"));
			event.setMobileNo(ParamUtil.getString(request, "mobileNo"));
			event.setIsAdmin(ParamUtil.getInt(request, "isAdmin"));
			String beginTime = ParamUtil.getString(request, "beginTime");
			String endTime = ParamUtil.getString(request, "endTime");
			if(!"".equals(beginTime)&&!"".equals(endTime)){
				event.setBeginTime(DataFormat.parseDate(beginTime));
				event.setEndTime(DataFormat.parseDate(endTime));
			}
			UsersListResult result = UsersBean.getInstance().list(event);
			request.setAttribute("result", result);
			request.setAttribute("pagination", result.getPagination());
			request.setAttribute("event", event);
			return "queryNUsers";
		}catch(Exception e){
			log.error(e);
			return "failure";
		}
	}
	//��ѯ�û�bean
	public String queryUserBean(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String userName =  ParamUtil.getString(request, "id");
		user = UsersBean.getInstance().queryUserBean(userName);
		return "editUser";
	}
	//�����û�
	public String addUser(){	
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		user.setCreateTime(new Date(System.currentTimeMillis()));
		user.setUpdater(LoginUtil.getUserId());
		user.setCreator(LoginUtil.getUserId());
		user.setIsDelete(0);
		UsersBean.getInstance().saveUser(user);
		return "ok";
	}
	//ɾ���û�
	public void deleteUser(){	
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String userName = ParamUtil.getString(request, "id");
		UsersBean.getInstance().deleteUser(userName);
		return;
	}
	//�༭�û�
	public String editUser(){	
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		user.setUpdater(LoginUtil.getUserId());
		UsersBean.getInstance().updateUser(user);
		return "ok";
	}
	
	//��ѯ�û��½��
	public String queryUserNodes(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String userName = ParamUtil.getString(request, "id");
		NodesListResult result = NodesBean.getInstance().queryUserNodes(userName);
		request.setAttribute("result", result);
		request.setAttribute("id", userName);
		return "useNodesList";
	}
	//��������û�
	public String allocatNodes(){
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String userName = ParamUtil.getString(request, "id");
		String nids = ParamUtil.getString(request, "nids");
		String[] nidArray = nids.split(",");
		NodesBean.getInstance().batchInsert(userName , nidArray);
		return "ok";
	}

	
	public Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}