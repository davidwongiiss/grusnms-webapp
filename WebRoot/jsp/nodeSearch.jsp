<%@ page contentType="text/html; charset=GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>查询窗口</title>
		<link rel="stylesheet" type="text/css"	href="../css/style30.css"/>
	</head>
<body>
  <form action="<%= request.getContextPath() %>/nodes/nodes_listNodes.sip" id="query_form" method="post" target="mainf"> 
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
				<td class="font_text_right"  width="15%">节点名称</td>
				<td align="left" width='20%'  >
					<div class="inpit2_bg" > <input  id="name"   name="name"   type="text"  size="16" /></div>
				</td>
				<td class="font_text_right"  width="15%">节点类型</td>
				<td align="left" width='20%'  >
					<select name="acceptChannel" class="select152">
						<option value=''>&nbsp;</option>
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
	</body>
</html>
