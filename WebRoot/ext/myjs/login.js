var LoginPanel = function() {
	var win;


	//==========登陆按钮
	var loginButton = new Ext.Button( {
		width : 50,
		id : "loginButton",
		text : "登陆",
		minWidth : 70,
		type : "button", //按钮类型：可以是submit, reset or button 　默认是 button               
		autoShow : true, //默认false                         
		disabled : false, //默认false
		listeners : 
		{
			"click" : function() {
				login();
			}
		}    
	});


	//==========取消按钮
	var resetButton = new Ext.Button( {
		id : "resetButton",
		text : "取消",
		minWidth : 70,
		type : "button", //按钮类型：可以是submit, reset or button 　默认是 button               
		autoShow : true, //默认false                     
		disabled : false, //默认false
		listeners : //添加监听事件
		{
			"click" : function() {
				resetLogin();
			}
		}
	});


	//==========登陆表单3
	var loginForm = new Ext.form.FormPanel( {
		baseCls : "x-plain",
		defaultType : "textfield",
		layout : "absolute",
		items : [ {
			x : 10,
			y : 20,
			xtype : "label",
			text : "帐号："
		}, {
			x : 60,
			y : 15,
			name : "userName",
			allowBlank : false,
			maxLength: "14",
			maxLengthText: '长度不能超过14个字符',
			anchor : "90%",
			blankText : "帐号不能为空"
		}, {
			x : 10,
			y : 60,
			xtype : "label",
			text : "密码："
		}, {
			x : 60,
			y : 55,
			name : "passWord",
			inputType : "password",
			allowBlank : false,
			maxLength: "14",
			maxLengthText: '长度不能超过14个字符',
			anchor : "90%",
			blankText : "密码不能为空"
		} ],
		buttons : [ loginButton, resetButton ]
	});


	//==========窗口面板容器2
	var createWin = function() {
		win = new Ext.Window( {
			title : "用户登陆", // 窗口名称
			layout : "fit", //之前提到的布局方式fit，自适应布局 
			width : 300,
			height : 180,
			plain : true, // 透明背景
			bodyStyle : "padding:5px;",
			maximizable : false, //禁止最大化
			resizable : false,//禁止改变大小
			draggable:false, //禁止拖动 
			closable : false, //禁止关闭
			collapsible : false, //禁止折叠
			buttonAlign : "center",
			defaults : {// 容器内元素是否显示边框
				border : true
			},
			items : [loginForm]
		});
	};


	//==========提交表单
	var login = function() {
		if (loginForm.getForm().isValid()) {
			Ext.MessageBox.show( {
				msg : "正在登陆...",
				width : 300,
				progress : true,
				closable : false
			});
			var f = function(v) {
				return function() {
					var i = v / 11;
					Ext.MessageBox.updateProgress(i, "");
				};
			};
			for ( var i = 1; i < 13; i++) {
				setTimeout(f(i), i * 150);
			}
			Ext.Ajax.request( {
				url : "LoginAction",
				method : "post",
				params : { 
					username : Ext.get("username").dom.value,
					password : Ext.get("password").dom.value
				},
				success : function(response, options) {
					var responseArray = Ext.util.JSON.decode(response.responseText);
					if (responseArray.success == true) {
						document.location = "index.jsp";
					} else {
						loginForm.form.reset();
						Ext.Msg.alert("提示", "登录失败，请重新登录");
					}
				}
			});
		}
	};


	//==========清空登录表单面板内的数据
	var resetLogin = function() {
		loginForm.form.reset();
	};


	//==========等待的加载信息
	var loading = function(){
		setTimeout(function(){
			Ext.get('loading').remove();
			Ext.get('loading-mask').fadeOut({remove:true});
		}, 250);
	};


	//==========初始化1
	return {
		init : function() {
			loading();
			createWin();
			win.show();
		}
	}
}();
Ext.QuickTips.init();
Ext.onReady(LoginPanel.init, LoginPanel);