<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Iterator"%>
<%
	String path = request.getContextPath() ; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>����û�</title>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css"/>
		<script type="text/javascript" src="<%= path %>/js/Validator.js"></script>
		<script type="text/javascript" src="<%= path %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			function checkFrom()
			{
				if(Validator.Validate(document.getElementById('add_form'),2)){
					
				}
				return false;
			}
		
		</script>
	</head>
<body>
  <form action="<%= request.getContextPath() %>/nodes/users_addUser.sip" id="add_form" method="post" onsubmit="return checkFrom();"> 
  		<input type="hidden" value="" name="user.password"/>
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
				<td class="font_text_right"  width="15%">�û���</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > 
					<input  id="user.name"   name="user.name"  type="text"  size="16" require="true" dataType="LimitB"  min="1" max="12"  msg="��Ա�˺ű��������ҳ��Ȳ�����12λӢ���ַ���"/>
					</div>
					<font color="red">*</font>
				</td>
				<td class="font_text_right"  width="15%">��ʵ����</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="user.realname"   name="user.realname"   type="text"  size="16" require="true" dataType="LimitB"  min="1" max="12"  msg="��Ա�������������ҳ��Ȳ�����12λӢ���ַ����ֲ�����6�������ģ�"/></div>
					<font color="red">*</font>
				</td>
				
			</tr>
			<tr> 
				<td class="font_text_right"  width="15%">����</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="password1"   name="password1"   type="password"  size="16" /></div>
				</td>
				<td class="font_text_right"  width="15%">�ظ�����</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="password2"   name="password2"   type="password"  size="16" /></div>
				</td>
			</tr>
			<tr> 
				<td class="font_text_right"  width="15%">����</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="user.email"   name="user.email"   type="text"  size="16" /></div>
				</td>
				<td class="font_text_right"  width="15%">�绰����</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="user.mobileNo"   name="user.mobileNo"   type="text"  size="16" /></div>
				</td>
			</tr>
			<tr>
				<td class="font_text_right"  width="15%">�Ƿ�ϵͳ�û�</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > 
					<select name="user.isSystem"  class="select152">
						<option value="0">��</option>
						<option value="1">��</option>
					</select> 
					</div>
				</td>
				<td class="font_text_right"  width="15%">�Ƿ����Ա</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" >
					<select name="user.isAdmin"  class="select152">
						<option value="0">��</option>
						<option value="1">��</option>
					</select> 
					</div>
				</td>
			
			</tr>
			
			<tr>
				<td class="font_text_right"  width="15%">��������</td>
				<td align="left" width='20%'  colspan="3">
					<div class="inpit2_bg" > 
					<select name="user.deptId"  class="select152">
					<option value=''>&nbsp;</option>
					<%
					Map type_maps = Constant.deptMaps;
					Iterator type_it = type_maps.entrySet().iterator();
					while (type_it.hasNext()) {
					Map.Entry entry = (Map.Entry) type_it.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					%>
					<option value="<%= key %>" ><%= value %></option>
					<%
					}
					%>
					</select> 
					</div>
				</td>
			</tr>
				<tr> 
					<td colspan="6" align="center" style="text-align: center;">
						<input type="submit" class="btn60"  value='����' />
						<input type="reset" class="btn60"  value='ȡ��' />
					</td>
				</tr>
	
	</table>	
	</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
	</form>
	</body>
</html>
