<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.device.util.LoginUtil"%>
<%
	String path = request.getContextPath() ; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>�޸�����</title>
		<link rel="stylesheet" type="text/css"	href="<%= path %>/css/style30.css"/>
		<script type="text/javascript" src="<%= path %>/js/Validator.js"></script>
		<script type="text/javascript" src="<%= path %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			function checkFrom()
			{
				if(Validator.Validate(document.getElementById('myform'),2)){
					var password = $("#oldPassword").val();
					var url = "<%= path %>/nodes/users_checkPassword.sip";
					$.get(url,{'password':password},function(data){
							if(data == "1") 
							{
								$("#myform").submit();
							}
							else 
							{
								alert("�������������");
								return false;
							}
						});
				}
				return false;
			}
		</script>
	</head>
<body>
  <form action="<%= request.getContextPath() %>/nodes/users_changePassword.sip" id="myform" method="post" > 
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
				<td style="text-align: right" width="15%">�û���:&nbsp;</td>
				<td align="left" width='20%'  >
					<%= LoginUtil.getUserId() %>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"  width="15%">������:&nbsp;</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="oldPassword"   name="oldPassword"   type="password"  size="16" dataType="Require,LimitB" min="6" max="20"  msg="�����������##���볤�Ȳ�С��6λ������20λ��"/></div>
					<font color="red">*</font>
				</td>
			</tr>
			<tr> 
				<td style="text-align: right" width="15%">������:&nbsp;</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="newPassword"  name="newPassword"  type="password"  size="16" dataType="Require,LimitB" min="6" max="20"  msg="�����������##���볤�Ȳ�С��6λ������20λ��"/></div>
					<font color="red">*</font>
				</td>
			</tr>
			<tr> 
			
				<td style="text-align: right"  width="15%">�ظ�����:&nbsp;</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="newPassword2"   name="newPassword2"   type="password"  size="16" require="true" dataType="Repeat" to="newPassword" msg="������������벻һ��"/></div>
					<font color="red">*</font>
				</td>
			</tr>
				<tr> 
					<td colspan="2" align="center" style="text-align: center;">
						<input type="button" class="btn60"  value='����' onclick="javascript: checkFrom();" />
						<input type="reset" class="btn60"  value='ȡ��' />
					</td>
				</tr>
	
	</table>	
	</td><td class="border_r">&nbsp;</td></tr> <tr class="table_bottom"><td class="table_bottom_l"></td>  <td ></td><td class="table_bottom_r"></td></tr></table></div>
	</form>
	</body>
</html>
