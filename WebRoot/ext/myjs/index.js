var IndexPanel = function() {
	var win, wp;
	//==========头页面
	var north = new Ext.Panel( {
		id : "header",
		region : "north",
		margins : "0 0 0 0",
		height : 78,
		//html : "<img src='../images/header.jpg'>"
		/*bodyStyle: {
            background: 'yellow',
            position:'absolute ',
            'z-index': 10001
        },*/
		contentEl:"header"
	});

	//==========底部
	
	//var south = new Ext.Panel( {
	//	id : "bottom",
	//	region : "south",
	//	margins : "5 5 5 0",
	//	height : 20,
		//bodyStyle:"background:#f1f1f1",
	//	html : "<p alight=''right>版权所有：</p>"
	//items:tabPanel
	//});
	
	
	var tabPanel = new Ext.TabPanel( {
		id : 'tabPanel',
		region : 'center',
		deferredRender : false,
		activeTab : 0,
		enableTabScroll : true,
		defaults : {
			autoScroll : true
		}
		/*
			,
		 * bodyStyle: {
			background: 'black',
            height:'100%'
		items : [ {
			id:'id1',
			contentEl : 'center_context_desktop',
			//html:"<iframe src='"+path+"/jsp/monitor/monitorNodesFrame.jsp'  height='100%' width='100%' ></iframe>",
			title : '监控台',
			autoScroll : true,
			height:200
		} ]
        },*/
	});

	function addTab(id,name,url) {
		var n = Ext.getCmp(id);
		if(!n){
			var n = tabPanel
					.add( {
						"id" : id,
						"title" : name,
						closable : true,
						frame : true ,
						//autoLoad: true,
						html : "<iframe id='iframe_"+id+"' src='"+url+"'  height='100%' width='100%' ></iframe>"//,
						//listeners: { "beforeadd": function (obj, component, index) {
                        //	frames['iframe_' + id].location.reload();
                   		//}}
					});
			
		}else{
			frames["iframe_"+id].location.href = url;
		};
		tabPanel.setActiveTab(n);
	}

	addTab("id1" , "监控台" , path+"/jsp/monitor/monitorNodesFrame.jsp");

	//==========建立视图2
	var createWin = function() {
		win = new Ext.Viewport( {
			layout : 'border',
			//items:[ north, center, west]
			items : [ north , tabPanel ]
		});
	};

	//==========初始化1
	return {
		init : function() {
			createWin();
			win.show();
			window._addTab = addTab;
		}
	}
}();
Ext.onReady(IndexPanel.init, IndexPanel);