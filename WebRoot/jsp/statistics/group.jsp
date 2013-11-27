<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Map"%>
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
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.all-3.5.js"></script>
	</head>
<body>
<%
	String cycle = (String)request.getAttribute("cycle");
	String beginTime = (String)request.getAttribute("beginTime");
	String endTime = (String)request.getAttribute("endTime");
	
	String groupType = (String)request.getAttribute("groupType");
	if(groupType == null || "null".equals(groupType) || "".equals(groupType.trim()))
	{
		groupType = "";
	}
	String gids = (String)request.getAttribute("gids");
%>
<form action="<%=request.getContextPath()%>/nodes/statis_goipList.sip" method="post" name="myform" id="myform">
	<input type="hidden" value="<%= StringUtil.killNull(cycle)%>" name="cycle" id="cycle" />
	<input type="hidden" value="<%= StringUtil.killNull(beginTime)%>" name="beginTime" id="beginTime" />
	<input type="hidden" value="<%= StringUtil.killNull(endTime) %>" name="endTime" id="endTime"/>
	
	<input type="hidden" value="<%= StringUtil.killNull(gids)%>" name="gids" id="gids"/>
	<input type="hidden" value="<%= StringUtil.killNull(groupType)%>" name="groupType" id="groupType"/>
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
				//加载树节点
				$("#tree_id").bind("change",function(){
					var codeType = $(this).val();
					if(!codeType) return;
					loadTree(codeType);
				});
				//加载树
				function loadTree(ctype){
					$.ajax({
						type: "GET",
				  		url: "<%= request.getContextPath() %>/nodes/nodeGroup_bulidGroupTree.sip?type="+ctype,
						success: function(msg){
									var array = eval(msg);
									var zNodes = array || [];
									$.fn.zTree.init($("#treeDemo"), setting, zNodes);
						}
					});
				}
				//默认加载第一个
				var codeType = "<%=groupType%>";
				if(!codeType){
					codeType = $("#tree_id option[index=0]").val();
				}
				$("#tree_id option[value='"+codeType+"']").attr("selected", true);
				loadTree(codeType);
				
				/**初始化tree，如果已经有选择的，选中复选框***/
				initTree();
				/**初始化tree，结束 **/
				
			});
			
			function initTree(){
				//勾选上之前勾中的
				debugger;
				var gids = "<%=gids%>";
				var idArray = gids.split(",");
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				for (var i=0, len =idArray.length; i < len; i++) {
					try{
						var node = treeObj.getNodeByParam("id",idArray[i],null);
						treeObj.checkNode(nodes, true, true);
					}catch(e){}
				}
			}
			//结点点击事件
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
				$("#gids").val(gids.replace("#,",""));
				var groupType = $("#tree_id").find("option:selected").val();
				$("#groupType").val(groupType);
				$("#myform").attr('action', "<%=request.getContextPath()%>/nodes/statis_goipList.sip");
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
