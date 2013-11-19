
//树事件
var tree_evet = {
	 addHoverDom : function(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_a");
		if (treeNode.editNameFlag || $("#" + treeNode.id + "_add").length>0) return;
		var addStr = "<span title='add' class='button add' id='" + treeNode.id + "_add' />";
		sObj.append(addStr);
		var btn = $("#" + treeNode.id + "_add");
		if (btn) btn.bind("click", function(event){
			$("#flag").val("add");
			$("#pId").val(treeNode.id);
			$("#title").html("添加--<font color='red'>"+treeNode.name+"</font>--子分组");
		});
	},

	removeHoverDom : function(treeId, treeNode) {
		$("#" + treeNode.id + "_add").unbind().remove();
	},
	//
	showRemoveBtn:function(treeId, treeNode) {
		return (treeNode.id!=0)&&(treeNode.isSystem!=1)&&(treeNode.isLastNode);
	},
	showRenameBtn:function(treeId, treeNode) {
		return (treeNode.id!=0)&&(treeNode.isSystem!=1);;
	},
	onedit:function(event, treeId, treeNode){
		
			if(treeNode.isFirstNode) return;
			$("#flag").val("update");
			$("#pId").val(treeNode.pId);
			$("#id").val(treeNode.id);
			$("#title").html("修改--<font color='red'>"+treeNode.name+"</font>--分组");
			$("input[name=nodeGroups\\.name]").val(treeNode.name);
			//$("#nodeGroups\\.groupType option[value='"+treeNode.name+"']").attr("selected", true);
			$("#nodeGroups\\.groupType").attr("value", treeNode.groupType);
			$("input[name=nodeGroups\\.latitude]").val(treeNode.latitude);
			$("input[name=nodeGroups\\.longitude]").val(treeNode.longitude);
			$("input[name=nodeGroups\\.description]").text(treeNode.description);
			$("input[type=button]").val("修改");
	},
	onRemove : function(e, treeId, treeNode) {
			$.ajax({
				type: "GET",
				url: base+"/nodes/nodeGroup_deleteGroup.sip",
				data: {"groupId":treeNode.id},
				callback:function(id){
					alert("删除"+id+"成功");
				}
			});
		}
}

	//树配置
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				addHoverDom: tree_evet.addHoverDom,
				removeHoverDom: tree_evet.removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true ,
				showRenameBtn : false,
				removeTitle : '删除',
				showRemoveBtn: tree_evet.showRemoveBtn
			},
			callback: {
				onClick: tree_evet.onedit,
				onRemove: tree_evet.onRemove
			}
		};


