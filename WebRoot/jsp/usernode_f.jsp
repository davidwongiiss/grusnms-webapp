<%@ page language="java"  pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Frameset//EN">
<HTML><HEAD><TITLE></TITLE>
	<META http-equiv=Content-Type content="text/html; charset=gb2312">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
</HEAD>
<FRAMESET border=0 name=userNodeFrame frameSpacing=0  cols=270,15,* frameBorder=0>
<FRAME name=user src="<%= request.getContextPath() %>/jsp/queryNUsers.jsp" noresize="noresize" scrolling="auto">
<frame name="allows" src="frame_reset.htm" scrolling="no"/>
<FRAME name=nodes_detailf src="<%= request.getContextPath() %>/nodes/users_queryUserNodes.sip" noresize="noresize" scrolling="no">
</FRAMESET>
</HTML>
