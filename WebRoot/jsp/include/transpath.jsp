<!-- saved from url=(0022)http://internet.e-mail -->
<%@ page contentType="text/html; charset=gb2312" %>
<% 
 request.setCharacterEncoding("gb2312"); //设置输入编码格式
 response.setContentType("text/html;charset=gb2312"); //设置输出编码格式
 String s=new  String(request.getQueryString().getBytes("ISO-8859-1"),"GB2312");
 String title=" ";
 if(request.getParameter("v_title")!=null){
   title=request.getParameter("v_title");
   //title=new  String(request.getParameter("v_title").getBytes("ISO-8859-1"),"GB2312");
 }
 %>
 <html>
<head>
<title><%= title %></title>
<SCRIPT type="text/javascript">
 // 动态改变窗口大小
 function doResizeWindow(w,h){
   try{
     // must end with "px"
     window.dialogWidth=w;
     window.dialogHeight=h;
     //window.resizeTo(w,h); 
   }catch(e){
      
   }
 }
 // 获取窗口宽度
 function doGetDialogWidth(){
   try{
     return window.dialogWidth;
   }catch(e){}
 }
 // 获取窗口高度
 function doGetDialogHeight(){
   try{
     return window.dialogHeight;
   }catch(e){}
 }
</SCRIPT>
<body >
<iframe id="nested" src="<%=request.getParameter("transpath")%>?<%= s %>" width="100%" height="100%" scrollbar="no" frameborder="0"></iframe>
</body>
</html>

