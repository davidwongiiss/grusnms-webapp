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
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.core-3.5.js"></script>
	</head>
<body>
		<ul id="treeDemo" class="ztree" style=" height: 100%;width: 100%;border: 0px;overflow-y:auto "></ul>

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
			//结点点击事件
			function zTreeOnClick(event, treeId, treeNode){
				if(treeNode.pId != null)
				{
					parent.frames.mainf.location.href="<%= request.getContextPath() %>/nodes/nodes_queryNodes.sip?groupId="+treeNode.id+"&groupName="+treeNode.name;
				}else{
					alert("跟分组无法绑定结点");
				}
			}
			
			//-->
		</script>

</body>
</html>
