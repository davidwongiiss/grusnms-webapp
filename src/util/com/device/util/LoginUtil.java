package com.device.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {
	
	public static String getUserId(){
		return "rainbow";
	}
	
	public static boolean checkAdmin(){
		return true;
	}
	
	public static boolean clearSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		return true;
	}

}
