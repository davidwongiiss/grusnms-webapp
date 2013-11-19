$(document).ready(function(){
	var result = testSelect.genSelect();
	$("#groupdiv").html("移动到："+ result + "<input type='button' value='分配' id='allocat' class='btn60'/>");
	$("#allocat").bind("click",function(){
		var str = "#";
		$("input[type=checkbox][checked]").each(function(){ 
			str+=","+$(this).val();
		});
		str = str.replace("#,","");
		$("#ids").val(str);
		var gid = $("#groupTypes").find("option:selected").attr("id")
		//var gid = $("#groupTypes").attr("id");
		$("#gid").val(gid);
		$("#myform").attr("action",base+"/nodes/nodes_insertNodeToGroup.sip");
		$("#myform").submit();
	});
	$("#checkAll").click(function(){    
		if($(this).attr("checked") == true){// 全选    
				$("input[type=checkbox][name=checkbox]").each(function(){    
					$(this).attr("checked", true);    
				});    
			} else {// 取消全选    
                $("input[type=checkbox][name=checkbox]").each(function(){    
                    $(this).attr("checked", false);    
                });    
            }    
        });    
});
