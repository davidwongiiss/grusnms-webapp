function panel(){ 
    var panel=new Ext.Panel({ 
        renderTo:'content', 
        title:'面板的头部', 
        width:400, 
        height:200, 
        html:'<h1>面板的主显示区域..可包含任何html代码</h1>', 
        tbar:[{text:'顶部工具栏按钮'}], 
        bbar:[{text:'底部工具栏'}], 
        buttons:[ 
            { 
                text:'面板底部按钮', 
                handler:function() 
                { 
                    Ext.Msg.alert('提示','面板底部按钮的事件！'); 
                } 
            } 
        ] 
    }); 
}



