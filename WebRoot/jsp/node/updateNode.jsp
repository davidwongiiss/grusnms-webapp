<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.device.po.Nodes"%>
<%@page import="com.device.util.StringUtil"%>
<%
	String path = request.getContextPath() ; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>修改设备</title>
		<link rel="stylesheet" type="text/css"	href="<%= path %>/css/style30.css"/>
		<link rel="stylesheet" type="text/css"	href="<%= path %>/css/ip.css"/>
		<script type="text/javascript" src="<%= path %>/js/Validator.js"></script>
		<script type="text/javascript" src="<%= path %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/ip.js"></script>
		<script type="text/javascript">
			function checkFrom()
			{
				if(Validator.Validate(document.getElementById('form1'),2)){
					var ip1 = $("#ip1").val();
					var ip2 = $("#ip2").val();
					var ip3 = $("#ip3").val();
					var ip4 = $("#ip4").val();
					$("#node\\.ip").val(ip1+"."+ip2+"."+ip3+"."+ip4);
					return true;
				}
				return false;
			}
		</script>
	</head>
<body>
<%
	Nodes node = (Nodes)request.getAttribute("node");
	if(node == null) node = new Nodes();
%>
  <form action="<%= request.getContextPath() %>/nodes/nodes_updateNodes.sip" name="form1" id="form1" method="post" onsubmit ="return checkFrom();" > 
  		<input type="hidden" name="node.ip" id="node.ip"/>
  		<input type="hidden" name="node.id" id="node.id" value="<%= node.getId() %>"/>
  		<input type="hidden" name="node.createTime" id="node.createTime" value="<%= node.getCreateTime() %>"/>
  		<input type="hidden" name="node.creator" id="node.creator" value="<%= node.getCreator() %>"/>
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
		 	<table  width="100%" border="0" cellspacing="0" cellpadding="0" >     
			<tr> 
				<td class="font_text_right"  width="15%">设备名</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > 
					<input  id="node.name"   name="node.name" value="<%= StringUtil.killNull(node.getName()) %>"  type="text" disabled="disabled"  readonly="readonly" />
					</div>
					<font color="red">*</font>
				</td>
				<td class="font_text_right"  width="15%">设备型号</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > 
					<input type="text" name="node.deviceType" value="<%=Constant.DEVICETYPE %>" disabled="disabled"  readonly="readonly" />
					</div>
					<font color="red">*</font>
				</td>
				
			</tr>
			<tr> 
				<td class="font_text_right"  width="15%">ip地址</td>
				<% String ip = node.getIp(); 
					String[] ipArray = new String[4];
					if(ip != null){
						ipArray  = ip.split("\\.");
					}
				%>
				<td align="left" width='20%'  >
						<table border="0" cellpadding="0" cellspacing="0" class="textiploghidden" style="width:140px"> 
	                      <tr> 
	                        <td width="50" align="center" valign="bottom"><input id="ip1" name="ip1" maxlength="3" class="textipinput" value="<%= ipArray[0] %>" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_2('form1','ip1')"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input id="ip2" name="ip2" maxlength="3" class="textipinput" value="<%= ipArray[1] %>" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip2'))"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input id="ip3" name="ip3" maxlength="3" class="textipinput" value="<%= ipArray[2] %>" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip3'))"/></td> 
	                        <td align="center" width="4" style="font-weight:bold;color=#000000">.</td> 
	                        <td width="50" align="center" valign="bottom"><input id="ip4" name="ip4" maxlength="3" class="textipinput" value="<%= ipArray[3] %>" onKeyDown="checkIPValid_1()" onKeyUp="checkIPValid_3(eval('document.form1.ip4'))"/></td> 
	                      </tr> 
                    	</table>
				</td>
				<td class="font_text_right"  width="15%">设备类型</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > 
						<input  id="node.deviceModal"   name="node.deviceModal"   type="text" value="<%= Constant.DEVICENO %>" readonly="readonly" />
					</div>
				</td>
			</tr>
			<!-- 
			<tr> 
				<td class="font_text_right"  width="15%">经度</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="node.longitude"   name="node.longitude" value="<%= node.getLongitude() %>"  type="text"  size="16" /></div>
				</td>
				<td class="font_text_right"  width="15%">纬度</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="node.latitude"   name="node.latitude" value="<%= node.getLatitude() %>"  type="text"  size="16" /></div>
				</td>
			</tr>
			 -->
			<tr> 
				<td class="font_text_right"  width="15%">登陆用户</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="node.loginUser"   name="node.loginUser" value="<%= node.getLoginUser() %>"  type="text"  size="16" /></div>
				</td>
				<td class="font_text_right"  width="15%">登陆密码</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="node.loginPassword"   name="node.loginPassword" value="<%= node.getLoginPassword() %>"  type="text"  size="16" /></div>
				</td>
			</tr>
			<tr> 
				<td class="font_text_right"  width="15%">描述</td>
				<td align="left" width='20%' colspan="3" >
					<textarea rows="5" cols="60" name="node.description"><%= node.getDescription() %></textarea>
				</td>
			</tr>
				<tr> 
					<td colspan="6" align="center" style="text-align: center;">
						<input type="submit" class="btn60"  value='修改' />
						<input type="button" class="btn60"  value='取消' onclick="javascript: window.returnValue=false;window.close()"/>
					</td>
				</tr>
	
	</table>	
	</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
	</form>
	</body>
</html>
