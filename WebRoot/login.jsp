<%@ page contentType="text/html; charset=GBK"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>登陆界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<!-- system -->
<!--link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-patch.css" />  -->
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="ext/myjs/login1.js" charset="gb2312"></script>
<link href="css/common.css" media="screen" rel="stylesheet"
	type="text/css" />
<style media="screen" type="text/css">
body {
	background: #016aa9;
}

#login {
	width: 960px;
	height: 500px;
	background: url(images/login.jpg) no-repeat;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -250px 0 0 -480px;
}

#login form {
	width: 340px;
	height: 120px;
	position: absolute;
	top: 208px;
	left: 315px;
}

#login div {
	padding: 0 0 5px;
}

#login label {
	width: 80px;
	text-align: right;
	font-size: 14px;
	display: inline-block;
	color: #FFF;
	font-weight: bold;
}

#login .input {
	height: 25px;
	line-height: 16px;
	padding: 2px;
	font-size: 14px;
	width: 160px;
	border: 1px #7DBAD7 solid;
	background: #292929;
	color: #6CD0FF;
	letter-spacing: 1px;
}

#login #check-code {
	width: 60px;
}

#login img {
	width: 50px;
	height: 16px;
	vertical-align: -4px; *
	vertical-align: -2px;
}

#login .btn {
	padding-left: 84px;
}

#login .button {
	width:40px;
	height:26px;
	font-size: 14px;
	margin-right: 10px;
	cursor: pointer;
}
</style>
</head>
<body>
<div id="login">

<form id="myForm" action="#" method="post" target="_parent">
<div><label for="username">用户ID:</label> 
<input class="input" id="username" maxlength="20" name="users_userid" type="text" value="" /></div>
<div><label for="password">密码:</label> 
<input class="input" id="password" maxlength="20" name="users_password" type="password" value="" /></div>
<div class="btn">
<input class="button" id="submit" type="button" onclick="subForm();" value="提交"/>
<input class="button" id="reset" type="button" onclick="formReset();" value="重置"/>
</div>
</form>
</div>

<!--
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-base.js"></script>
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-all.js"></script>

<script type="text/javascript" src="<%=path%>/ext/myjs/login.js?rand=1135"></script>
  -->

</body>
</html>
