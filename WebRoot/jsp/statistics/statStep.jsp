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
		</script>
	</head>
	<body>
	<%
		String type = (String)request.getAttribute("type");
		if(type == null){
			type = "";
		}
	%>
	<form action="<%= request.getContextPath() %>/jsp/statistics/chart2.jsp" id="myform" name="myform" target="_blank">
			<input type="hidden" value="" name="lineWithDetail" id="lineWithDetail"/>
			<input type="hidden" value="" name="ips" id="ips"/>
			<input type="hidden" value="" name="nids" id="nids"/>
			<div class="query_table_d" id="step1" >
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
										&nbsp;<select name="cycle" id="cycle" class="select152" onchange="chageTimeObj()">
											<option value='1minute' selected="selected">
												1分钟
											</option>
											<option value='5minute'>
												5分钟
											</option>
											<option value='30minute'>
												30分钟
											</option>
											<option value='60minute'>
												小时
											</option>
											<option value='day' >
												天
											</option>
											<option value='week' >
												周
											</option>
											<option value='month' >
												月
											</option>
											<option value='year' >
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
										<input type="hidden" value="" name="beginTime" id="beginTime" />
										<input type="hidden" value=" " name="endTime" id="endTime"/>
										<div id="1minute" >
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{H:-1});}'})" class="Wdate" />
										--至--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{H:1});}'})" class="Wdate"/>
										</div>
										<div id="5minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{H:-12});}'})" class="Wdate" />
										--至--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{H:12});}'})" class="Wdate"/>
										</div>
										<div id="30minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{d:-1});}'})" class="Wdate" />
										--至--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{d:1});}'})" class="Wdate"/>
										</div>
										<div id="60minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{d:-3});}'})" class="Wdate" />
										--至--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{d:3});}'})" class="Wdate"/>
										</div>
										<div id="day" style="display: none">
											&nbsp;<input id="beginTime2" name="beginTime2" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime2\',{d:-31});}'})" class="Wdate"/>
										--至--
											<input id="endTime2" name="endTime2" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime2\');}',maxDate:'#F{$dp.$D(\'beginTime2\',{d:31});}'})" class="Wdate"/>
										</div>
										<div id="week" style="display: none">
											&nbsp;<input id="beginTime3" name="beginTime3" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',maxDate:'#F{$dp.$D(\'endTime3\',{d:-30});}'})" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime3" name="endTime3" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime3\');}',maxDate:'#F{$dp.$D(\'beginTime3\',{d:30});}'})" class="Wdate"/>
										</div>
										<div id="month" style="display: none">
											&nbsp;<input id="beginTime4" name="beginTime4" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',maxDate:'#F{$dp.$D(\'endTime4\',{M:-12});}'})" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime4" name="endTime4" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime4\');}',maxDate:'#F{$dp.$D(\'beginTime4\',{M:12});}'})" class="Wdate"/>
										</div>
										<div id="year" style="display: none">
											&nbsp;<input id="beginTime5" name="beginTime5" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy',vel:'endTime'})" class="Wdate"/>
										--至--
											&nbsp;<input id="endTime5" name="endTime5" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime5\');}'})" class="Wdate"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="font_text_right" height="40px">统计方式：</td>
									<td><input type="radio" value="chart" name="chartType" checked="checked" />
									走势图  
									<input type="radio" value="total" name="chartType" />合计</td>
								</tr>
								<tr>
									<td></td>
									<td><input type="checkbox" value="1" id="line-with-detail" name="line-with-detail" />显示输入输出明细</td>
								</tr>
								<tr>
									<td colspan="6" align="center" style="text-align: center;">
										<input type="button" class="btn60" value='下一步'  onclick="nextStep()"/>
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
		
	<div id="step2" style="height:510px;display: none">
		<iframe src="<%=request.getContextPath()%>/jsp/statistics/staticDeviceTree.jsp" width="20%" height="99.5%"></iframe>
		<iframe name="mainf" src="<%= request.getContextPath() %>/jsp/statistics/iplist2.jsp" width="79%" height="99.5%"></iframe>
	</div>
</form>

<script type="text/javascript">
	function nextStep(){
		var bt = $("#beginTime").val();
		var et = $("#endTime").val();
		var chartType = $('input[name=chartType]:checked').val();
		if(bt == "" || et == "" || !chartType){
			alert("请选择开始时间和结束时间,统计类型");
			return false;
		}else{
			var isSelected = $("#line-with-detail").attr("checked");
			if(isSelected){
				$("#lineWithDetail").val("1");
			}
			$("#step1").hide();
			$("#step2").show();
		}
	}
	function dosubmit(ips ,nids ){
		$("#ips").val(ips);
		$("#nids").val(nids);
		var lineWithDetail = $("#lineWithDetail").val()
		debugger;
		if("1" == lineWithDetail){
			$("#myform").attr('action', "<%= request.getContextPath() %>/jsp/statistics/chart.jsp");
		}
		$("#myform").submit(); 
	}
</script>
	
	</body>
</html>
