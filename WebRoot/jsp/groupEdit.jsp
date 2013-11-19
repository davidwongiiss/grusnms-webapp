<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="com.device.util.Constant"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>结点树管理</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/demo.css?rand=13" type="text/css"/>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<link rel="stylesheet" type="text/css"	href="../css/style.css"/>
  		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.6.2.js"></script>
  		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.ztree.exedit-3.5.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/group/groupEdit.js?rand=31"></script>
		<style type="text/css">
			.ztree li span.button.add {margin-right:2px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
	</head>
	<body>
		<h6>
			结点组管理
		</h6>
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right">
				<div class="query_table_d">
				<form name="myform" id="myform">
					<input type="hidden" name="flag" id="flag" value=""/>
					<input type="hidden" name="pId" id="pId" value=""/>
					<input type="hidden" name="id" id="id" value=""/>
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

										<td colspan="2" align="center" style="text-align: center">
											<span id="title"></span>
										</td>
									</tr>
									<tr>

										<td>
											名称
										</td>
										<td>
												<div class="inpit2_bg" ><input name="nodeGroups.name" value="" type="text" /></div>
										</td>
									</tr>
									<tr>
										<td>
											分组类型
										</td>
										<td>
											<select name="nodeGroups.groupType" id="nodeGroups.groupType">
												<%
												Map map = Constant.groupTypes;
												Iterator it = map.entrySet().iterator();
												while (it.hasNext()) {
												Map.Entry entry = (Map.Entry) it.next();
												Object key = entry.getKey();
												Object value = entry.getValue();
												%>
												<option value="<%= key %>"><%= value %></option>
												<%
												}
												%>
												
											</select>
										</td>
									</tr>
									<tr>
										<td>
											经度
										</td>
										<td>
												<div class="inpit2_bg" ><input name="nodeGroups.latitude" value="" type="text" /></div>
										</td>
									</tr>
									<tr>
										<td>
											纬度
										</td>
										<td>
												<div class="inpit2_bg" ><input name="nodeGroups.longitude" value="" type="text" /></div>
										</td>
									</tr>
									<tr>
										<td>
											描述
										</td>
										<td>
											<textarea rows="6" cols="50" name="nodeGroups.description" style="margin-left: 0px;"></textarea>
										</td>
									</tr>
								<tr> 
									<td colspan="2" align="center" style="text-align: center;">
										<input type="button" onclick='submitFrom()'  value='保存'  id='null'  class="btn60"  onmouseover="this.className='btn60_v'" onmouseout="this.className='btn60'" onmousedown="this.className='btn60_h'"/>
										<input type="reset" value='重置'  id='null'  class="btn60"  onmouseover="this.className='btn60_v'" onmouseout="this.className='btn60'" onmousedown="this.className='btn60_h'"/>
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
				</form>
				</div>

			</div>
		</div>
		<script type="text/javascript">
		var base = "<%= request.getContextPath()%>";
		//加载树
		$(document).ready(function(){
				$.ajax({
						type: "GET",
				  		url: "<%= request.getContextPath() %>/nodes/nodeGroup_bulidGroupTree.sip?type=<%= StringUtil.killNull(request.getParameter("type"))%>",
						success: function(msg){
									var array = eval(msg);
									var zNodes = array || [];
									$.fn.zTree.init($("#treeDemo"), setting, zNodes);
						}
				});
			
			});
		
		//提交表单
		function submitFrom(){
			var flag = $("#flag").val();
			var pId = $("#pId").val();
			var name = $("input[name=nodeGroups\\.name]").val();
			var url = "<%= request.getContextPath() %>/nodes/nodeGroup_saveGroup.sip";
			if(flag == "add"){
				url="<%= request.getContextPath() %>/nodes/nodeGroup_saveGroup.sip";
			}else if(flag == "edit"){
				url="<%= request.getContextPath() %>/nodes/nodeGroup_editGroup.sip";
			}
			var options = {
				beforeSubmit:showRequest,
				url:  url,
				type: 'POST',
		   		    success: function(id){
		   		        alert("添加成功！");
		   		        restForm();
						//回调函数
						if(flag){
							var zTree = $.fn.zTree.getZTreeObj("treeDemo");
							if(flag == "add"){
								var treeNode = zTree.getNodeByParam("id",pId,null);
								zTree.addNodes(treeNode , {id:id, pId:pId, name:name});
							}else if(flag == "edit"){
								//todo rainbow
							}
						}	
					}
		              };
				function showRequest(formData, jqForm, options) { 
						formData = $(this).param(formData); 
						return true; 
				}
				$("#myform").ajaxSubmit(options);
		}
		
		function restForm(){
			$("input[type=text]").val("");
			$("textarea[name=nodeGroups\\.description]").val("");
		}
		</script>
</body>
</html>
