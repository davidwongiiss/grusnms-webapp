<%@ page contentType="text/html; charset=GB2312" %>
<%@page import="com.device.util.StringUtil"%>
<%@page import="java.util.Enumeration"%>
<HTML>
<HEAD>
<% 
	String param = "";
	Enumeration xenum=request.getParameterNames();
	while(xenum.hasMoreElements()){
	String name=(String)xenum.nextElement();
 	String value=request.getParameter( name);
 	param+=name+"="+value+"&";
	}
%>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<FRAMESET border=0 name=treeframe frameSpacing=0 cols=232,* frameBorder=NO>
<FRAME name=topf src="<%= request.getContextPath() %>/jsp/event/eventGroupTree.jsp?<%= param %>" noResize scrolling=no>
<FRAME name=mainf src="<%= request.getContextPath() %>/nodes/event_queryEvents.sip" noResize scrolling=no>
</FRAMESET>
</HTML>

