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
<%@page import="com.device.common.impl.NodesListResult"%>
<%@page import="com.device.po.Nodes"%>
<%@page import="com.device.common.impl.NodesListEvent"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>设备管理</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META content="MSHTML 6.00.5730.13" name=GENERATOR>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css">
		<link rel="stylesheet" type="text/css"	href="../css/ip.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/util.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/frame.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/ip.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js?rand=3"></script>
		<script type="text/javascript">
		function addNodes(){
			var add_url="<%= path %>/jsp/node/addNode.jsp";
			var retval = popUpModalDialog(add_url,860,500,"","",'<%=path%>',"增加设备");
			if(retval == "ok"){
				$("#myform").submit();
			}
		}
		function delNode(id){
			var url = "<%= path %>/nodes/nodes_deleteNode.sip?rand=new Date()";
			$.get(url,{"id":id},function(){$("#tr"+id).remove()});
		}
		function editNode(id){
			var url = "<%= path %>/nodes/nodes_queryNodeBean.sip?id="+id;
			var retval = popUpModalDialog(url,860,500,"","",'<%=path%>',"修改设备");
			$("#myform").submit();
		}
		function day(element){
			WdatePicker({el:element,dateFmt:"yyyy-MM-dd"});
		}
		</script>
	</HEAD>

	<BODY class="tab" >
	<%
		NodesListEvent nodes = (NodesListEvent)request.getAttribute("event");
		if(nodes == null) nodes = new NodesListEvent();
	%>
	<!-- 查询表单开始 -->
		<form name="form1" id="myform" action="<%= path %>/nodes/nodes_searchNodes.sip" method="post">
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
					<td class="font_text_right"  width="15%">设备名称</td>
					<td align="left" width='20%'>
						<div class="inpit2_bg" > <input  id="name"   name="name"  value="<%= StringUtil.killNull(nodes.getName()) %>"  type="text"  size="16" /></div>
					</td>
					<td class="font_text_right"  width="15%">设备号</td>
					<td align="left" width='20%'  >
						<div class="inpit2_bg">
							<input name="mobileNo" value="<%= StringUtil.killNull(nodes.getDeviceSn()) %>"  type="text" />
						</div>
					</td>
				</tr>
				<tr> 
					<td class="font_text_right"  width="15%">创建时间</td>
					<td align="left" width='20%'  >
						<input id="beginTime" name="beginTime" type="text" size="16" value="<%= StringUtil.killNull(nodes.getBeginTime()) %>" onclick="day(this)" class="Wdate"/>
						--至--
						<input id="endTime" name="endTime" type="text" size="16" value="<%= StringUtil.killNull(nodes.getEndTime()) %>" onclick="day(this)" class="Wdate"/>
					</td>

					<td class="font_text_right"  width="15%">Ip地址</td>
					<td align="left" width='20%'  >
						<table border="0" cellpadding="0" cellspacing="0" class="textiploghidden" style="width:140px"> 
	                      <tr> 
	                        <td width="50" align="center" valign="bottom"><input name="ip1" maxlength="3" class="textipinput" value="" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_2('form1','ip1')"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input name="ip2" maxlength="3" class="textipinput" value="" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip2'))"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input name="ip3" maxlength="3" class="textipinput" value="" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip3'))"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input name="ip4" maxlength="3" class="textipinput" value="" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip4'))"/></td> 
	                      </tr> 
                    	</table>
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
					设备列表
				</h2>
				<div class="table_list_right">
				</div>
				<div class="table_header_r" id="groupdiv">
					<a href="javascript:void(0);" onclick="addNodes();return false;" > <img src="<%= path %>/images/button/icon1.gif"  />增加设备</a>
				</div>
			</div>
			<table width="99.6%" height="65%" border="0" cellpadding="0"
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
							        <th scope="col">设备名称</th>
							        <th scope="col">设备描述</th>
							        <th scope="col">设备ip</th>
							        <!-- 
							        <th scope="col">经度</th>
							        <th scope="col">纬度</th>
							         -->
							        <th scope="col">设备类型</th>
							        <th scope="col">创建时间</th>
							        <th scope="col">修改时间</th>
							        <th scope="col">创建人</th>
							        <th scope="col">修改人</th>
							        <th scope="col" class="list_bor_no">操作</th>
							      </tr>
								  <%	
								  	if (c != null && c.size() > 0 ) {
									  Iterator<Nodes> it = c.iterator();
								
								       while(it.hasNext()){
								    	   Nodes item = it.next();
								           
									%>
							        <tr id="tr<%= item.getId() %>">
							        <td><%= item.getName() %></td>
							        <td><%= item.getDescription() %></td>
							        <td><%= item.getIp() %></td>
							        <!-- 
							        <td><!-- %= item.getLongitude() %></td>
							        <td><!-- %= item.getLatitude() %></td>
							         -->
							        <td><%= item.getDeviceType() %></td>
							        <td><%= item.getCreateTime() %></td>
							        <td><%= item.getUpdateTime() %></td>
							        <td><%= item.getCreator() %></td>
							        <td><%= item.getUpdater() %></td>
							        <td><a href="javascript:editNode('<%= item.getId() %>');void(0)">修改</a> &nbsp;&nbsp;<a href="javascript:delNode('<%= item.getId() %>');void(0)">删除</a></td>
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
				<jsp:param name="baseurl" value="/nodes/nodes_searchNodes.sip" />
			</jsp:include>
		<!-- 列表结束 -->	
	</BODY>
</HTML>