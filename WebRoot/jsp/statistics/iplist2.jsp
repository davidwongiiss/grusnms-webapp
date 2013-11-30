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
		<TITLE>IP列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/style30.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript"	src="<%=request.getContextPath()%>/js/util.js"></script>
		<script type="text/javascript">
			function selectIps(){
				debugger;
				var ids = getAllValue('_id');
				if(!ids){alert("请选择要统计的设备！");return}
				var array = ids.split(",");
				var ips = new Array();
				var nids = new Array() ;
				for(var i= 0; i < array.length ; i++)
				{
					var a = array[i].split("_");
					ips[i] = a[0];
					nids[i] = a[1];
				}
				$("#ips").val(ips.join(","));
				$("#nids").val(nids.join(","));
				$("#myform").submit();
			}
			function goStep(){
				$("#myform").attr('action', "<%=request.getContextPath()%>/nodes/statis_goGroup.sip");
				$("#myform").submit();
			}
		</script>
	</HEAD>
	<%
	List<Object[]> result = (List<Object[]>)request.getAttribute("ipList");
	%>	
	<BODY class="tab" >
	<%
		String cycle = (String)request.getAttribute("cycle");
		String beginTime = (String)request.getAttribute("beginTime");
		String endTime = (String)request.getAttribute("endTime");
		String chartType = (String)request.getAttribute("chartType");
		
		String groupType = (String)request.getAttribute("groupType");
		if(groupType == null || "null".equals(groupType) || "".equals(groupType.trim()))
		{
			groupType = "";
		}
		String gids = (String)request.getAttribute("gids");
	%>		
		<form action="<%= request.getContextPath() %>/jsp/statistics/chart.jsp" method="post" name="myform" id="myform" target="_blank">
			<input type="hidden" value="<%= StringUtil.killNull(cycle) %>" name="cycle" id="cycle" />
			<input type="hidden" value="<%= StringUtil.killNull(beginTime) %>" name="beginTime" id="beginTime" />
			<input type="hidden" value="<%= StringUtil.killNull(endTime) %>" name="endTime" id="endTime"/>
			<input type="hidden" value="<%= StringUtil.killNull(chartType) %>" name="chartType" id="chartType"/>
			<input type="hidden" value="<%= StringUtil.killNull(gids) %>" name="gids" id="gids"/>
			<input type="hidden" value="" name="ips" id="ips">
			<input type="hidden" value="" name="nids" id="nids">
			<div class="table_header" height="99%">
				<h2>
					IP列表
				</h2>
				<div class="table_list_right"></div>
				<div class="table_header_r">
				
				</div>
			</div>
			<table width="99.6%" height="80%" border="0" cellpadding="0"
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
							        <th scope="col" class="list_bor_no">创建人</th>
							      </tr>
								  <%	
								  if(result != null){
									for(Object[] obj : result){
								           
									%>
							        <tr>
							          <td><input type="checkbox" name="_id"	value="<%= obj[0] %>_<%= obj[1] %>" /></td>
							          <td align="left"><%= obj[0] %></td>
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
			<table width="88%">
				<tr>
					<td align="right"><input type="button" name="确定" class="btn90" onclick="selectIps();" value="确定"></td>
				</tr>
			</table>
			<script language="javascript" src="<%= request.getContextPath() %>/js/table_util.js"></script>
		</form>
	</BODY>
</HTML>