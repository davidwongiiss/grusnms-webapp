<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>结点树管理</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/demo.css" type="text/css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.all-3.5.js"></script>
	</head>
<body>
	<form action="<%=request.getContextPath()%>/nodes/statis_goipList.sip" method="post" name="myform" id="myform" target="mainf">
	<input type="hidden" value="" name="gids" id="gids"/>
	<table>
		<tr>
			<td height="400px">
				<ul id="treeDemo" class="ztree" style=" height: 100%;width: 100%;border: 0px;overflow-y:auto "></ul>
			</td>
		</tr>
		<tr>
			<td >
				<!-- 
				<input type="button" value="上一步" class="btn60" name="ok" onclick="goFirst()"/>
				 -->
				<input type="button" value="确定" class="btn60" name="ok" onclick="selectGroup()"/>
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
