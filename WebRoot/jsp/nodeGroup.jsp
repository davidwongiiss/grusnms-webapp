<%@ page contentType="text/html; charset=GB2312" %>
<%@ page
	import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"
%>
<%@page import="com.device.util.Constant"%>
<%@page import="com.device.common.impl.NodesListEvent"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="com.device.po.NodeGroups"%>
<%@page import="com.device.common.impl.NodeResult"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>结点列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
		<script type="text/javascript">
			function allocation(){
				var gid = $("#gid").val();
				if(!gid)
				{
					alert("请选择要分配结点的类型"); 
					return;
				};
				var ids = getAllValue('nodeid');
				if(!ids){
					alert("请选择要分配的结点"); 
					return;
				}
				$("#ids").val(ids);
				$("#myform").attr("action","<%= request.getContextPath() %>/nodes/nodes_insertNodeToGroup.sip");
				$("#myform").submit();
			}
		</script>
	</HEAD>
	<BODY class="tab" >
	<%
		NodesListEvent event = (NodesListEvent)request.getAttribute("event");
		if(event == null) event = new NodesListEvent();
	%>
	<!-- 查询表单开始 -->
		<form name="form1" id="myform" action="<%= request.getContextPath() %>/nodes/nodes_queryNodes.sip" method="post">
		<input type="hidden" value="" id="ids" name="ids">
		<input type="hidden" value="<%= request.getAttribute("groupId") %>" id="gid" name="gid">
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
					<td class="font_text_right"  width="15%">结点名称</td>
					<td align="left" width='20%'  >
						<div class="inpit2_bg" > <input  id="name"   name="name"  value="<%= StringUtil.killNull(event.getName())%>"  type="text"  size="16" /></div>
					</td>
					<td>
						<input type="submit" class="btn60"  value='查询' />
					</td>
				</tr>
		</table>	
		</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
		</form>
	
	<!-- 查询表单结束 -->
	<!-- 列表开始 -->

			<div class="table_header">
				<h2>
					<font color="red"><%= request.getAttribute("groupName") %></font>结点列表
				</h2>
				<div class="table_list_right">
				</div>
				<div class="table_header_r" id="groupdiv">
					<a href="javascript:void(0);" onclick= allocation();return false;" > <img src="<%= request.getContextPath() %>/images/button/icon2.gif"  />分配</a>
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
							NodesListResult result = (NodesListResult)request.getAttribute("result");
							Collection<NodeResult> c = null;
							if (result != null) {
								c = result.getC();
							}
							%>	
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
									<th scope="col"><input type="checkbox" id="checkAll" value="_all" onclick="selectAll('checkAll','nodeid')"/>全选</th>      
							        <th scope="col">名称</th>
							        <th scope="col">类型</th>
							        <th scope="col">日期</th>
							        <th scope="col">描述</th>
							        <th scope="col" class="list_bor_no">创建人</th>
							      </tr>
								  <%	
								  	if (c != null && c.size() > 0 ) {
									  Iterator<NodeResult> it = c.iterator();
								
								       while(it.hasNext()){
								    	   NodeResult item = it.next();
								           
									%>
							        <tr>
							          <td><input type="checkbox" name="nodeid" value="<%= item.getId() %>" <% if(item.getNodeId() != null) out.print("checked"); %>/></td>
							          <td class="text_right"><%= item.getName() %></td>
							          <td class="text_right"><%= item.getDeviceType() %></td>
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
			<jsp:include page="include/pagination.jsp">
				<jsp:param name="baseurl" value="/nodes/nodes_queryNodes.sip" />
			</jsp:include>
		<!-- 列表结束 -->		
	</BODY>
</HTML>