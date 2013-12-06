package com.device.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.device.common.LoginResult;

public class LoginUtil {
	static final boolean enabled = false ;//ÊÇ·ñ´ò¿ªµÇÂ½
	
	public static String getUserId(HttpServletRequest req){
		LoginResult result = (LoginResult)req.getSession().getAttribute("ur");
		if(result != null ){
			return result.getUserName();
		}
		if(!enabled){
			return "admin";
		}else{
			return null;
		}
	}
	
	public static boolean checkAdmin(HttpServletRequest req){
		LoginResult result = (LoginResult)req.getSession().getAttribute("ur");
		if(result != null ){
			int i = result.isIsAdmin();
			if(i>0)return true;
		}
		if(!enabled){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean clearSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("ur");
		return true;
	}

}
