String.prototype.trim = function () {
        return this.replace(/(^\s*)|(\s*$)/g, "");
};

 // open new Tab page or new window
 function openWin(id,name,url,width,height){
	 if(url){
		 if(typeof(tabPL) != "undefined" && tabPL && tabPL.subSystemCtxPath){
			 if(url.toUpperCase().substring(0,4) != 'HTTP'){//如果不以http开头
				 var length = tabPL.requestCtxPath.length;//工程url
				 if(url.substring(0,length) == tabPL.requestCtxPath){//如果打开标签url以工程名开头
					url =  tabPL.subSystemCtxPath + url.substring(length,url.length);//把工程名替换成子系统路径URL
				 }else{
					 if(url.substring(0,1) != "/"){
						 url = "/" + url;
					 }
					 url = tabPL.subSystemCtxPath + url;
				 }
			 }
			 
			 try{
				 HttpProxyAjaxService.url(tabPL.requestCtxPath,url,function(transforUrl){
					 url = transforUrl;
					 try{
					     top.vTab(id,name,url);
					 }catch(e){
						 v_open_proxy_Win(id,name,url,width,height);
					 }
				 });
			 }catch(e){
				 v_open_proxy_Win(id,name,url,width,height);
			 }
		 }else{
			 v_open_proxy_Win(id,name,url,width,height);
		 }
	 }
 }
 function v_open_proxy_Win(id,name,url,width,height){
	 try{
	     top.vTab(id,name,url);
	 }catch(e){
		 try{
			 //windowProxy.post({'type':'openTab','id':id,'name':name,'url': url,'parentId':null});
			 Porthole.WindowProxyLegacy.dispatchMessage({'type':'openTab','id':id,'name':name,'url': url,'parentId':null});
		 }catch(e){
			 if(!height) height= "500px";
			 if(!width) width= "500px";
			 window.open(url,name,"resizable=no,height="+height+",width="+width+",menubar=no,scrollbars=no,toolbar=no,titlebar=no,resizable=yes");
		 }
	        //var retval = popUpModalDialog(url,width,height,100,100,'',name);*/
	 }
 }
 
 function refreshParentTab(id,name,url){
	 Porthole.WindowProxyLegacy.dispatchMessage({'type':'refreshTab','id':id});
 }
 
 function v_openOkWin(){
       var xURL="../../platform/web/ok.jsp";
       var showx = 150;
       var showy = 150;
       var retval = popUpModalDialog(xURL,400,350,showx,showy);
    }
    function v_openErrorWin(){
       var xURL="../../platform/web/error.jsp";
       var showx = 150 ;
       var showy = 150;
       var retval = popUpModalDialog(xURL,400,350,showx,showy);
    }
 // //close Tab page or window
 function v_closeWindow() {
   try{
   	top.tabManager.closeCurWorkspace();
   	top.tabManager.openPre();
    //top.v_closeCurrentTab();
   }catch(e){
		if(window.opener||window.dialogArguments){
			window.close();
		}else{
			try{
				windowProxy.post({'type':'closeTab'});
			}catch(e){}
		}
   } 
 }
 // open new window
