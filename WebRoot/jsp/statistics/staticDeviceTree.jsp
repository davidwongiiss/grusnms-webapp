<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.StringUtil"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.net.URLEncoder"%>
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
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.core-3.5.js"></script>
	</head>
<body style="width: 98%;height: 100%;border: 1px #95b6d9 solid;margin-right:2px;">
<%
	String param = "";
  	Enumeration xenum=request.getParameterNames();
  	while(xenum.hasMoreElements()){
    	String name=(String)xenum.nextElement();
     	String value=URLEncoder.encode(request.getParameter( name));
     	param+=name+"="+value+"&";
  	}
  	System.out.println(param);
%>
	<%--
		String cycle = (String)request.getAttribute("cycle");
		String beginTime = (String)request.getAttribute("beginTime");
		String endTime = (String)request.getAttribute("endTime");
	--%>
	<table>
		<tr>
			<td height="20px;">
				<div style="padding: 5px;">
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
						success: function(msg){
									var array = eval(msg);
									var zNodes = array || [];
									$.fn.zTree.init($("#treeDemo"), setting, zNodes);
						}
					});
				}
				//默认加载第一个
				var codeType = $("#tree_id option[index=0]").val();
				loadTree(codeType);
			});
			
			
			//结点点击事件
			function zTreeOnClick(event, treeId, treeNode){
				parent.frames.mainf.location.href="<%= request.getContextPath() %>/nodes/statis_goDeviceipList.sip?<%= param %>&gids="+treeNode.id;
			}
			//-->
		</script>

</body>
</html>
