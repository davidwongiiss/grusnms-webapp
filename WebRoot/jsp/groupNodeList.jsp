<%@ page contentType="text/html; charset=GB2312" %>
<%@ page
	import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>结点列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
	</HEAD>
	<%
	NodesListResult result = (NodesListResult)request.getAttribute("result");
	Collection<Nodes> c = null;
	if (result != null) {
		c = result.getC();
	}
	%>	
	<BODY class="tab" >
		<div class="title_zj">
			移动到<input  id="businessNo"  onfocus="changeCss(this,'onfocus');"  onblur="changeCss(this,'onblur');"  name="businessNo" type="text" size="16" />
			<input type="button" onclick='query()'  value='确定'  id='null'  class="btn60"  onmouseover="this.className='btn60_v'" onmouseout="this.className='btn60'" onmousedown="this.className='btn60_h'"/>
		</div>
		<form name="form1" action="">
			<input type="hidden" name="ids">
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
									<th>全选</th>      
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
							          <td></td>
							          <td class="text_right"><%= item.getName() %></td>
							          <td class="text_right"><%= item.getDeviceType() %></td>
							          <td><%= item.getCreateTime() %></td>
							          <td><%= item.getCreator() %></td>
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
				<jsp:param name="baseurl" value="/nodes/nodes_listNodes.sip" />
			</jsp:include>
		</form>
	</BODY>
</HTML>