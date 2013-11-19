<%@ page contentType="text/html; charset=GB2312" %>
<%@page import="com.device.util.StringUtil"%>
<HTML>
<HEAD>
<% String type = request.getParameter("type"); %>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<FRAMESET border=0 name=treeframe frameSpacing=0 cols=232,* frameBorder=NO>
<FRAME name=topf src="<%= request.getContextPath() %>/jsp/groupTree.jsp?type=<%= StringUtil.killNull(type) %>" noResize scrolling=no>
<FRAME name=mainf src="<%= request.getContextPath() %>/nodes/nodes_queryNodes.sip" noResize scrolling=no>
</FRAMESET>
</HTML>

