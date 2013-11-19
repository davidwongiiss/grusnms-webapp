
/*************************************************
Validator v1.01
code by 我佛山人
wfsr@cunite.com
http://www.cunite.com
*************************************************/
String.prototype.Trim = function(){
return this.replace(/(^\s*)|(\s*$)/g, "");
}
Validator = {Require:/.+/, Email:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, 
Phone:/^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/, 
Telephone:/^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?\d*$/,                                            
Mobile:/^((\(\d{3}\))|(\d{3}\-))?\d{11}$/,  
Url:/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/, 
IdCard:/^\d{15}(\d{2}[A-Za-z0-9])?$/, 
Certificates:/^[A-Za-z0-9]+$/, 
Currency:/^\d+(\.\d+)?$/, 
Number:/^\d+$/, 
Zip:/^\d{6}$/, 
QQ:/^[1-9]\d{4,8}$/, 
Integer:/^[-\+]?\d+$/, 
PositiveInteger:/^(\d|([1-9]{1}\d{1,5}))$/, 
Double:/^[-\+]?\d+(\.\d+)?$/, 
English:/^[A-Za-z]+$/, 
Chinese:/^[\u0391-\uFFE5]+$/, 
UnSafe:/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/, 
IP:/^(([0-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([0-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([0-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/,
IsSafe:function (str) {
	return !this.UnSafe.test(str);
}, 
SafeString:"this.IsSafe(value.Trim())", 
Limit:"this.limit(value.Trim().length,getAttribute('min'), getAttribute('max'))", 
LimitB:"this.limit(this.LenB(value.Trim()), getAttribute('min'), getAttribute('max'))", 
Date:"this.IsDate(value.Trim(), getAttribute('min'), getAttribute('format'))", 
Repeat:"value == document.getElementsByName(getAttribute('to'))[0].value.Trim()", 
Range:"parseInt(getAttribute('min')) < value && value < parseInt(getAttribute('max'))", 
RangeNull:"parseInt(getAttribute('min')) < value && value < parseInt(getAttribute('max')) && value != ''", 
Compare:"this.compare(value.Trim(),getAttribute('operator'),getAttribute('to'))", 
Custom:"this.Exec(value, getAttribute('regexp'))", 
Group:"this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))", 
ErrorItem:[document.forms[0]], 
ErrorMessage:["\u4ee5\u4e0b\u539f\u56e0\u5bfc\u81f4\u63d0\u4ea4\u5931\u8d25\uff1a\t\t\t\t"], 
PasswordRule:"this.CheckPassword(value)",
Validate:function (theForm, mode) {
	var obj = theForm || event.srcElement;
	
	var count = obj.elements.length;
	this.ErrorMessage.length = 1;
	this.ErrorItem.length = 1;
	this.ErrorItem[0] = obj;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			var _dataType = getAttribute("dataType");
			if(!_dataType)
				continue;
			//这样支持一个输入项进行多次验证
			var dataTypes=_dataType.split(",");
			var msg=getAttribute("msg");
			var msgs=[];
			if(msg)
				msgs=msg.split("##");
			for(var ii=0;ii<dataTypes.length;ii++){
			
				if (typeof (dataTypes[ii]) == "object" || typeof (this[dataTypes[ii]]) == "undefined") {
				continue;
				}
				this.ClearState(obj.elements[i]);
				if (getAttribute("require") == "false" && value == "") {
					continue;
				}
				switch (dataTypes[ii]) {
				  case "Date":
				  case "Repeat":
				  case "Range":
				  case "RangeNull":
				  case "Compare":
				  case "Custom":
				  case "Group":
				  case "Limit":
				  case "LimitB":
				  case "PasswordRule":
				  case "SafeString":
					if (!eval(this[dataTypes[ii]])) {
						this.AddError(i, msgs[ii]);
					}
					break;
				  default:
					if (!this[dataTypes[ii]].test(value)) {
						this.AddError(i, msgs[ii]);
					}
					break;
				}
			}
		}
	}
	if (this.ErrorMessage.length > 1) {
		mode = mode || 1;
		var errCount = this.ErrorItem.length;
		switch (mode) {
		  case 2:
			for (var i = 1; i < errCount; i++) {
				this.ErrorItem[i].style.color = "red";
			}
		  case 1:
			alert(this.ErrorMessage.join("\n"));
			this.ErrorItem[1].focus();
			break;
		  case 3:
			for (var i = 1; i < errCount; i++) {
				try {
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/, "*");
				}
				catch (e) {
					alert(e.description);
				}
			}
			this.ErrorItem[1].focus();
			break;
		  default:
			alert(this.ErrorMessage.join("\n"));
			break;
		}
		return false;
	}
	return true;
}, limit:function (len, min, max) {
	min = min || 0;
	max = max || Number.MAX_VALUE;
	return min <= len && len <= max;
}, LenB:function (str) {
	return str.replace(/[^\x00-\xff]/g, "**").length;
}, ClearState:function (elem) {
	with (elem) {
		if (style.color == "red") {
			style.color = "";
		}
		var lastNode = parentNode.childNodes[parentNode.childNodes.length - 1];
		if (lastNode.id == "__ErrorMessagePanel") {
			parentNode.removeChild(lastNode);
		}
	}
}, AddError:function (index, str) {
	this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
	//过滤相同的提示信息
	for(var i=0;i<this.ErrorMessage.length;i++){
		if(this.ErrorMessage[i]&&this.ErrorMessage[i].substring(2)==str)
			return;
	}
	this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
}, Exec:function (op, reg) {
	return new RegExp(reg, "g").test(op);
}, compare:function (op1, operator, op2) {
	switch (operator) {
	  case "NotEqual":
		return (op1 != op2);
	  case "GreaterThan":
		return (op1 > op2);
	  case "GreaterThanEqual":
		return (op1 >= op2);
	  case "LessThan":
		return (op1 < op2);
	  case "LessThanEqual":
		return (op1 <= op2);
	  default:
		return (op1 == op2);
	}
}, MustChecked:function (name, min, max) {
	var groups = document.getElementsByName(name);
	var hasChecked = 0;
	min = min || 1;
	max = max || groups.length;
	for (var i = groups.length - 1; i >= 0; i--) {
		if (groups[i].checked) {
			hasChecked++;
		}
	}
	return min <= hasChecked && hasChecked <= max;
}, IsDate:function (op, formatString) {
	formatString = formatString || "ymd";
	var m, year, month, day;
	switch (formatString) {
	  case "ymd":
		m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
		if (m == null) {
			return false;
		}
		day = m[6];
		month = m[5]--;
		year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
		break;
	  case "dmy":
		m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
		if (m == null) {
			return false;
		}
		day = m[1];
		month = m[3]--;
		year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
		break;
	  default:
		break;
	}
	if (!parseInt(month)) {
		return false;
	}
	month = month == 12 ? 0 : month;
	var date = new Date(year, month, day);
	return (typeof (date) == "object" && year == date.getFullYear() && month == date.getMonth() && day == date.getDate());
	function GetFullYear(y) {
		return ((y < 30 ? "20" : "19") + y) | 0;
	}
},CheckPassword:function(str){
 	var _upperNum = 0;
 	var _lowerNum = 0;
 	var _numberNum = 0;
 	var _charNum = 0;
 	var _password = new String(str);
 	if(_password.length < 6 || _password.length > 20){
 		return false;
 	}
 	for(var i=0;i<_password.length;i++){
 		var c = _password.charAt(i);
 		if(c >= 'A' && c<='Z'){
 			_upperNum =1;
 		}else if(c >= 'a' && c<='z'){
 			_lowerNum =1;
 		}else if(c >= '0' && c<='9'){
 			_numberNum = 1;
 		}else{
 			_charNum =1;
 		}
 		
 	}
 	if((_upperNum+_lowerNum+_numberNum+_charNum) > 2){
 		return true;
 	}else{
		return false; 	
 	}
 }
};
