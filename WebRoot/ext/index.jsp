<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.device.util.Constant"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>主页</title>
<!-- system -->
<link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/ext/resources/css/ext-patch.css" />
<link href="<%=path%>/css/menu.css?rand=v7" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<link href="<%=path%>/css/menu_ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript">
	var path = '<%= path %>';
</script>
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-base.js"></script>
<script type="text/javascript" src="<%=path%>/ext/extjs/ext-all-debug.js"></script>
<!-- custom 
<script type="text/javascript" src="<!%=path%>/ext/myjs/tab.js?rand=1115v14"></script>
-->
<script type="text/javascript" src="<%=path%>/ext/myjs/index.js?rand=1115v44"></script>
</head>
<body>
<div class="menucontainer" id="header" style="position:fixed;z-index:999;left:1px;top:1px;z-index: 1000;width:100%">
<div class="menu" style="height:76px">
<ul>
<!-- 
<li><a href="javascript:void(0)" onclick="_addTab('home','首页','<%= path %>/nodes/nodes_queryNodes.sip')">首页</a></li> //nodesHandle.jsp
 <li><a href="javascript:void(0)" onclick="_addTab('home','首页','<%= path %>/nodes/nodes_searchNodes.sip')">首页</a></li>
 -->
<li><a href="javascript:void(0)" onclick="_addTab('id1','监控台','<%=path%>/jsp/monitor/monitorNodesFrame.jsp')">监控台</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id2','报警管理','<%= path %>/jsp/event/eventsFrame.jsp')">报警管理</a></li>
<li><a href="#" >报表</a>
<table><tr><td>
<ul class="on_on ul_on" ><li>
<a href="javascript:void(0)" onclick="_addTab('id31','按设备统计','<%=path%>/jsp/statistics/search2.jsp')">按设备统计</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id32','按地域统计','<%=path%>/nodes/statis_goFrist.sip?type=<%= Constant.AREA %>')" >按地域统计</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id33','按部门统计','<%=path%>/nodes/statis_goFrist.sip?type=<%= Constant.DEPT %>')">按部门统计</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id34','按自定义统计','<%=path%>/nodes/statis_goFrist.sip?type=<%= Constant.DEFINE %>')">按自定义统计</a></li>
</ul></td></tr></table>

</li>

<li><a href="javascript:void(0)" onclick="_addTab('id4','设备管理','<%= path %>/nodes/nodes_searchNodes.sip')">设备管理</a></li>
<li><a class="on_on menu_on" href="#" >分组管理</a>
<table><tr><td>
<ul class="on_on ul_on" ><li>
<a href="javascript:void(0)" onclick="_addTab('id51','地域组','<%=path%>/jsp/group/groupEdit.jsp?type=<%= Constant.AREA %>')">地域组</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id52','部门组','<%=path%>/jsp/group/groupEdit.jsp?type=<%= Constant.DEPT %>')" >部门组</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id53','自定义组','<%=path%>/jsp/group/groupEdit.jsp?type=<%= Constant.DEFINE %>')">自定义组</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id54','地域分组','<%=path%>/jsp/groupNodesFrame.jsp?type=<%= Constant.AREA %>')">地域分组</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id55','部门分组','<%=path%>/jsp/groupNodesFrame.jsp?type=<%= Constant.DEPT %>')" >部门分组</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id56','自定义分组','<%=path%>/jsp/groupNodesFrame.jsp?type=<%= Constant.DEFINE %>')" >自定义分组</a></li>
</ul></td></tr></table>

</li>
<li><a href="#" target="_self">用户管理</a>
<table><tr><td>
<ul class="on_on ul_on" >
<li class="ul_li_a_on"><a href="javascript:void(0)" onclick="_addTab('id61','用户','<%=path%>/nodes/users_queryUsers.sip')">用户</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id62','分配设备','<%=path%>/jsp/usernode_f.jsp')">分配设备</a></li>
<li><a href="javascript:void(0)" onclick="_addTab('id63','修改密码','<%=path%>/jsp/users/changePassword.jsp')">修改密码</a></li>
</ul></td></tr></table>
</li>
<li><a href="#" target="_self">帮助</a></li>
</ul>
</div>
</div>
<!-- 
<div id="center_context_desktop">
<iframe src='<//%=path%>/jsp/monitor/monitorNodesFrame.jsp'   style='width:100%;height:100%' ></iframe>
</div>
 -->
</body>
</html>