<%@ page contentType="text/html; charset=GB2312" %>
<%@page import="com.device.util.StringUtil"%>
<HTML>
<HEAD>
	<%
		String cycle = (String)request.getParameter("cycle");
		String beginTime = (String)request.getParameter("beginTime");
		String endTime = (String)request.getParameter("endTime");
		String type = (String)request.getParameter("type");
		if(type == null){
			type = "";
		}
	%>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<FRAMESET border=0 name=treeframe frameSpacing=0 cols=232,* frameBorder=NO>
<FRAME name=topf src="<%=request.getContextPath()%>/nodes/statis_goGroup.sip?cycle=<%=cycle %>&beginTime=<%= beginTime %>&endTime=<%=endTime %>&type=<%= type %>" noResize scrolling=no>
<FRAME name=mainf src="<%= request.getContextPath() %>/jsp/statistics/iplist.jsp" noResize scrolling=no>
</FRAMESET>
</HTML>

