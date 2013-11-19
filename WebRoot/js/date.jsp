<%@ page contentType="text/html; charset=GBK" %>
<%
   String inputName= request.getParameter("inputName");
   String path= request.getParameter("path");
   if(inputName==null || inputName.trim().length()<1){
     System.out.println("日期输入框元素名称没有传递！");
   }
   
      
   String inputDate = request.getParameter("inputDate");
   String _year = "";
   String _month = "";
   String _date = "";

   if(inputDate!=null && inputDate.trim().length()>8){
           _year = inputDate.substring(0,4);
	   _month = inputDate.substring(5,7);
	   _date = inputDate.substring(8,10); 
   }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>日历</title>
<SCRIPT src="<%=path%>/js/JSCalendar.js"></SCRIPT>

<SCRIPT language="JAVASCRIPT">
 function fillBlank(name){
    document.all["<%=inputName%>"].value = document.form1.tt.value;
 }
</SCRIPT>

</head>

<body >
<form name="form1" method="post" action="" >
   <input  style="display:none" type="text" name="<%=inputName%>">
</form>
<script language="javascript">
             <%
	       if(inputDate==null || inputDate.trim().length()<10){
	     %>
	       JSCalendar(document.all["<%=inputName%>"],"<%=path%>"); 
	     <%
	       }else{
	     %>
                JSCalendar(document.all["<%=inputName%>"],"<%=path%>","<%=_year%>","<%=_month%>","<%=_date%>"); 
             <%
	       }
	     %>
</script>
</body>
</html>
