<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.device.util.LoginUtil"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>��½����</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
<body>
<%= LoginUtil.clearSession(request)%>
<script type="text/javascript">
	window.location.href = "<%= request.getContextPath()%>/login.jsp";
</script>
</body>
</html>
