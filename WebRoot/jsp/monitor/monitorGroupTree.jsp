<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>结点树管理</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style30.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/demo.css" type="text/css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.core-3.5.js"></script>
</head>
<body style="width: 98%;height: 100%;border: 1px #95b6d9 solid;margin-right:2px;">
	<table>
		<tr>
			<td height="20px;">
				<div style="padding: 5px;">
					<span style="font-size: 12px;">分组类型</span>
					<select name="groupTypes" class="select152" id="tree_id">
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
		<tr>
			<td>
				<div style="">
					<ul id="treeDemo" class="ztree" style=" height: 100%;width: 98%;border: 0px;overflow-y:auto "></ul>
				</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
			<!--
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: zTreeOnClick
				}
			};

			$(document).ready(function(){
				//加载树节点
				$("#tree_id").bind("change",function(){
					var codeType = $(this).val();
					if(!codeType) return;
					loadTree(codeType);
				});

				function loadTree(ctype){
					$.ajax({
						type: "GET",
						url: "<%= request.getContextPath() %>/nodes/nodeGroup_bulidGroupTree.sip?type="+ctype,
						success: function(msg) {
							var array = eval(msg);
							var zNodes = array || [];
							$.fn.zTree.init($("#treeDemo"), setting, zNodes);
							
							var ids = '';
							for (var i = 0; i < zNodes.length; ++i) {
								if (zNodes[i].pId !== null) { 
  								ids += zNodes[i].id;
 									ids += ',';
								}
							}
							
							if (ids != '') {
								ids = ids.substr(0, ids.length - 1);
							}
							
							groupIds_ = ids.slice();
							check_group_status();
						}
					});
				}
				
				//默认加载第一个
				var codeType = $("#tree_id option[index=0]").val();
				loadTree(codeType);
			});


			//结点点击事件
			function zTreeOnClick(event, treeId, treeNode){
				var groupType = $("#tree_id").val();
				parent.frames.mainf.location.href="<%= request.getContextPath() %>/nodes/monitor_query_monitorList.sip?groupType="+groupType+"&groupId="+treeNode.id;
			}
			
			var groupIds_ = null;

			// 检查分组状态
			function check_group_status() {
		    $.ajax({
		      type: 'POST',
		      url: '<%= request.getContextPath() %>/nodes/monitor_query_queryGroupsStatus.sip',
		      dataType: 'json',
		      data: {
		        ids: groupIds_
		      }
		    }).done(function(data) {
		      if (data != null && $.isArray(data) && data.length > 0) {
		        try {
		          update_group_status_view(data);
		        }
		        catch (ex) {
		          console.log(ex.message);
		        }
		      }
		    }).always(function() {
		      setTimeout(check_group_status, 5000);
		    });
			};
			
			/**
			@param data json对象数组
			[{"id":"520100","name":"贵阳","x":"0","y":"0","offlineCount":1,"alarmCount":1,"eventCount":2}]
			*/
			function update_group_status_view(data) {
			};
			//-->
	</script>

</body>
</html>
