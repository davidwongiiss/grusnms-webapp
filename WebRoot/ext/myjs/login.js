var LoginPanel = function() {
	var win;


	//==========��½��ť
	var loginButton = new Ext.Button( {
		width : 50,
		id : "loginButton",
		text : "��½",
		minWidth : 70,
		type : "button", //��ť���ͣ�������submit, reset or button ��Ĭ���� button               
		autoShow : true, //Ĭ��false                         
		disabled : false, //Ĭ��false
		listeners : 
		{
			"click" : function() {
				login();
			}
		}    
	});


	//==========ȡ����ť
	var resetButton = new Ext.Button( {
		id : "resetButton",
		text : "ȡ��",
		minWidth : 70,
		type : "button", //��ť���ͣ�������submit, reset or button ��Ĭ���� button               
		autoShow : true, //Ĭ��false                     
		disabled : false, //Ĭ��false
		listeners : //��Ӽ����¼�
		{
			"click" : function() {
				resetLogin();
			}
		}
	});


	//==========��½��3
	var loginForm = new Ext.form.FormPanel( {
		baseCls : "x-plain",
		defaultType : "textfield",
		layout : "absolute",
		items : [ {
			x : 10,
			y : 20,
			xtype : "label",
			text : "�ʺţ�"
		}, {
			x : 60,
			y : 15,
			name : "userName",
			allowBlank : false,
			maxLength: "14",
			maxLengthText: '���Ȳ��ܳ���14���ַ�',
			anchor : "90%",
			blankText : "�ʺŲ���Ϊ��"
		}, {
			x : 10,
			y : 60,
			xtype : "label",
			text : "���룺"
		}, {
			x : 60,
			y : 55,
			name : "passWord",
			inputType : "password",
			allowBlank : false,
			maxLength: "14",
			maxLengthText: '���Ȳ��ܳ���14���ַ�',
			anchor : "90%",
			blankText : "���벻��Ϊ��"
		} ],
		buttons : [ loginButton, resetButton ]
	});


	//==========�����������2
	var createWin = function() {
		win = new Ext.Window( {
			title : "�û���½", // ��������
			layout : "fit", //֮ǰ�ᵽ�Ĳ��ַ�ʽfit������Ӧ���� 
			width : 300,
			height : 180,
			plain : true, // ͸������
			bodyStyle : "padding:5px;",
			maximizable : false, //��ֹ���
			resizable : false,//��ֹ�ı��С
			draggable:false, //��ֹ�϶� 
			closable : false, //��ֹ�ر�
			collapsible : false, //��ֹ�۵�
			buttonAlign : "center",
			defaults : {// ������Ԫ���Ƿ���ʾ�߿�
				border : true
			},
			items : [loginForm]
		});
	};


	//==========�ύ��
	var login = function() {
		if (loginForm.getForm().isValid()) {
			Ext.Ajax.request( {
				url : "nodes/n_login.sip",
				method : "post",
				params : { 
					username : Ext.get("username").dom.value,
					password : Ext.get("password").dom.value
				},
				success : function(response, options) {
					var resp_text = eval(response.responseText);
					if (resp_text  == true) {
						document.location = "index.jsp";
					} else {
						loginForm.form.reset();
						Ext.Msg.alert("��ʾ", "��¼ʧ�ܣ������µ�¼");
					}
				}
			});
		}
	};


	//==========��յ�¼������ڵ�����
	var resetLogin = function() {
		loginForm.form.reset();
	};


	//==========�ȴ��ļ�����Ϣ
	var loading = function(){
		setTimeout(function(){
			try{
				Ext.get('loading').remove();
				Ext.get('loading-mask').fadeOut({remove:true});
			}catch(e){}
		}, 250);
	};


	//==========��ʼ��1
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