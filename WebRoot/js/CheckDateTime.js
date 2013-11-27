
String.prototype.Trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g,"");
}
//***************************************************
// ����: CheckDateTime 
// ����; �ж������Text�Ƿ��ǺϷ�������ʱ���ʽ����2004-02-04 20:33/2004-02-04 20:33:22  ��֤�ĸ�ʽ��:yyyy-MM-dd(yyyy-M-d)��ʽ,yyyyMMdd��ʽ �Լ� yyyy/MM/dd��ʽ + HH:mm:ss / H:m:s /HH:mm
// ˵��: ����ֻ�����ڲ��ֶ�û��ʱ�䲿��
// ����: 1���ؼ��Ķ���   
// ����ֵ��
//        ����ؼ������ֵΪ�մ�������У��ͨ����           ����true
//        ����ִ����ϣ�У��ͨ��                            ����true
//        ����ִ������ϣ�                                 ����false    
// ����: lsj
// ���ʱ��: 2005-07-11
// �÷�:
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
     
     if (strDateString.length == 0) //ע��      
     {  return true;  }
     
     if (strDateString.length < 19) // ʱ���ʽ YYYY-MM-DD hh:mm:ss   modify by HYL since 2009-05-07
     {  return false;  }
     
     eval(dateControlName).value=strDateString;
          
     //ȡ�÷ָ���" " ��λ�� ���ڲ��ֺ�ʱ�䲿�ֵķָ���             
     for (var i=0;i<strDateString.length;i++)
     {
        if (strDateString.substr(i,1) == " ")
        {
            strSp=i;
            break;
        }
     }
     //���û�зָ���" "������ʱ���ʽ����
     if (strSp == 0){
       alert("���������ʱ���ʽ����ȷ");
         return false;
      }
    strDate = strDateString.substr(0,strSp); //ȡ���ڲ���
    strTime = strDateString.substr(strSp+1,strDateString.length-strSp+1); //ȡʱ�䲿��
    
    var separator = ':';//ʱ��ָ���
    var arrayOfTimes = strTime.split(separator); // HH:mm:ss �����ָ���    
    if(arrayOfTimes.length == 2 ) 
    { //���û�����ӣ����� ʹ֮��Ϊ HH:mm:ss ��ʽ
        strTime = strTime   +":00"; 
    }
    if (CheckDate(strDate) == true)//�����Ӻ���CheckDate
    {   //У�����ڲ�����ȷ 
         // if (CheckTime(strTime) == true)
          if (isTime(strTime) == true) //�����Ӻ���isTime
              {  return true;}
          else
              {  return false;}
    }
    else
    {//У�����ڲ��ֲ���ȷ ������false
	 alert("��������ڸ�ʽ����ȷ");
          return false;
    }                  
}
  // ���ʱ��: 2005-07-11
  //��ʱ�䣬���� (13:04:06)
  function isTime(str)
      {
        var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
        if (a == null) {alert('�����ʱ���ʽ����ȷ'); return false;}
        if (a[1]>24 || a[3]>60 || a[4]>60)
        {
          alert("ʱ���ʽ����");
          return false
        }
        return true;
      }
