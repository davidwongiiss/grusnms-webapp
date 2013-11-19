var IndexPanel = function() {
	var win, wp;



	//==========头页面
	var north = new Ext.Panel( {
		//id : "header",
		region : "north",
		margins : "2 0 0 0",
		height : 75,
		//html : "<img src='../images/header.jpg'>"
		//bodyStyle:"background:#f1f1f1"
		contentEl:"header"
	});

	//==========底部
	var south = new Ext.Panel( {
		id : "bottom",
		region : "south",
		margins : "5 5 5 0",
		height : 20,
		//bodyStyle:"background:#f1f1f1",
		html : "<p alight=''right>版权所有：---davidwongiiss</p>"
	//items:tabPanel
	});
	
	var tabPanel = new Ext.TabPanel( {
		id : 'tabPanel',
		region : 'center',
		deferredRender : false,
		activeTab : 0,
		enableTabScroll : true,
		defaults : {
			autoScroll : true
		},
		items : [ {
			contentEl : 'center_context_desktop',
			title : '首页',
			autoScroll : true
		} ]
	});

	function addTab(id,name,url) {
		var n = tabPanel
				.add( {
					"id" : id,
					"title" : name,
					closable : true,
					html : "<iframe src='"+url+"'   style='width:100%;height:100%' ></iframe>"
				});
		tabPanel.setActiveTab(n);
	}

	//addTab();

	//==========建立视图2
	var createWin = function() {
		win = new Ext.Viewport( {
			layout : 'border',
			//items:[ north, center, west]
			items : [ north, south, tabPanel ]
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