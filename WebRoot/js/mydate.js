function CalendarMinute(name,fName)
{
	this.name = name;
	this.fName = fName || "m_input";
	this.timer = null;
	this.fObj = null;
	
	this.toString = function()
	{
		var objDate = new Date();	
		//var sHour_Common=	"class=\"m_input\" maxlength=\"2\" name=\""+this.fName+"\" onkeyup=\""+this.name+".prevent(this)\" onkeypress=\"if (!/[0-9]/.test(String.fromCharCode(event.keyCode)))event.keyCode=0\" onpaste=\"return false\" ondragenter=\"return false\"";
		var sMinute_Common = "class=\"m_input\" maxlength=\"2\" name=\""+this.fName+"\" onkeyup=\""+this.name+".prevent(this)\" onkeypress=\"if (!/[0-9]/.test(String.fromCharCode(event.keyCode)))event.keyCode=0\" onpaste=\"return false\" ondragenter=\"return false\"";
		//var sSecond_Common = "class=\"m_input\" maxlength=\"2\" name=\""+this.fName+"\" onkeyup=\""+this.name+".prevent(this)\" onkeypress=\"if (!/[0-9]/.test(String.fromCharCode(event.keyCode)))event.keyCode=0\" onpaste=\"return false\" ondragenter=\"return false\"";
		var sButton_Common = "class=\"m_arrow\" onfocus=\"this.blur();\" ";
		
		var str = "";
		str += "<table class=\"cal_drawtime\" cellspacing=\"0\" cellpadding=\"0\">"
		str += "<tr>"
			str += "<td>"
				str += "<input style=\"height:22px; width:44px;font-size:10px;background:url(../images/time/btn_bg.jpg) no-repeat; margin:0 3px;margin-left:15px;border:none\" type=\"button\" value=\"����\" onclick=\"save();\" />"
				str += "<input style=\"height:22px; width:44px;font-size:10px;background:url(../images/time/btn_bg.jpg) no-repeat;border:none\" type=\"button\" value=\"ȡ��\" onclick=\"cancle();\" />"
				str += "&nbsp;&nbsp;&nbsp;"
					str += "</td>"
		str += "</table>"
		return str;
	}
	
	this.play = function()
	{
		this.timer = setInterval(this.name+".playback()",1000);
	}
	this.formatTime = function(sTime)
	{
		sTime = ("0"+sTime);
		return sTime.substr(sTime.length-2);
	}
	this.playback = function()
	{
		var objDate = new Date();
		var arrDate = [objDate.getHours(),objDate.getMinutes(),objDate.getSeconds()];
		var objMinute = document.getElementsByName(this.fName);
		for (var i=0;i<objMinute.length;i++)
		{
			objMinute[i].value = this.formatTime(arrDate[i])
		}
	}
	this.prevent = function(obj)
	{
		clearInterval(this.timer);
		this.setFocusObj(obj);
		var value = parseInt(obj.value,10);
		var radix = parseInt(obj.radix,10)-1;
		if (obj.value>radix||obj.value<0)
		{
			obj.value = obj.value.substr(0,1);
		}
	}
	this.controlTime = function(id)
	{
		
		event.cancelBubble = true;
		this.fObj = document.getElementById(id);
		if (!this.fObj) return;
		clearInterval(this.timer);
		var cmd = event.srcElement.innerText=="5"?true:false;
		var i = parseInt(this.fObj.value,10);
		var hour = this.fName+"_hour";
		if (id == hour)
		{
			this.fObj.radix = 24;
		}
		else
		{
				this.fObj.radix = 60;
		}
		var radix = parseInt(this.fObj.radix,10)-1;
		
		if (i==radix&&cmd)
		{
			i = 0;
		}
		else if (i==0&&!cmd)
		{
			i = radix;
		}
		else
		{
			cmd?i++:i--;
		}
		this.fObj.value = this.formatTime(i);
		this.fObj.select();
		//getDateTime();
	}
	this.setTime = function(obj)
	{
		obj.value = this.formatTime(obj.value);
	}
	this.setFocusObj = function(obj)
	{		
		eval(this.fName+"_up").disabled = eval(this.fName+"_down").disabled = false;
		this.fObj = obj;
	}
	this.getTime = function()
	{
		var arrTime = new Array(2);
		for (var i=0;i<document.getElementsByName(this.fName).length;i++)
		{
			arrTime[i] = document.getElementsByName(this.fName)[i].value;
            //alert(arrTime[i]);
        }
		return arrTime.join(":");
	}
}

