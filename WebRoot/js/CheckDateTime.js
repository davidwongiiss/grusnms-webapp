
String.prototype.Trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g,"");
}
//***************************************************
// 名称: CheckDateTime 
// 功能; 判断输入的Text是否是合法的日期时间格式；如2004-02-04 20:33/2004-02-04 20:33:22  验证的格式有:yyyy-MM-dd(yyyy-M-d)格式,yyyyMMdd格式 以及 yyyy/MM/dd格式 + HH:mm:ss / H:m:s /HH:mm
// 说明: 可以只有日期部分而没有时间部分
// 参数: 1：控件的对象   
// 返回值：
//        如果控件对象的值为空串，定义校验通过，           返回true
//        如果字串符合，校验通过                            返回true
//        如果字串不符合，                                 返回false    
// 作者: lsj
// 添加时间: 2005-07-11
// 用法:
//      var DateControl = document.getElementById('txtDate') ;
//         if(CheckDateTime(DateControl) == false)  {  return false ;  }    
//****************************************************
function CheckDateTime(dateControlName)
{
     var strDTCheck = eval(dateControlName).value.Trim();
     var strDateString = eval(dateControlName).value.Trim();
     var strDate;
     var strTime;
     var strSp = 0;
     
     if (strDateString.length == 0) //注意      
     {  return true;  }
     
     if (strDateString.length < 19) // 时间格式 YYYY-MM-DD hh:mm:ss   modify by HYL since 2009-05-07
     {  return false;  }
     
     eval(dateControlName).value=strDateString;
          
     //取得分隔符" " 的位置 日期部分和时间部分的分隔符             
     for (var i=0;i<strDateString.length;i++)
     {
        if (strDateString.substr(i,1) == " ")
        {
            strSp=i;
            break;
        }
     }
     //如果没有分隔符" "；日期时间格式错误
     if (strSp == 0){
       alert("输入的日期时间格式不正确");
         return false;
      }
    strDate = strDateString.substr(0,strSp); //取日期部分
    strTime = strDateString.substr(strSp+1,strDateString.length-strSp+1); //取时间部分
    
    var separator = ':';//时间分隔符
    var arrayOfTimes = strTime.split(separator); // HH:mm:ss 两个分隔符    
    if(arrayOfTimes.length == 2 ) 
    { //如果没有秒钟，补上 使之成为 HH:mm:ss 格式
        strTime = strTime   +":00"; 
    }
    if (CheckDate(strDate) == true)//调用子函数CheckDate
    {   //校验日期部分正确 
         // if (CheckTime(strTime) == true)
          if (isTime(strTime) == true) //调用子函数isTime
              {  return true;}
          else
              {  return false;}
    }
    else
    {//校验日期部分不正确 ，返回false
	 alert("输入的日期格式不正确");
          return false;
    }                  
}
  // 添加时间: 2005-07-11
  //短时间，形如 (13:04:06)
  function isTime(str)
      {
        var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
        if (a == null) {alert('输入的时间格式不正确'); return false;}
        if (a[1]>24 || a[3]>60 || a[4]>60)
        {
          alert("时间格式不对");
          return false
        }
        return true;
      }
//***************************************************
// 名称: CheckDate 
// 功能; 判断得到的日期格式是否合法(注：不包括时间部分),
//       验证的格式有:yyyy-MM-dd(yyyy-M-d)格式,yyyyMMdd格式 以及 yyyy/MM/dd格式 
// 调用: 调用子函数CheckyyyyMMdd(dayString) 
// 参数: String SparaDate (1999-01-01)  or  (1999/01/01)         
// 返回值：
//        如果字串日期格式合法，定义校验通过          返回true
//        如果字串日期格式不合法，                     返回false
// 作者:  
// 时间: 2005-06-23
//****************************************************
function CheckDate(SparaDate)
 { 
    //如果是正确的yyyyMMdd格式则校验通过,增加时间2005-06-23 added by linsj
    if (CheckyyyyMMdd(SparaDate) == true)
    {    return true;   }
    
     var strYMDSP = 0; 
     var strYMD;
    
     //判断YYYYMMDD中的分隔符号 不是- 或/报错     
      if (!(SparaDate.substr(4,1)=="-"))
     // { 
      //    if(!(SparaDate.substr(4,1)=="/"))
           {    return false;   }
      // }            
         
     var strYear = SparaDate.substr(0,4);
     SparaDate   = SparaDate.substr(5,SparaDate.length-5);
          
    //去掉年后的字符串   
    for (i=0;i<SparaDate.length;i++)
    {
        if (SparaDate.substr(i,1)=="-") 
        {
           strYMDSP = i;
           break;
        }
    //   if (SparaDate.substr(i,1)=="/") 
    //   {
      //    strYMDSP = i;
       //   break;
       //}
    }         
   //剩下的字符串中没有-或/报错   
    if  (strYMDSP<1)
    {
	  
       return false;
    }
        
   var strMonth = SparaDate.substr(0,strYMDSP);
   var strDay= SparaDate.substr(strYMDSP+1,SparaDate.length-strYMDSP+1);   
   //如果月和日是一位的话补0 ,使之成为yyyyMMdd格式
   if (strMonth.length == 1)
   {   strMonth = "0"+strMonth;        }  //补0             
   if (strDay.length == 1)
   {      strDay = "0"+strDay;         }  //补0 
    //调用CheckyyyyMMdd函数判断yyyyMMdd格式的日期
    if (CheckyyyyMMdd(strYear+strMonth+strDay) == true)
    {    return true;   }
    else
    {  
	 
	return false;   }   

 }  // end  function CheckDate
