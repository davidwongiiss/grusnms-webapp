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
		<title>��ѯ����</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css" />
		<script type="text/javascript" src="<%=path%>/js/frame.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/js/jquery-1.6.2.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js?rand=3"></script>
		<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/style30.css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/demo.css" type="text/css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.all-3.5.js"></script>
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
	<form id="myform" name="myform" target="_blank" method="post">
			<div class="query_table_d" id="step1">
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
										����
									</td>
									<td align="left" width='20%'>
										&nbsp;<select name="cycle" id="cycle" class="select152" id="cycle" onchange="chageTimeObj()">
											<option value='1minute' selected="selected">
												1����
											</option>
											<option value='5minute'>
												5����
											</option>
											<option value='30minute'>
												30����
											</option>
											<option value='60minute'>
												Сʱ
											</option>
											<option value='day' >
												��
											</option>
											<option value='week' >
												��
											</option>
											<option value='month' >
												��
											</option>
											<option value='year' >
												��
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="font_text_right" width="15%" height="40px">
										ʱ�䷶Χ
									</td>
									<td align="left" width='20%' id="timeDiv">
										<input type="hidden" value="" name="beginTime" id="beginTime" />
										<input type="hidden" value=" " name="endTime" id="endTime"/>
										<div id="1minute" >
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{H:-1});}'})" class="Wdate" />
										--��--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{H:1});}'})" class="Wdate"/>
										</div>
										<div id="5minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{H:-12});}'})" class="Wdate" />
										--��--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{H:12});}'})" class="Wdate"/>
										</div>
										<div id="30minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{d:-1});}'})" class="Wdate" />
										--��--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{d:1});}'})" class="Wdate"/>
										</div>
										<div id="60minute" style="display: none">
											&nbsp;<input id="beginTime1" name="beginTime1" value="" type="text" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime1\',{d:-3});}'})" class="Wdate" />
										--��--
											<input id="endTime1" name="endTime1" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime1\');}',maxDate:'#F{$dp.$D(\'beginTime1\',{d:3});}'})" class="Wdate"/>
										</div>
										<div id="day" style="display: none">
											&nbsp;<input id="beginTime2" name="beginTime2" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'beginTime',minDate:'#F{$dp.$D(\'endTime2\',{d:-31});}'})" class="Wdate"/>
										--��--
											<input id="endTime2" name="endTime2" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime2\');}',maxDate:'#F{$dp.$D(\'beginTime2\',{d:31});}'})" class="Wdate"/>
										</div>
										<div id="week" style="display: none">
											&nbsp;<input id="beginTime3" name="beginTime3" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',maxDate:'#F{$dp.$D(\'endTime3\',{d:-30});}'})" class="Wdate"/>
										--��--
											&nbsp;<input id="endTime3" name="endTime3" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime3\');}',maxDate:'#F{$dp.$D(\'beginTime3\',{d:30});}'})" class="Wdate"/>
										</div>
										<div id="month" style="display: none">
											&nbsp;<input id="beginTime4" name="beginTime4" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',maxDate:'#F{$dp.$D(\'endTime4\',{M:-12});}'})" class="Wdate"/>
										--��--
											&nbsp;<input id="endTime4" name="endTime4" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime4\');}',maxDate:'#F{$dp.$D(\'beginTime4\',{M:12});}'})" class="Wdate"/>
										</div>
										<div id="year" style="display: none">
											&nbsp;<input id="beginTime5" name="beginTime5" type="text" value="" size="16" onclick="WdatePicker({dateFmt:'yyyy',vel:'endTime'})" class="Wdate"/>
										--��--
											&nbsp;<input id="endTime5" name="endTime5" type="text" size="16" value="" onclick="WdatePicker({dateFmt:'yyyy-MM',vel:'endTime',minDate:'#F{$dp.$D(\'beginTime5\');}'})" class="Wdate"/>
										</div>
									</td>
								</tr>
								<tr>
									<td class="font_text_right" height="40px">ͳ�Ʒ�ʽ��</td>
									<td><input type="radio" value="chart" name="chartType" checked="checked"/>
									����ͼ  
									<input type="radio" value="total" name="chartType" />�ϼ�</td>
								</tr>
								<tr>
									<td colspan="6" align="center" style="text-align: center;">
										<input type="button" class="btn60" value='��һ��'  onclick="nextStep()"/>
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
<!--  �ڶ��� -->		
	<div id="step2" style="display: none">
		<input type="hidden" value="" name="gids" id="gids"/>
		<table width="30%" align="center">
			<tr height="400px;">
				<td>
					<div style="margin:0 auto;">
					<ul id="treeDemo" class="ztree" style=" margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width: 220px;height: 360px;overflow-y: scroll;overflow-x: auto;"></ul>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				<input type="button" value="��һ��" class="btn60" name="cancel" onclick="goFirst()"/>
				<input type="button" value="��һ��" class="btn60" name="ok" onclick="selectGroup()"/>
				</td>
			</tr>
		</table>
	</div>
<!-- �ڶ������� -->
</form>	
<script type="text/javascript">
	function nextStep(){
		var bt = $("#beginTime").val();
		var et = $("#endTime").val();
		var chartType = $('input[name=chartType]:checked').val();
		if(bt == "" || et == "" || !chartType){
			alert("��ѡ��ʼʱ��ͽ���ʱ��,ͳ������");
			return false;
		}else{
			$("#step1").hide();
			$("#step2").show();
		}
	}
	function goFirst(){
		$("#step1").show();
		$("#step2").hide();
	}
	//������
	var setting = {
			view: {
					selectedMulti: false
				},
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
			
		$(document).ready(function(){
				$.ajax({
					type: "GET",
			  		url: "<%= request.getContextPath() %>/nodes/nodeGroup_bulidGroupTree.sip?type=<%= StringUtil.killNull(request.getParameter("type")) %>",
					success: function(msg){
								var array = eval(msg);
								var zNodes = array || [];
								$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					}
			});
		});
			
		function selectGroup(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = treeObj.getCheckedNodes(true);
				var gids = "#";
				var groupNames = "#";
				for(var i = 0 ; i < nodes.length ; i++)
				{
					if(nodes[i].pId){
						gids += "," + nodes[i].id;
						groupNames +=  "," + nodes[i].name; 
					}
				}
				gids = gids.replace("#,","");
				gids = gids.replace("#","");
				$("#gids").val(gids);
				if(!gids){
					alert("��ѡ��Ҫͳ�Ƶķ�����");
					return ;
				}
				$("#myform").attr('action', "<%=request.getContextPath()%>/jsp/statistics/chart2.jsp");
				$("#myform").submit();
		}
</script>
	
	</body>
</html>
