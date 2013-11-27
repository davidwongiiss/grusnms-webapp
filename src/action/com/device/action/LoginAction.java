package com.device.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.UsersBean;
import com.device.common.LoginResult;
import com.device.po.Users;
import com.device.util.ParamUtil;

public class LoginAction {
	private static Log log = LogFactory.getLog(LoginAction.class);
	//≤È—Ø”√ªß
	public String login(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String name = ParamUtil.getString(request, "userName");
		String password = ParamUtil.getString(request, "passWord");
		Users user = UsersBean.getInstance().queryUserBean(name);
		LoginResult ur = new LoginResult();
		if("admin".equals(name) && "admin".equals(password)){
			ur.setIsAdmin(1);
			ur.setUserName("admin");
		}
		if(user!=null && user.getPassword().equals(password)){
			ur.setIsAdmin(user.getIsAdmin());
			ur.setUserName(user.getName());
		}
		if(ur != null){
			return "login";
		}else{
			return "index";
		}
		
	}
	

}