//***************************************************
// 名称: CheckyyyyMMdd 
// 功能; 判断得到的日期字符串的日期格式是否合法,仅yyyyMMdd格式；   
// 参数: String dayString 如：(19980508 yyyyMMdd格式)
// 时间: 2005-06-23
// 作者: lsj
//****************************************************
function CheckyyyyMMdd(dayString)
{
    //年月日检验函数
    var digit = "0123456789";
    datelist = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
    if (dayString.length !=8) return(false);
    for(i=0;i<8;i++)
    {
        if(digit.indexOf(dayString.charAt(i),0)==-1) return(false);
    }
    year  = dayString.substr(0,4); //截取年部分
    month = dayString.substr(4,2); //截取月部分
    date  = dayString.substr(6,2); //截取日部分
    if (year>3000||year<1000||month>12||month<1||date>31||date<1)  return(false);
    if(date>datelist[month-1]) return(false);

    yyyy = eval(year);
    if ( month == "02" )
    {
        if ( (yyyy % 400) == 0 )
        {
            if ( date>29 )  return(false);
        }
        else if ( (yyyy % 4) == 0 && (yyyy % 100) != 0 )
        {
            if ( date>29 )  return(false);
        }
        else
        {
            if ( date>28 )  return(false);
        }
    }
    return(true);
} // end function CheckyyyyMMdd


//********************** 方法二 **********************
       
      //校验 完全使用时间正则表达式
        //用法：isDateTimeString(document.getElementById('TextBox1').value) ;
       function isDateTimeString(str)
      {
        str = str.Trim();
        if(str.length == 0 ) return true ;
        
        var a = str.match(/^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s((([0-9]|[0-1][0-9]|[2][0-3]))\:([0-9]|[0-5][0-9])(()|(\:([0-9]|[0-5][0-9])))))?$/);
        if (a == null) { return false;}
        if (a[1]>24 || a[3]>60 || a[4]>60)
        {
        alert("时间格式不对");
          return false
        }
        return true;
      }
     
     
     //另外可以参考的函数
     //***************************************************
// 名称: CheckTime 
// 功能; 判断得到的时间格式是否合法；   
// 参数: String (21:21:21)
// 调用 的方法：function CheckHHmmss(time)          
// 作者:  
//****************************************************
/* 
function CheckTime(SparaTime)
{

    var CurrentTimeString;
    var strTimeString;
    var strLength = 0;
    strTimeString = "";
    CurrentTimeString = SparaTime.Trim(); //去掉两边空格
   if (CurrentTimeString.length == 0)
   { //长度为0，即没有输入时间
      strTimeString = "000000"; 
   }
   else
   {    
      strLength = CurrentTimeString.length;
      //如果长度不满8位，报错              
      if (strLength == 8) 
      {            
        //去掉时间间隔符：":"               
         for(var j=0 ;j< CurrentTimeString.length;j++)
         {
            if (CurrentTimeString.substr(j,1) != ":")
            {
                strTimeString = strTimeString+CurrentTimeString.substr(j,1);
            }
         }
       }
       else
       { 
            return false; 
       }                
    }
   return CheckHHmmss (strTimeString);          
}
//***************************************************
// 名称: CheckHHmmss 
// 功能; 判断得到的时间格式是否合法；   
// 参数: String 如：(222222)
// 时间: 2005-07-11
// 作者: 
//****************************************************
 
function CheckHHmmss (time)
{
//时间校验函数
    var digit="0123456789";
    if (time.length !=6) return(false);
    for(i=0;i<6;i++)
    {
        if(digit.indexOf(time.charAt(i),0)==-1) return(false);
    }
    hh=time.substr(0,2);
    mm=time.substr(2,2);
    ss=time.substr(4,2);
    if (hh>24||hh<0||mm>60||mm<0||ss>60||ss<0)  return(false);
    return(true);
}
 */
function CheckDT(textDate){
 var DateControl = document.getElementById(textDate);
 if(DateControl != ""){
 if(CheckDateTime(DateControl) == false) {
 
   return false;
  }
}
  }
function CheckD(textDate){

 var DateControl = document.getElementById(textDate).value;
 DateControl = DateControl.replace(/(^\s*)|(\s*$)/g, "");
 if(DateControl != ""){
 if(CheckDate(DateControl) == false) {
  alert("输入的日期格式不正确");
   return false;
  } 
}
}


