<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>查询窗口</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css" />
		<script type="text/javascript" src="<%=path%>/js/frame.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/js/jquery-1.6.2.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js?rand=3"></script>
		<script type="text/javascript">
		function chageTimeObj(){
			$("#timeDiv").find("div").hide();
			var obj =  $("#cycle").find("option:selected").val();
			$("#"+obj).show();
		}
		function hour(element,obj){
			WdatePicker({el:element,dateFmt:"HH:mm:ss",vel:obj});
		}
		function day(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM-dd",vel:obj});
		}
		function week(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM-dd",vel:obj});
		}
		function month(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM",vel:obj});
		}
		function year(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy",vel:obj});
		}
		function check(){
			var bt = $("#beginTime").val();
			var et = $("#endTime").val();
			if(bt == "" || et == ""){
				alert("请选择开始时间和结束时间");
				return false;
			}else{
				return true ;
			}
		}
		</script>
	</head>
	<body>
	<%
		String cycle = (String)request.getAttribute("cycle");
		String beginTime = (String)request.getAttribute("beginTime");
		String endTime = (String)request.getAttribute("endTime");
		String type = (String)request.getAttribute("type");
		if(type == null){
			type = "";
		}
	%>
		<form action="<%=request.getContextPath()%>/jsp/statistics/staticGroupFrame.jsp" id="query_form" method="post" target="_self" onsubmit="return check();">
			<input type="hidden" name="type" value="<%= type %>" id="type"/>
			<div class="query_table_d" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="query_table">
					<tr class="table_top">
						<td width="3%" class="table_top_l"></td>
						<td></td>
						<td width="3%" class="table_top_r"></td>
					</tr>
					<tr>
						<td class="font_text_right border_l">
							&nbsp;
						</td>
						<td class="border_b">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="query_table_in">
								<tr>
									<td class="font_text_right" width="15%" height="40px;">
										周期
									</td>
									<td align="left" width='20%'>
										&nbsp;<select name="cycle" id="cycle" class="select152" id="cycle" onchange="chageTimeObj()">
											<option value='hour' <%if("hour".equals(cycle)) out.print("selected"); %>>
												小时
											</option>
											<option value='day' <%if("day".equals(cycle)) out.print("selected"); %>>
												天
											</option>
											<option value='week' <%if("week".equals(cycle)) out.print("selected"); %>>
												周
											</option>
											<option value='month' <%if("month".equals(cycle)) out.print("selected"); %>>
												月
											</option>
											<option value='year' <%if("year".equals(cycle)) out.print("selected"); %>>
												年
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="font_text_right" width="15%" height="40px">
										时间范围
									</td>
									<td align="left" width='20%' id="timeDiv">
										<input type="hidden" value="<%= StringUtil.killNull(beginTime) %>" name="beginTime" id="beginTime" />
										<input type="hidden" value=" <%= StringUtil.killNull(endTime) %>" name="endTime" id="endTime"/>
										<div id="hour" >
											&nbsp;<input id="beginTime1" name="beginTime1" value="<%= StringUtil.killNull(beginTime) %>" type="text" size="16" onclick="hour(this , 'beginTime')" class="Wdate" />
										--至--
											<input id="endTime1" name="endTime1" type="text" size="16" value="<%= StringUtil.killNull(endTime) %>" onclick="hour(this , 'endTime')" class="Wdate"/>
										</div>
										<div id="day" style="display: none">
											&nbsp;<input id="beginTime2" name="beginTime2" type="text" value="<%= StringUtil.killNull(beginTime) %>" size="16" onclick="day(this , 'beginTime')" class="Wdate"/>
										--至--
											<input id="endTime2" name="endTime2" type="text" size="16" value="<%= StringUtil.killNull(endTime) %>" onclick="day(this , 'endTime')" class="Wdate"/>
										</div>
										<div id="week" style="display: none">
											&nbsp;<input id="beginTime3" name="beginTime3" type="text" value="<%= StringUtil.killNull(beginTime) %>" size="16" onclick="week(this , 'beginTime')" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime3" name="endTime3" type="text" size="16" value="<%= StringUtil.killNull(endTime) %>" onclick="week(this,'endTime')" class="Wdate"/>
										</div>
										<div id="month" style="display: none">
											&nbsp;<input id="beginTime4" name="beginTime4" type="text" value="<%= StringUtil.killNull(beginTime) %>" size="16" onclick="month(this,'beginTime')" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime4" name="endTime4" type="text" size="16" value="<%= StringUtil.killNull(endTime) %>" onclick="month(this,'endTime')" class="Wdate"/>
										</div>
										<div id="year" style="display: none">
											&nbsp;<input id="beginTime5" name="beginTime5" type="text" value="<%= StringUtil.killNull(beginTime) %>" size="16" onclick="year(this,'beginTime')" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime5" name="endTime5" type="text" size="16" value="<%= StringUtil.killNull(endTime) %>" onclick="year(this,'endTime')" class="Wdate"/>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center" style="text-align: center;">
										<input type="submit" class="btn60" value='下一步' />
									</td>
								</tr>

							</table>
						</td>
						<td class="border_r">
							&nbsp;
						</td>
					</tr>
					<tr class="table_bottom">
						<td class="table_bottom_l"></td>
						<td></td>
						<td class="table_bottom_r"></td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
