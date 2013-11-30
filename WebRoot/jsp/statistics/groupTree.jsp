<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>结点树管理</title>
		<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/style30.css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/demo.css" type="text/css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.all-3.5.js"></script>
	</head>
<body>
	<form action="<%=request.getContextPath()%>/nodes/statis_goipList.sip" method="post" name="myform" id="myform" target="_blank">
	<input type="hidden" value="" name="gids" id="gids"/>
	<table width="30%" align="center">
		<tr>
			<td height="20px;">
				<div style="padding: 5px;margin:0 auto;">
				<span style="font-size: 12px;">分组类型</span>	
				<select name="groupTypes"  class="select152" id="tree_id">
						<%
							Map type_maps = Constant.groupTypes;
							Iterator type_it = type_maps.entrySet().iterator();
							while (type_it.hasNext()) {
								Map.Entry entry = (Map.Entry) type_it.next();
								Object key = entry.getKey();
								Object value = entry.getValue();
						%>
							<option value="<%= key %>"><%= value %></option>
						<%
							}
						%>
				</select>
			</div>
			</td>		
		</tr>
		
		<tr height="400px;">
			<td>
				<div style="margin:0 auto;">
				<ul id="treeDemo" class="ztree" style=" margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width: 220px;height: 360px;overflow-y: scroll;overflow-x: auto;"></ul>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<input type="button" value="上一步" class="btn60" name="cancel" onclick="goFirst()"/>
			<input type="button" value="下一步" class="btn60" name="ok" onclick="selectGroup()"/>
			</td>
		</tr>
	</table>
	</form>
		<script type="text/javascript">
			<!--
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
					gids += "," + nodes[i].id;
					groupNames +=  "," + nodes[i].name; 
				}
				gids = gids.replace("#,","");
				ids = gids.replace("#","");
				$("#gids").val(gids);
				if(!gids){
					alert("请选择要统计的分类组");
					return ;
				}
				$("#myform").attr('action', "<%=request.getContextPath()%>/jsp/statistics/chart.jsp");
				$("#myform").submit();
			}
			//上一步
			function goFirst(){
				$("#myform").attr('action', "<%=request.getContextPath()%>/nodes/statis_goFrist.sip");
				$("#myform").submit();
			}
			
			//-->
		</script>

</body>
</html>