//	Written by cloudchen, 2004/03/16
function CalendarCalendar(name,fName)
{
	this.name = name;
	this.fName = fName || "calendar";
	this.year = new Date().getFullYear();
	this.month = new Date().getMonth();
	this.date = new Date().getDate();
    //alert(this.month);
    //private
	this.toString = function()
	{
		var str = "";
		str += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" onselectstart=\"return false\">";
		str += "<tr>";
		str += "<td>";
		str += this.drawYear();
		//str += this.drawMonth();
		str += "</td>";
		str += "<td align=\"right\">";
		//str += this.drawYear();
		str += this.drawMonth();
		str += "</td>";
		str += "</tr>";
		str += "<tr>";
		str += "<td colspan=\"2\">";
		str += "<div class=\"c_frameborder\">";
		str += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"c_dateHead\" >";
		str += "<tr>";
		str += "<td class=\"red\">��</td><td>һ</td><td>��</td><td>��</td><td>��</td><td>��</td><td class=\"red\">��</td>";
		str += "</tr>";
		str += "</table>";
		str += this.drawDate();
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		return str;
	}
	//private
	this.drawYear = function()
	{
		var str = "";
		str += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		str += "<tr>";
		str += "<td>";
		str += "<input class=\"c_year\" maxlength=\"4\" value=\""+this.year+"\" name=\""+this.fName+"\" id=\""+this.fName+"_year\" readonly>";
		//DateField
		str += "<input type=\"hidden\" name=\""+this.fName+"\" value=\""+this.date+"\" id=\""+this.fName+"_date\">";
		str += "</td>";
		str += "<td>";
		str += "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">";
		str += "<tr>";
		str += "<td><button class=\"c_arrow\" height=\"22px\" onfocus=\"this.blur()\" onclick=\"event.cancelBubble=true;document.getElementById('"+this.fName+"_year').value++;"+this.name+".redrawDate()\">5</button></td>";
		str += "</tr>";
		str += "<tr>";
		str += "<td><button class=\"c_arrow\" height=\"22px\" onfocus=\"this.blur()\" onclick=\"event.cancelBubble=true;document.getElementById('"+this.fName+"_year').value--;"+this.name+".redrawDate()\">6</button></td>";
		str += "</tr>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		return str;
	}
	//priavate
	this.drawMonth = function()
	{     //alert(this.fName);
		var aMonthName = ["һ","��","��","��","��","��","��","��","��","ʮ","ʮһ","ʮ��"];
		var str = "";
		str += "<select class=\"c_month\" name=\""+this.fName+"\" id=\""+this.fName+"_month\" onchange=\""+this.name+".redrawDate()\">";
		for (var i=0;i<aMonthName.length;i++) {
			str += "<option value=\""+(i+1)+"\" "+(i==this.month?"selected":"")+">"+aMonthName[i]+"��</option>";
		}
		str += "</select>";
		return str;
	}
	//private
	this.drawDate = function()
	{
		var str = "";
		var fDay = new Date(this.year,this.month,1).getDay();
		var fDate = 1-fDay;
		var lDay = new Date(this.year,this.month+1,0).getDay();
		var lDate = new Date(this.year,this.month+1,0).getDate();
		str += "<table class=\"cal_drawdate\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\""+this.fName+"_dateTable"+"\">";
		for (var i=1,j=fDate;i<7;i++)
		{
			str += "<tr>";
			for (var k=0;k<7;k++)
			{
				if (k==0||k==6)
				{
					str += "<td><span style=\"color:#ff0000\" "+(j==this.date?" class=\"selected\"":"")+" onclick=\""+this.name+".redrawDate(this.innerText)\">"+(isDate(j++))+"</span></td>";
				}
				else
				{
						str += "<td><span"+(j==this.date?" class=\"selected\"":"")+" onclick=\""+this.name+".redrawDate(this.innerText)\">"+(isDate(j++))+"</span></td>";
			
				}
			}
			str += "</tr>";
		}
		str += "</table>";
		return str;

		function isDate(n)
		{
			return (n>=1&&n<=lDate)?n:"";
		}
	}
	//public
	this.redrawDate = function(d)
	{
		this.year = document.getElementById(this.fName+"_year").value;
		this.month = document.getElementById(this.fName+"_month").value-1;
        //alert(this.date)
        this.date = d || this.date;
        //alert(this.date)
        document.getElementById(this.fName+"_year").value = this.year;
		document.getElementById(this.fName+"_month").selectedIndex = this.month;
		document.getElementById(this.fName+"_date").value = this.date;
		if (this.date>new Date(this.year,this.month+1,0).getDate()) this.date = new Date(this.year,this.month+1,0).getDate();
		document.getElementById(this.fName+"_dateTable").outerHTML = this.drawDate();
		//alert(this.year);
		//alert(this.month);
		//alert(this.date);
		//getDateTime();
	}
	//public
	this.getDate = function(delimiter)
	{
        var s_month,s_date;
        s_month=this.month+1;
        s_date=this.date;
        s_month = ("0"+s_month);
        s_month=s_month.substr(s_month.length-2);
        s_date = ("0"+s_date);
        s_date=s_date.substr(s_date.length-2);
        if (!delimiter) delimiter = "-";
		var aValue = [this.year,s_month,s_date];
		return aValue.join(delimiter);
	}
}
function getDateTime(){

	//alert(c.getDate()+' '+m.getTime());	
	gdCtrl.value = c.getDate()+' '+m.getTime(); 
}
//var gdCtrl = new Object();
function showCal(time){	

	//gdCtrl = popCtrl;
	event.cancelBubble=true;
	//alert(popCtrl);
//	var point = fGetXY(popCtrl);
	//var point = new Point(100,100);
    //alert(gdCtrl.value);
    var gdValue=time;
    var i_year,i_month,i_day;
    if(time!="" && validateDate1(time,'yyyy-MM-dd')){
        i_year=gdValue.substr(0,4);                  
        if(gdValue.substr(5,1)=="0"){
            i_month=parseInt(gdValue.substr(6,1));
        }else{
            i_month=parseInt(gdValue.substr(5,2));
        }
        if(gdValue.substr(8,1)=="0"){
            i_day=parseInt(gdValue.substr(9,1));
        }else{
            i_day=parseInt(gdValue.substr(8,2));
        }
//        i_hour1=gdValue.substr(11,2);
//        i_minute=gdValue.substr(14,2);
//        i_second=gdValue.substr(17,2);
        //alert(i_second);
        //alert(i_hour1+"aaa");
        //alert(i_minute);
        document.getElementById(c.fName+"_year").value = i_year;
        document.getElementById(c.fName+"_month").value= i_month;
		//document.getElementById(c.fName+"_date").value = i_day;          
        c.date=i_day;
//        document.getElementsByName(m.fName)[0].value=i_hour1;
//        document.getElementsByName(m.fName)[1].value=i_minute;
//        document.getElementsByName(m.fName)[2].value=i_second;
        
        c.redrawDate();
    }
//    //c.month=
//    with (dateTime.style) {
//		left = point.x+'px';
//		top  = (point.y+popCtrl.offsetHeight+1)+"px";
//		width = dateTime.offsetWidth+"px";
//		height = dateTime.offsetHeight+"px";
//		//fToggleTags(point);
//		visibility = 'visible';
//	}
//	
	dateTime.focus();
}
function Point(iX, iY){

	this.x = iX;
	this.y = iY;
}
function validateDate1(date,format){

  var time=date;
  if(time=="") return;
  var reg=format;
  var reg=reg.replace(/yyyy/,"[0-9]{4}");
  var reg=reg.replace(/yy/,"[0-9]{2}");
  var reg=reg.replace(/MM/,"((0[1-9])|1[0-2])");
  var reg=reg.replace(/M/,"(([1-9])|1[0-2])");
  var reg=reg.replace(/dd/,"((0[1-9])|([1-2][0-9])|30|31)");
  var reg=reg.replace(/d/,"([1-9]|[1-2][0-9]|30|31))");
//  var reg=reg.replace(/HH/,"(([0-1][0-9])|20|21|22|23)");
//  var reg=reg.replace(/H/,"([0-9]|1[0-9]|20|21|22|23)");
//  var reg=reg.replace(/mm/,"([0-5][0-9])");
//  var reg=reg.replace(/m/,"([0-9]|([1-5][0-9]))");
//  var reg=reg.replace(/ss/,"([0-5][0-9])");
//  var reg=reg.replace(/s/,"([0-9]|([1-5][0-9]))");
  reg=new RegExp("^"+reg+"$");
  if(reg.test(time)==false){//��֤��ʽ�Ƿ�Ϸ�
    //alert(alt);
    //date.focus();
    return false;
  }
  return true;
}
function fGetXY(aTag){	

	var oTmp=aTag;
	var pt = new Point(0,0);
	do {		
		pt.x += oTmp.offsetLeft;
		pt.y += oTmp.offsetTop;
		oTmp = oTmp.offsetParent;
	} while(oTmp.tagName!="BODY");
	return pt;
}
function hideCalendar(){

  dateTime.style.visibility = "hidden";
  /*for (i in goSelectTag)                                        //????????,goSelectTag????
  	goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;*/
}

function save()
{
		//var hiddenTime = document.getElementById("hiddenTime");
		//hiddenTime.value=c.getDate()+' '+m.getTime();
//		dateTime.style.visibility = "hidden";
//	  gdCtrl.value = c.getDate()+' '+m.getTime(); 
	 window.returnValue = c.getDate(); 
	 window.close();
}

function cancle()
{
//		dateTime.style.visibility = "hidden";
//		gdCtrl.value='';
	 window.returnValue="";
     window.close();
}