//***************************************************
// ����: CheckDate 
// ����; �жϵõ������ڸ�ʽ�Ƿ�Ϸ�(ע��������ʱ�䲿��),
//       ��֤�ĸ�ʽ��:yyyy-MM-dd(yyyy-M-d)��ʽ,yyyyMMdd��ʽ �Լ� yyyy/MM/dd��ʽ 
// ����: �����Ӻ���CheckyyyyMMdd(dayString) 
// ����: String SparaDate (1999-01-01)  or  (1999/01/01)         
// ����ֵ��
//        ����ִ����ڸ�ʽ�Ϸ�������У��ͨ��          ����true
//        ����ִ����ڸ�ʽ���Ϸ���                     ����false
// ����:  
// ʱ��: 2005-06-23
//****************************************************
function CheckDate(SparaDate)
 { 
    //�������ȷ��yyyyMMdd��ʽ��У��ͨ��,����ʱ��2005-06-23 added by linsj
    if (CheckyyyyMMdd(SparaDate) == true)
    {    return true;   }
    
     var strYMDSP = 0; 
     var strYMD;
    
     //�ж�YYYYMMDD�еķָ����� ����- ��/����     
      if (!(SparaDate.substr(4,1)=="-"))
     // { 
      //    if(!(SparaDate.substr(4,1)=="/"))
           {    return false;   }
      // }            
         
     var strYear = SparaDate.substr(0,4);
     SparaDate   = SparaDate.substr(5,SparaDate.length-5);
          
    //ȥ�������ַ���   
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
   //ʣ�µ��ַ�����û��-��/����   
    if  (strYMDSP<1)
    {
	  
       return false;
    }
        
   var strMonth = SparaDate.substr(0,strYMDSP);
   var strDay= SparaDate.substr(strYMDSP+1,SparaDate.length-strYMDSP+1);   
   //����º�����һλ�Ļ���0 ,ʹ֮��ΪyyyyMMdd��ʽ
   if (strMonth.length == 1)
   {   strMonth = "0"+strMonth;        }  //��0             
   if (strDay.length == 1)
   {      strDay = "0"+strDay;         }  //��0 
    //����CheckyyyyMMdd�����ж�yyyyMMdd��ʽ������
    if (CheckyyyyMMdd(strYear+strMonth+strDay) == true)
    {    return true;   }
    else
    {  
	 
	return false;   }   

 }  // end  function CheckDate
//***************************************************
// ����: CheckyyyyMMdd 
// ����; �жϵõ��������ַ��������ڸ�ʽ�Ƿ�Ϸ�,��yyyyMMdd��ʽ��   
// ����: String dayString �磺(19980508 yyyyMMdd��ʽ)
// ʱ��: 2005-06-23
// ����: lsj
//****************************************************
function CheckyyyyMMdd(dayString)
{
    //�����ռ��麯��
    var digit = "0123456789";
    datelist = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
    if (dayString.length !=8) return(false);
    for(i=0;i<8;i++)
    {
        if(digit.indexOf(dayString.charAt(i),0)==-1) return(false);
    }
    year  = dayString.substr(0,4); //��ȡ�겿��
    month = dayString.substr(4,2); //��ȡ�²���
    date  = dayString.substr(6,2); //��ȡ�ղ���
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


//********************** ������ **********************
       
      //У�� ��ȫʹ��ʱ��������ʽ
        //�÷���isDateTimeString(document.getElementById('TextBox1').value) ;
       function isDateTimeString(str)
      {
        str = str.Trim();
        if(str.length == 0 ) return true ;
        
        var a = str.match(/^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s((([0-9]|[0-1][0-9]|[2][0-3]))\:([0-9]|[0-5][0-9])(()|(\:([0-9]|[0-5][0-9])))))?$/);
        if (a == null) { return false;}
        if (a[1]>24 || a[3]>60 || a[4]>60)
        {
        alert("ʱ���ʽ����");
          return false
        }
        return true;
      }
     
     
     //������Բο��ĺ���
     //***************************************************
// ����: CheckTime 
// ����; �жϵõ���ʱ���ʽ�Ƿ�Ϸ���   
// ����: String (21:21:21)
// ���� �ķ�����function CheckHHmmss(time)          
// ����:  
//****************************************************
/* 
function CheckTime(SparaTime)
{

    var CurrentTimeString;
    var strTimeString;
    var strLength = 0;
    strTimeString = "";
    CurrentTimeString = SparaTime.Trim(); //ȥ�����߿ո�
   if (CurrentTimeString.length == 0)
   { //����Ϊ0����û������ʱ��
      strTimeString = "000000"; 
   }
   else
   {    
      strLength = CurrentTimeString.length;
      //������Ȳ���8λ������              
      if (strLength == 8) 
      {            
        //ȥ��ʱ��������":"               
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
// ����: CheckHHmmss 
// ����; �жϵõ���ʱ���ʽ�Ƿ�Ϸ���   
// ����: String �磺(222222)
// ʱ��: 2005-07-11
// ����: 
//****************************************************
 
function CheckHHmmss (time)
{
//ʱ��У�麯��
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
  alert("��������ڸ�ʽ����ȷ");
   return false;
  } 
}
}


