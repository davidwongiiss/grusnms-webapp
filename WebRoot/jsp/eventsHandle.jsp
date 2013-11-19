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
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>事件处理</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
	</HEAD>

	<BODY class="tab" >
	<%
		EventsListEvent event = (EventsListEvent)request.getAttribute("event");
		if(event == null) event = new EventsListEvent();
	%>
	<!-- 查询表单开始 -->
		<form name="form1" id="myform" action="<%= path %>/nodes/event_queryEvents.sip" method="post">
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
					<td class="font_text_right"  width="15%">等级</td>
					<td align="left" width='20%'  >
							<select name="severity">
								<option value=''>请选择</option>
								<option value='1' <%if(("1").equals(event.getSeverity()))out.print("selected"); %>>一级</option>
								<option value='2' <%if(("2").equals(event.getSeverity()))out.print("selected"); %>>二级</option>
								<option value='3' <%if(("3").equals(event.getSeverity()))out.print("selected"); %>>三级</option>
								<option value='4' <%if(("4").equals(event.getSeverity()))out.print("selected"); %>>四级</option>
								<option value='5' <%if(("5").equals(event.getSeverity()))out.print("selected"); %>>五级</option>
								<option value='6' <%if(("6").equals(event.getSeverity()))out.print("selected"); %>>六级</option>
							</select>
					</td>
					<td class="font_text_right"  width="15%">处理</td>
					<td align="left" width='20%'  >
						<select name="handle">
							<option value="">请选择</option>
							<option value="1" <%if(event.getHandle()!= null && event.getHandle()==1)out.print("selected"); %>>已处理</option>
							<option value="0" <%if(event.getHandle()!= null && event.getHandle()==0)out.print("selected"); %>>未处理</option>
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
					事件列表
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
							EventsListResult result = (EventsListResult)request.getAttribute("result");
							Collection<NodeEvents> c = null;
							if (result != null) {
								c = result.getC();
							}
							%>	
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
									<th scope="col"><input type="checkbox" id="checkAll" value="_all" onclick="selectAll('checkAll','eventId')"/>全选&nbsp;&nbsp;&nbsp;<a href="javascript:handerAll();void(0)">处理</a></th>      
							        <th scope="col">结点</th>
							        <th scope="col">分组类型</th>
							        <th scope="col">等级</th>
							        <th scope="col">描述</th>
							        <th scope="col">创建时间</th>
							        <th scope="col" class="list_bor_no">操作</th>
							      </tr>
								  <%	
								  	if (c != null && c.size() > 0 ) {
									  Iterator<NodeEvents> it = c.iterator();
								
								       while(it.hasNext()){
								    	   NodeEvents item = it.next();
								           
									%>
							        <tr>
							        <td><input type="checkbox" name="eventId" value="<%= item.getId() %>"/></td>
							        <td><%= item.getNodeId() %></td>
							        <td>分组类型</td>
							        <td><%= item.getSeverity() %></td>
							        <td><%= item.getDescription() %></td>
							        <td><%= item.getCreateTime() %></td>
							        <td id="td<%= item.getNodeId() %>">
							        	<% if(item.getHandled()==0){ %>
							        	<a href="javascript:handle('<%= item.getNodeId() %>');void(0)">处理</a>
							        	<%}else{
							        	%>	
							        		已处理
							        	<%	
							        	} %>
							        </td>
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
				<jsp:param name="baseurl" value="/nodes/event_queryEvents.sip" />
			</jsp:include>
		<!-- 列表结束 -->	
		<script type="text/javascript">
			function handle(id){
				var url = "<%= path %>/nodes/event_handleEvent.sip";
				$.get(url,{"id":id},function(){$("#td"+id).html("已处理")});
			}
			function handerAll(){
				var ids = getAllValue('eventId');
				$("#ids").val(ids);
				$("#myform").attr("action","<%= path %>/nodes/event_batchHandleEvents.sip");
				$("#myform").submit();
			}
		</script>
	</BODY>
</HTML>