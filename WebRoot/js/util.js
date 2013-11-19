function selectAll(all_box, group_box) {
	var main_checkbox = document.getElementById(all_box);
	var sub_boxes = document.getElementsByName(group_box);
	for (var i = 0; i < sub_boxes.length; i++) {
		if (main_checkbox.checked) {
			sub_boxes[i].checked = true;
		} else {
			sub_boxes[i].checked = false;
		}
	}
}
/**
	把指定的一组checkbox所选的值构造为逗号分隔的字符串，赋值给elem指定的表单元素
*/
function getAllValue(group_box, elemName) {
	var boxes = document.getElementsByName(group_box);
	var values = "";
	for (var i = 0; i < boxes.length; i++) {
		if (boxes[i].checked) {
			values += boxes[i].value + ",";
		}
	}
	if (values.length > 0) {
		values = values.substring(0, values.length - 1);
	}
	if (elemName) {
		var elem = document.getElementById(elemName);
		if (elem) {
			elem.value = values;
		} else {
			var elems = document.getElementsByName(elemName);
			if (elems && elems.length > 0) {
				elems[0].value = values;
			}
		}
	}
	return values;
}
function shutdownWindow(currentIndex) {
	//先判断是不是在标签页中，如果在，就关闭标签页，否则，关闭窗口
	if(currentIndex==null) currentIndex=-1;
	try{
	if(top.tabManager){
	   	top.tabManager.closeCurWorkspace();
	}else if(window.close){
		window.opener=null;
		window.open('',"_self");
	   	//if(getCurrentTabIndex()==currentIndex){
	   	 //alert("currentIndex="+currentIndex);
	   	 window.close();
	   	//}else{
	   	 //alert("getCurrentTabIndex()="+getCurrentTabIndex()+" currentIndex="+currentIndex);
	   	//}
	}
	}catch(e){}
}
function refreshParent() {
	try
	{
		if (opener) {
			if (opener.refreshSelf) {
				opener.refreshSelf();
			}
		} else {
			if (window.dialogArguments) {
				if (window.dialogArguments.refreshSelf) {
					window.dialogArguments.refreshSelf();
				}
			} else {
	 		//在这里刷新父标签
	 			var parentTab="";
	 			try{
	 				parentTab=top.v_getCurrentTabVal();
	 			}catch(e){}
	 			if(parentTab){
	 				if(parentTab.refreshSelf){
	 					parentTab.refreshSelf();
	 				}
	 			} 
			}
		}
	}
	catch(e){}
}
function openNewWindow(xURL, xwidth, xheight,target) {
	var showx = (window.screen.availWidth - xwidth) / 2;
	var showy = (window.screen.availHeight - xheight) / 2;
	var _target=target?"_blank":target;
	var retval = window.open(xURL, _target, "resizable=yes,height=" + xheight + ",width=" + xwidth + ",left=" + showx + ",top=" + showy + ",menubar=no,scrollbars=yes,toolbar=no,titlebar=no");
}
/**
*判断给定中英文混合串长度是否超长
*value:待判断的字符串
*max:允许的最大长度
*ignoreZh：是否忽略中文。如果设置ignoreZh为true，则中为也认为是一个字符，否则，中文认为是两个字符。
*默认中文按两个字符计算。
*/
function validateMaxLen(value, max, ignoreZh) {
	if (!max) {
		return true;
	}
	return caculateLength(value, ignoreZh) <= max;
}
/**
*计算目标字符串的长度。如果ignore为true，中文认为是一个字符；否则，中文作两个字符处理。
*value：目标字符串
*ignoreZh：是否忽略中文
*/
function caculateLength(value, ignoreZh) {
	if (!value) {
		return 0;
	}
	if (ignoreZh) {
		return value.length;
	}
	var len = 0;
	for (var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) < 128 && value.charCodeAt(i) > 0) {
			len++;
		} else {
			len = len + 2;
		}
	}
	return len;
}
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.replaceAll = function(s1,s2){    
return this.replace(new RegExp(s1,"gm"),s2);    
};
/**对form中的每个元素执行trim操作*/
function trimForm(theForm) {
	var obj = theForm || event.srcElement;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			if (type == "button" || type == "submit" || value == "") {
				continue;
			} else {
				value = value.trim();
			}
		}
	}
}
/**过滤输入框中的中文中划线，把它替换为两个英文的中划线*/
function replaceDash(theForm) {
	var obj = theForm || event.srcElement;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			if (type == "button" || type == "submit" || value == "") {
				continue;
			} else {
				value = value.replace(/—/g, "--");
			}
		}
	}
}
/**
	过滤英文的单引号、双引号，改成中文的单引号和双引号
*/
function replaceSpecial(theForm) {
	var obj = theForm || event.srcElement;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			if (type == "button" || type == "submit" || value == "") {
				continue;
			} else {
				value = value.replace(/'/g, "\u2019");
				value = value.replace(/"/g, "\u201d");
			}
		}
	}
}
function clearAllSpace(theForm) {
	var obj = theForm || event.srcElement;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			if (type == "button" || type == "submit" || value == "") {
				continue;
			} else {
				value = value.replace(/\s+/g, "");
			}
		}
	}
}

/**标识表格中一行的颜色*/
function markTr(trId,tableId){
	if(!tableId)
		tableId="hollylistTable";
	var listTable=document.getElementById(tableId);
	var childNodes=listTable.rows;
	for(var i=1;i<childNodes.length;i++){
		childNodes[i].style.background = "#FFFFFF";
	}
	var markTr=document.getElementById("tr_"+trId);
	if(markTr)
		markTr.style.background = "#F7F7CC"
}

function yesOrNoConfirm(message)   
{   
      execScript("n= (msgbox('"+message+"',vbYesNo,'确认对话框')=vbYes)","vbscript");   
      return(n);   
}   

function openNewWindow(xURL,width,height,_target)
{
	if(!_target)
		_target="_blank";
	var showx = (window.screen.availWidth-width)/2;
    var showy = (window.screen.availHeight-height)/2;
    return window.open(xURL,_target,"resizable=yes,height="+height+",width="+width+",left="+showx+",top="+showy+",menubar=no,scrollbars=yes,toolbar=no,titlebar=no");
}
function openNewDialog(xURL,width,height)
{
	window.showModalDialog(xURL,window,"dialogHeight:"+height+";dialogWidth:"+width+";scroll:yes;resizable:yes");
}

/**
 * 设置select元素的选中状态。无论value还是text值与传入的值匹配上，都把select设置为该值。
 * @param {Object} elem
 * @param {Object} valOrName
 */
function setSelectValue(elem,valOrName){
	if(!elem||!valOrName||valOrName=="")
		return -1;
	valOrName=valOrName.trim();
	if(elem.tagName!=="SELECT")
		return -1;
	for(var idx=0;idx<elem.length;idx++){
		if(elem.options[idx].value==valOrName||elem.options[idx].text==valOrName){
			elem.selectedIndex=idx;
			return idx;
		}
	}
	return -1;
}


