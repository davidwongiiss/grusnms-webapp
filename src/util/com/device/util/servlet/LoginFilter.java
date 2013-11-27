/*
 * Created on 2005-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.util.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自动进行认证
 * @author chenligang
 *sohu-R&D
 */
public class LoginFilter implements Filter {
    protected FilterConfig filterConfig = null;
    /**
     *
     */

	/**
	 * 初始化过滤器
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     *
     */

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        httpRequest.setCharacterEncoding("GBK");
        HttpSession session = httpRequest.getSession();
        
        if(session.getAttribute("user")==null){
        	httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp"); 
        	return ;
        }else{
        	chain.doFilter(request,response);
        }
    }

    /**
     *
     */

    public void destroy() {
        // TODO Auto-generated method stub
        this.filterConfig=null;
    }

}
