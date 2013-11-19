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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>结点列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/group/groupSelect.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/group/nodeGroupEvent.js?rand=18"></script>
	</HEAD>

	<BODY class="tab" >
	<%
		NodesListEvent event = (NodesListEvent)request.getAttribute("event");
		if(event == null) event = new NodesListEvent();
	%>
	<!-- 查询表单开始 -->
		<form name="form1" id="myform" action="<%= request.getContextPath() %>/nodes/nodes_queryNodes.sip" method="post">
		<input type="hidden" value="" id="ids" name="ids">
		<input type="hidden" value="" id="gid" name="gid">
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
					<td class="font_text_right"  width="15%">设备类型</td>
					<td align="left" width='20%'  >
						<select name="deviceType" class="select152">
							<option value=''>&nbsp;</option>
							<%
								Map type_maps = Constant.deviceTypes;
								Iterator type_it = type_maps.entrySet().iterator();
								while (type_it.hasNext()) {
									Map.Entry entry = (Map.Entry) type_it.next();
									Object key = entry.getKey();
									Object value = entry.getValue();
							%>
							<option value="<%= key %>" <% if(key.equals(event.getDeviceType())){out.print("selected");}; %>><%= value %></option>
							<%
								}
							%>
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
					结点列表
				</h2>
				<div class="table_list_right">
				</div>
				<div class="table_header_r" id="groupdiv">
					
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
							Collection<Nodes> c = null;
							if (result != null) {
								c = result.getC();
							}
							%>	
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
									<th scope="col"><input type="checkbox" id="checkAll" value="_all"/>全选</th>      
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
							          <td><input type="checkbox" name="nodeid" value="<%= item.getId() %>"/></td>
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
			<script language="javascript">
				if (document.getElementById('hollylistTable') != null) {
					var Ptr = document.getElementById("hollylistTable").getElementsByTagName(
							'tr');
					if (Ptr != null) {
						function addcss() {
							for (i = 1; i < Ptr.length; i++) {
								Ptr[i].className = (i % 2 > 0) ? 't1' : 't2';
							}
						}
						window.onload = addcss;
						for ( var i = 0; i < Ptr.length; i++) {
							Ptr[i].onmouseover = function() {
								this.tmpClass = this.className;
								this.className = 't3';
							};
							Ptr[i].onmouseout = function() {
								this.className = this.tmpClass;
							};
						}
					}
				}
				</script>
			<jsp:include page="include/pagination.jsp">
				<jsp:param name="baseurl" value="/nodes/nodes_queryNodes.sip" />
			</jsp:include>
		<!-- 列表结束 -->	
		
		<!-- 给下拉框添加数据 开始 -->
		<script type="text/javascript">
		var base = "<%= request.getContextPath()%>";
		var testSelect = new GroupSelectObj("groupTypes","name","","&nbsp;&nbsp;");
		<%
		Collection<NodeGroups> nodeGroups = (Collection<NodeGroups>)request.getAttribute("nodeGroups");	
		if (nodeGroups != null && nodeGroups.size() > 0 ) {
			  Iterator<NodeGroups> it = nodeGroups.iterator();
			  while(it.hasNext()){
				  NodeGroups g = it.next();
		%>
			testSelect.addOption("<%=g.getId()%>","<%=g.getName()%>","<%=g.getpId()%>","");
		<%
			  }
		}
		%>
		</script>
		
		<!--添加数据 结束 -->
	</BODY>
</HTML>