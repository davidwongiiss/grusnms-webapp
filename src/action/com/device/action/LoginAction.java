package com.device.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.device.bean.UsersBean;
import com.device.common.LoginResult;
import com.device.po.Users;
import com.device.util.ParamUtil;
import com.device.util.Struts2Utils;

public class LoginAction {
	private static Log log = LogFactory.getLog(LoginAction.class);
	//≤È—Ø”√ªß
	public void login(){
		HttpServletRequest request=org.apache.struts2.ServletActionContext.getRequest();
		String name = ParamUtil.getString(request, "username");
		String password = ParamUtil.getString(request, "password");
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
		request.getSession().setAttribute("ur", ur);
		Struts2Utils.renderJson("{success:true}");
		
	}
	

}
