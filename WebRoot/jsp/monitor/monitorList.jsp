<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>����б�</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META content="MSHTML 6.00.5730.13" name=GENERATOR>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
</HEAD>
<%
	NodesListResult result = (NodesListResult) request.getAttribute("result");
	Collection<Nodes> c = null;
	if (result != null) {
		c = result.getC();
	}
%>
<BODY class="tab">
	<div class="table_header" height="99%">
		<h2>����б�</h2>
		<div class="table_list_right"></div>
		<div class="table_header_r"></div>
	</div>
	<table width="99.6%" height="90%" border="0" cellpadding="0" cellspacing="0" class="ls_list1">
		<tr>
			<td>
				<div id="listTableDivId" style="overflow: auto; width: 100%; height: 100%; overflow-y: yes; overflow-x: yes;">
					<p>
					<table width="100%" class="ls_list" border="0" cellpadding="0" cellspacing="0" id="hollylistTable">
						<tr style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
							<th scope="col">�豸����</th>
							<th scope="col">�豸����</th>
							<th scope="col">�豸ip</th>
							<!-- 
							<th scope="col">����</th>
							<th scope="col">γ��</th>
							-->
							<th scope="col">����ʱ��</th>
							<th scope="col">�޸�ʱ��</th>
							<th scope="col">������</th>
							<th scope="col">�޸���</th>
						</tr>
						<%
							if (c != null && c.size() > 0) {
								Iterator<Nodes> it = c.iterator();

								while (it.hasNext()) {
									Nodes item = it.next();
						%>
						<tr>
							<td><%=item.getName()%></td>
							<td><%=item.getDescription()%></td>
							<td><a
								href="javascript:top._addTab('<%=item.getIp()%>','״̬���-<%=item.getIp()%>','<%=request.getContextPath()%>/nodes/monitor_query_showNodeBitrate.sip?address=<%=item.getIp()%>')"><%=item.getIp()%></a></td>
							<!-- 
							<td><!!%= item.getLongitude() %></td>
							<td><!!%= item.getLatitude() %></td>
							-->
							<td><%=item.getCreateTime()%></td>
							<td><%=item.getUpdateTime()%></td>
							<td><%=item.getCreator()%></td>
							<td><%=item.getUpdater()%></td>
						</tr>
						<%
							}
							}
						%>
					</table>
					</p>
				</div>
			</td>
		</tr>
	</table>
	<script language="javascript" src="<%=request.getContextPath()%>/js/table_util.js"></script>
</BODY>
</HTML>