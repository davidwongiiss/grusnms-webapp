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
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/frame.js"></script>
	</HEAD>
	<%
	NodesListResult result = (NodesListResult)request.getAttribute("result");
	Collection<Nodes> c = null;
	if (result != null) {
		c = result.getC();
	}
	%>	
	<BODY class="tab" >
		<form name="myform" id="myform" action="<%= request.getContextPath() %>/nodes/users_queryUserNodes.sip">
			<input type="hidden" name="id" id="id" value="<%= StringUtil.killNull(request.getAttribute("id")) %>">
			<div class="table_header" height="99%">
				<h2>
					结点列表
				</h2>
				<div class="table_list_right"></div>
				<div class="table_header_r">
 					<a href="javascript:void(0);" onclick="allocatNodes();" > <img src="<%= request.getContextPath() %>/images/button/icon1.gif"  />添加关联结点</a>
				</div>
			</div>
			<table width="99.6%" height="88%" border="0" cellpadding="0"
				cellspacing="0" class="ls_list1">
				<tr>
					<td>
						<div id="listTableDivId"
							style="overflow: auto; width: 100%; height: 100%; overflow-y: yes; overflow-x: yes;">
							<p>
								<table width="100%" class="ls_list" border="0" cellpadding="0"	cellspacing="0" id="hollylistTable">
									<tr
										style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
									      
							        <th scope="col">名称</th>
							        <th scope="col">类型</th>
							        <th scope="col">日期</th>
							        <th scope="col" class="list_bor_no">创建人</th>
							        <th>操作</th>
							      </tr>
								  <%	
								  		if (c != null && c.size() > 0 ) {
									    Iterator<Nodes> it = c.iterator();
								
								       while(it.hasNext()){
								       	Nodes item = it.next();
								           
									%>
							        <tr id="tr_<%= item.getId() %>">
							          <td class="text_right"><%= item.getName() %></td>
							          <td class="text_right"><%= item.getDeviceType() %></td>
							          <td><%= item.getCreateTime() %></td>
							          <td><%= item.getCreator() %></td>
							          <td><a href="javascript:delRelate('<%= item.getId() %>');void(0)">删除</a></td>
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
	<script type="text/javascript">
		function delRelate(nid){
			var name = $("#id").val();
			$.post("<%= request.getContextPath()%>/nodes/users_delRelateNode.sip", { id: nid , name: name } ,function(data){
				$("#tr_"+nid).remove();
			});
		}
		function allocatNodes(){
			var id = $("input [name=id]").val();
			if(id){alert("请选择要给哪个用户分配"); return ;}
			var url = "<%= request.getContextPath()%>/nodes/nodes_listNodes.sip?name=<%= StringUtil.killNull(request.getAttribute("id")) %>";
			var retval = popUpModalDialog(url,860,500,"","",'<%=request.getContextPath()%>',"分配设备");
			if(retval == "ok"){
				$("#myform").submit();
			}
		}
	</script>
	</BODY>
</HTML>