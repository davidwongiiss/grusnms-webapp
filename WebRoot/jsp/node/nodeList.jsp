<%@ page contentType="text/html; charset=GB2312" %>
<%@ page
	import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"
%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>结点列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
		<script type="text/javascript">
			function allocation(){
				debugger;
				var ids = getAllValue('eventId');
				$("#nids").val(ids);
				$("#myform").submit();
			}
		</script>
	</HEAD>
	<%
	NodesListResult result = (NodesListResult)request.getAttribute("result");
	Collection<Nodes> c = null;
	if (result != null) {
		c = result.getC();
	}
	%>	
	<BODY class="tab" >
		<form name="form1" id="myform" action="<%= request.getContextPath() %>/nodes/users_allocatNodes.sip">
			<input type="hidden" name="id" value="<%= StringUtil.killNull(request.getParameter("id")) %>">
			<input type="hidden" name="nids" id="nids"/>
			<div class="table_header" height="99%">
				<h2>
					结点列表
				</h2>
				<div class="table_list_right"></div>
				<div class="table_header_r">
 					<a href="javascript:void(0);" onclick="allocation();return false;" > <img src="<%= request.getContextPath() %>/images/button/icon2.gif"  />分配</a>
				</div>
			</div>
			<table width="99.6%" height="75%" border="0" cellpadding="0"
				cellspacing="0" class="ls_list1">
				<tr>
					<td>
						<div id="listTableDivId"
							style="overflow: auto; width: 100%; height: 100%; overflow-y: yes; overflow-x: yes;">
							<p>
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
									<th scope="col"><input type="checkbox" id="checkAll" value="_all" onclick="selectAll('checkAll','eventId')"/>全选</a></th>     
							        <th scope="col">名称</th>
							        <th scope="col">类型</th>
							        <th scope="col">日期</th>
							        <th scope="col">描述</th>
							        <th scope="col" class="list_bor_no">创建人</th>
							      </tr>
								  <%	
								  		if (c != null && c.size() > 0 ) {
									    Iterator<Nodes> it = c.iterator();
								
								       while(it.hasNext()){
								       	Nodes item = it.next();
								           
									%>
							        <tr>
							          <td><input type="checkbox" name="eventId" value="<%= item.getId() %>"/></td>
							          <td><%= item.getName() %></td>
							          <td><%= item.getDeviceType() %></td>
							          <td><%= item.getCreateTime() %></td>
							          <td><%= item.getDescription() %></td>
							          <td><%= item.getCreator() %></td>
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
			<jsp:include page="../include/pagination.jsp">
				<jsp:param name="baseurl" value="/nodes/nodes_listNodes.sip" />
			</jsp:include>
		</form>
	</BODY>
</HTML>