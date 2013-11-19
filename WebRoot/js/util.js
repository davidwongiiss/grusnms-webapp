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
	��ָ����һ��checkbox��ѡ��ֵ����Ϊ���ŷָ����ַ�������ֵ��elemָ���ı�Ԫ��
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
	//���ж��ǲ����ڱ�ǩҳ�У�����ڣ��͹رձ�ǩҳ�����򣬹رմ���
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
	 		//������ˢ�¸���ǩ
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
*�жϸ�����Ӣ�Ļ�ϴ������Ƿ񳬳�
*value:���жϵ��ַ���
*max:�������󳤶�
*ignoreZh���Ƿ�������ġ��������ignoreZhΪtrue������ΪҲ��Ϊ��һ���ַ�������������Ϊ�������ַ���
*Ĭ�����İ������ַ����㡣
*/
function validateMaxLen(value, max, ignoreZh) {
	if (!max) {
		return true;
	}
	return caculateLength(value, ignoreZh) <= max;
}
/**
*����Ŀ���ַ����ĳ��ȡ����ignoreΪtrue��������Ϊ��һ���ַ������������������ַ�����
*value��Ŀ���ַ���
*ignoreZh���Ƿ��������
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
/**��form�е�ÿ��Ԫ��ִ��trim����*/
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
/**����������е������л��ߣ������滻Ϊ����Ӣ�ĵ��л���*/
function replaceDash(theForm) {
	var obj = theForm || event.srcElement;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			if (type == "button" || type == "submit" || value == "") {
				continue;
			} else {
				value = value.replace(/��/g, "--");
			}
		}
	}
}
/**
	����Ӣ�ĵĵ����š�˫���ţ��ĳ����ĵĵ����ź�˫����
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

/**��ʶ�����һ�е���ɫ*/
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
      execScript("n= (msgbox('"+message+"',vbYesNo,'ȷ�϶Ի���')=vbYes)","vbscript");   
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
 * ����selectԪ�ص�ѡ��״̬������value����textֵ�봫���ֵƥ���ϣ�����select����Ϊ��ֵ��
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


