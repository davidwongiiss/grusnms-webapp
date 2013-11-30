<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>结点列表</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META content="MSHTML 6.00.5730.13" name=GENERATOR>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<style type="text/css">
.offline{background-color:#800080;}
.alarm{background-color:#FFC0CB;}
.warning{background-color:#FFFF00}
.normal{background-color:#90EE90}
</style>
</HEAD>
<%
	NodesListResult result = (NodesListResult) request.getAttribute("result");
	Collection<Object[]> c = null;
	if (result != null) {
		c = result.getC();
	}
%>
<BODY class="tab">
	<div class="table_header" height="99%">
		<h2>监控台</h2>
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
							<th scope="col">IP</th>
							<th scope="col">状态</th>
							<th scope="col">告警</th>
							<th scope="col">gbe流量</th>
							<th scope="col">gbe服务数</th>
							<th scope="col">qam流量</th>
							<th scope="col">qam服务数</th>
						</tr>
						<script>
							var ips = new Array();
							var nodes = new Array();
						</script>
						<%
							if (c != null && c.size() > 0) {
								Iterator<Object[]> it = c.iterator();
								while (it.hasNext()) {
									Object[] item = it.next();
						%>
						<script>
							ips.push('<%= item[1] %>');
							nodes.push('<%= item[0] %>');
						</script>
						<tr id="tr<%= item[0] %>">
							<td><%= item[0] %></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
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
	<script type="text/javascript">
  		$(document).ready(function(){
  			debugger;
  			var _ips = ips.join(",");
  			var _nodes = nodes.join(",");
  			if(_ips && _nodes){
  				poll();
  			}
  			
  			function poll(){
  				 $.ajax({
			     type:"POST",
			     url:"<%= request.getContextPath() %>/nodes/monitor_query_updateView.sip",
			     dataType:"json",
			     timeout:10000,
			     data:{ips:_ips,nodes:_nodes},
			     success:function(data,textStatus){
					handlerMessage(data);			           
			     }
			 });
  			}
  			function handlerMessage(data){
  				
  				for(var i = 0 ; i <data.length; i++)
  				{
  					var obj = data[i];
  					var otd = $("#tra89c0829-9d2d-4d1a-996e-07bbdcfdd246");
  					var offline = obj.status ;
  					var alarm = obj.alarm ;
  					var warning = obj.warning ;
  					if(offline == 0)
  					{
  						otd.css("background-color","#800080");
  					}else if(alarm == true)
  					{
  						otd.css("background-color","#FFC0CB");
  					}else if(warning == true)
  					{
  						otd.css("background-color","#FFFF00");
  					}else{
  						//normal
  						otd.css("background-color","#90EE90");
  					}
  					setTimeout(poll,10000);
  					//if("#"+tid)
  				}
  				
  			}
        });

		
	
	</script>
</BODY>
</HTML>