<%@ page contentType="text/html; charset=GB2312" %>
<%@ page
	import="java.util.*,
				com.device.common.impl.EventsListEvent,
				com.device.po.NodeEvents"
%>
<%@page import="com.device.util.Constant"%>
<%@page import="com.device.common.impl.EventsListResult"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="com.device.po.NodeGroups"%>
<%@page import="com.device.common.impl.UsersListResult"%>
<%@page import="com.device.po.Users"%>
<%@page import="com.device.common.impl.UsersEvent"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>用户管理</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/frame.js"></script>
		<script type="text/javascript">
		function addUser(){
			var add_url="<%= path %>/jsp/addUser.jsp";
			var retval = popUpModalDialog(add_url,860,500,"","",'<%=path%>',"增加人员");
			$("#myform").submit();
		}
		function delUser(id){
			var url = "<%= path %>/nodes/users_deleteUser.sip?rand=new Date()";
			$.get(url,{"id":id},function(){$("#tr"+id).remove()});
		}
		function editUser(id){
			var url = "<%= path %>/nodes/users_queryUserBean.sip?id="+id;
			var retval = popUpModalDialog(url,860,500,"","",'<%=path%>',"修改人员");
			$("#myform").submit();
		}
		</script>
	</HEAD>

	<BODY class="tab" >
	<%
		UsersEvent users = (UsersEvent)request.getAttribute("event");
		if(users == null) users = new UsersEvent();
	%>
	<!-- 查询表单开始 -->
		<form name="form1" id="myform" action="<%= path %>/nodes/users_queryUsers.sip" method="post">
		<input type="hidden" value="" id="ids" name="ids">
		<div class="query_table_d"> 
			 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="query_table">
			 <tr class="table_top">
			 	<td width="3%" class="table_top_l"></td>
			 	<td></td>
			 	<td width="3%" class="table_top_r"></td>
			 </tr> 
			 <tr>
			 	<td class="font_text_right border_l">&nbsp;</td>
			 	<td class="border_b">
			 	<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="query_table_in">     
				<tr> 
					<td class="font_text_right"  width="15%">用户</td>
					<td align="left" width='20%'>
						<div class="inpit2_bg" > <input  id="name"   name="name"  value=""  type="text"  size="16" /></div>
					</td>
					<td class="font_text_right"  width="15%">电话号码</td>
					<td align="left" width='20%'  >
						<div class="inpit2_bg">
							<input name="mobileNo" value="" type="text" />
						</div>
					</td>
				</tr>
				<tr> 
					<td class="font_text_right"  width="15%">创建时间</td>
					<td align="left" width='20%'  >
					<script src="<%= path %>/js/JSCalendar.js"></script>
					<script src="<%= path %>/js/CheckDateTime.js"></script>
					<input name="beginTime" id="beginTime" type="text" size="10" value=""  null readonly  class="inpit_bg" onblur="CheckD('beginTime');" />
					<img  name="loginTimeButton"  src="<%= path %>/images/button/time_btn.jpg"  class="time_btn"   style="cursor:hand" onClick="selectQustion('beginTime',document.getElementById('beginTime').value,'<%= path %>/');" >
					--至--
					<script src="<%= path %>/js/JSCalendar.js"></script>
					<script src="<%= path %>/js/CheckDateTime.js"></script>
					<input name="endTime" id="endTime" type="text" size="10" value=""  null readonly  class="inpit_bg" onblur="CheckD('endTime');" />
					<img  name="loginTimeButton"  src="<%= path %>/images/button/time_btn.jpg"  class="time_btn"   style="cursor:hand" onClick="selectQustion('endTime',document.getElementById('endTime').value,'<%= path %>/');" >
					
					</td>

					<td class="font_text_right"  width="15%">是否管理员</td>
					<td align="left" width='20%'  >
						<select name="isAdmin" class="select152">
							<option value='' selected>选择</option>
							<option value='1'>是</option>
							<option value='0'>否</option>
						</select>
					</td>
				</tr>
					<tr> 
						<td colspan="6" align="center" style="text-align: center;">
							<input type="submit" class="btn60"  value='查询' />
							<input type="reset" class="btn60"  value='重置' />
						</td>
					</tr>
		
		</table>	
		</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
		</form>
	
	<!-- 查询表单结束 -->
	<!-- 列表开始 -->

			<div class="table_header">
				<h2>
					用户列表
				</h2>
				<div class="table_list_right">
				</div>
				<div class="table_header_r" id="groupdiv">
					<a href="javascript:void(0);" onclick="addUser();return false;" > <img src="<%= path %>/images/button/icon1.gif"  />增加人员</a>
				</div>
			</div>
			<table width="99.6%" height="75%" border="0" cellpadding="0"
				cellspacing="0" class="ls_list1">
				<tr>
					<td>
						<div id="listTableDivId"
							style="overflow: auto; width: 100%; height: 100%; overflow-y: yes; overflow-x: yes;">
							<p>
							<%
							UsersListResult result = (UsersListResult)request.getAttribute("result");
							Collection<Users> c = null;
							if (result != null) {
								c = result.getC();
							}
							%>	
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
							        <th scope="col">用户名</th>
							        <th scope="col">真实姓名</th>
							        <th scope="col">电话</th>
							        <th scope="col">email</th>
							        <th scope="col">创建时间</th>
							        <th scope="col">修改时间</th>
							        <th scope="col">创建人</th>
							        <th scope="col">修改人</th>
							        <th scope="col" class="list_bor_no">操作</th>
							      </tr>
								  <%	
								  	if (c != null && c.size() > 0 ) {
									  Iterator<Users> it = c.iterator();
								
								       while(it.hasNext()){
								    	   Users item = it.next();
								           
									%>
							        <tr id="tr<%= item.getName() %>">
							        <td><%= item.getName() %></td>
							        <td><%= item.getRealname() %></td>
							        <td><%= item.getMobileNo() %></td>
							        <td><%= item.getEmail() %></td>
							        <td><%= item.getCreateTime() %></td>
							        <td><%= item.getUpdateTime() %></td>
							        <td><%= item.getCreator() %></td>
							        <td><%= item.getUpdater() %></td>
							        <td><a href="javascript:editUser('<%= item.getName() %>');void(0)">修改</a> &nbsp;&nbsp;<a href="javascript:delUser('<%= item.getName() %>');void(0)">删除</a></td>
							        </tr>
								     <%}
								      }
										%>
								</table>
							</p>
						</div>
					</td>
				</tr>
			</table>
			<script language="javascript" src="<%= request.getContextPath() %>/js/table_util.js"></script>
			<jsp:include page="include/pagination.jsp">
				<jsp:param name="baseurl" value="/nodes/users_queryUsers.sip" />
			</jsp:include>
		<!-- 列表结束 -->	
	</BODY>
</HTML>