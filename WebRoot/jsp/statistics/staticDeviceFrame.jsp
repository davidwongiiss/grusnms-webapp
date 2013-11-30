<%@ page contentType="text/html; charset=GB2312" %>
<%@page import="com.device.util.StringUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.net.URLEncoder"%>
<HTML>
<HEAD>
<%
	String param = "";
	Enumeration xenum=request.getParameterNames();
	while(xenum.hasMoreElements()){
  	String name=(String)xenum.nextElement();
   	String value=URLEncoder.encode(request.getParameter( name));
   	param+=name+"="+value+"&";
	}
  System.out.println(param);
%>

	<%--
		String cycle = (String)request.getParameter("cycle");
		String beginTime = (String)request.getParameter("beginTime");
		String endTime = (String)request.getParameter("endTime");
		String chartType = (String)request.getParameter("chartType");
		String type = (String)request.getParameter("type");
		if(type == null){
			type = "";
		}
	--%>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<FRAMESET border=0 name=treeframe frameSpacing=0 cols=232,* frameBorder=NO>
<FRAME name=topf src="<%=request.getContextPath()%>/jsp/statistics/staticDeviceTree.jsp?<%= param %>" noResize scrolling=no>
<FRAME name=mainf src="<%= request.getContextPath() %>/jsp/statistics/iplist2.jsp" noResize scrolling=no>
</FRAMESET>
</HTML>