function popUpModalDialog(xURL,width, height, showx, showy,path,title,is_blank) {
	var localObj = window.location;
	var contextPath = "/"+localObj.pathname.split("/")[1];
     if(is_blank==null) is_blank=false;
     if(is_blank=='') is_blank=false;
     if (width == null) {
        width = 240;
        height = 264;
     }
     
    if (showx == null & showx!='') {
                showx = event.screenX - event.offsetX - 4 - 210 ;
                showy = event.screenY - event.offsetY + 18;
        }
        xURL=xURL.replace("?","&");
        if(title==null) title=" ";
        if(typeof path == "undefined" || !path){
         xURL=contextPath+"/jsp/include/transpath.jsp?i-width="+(width-26)+"&i-height="+(height-32)+"&transpath="+xURL+"&v_title="+title;
        }else{
         xURL=path+"/jsp/include/transpath.jsp?i-width="+(width-26)+"&i-height="+(height-32)+"&transpath="+xURL+"&v_title="+title;
        }
        var windowProps="dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:no;scrollbars:yes;Resizable=yes;help:no";
        if(is_blank){
          //return window.showModalDialog(xURL,window,windowProps);
         return window.showModelessDialog(xURL,window,windowProps);
         // return window.open(xURL,"frame","scrollbars=1,height="+height+",width="+width+",status=yes,toolbar=no,menubar=no,location=no","false");
        }else{
          return window.showModalDialog(xURL,window,windowProps);
        }
 }
  // select all 
  //fromName  
  //input2:select all checkbox id you can use " this " 
 function chkall(fromName,input2)
 {
    var objForm = document.forms[fromName];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++)
    {
        if (input2.checked == true)
        {
            if (objForm.elements[iCount].type == "checkbox")
            {
                objForm.elements[iCount].checked = true;
            }
        }
        else
        {
            if (objForm.elements[iCount].type == "checkbox")
            {
                objForm.elements[iCount].checked = false;
            }
        }
    }
 }
 
  //// get selected ids  
  //fromName  
  //input2:select all checkbox id you can use " this " 
 function v_getCheckedIds(fromName)
 {
    var objForm = document.forms[fromName];
    var objLen = objForm.length;
    var ids="";
    for (var iCount = 0; iCount < objLen; iCount++)
    {
      if (objForm.elements[iCount].type == "checkbox")
      { 
        if(objForm.elements[iCount].checked == true){
          if(objForm.elements[iCount].value!=null & objForm.elements[iCount].value!="v_selAll" ) ids=ids+objForm.elements[iCount].value+",";
        }
      }
    }
    return ids;
 }
 // check the id value if exist form  checkbox 
 function existAlready(v,formName){
    if(v=='v_selAll') return true;
    try{
    var objForm = document.forms[formName];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++)
    {
            if (objForm.elements[iCount].type == "checkbox")
            {
              if(objForm.elements[iCount].value==v) return true;
            }
    }
    }catch(e){
      return false;
    }
    return false;        
}
// remove <tr> from  Table by the form formName 
 function removeTr(formName,toTable){
    try{
    var toTable = document.getElementById(toTable);
    var objForm = document.forms[formName];
    var objLen = objForm.length;
    var delTr;
    var root;
    for(var i=0;i<objLen;i++){
       delTr=getCheckedTrNode(formName);
       if(delTr!=null){
         root=delTr.parentNode;
         root.removeChild(delTr);
       }
    }
    }catch(e){
      alert(e);
    }
 }
 
 
 
 function getCheckedTrNode(formName){
    var objForm = document.forms[formName];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++)
    {
            if (objForm.elements[iCount].type == "checkbox")
            {   
                if(objForm.elements[iCount].checked){
                  if(objForm.elements[iCount].value!='v_selAll'){
                    return objForm.elements[iCount].parentNode.parentNode;
                  }  
                }
            }
     } 
     return null;   
 } 

 function v_changePageSize(size){
  try{
   document.forms[0].elements["pagesize"].value=size;
  }catch(e){}
   
   parent.topf.document.forms[0].elements["pagesize"].value=size;
 }

 function v_resetPropertyAndOrder(p,o){
    try{
       parent.topf.document.forms[0].elements["property"].value=p;
       parent.topf.document.forms[0].elements["order"].value=o;
    }catch(e){}
 }
 //从原来的framework/newStyle/js/util.js迁移到这来，基本都引入frame.js所以也没基本不用动js
 function changeCss(obt,str)
{
	 /*if ("onfocus"==str)
	 {
	 		obt.parentNode.parentNode.className="inpit3_bg";
	 		obt.parentNode.className="inpit3_3bg";
	 }
	 else if ("onblur"==str)
	 {
	 		obt.parentNode.parentNode.className="inpit2_bg";
	 		obt.parentNode.className="inpit2_2bg";
	 } */
}

function banBackSpace(e){
	 var ev = e || window.event;//获取event对象
	 var obj = ev.target || ev.srcElement;//获取事件源
	 var t = obj.type || obj.getAttribute('type');//获取事件源类型
	 //获取作为判断条件的事件类型
	 var vReadOnly = obj.readOnly;
	 var vDisabled = obj.disabled;
	 //处理undefined值情况
	 vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
	 vDisabled = (vDisabled == undefined) ? true : vDisabled;
	 //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	 //并且readOnly属性为true或disabled属性为true的，则退格键失效
	 var flag1= ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")&& (vReadOnly==true || vDisabled==true);
	 //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	 var flag2= ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ;
	 //判断
	 if(flag2 || flag1)
		 return false;
}
