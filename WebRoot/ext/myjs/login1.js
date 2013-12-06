
function subForm(){
	var sUserName = document.getElementById('username').value;
	var sPassWord = document.getElementById('password').value;
	if(sUserName != "" && sPassWord != ""){	
		//提交表单
		$.ajax({
			type:"post",
			url:"nodes/n_login.sip",
			data:"username="+sUserName+"&password="+sPassWord,
			complete:function(XMLHttpRequest, textStatus){
				var resp_text = eval(XMLHttpRequest.responseText);								
				if (resp_text  == true) {
					document.location = "index.jsp";
				} else {
					sUserName="";
					sPassWord="";
					alert("登录失败，请重新登录");
				}
			}
		});		
	}else{
		alert("用户名或者密码不能为空！");
		formReset();
		return false;
	}
}

function formReset(){
	document.getElementById('username').value="";
	document.getElementById('password').value="";	
}