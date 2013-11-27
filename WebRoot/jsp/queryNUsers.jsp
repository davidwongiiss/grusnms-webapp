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
		<TITLE>�û�����</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/frame.js"></script>
	</HEAD>

	<BODY class="tab" >
	<%
		UsersEvent users = (UsersEvent)request.getAttribute("event");
		if(users == null) users = new UsersEvent();
	%>
	<!-- ��ѯ����ʼ -->
		<form name="form1" id="myform" action="<%= path %>/nodes/users_queryNUsers.sip" method="post">
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
					<td class="font_text_right"  width="15%">�û�</td>
					<td align="left" width='20%'>
						<div class="inpit2_bg" > <input  id="name"   name="name"  value=""  type="text"  size="16" /></div>
					</td>
				</tr>
				<tr>
					<td class="font_text_right"  width="15%">�绰����</td>
					<td align="left" width='20%'  >
						<div class="inpit2_bg">
							<input name="mobileNo" value="" type="text" />
						</div>
					</td>
				</tr>
				<tr> 
					<td colspan="2" align="center" style="text-align: center;">
						<input type="submit" class="btn60"  value='��ѯ' />
						<input type="reset" class="btn60"  value='����' />
					</td>
				</tr>
		
		</table>	
		</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
		</form>
	
	<!-- ��ѯ������ -->
	<!-- �б�ʼ -->

			<div class="table_header">
				<h2>
					�û��б�
				</h2>
				<div class="table_list_right">
				</div>
				<div class="table_header_r" id="groupdiv">
				</div>
			</div>
			<table width="99.6%" height="70%" border="0" cellpadding="0"
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
							        <th scope="col">�û���</th>
							        <th scope="col">��ʵ����</th>
							        <th scope="col">�绰</th>
							      </tr>
								  <%	
								  	if (c != null && c.size() > 0 ) {
									  Iterator<Users> it = c.iterator();
								
								       while(it.hasNext()){
								    	   Users item = it.next();
								           
									%>
							        <tr id="tr<%= item.getName() %>">
							        <td><a href="<%= path %>/nodes/users_queryUserNodes.sip?id=<%= item.getName()  %>" target="nodes_detailf"><%= item.getName() %></a></td>
							        <td><%= item.getRealname() %></td>
							        <td><%= item.getMobileNo() %></td>
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
		<!-- �б���� -->	
	</BODY>
</HTML>