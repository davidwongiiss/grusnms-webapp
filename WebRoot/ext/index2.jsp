<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>主页</title>

<!-- system -->

<link href="<%=path%>/css/menu.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<link href="<%=path%>/css/menu_ie.css" rel="stylesheet" type="text/css" />
<![endif]-->





</head>
<body>
<div class="menucontainer">
<div class="menu">
<ul>
<li><a href="http://www.makewing.com" target="_blank">Home</a></li>
<li><a href="#" target="_self">About us</a></li>
<li><a href="#"  class="on_on menu_on">Services<!--[if IE 7]><!-->
</a>
<!--<![endif]-->
<table>
<tr>
<td>
<ul class="on_on ul_on">
<li class="ul_li_a_on"><a href="#" >Design</a></li>
<li><a href="#" >Strategy</a></li>
<li><a href="#" >Analysis</a></li>
</ul>
</td>
</tr>
</table>
<!--[if lte IE 6]>
</a>
<![endif]-->
</li>
<li><a href="#" target="_self">Support</a></li>
<li><a href="#" target="_self">Forums</a></li>
<li><a href="#" target="_self">Contact Us</a></li>
</ul>
</div>
</div>



</body>

</html>
