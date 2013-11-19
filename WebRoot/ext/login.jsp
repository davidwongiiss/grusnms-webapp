
<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>用户登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- system -->
<link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-patch.css" />
</head>
<body>
<div id="loading-mask" style="">
<div id="loading">
<div style="text-align:center;padding-top:26%">
<img src="<%=path%>/ext/resources/images/default/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="middle"/>Loading...
</div>
</div>
</div>
<!-- system -->
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-base.js"></script>
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-all.js"></script>
<!-- custom -->
<script type="text/javascript" src="<%=path%>/ext/myjs/login.js?rand=1115"></script>
</body>
</html>
