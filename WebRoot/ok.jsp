<%@ page contentType="text/html; charset=GBK" %>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%
	String forwardUrl=null;
	forwardUrl=request.getParameter("forwardUrl");
	if(forwardUrl==null)
		forwardUrl=(String)request.getAttribute("forwardUrl");
	if(forwardUrl==null)
		forwardUrl=(String)ActionContext.getContext().getValueStack().findValue("forwardUrl");
	
	String msg=null;
	msg=request.getParameter("msg");
	if(msg==null)
		msg=(String)request.getAttribute("msg");
	if(msg==null)
		msg=(String)ActionContext.getContext().getValueStack().findValue("msg");
	if(msg==null)
		msg="您的操作成功！";
	
	String params="";
	String target="";
	if(forwardUrl!=null){
		params=request.getParameter("params");
		if(params==null)
			params=""+request.getAttribute("params");
		if(params==null||"null".equals(params))
			params=(String)ActionContext.getContext().getValueStack().findValue("params");
		if(params==null||"null".equals(params))
			params="";
			
		target=request.getParameter("target");
		if(target==null)
			target=(String)request.getAttribute("target");
		if(target==null)
			target=ActionContext.getContext().getValueStack().findString("target");
		if(target==null)
			target="_self";
	}
	else{
		forwardUrl="";
	}
%>
<html>
<head>
<base target=_self>
<title>HollyV8客户服务系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="javascript">
var shuted=false;
window.returnValue="ok";
setTimeout("shutup(0);",1000);


function shutup(isUnload){
  if(isUnload==null) isUnload=0;
  try{
       
	if(shuted)
		return;
	shuted=true;
	var forwardUrl="<%=forwardUrl%>";
	var params="<%=params%>";
	if(forwardUrl){
		if(forwardUrl.indexOf("?")!=-1){
			if(params.indexOf("&")==0)
				document.hiddenForm.action=forwardUrl+params;
			else
				document.hiddenForm.action=forwardUrl+"&"+params;
		}
		else{
			if(params.indexOf("&")==0)
			
				document.hiddenForm.action=forwardUrl+"?"+params.substring(1);
			else
				document.hiddenForm.action=forwardUrl+"?"+params;
		}
		document.hiddenForm.method="post";
		document.hiddenForm.target="<%=target%>";
		document.hiddenForm.submit();
	}
	else
		refreshParent();
	if("<%=target%>"!="_self" && isUnload==0){
		window.returnValue="ok";
		shutdownWindow();
	}
   }catch(e){}	
}
	
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000"  onunload="shutup(1)">
<form name="hiddenForm" action="">
<span id="v_close_tag_id" >&nbsp;</span>
</form>
<table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
  <tr>
    <td align="center">
      <table width="80%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/9-25b.gif" valign="top" align="left"><img src="<%=request.getContextPath()%>/framework/images/9-25a.gif" width="5" height="5"></td>
          <td width="98%" background="<%=request.getContextPath()%>/images/9-25b.gif" valign="top" align="left"></td>
          <td valign="top" align="right" background="<%=request.getContextPath()%>/images/9-25b.gif" width="1%"><img src="<%=request.getContextPath()%>/images/9-25c.gif" width="7" height="5"></td>
        </tr>
        <tr>
          <td height="56" background="<%=request.getContextPath()%>/images/9-25d.gif" width="1%" valign="top" align="left">&nbsp;</td>
          <td width="98%" height="70" align="center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="right" width="19%"><img src="<%=request.getContextPath()%>/images/9-25m.gif" width="42" height="42"></td>
                <td width="81%" align="center"><font color="blue"><%=msg%></font></td>
              </tr>
            </table>
          </td>
          <td valign="top" align="right" background="<%=request.getContextPath()%>/images/9-25g.gif" height="56" width="1%"><img src="<%=request.getContextPath()%>/images/9-25g.gif" width="7" height="3"></td>
        </tr>
        <tr>
          <td background="<%=request.getContextPath()%>/images/9-25f.gif" height="2" width="1%" valign="top" align="left"></td>
          <td background="<%=request.getContextPath()%>/images/9-25h.gif" width="98%" valign="top" align="left"><img src="<%=request.getContextPath()%>/images/9-25h.gif" width="3" height="2"></td>
          <td background="<%=request.getContextPath()%>/images/9-25l.gif" width="1%" valign="top" align="left"></td>
        </tr>
        <tr>
          <td height="50" background="<%=request.getContextPath()%>/images/9-25f.gif" width="1%" valign="top" align="left"><img src="<%=request.getContextPath()%>/images/9-25f.gif" width="8" height="4"></td>
          <td width="98%" bgcolor="#D9E6F4" height="60" align="center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center" class="a2"><img src="<%=request.getContextPath()%>/images/9-25o.gif" width="42" height="13" border="0" style="cursor:hand" alt="关闭" onclick="shutup(0);">　　</td>
              </tr>
            </table>
          </td>
          <td valign="top" align="right" background="<%=request.getContextPath()%>/images/9-25l.gif" height="50" width="1%"><img src="<%=request.getContextPath()%>/images/9-25l.gif" width="7" height="7"></td>
        </tr>
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/9-25j.gif" valign="top" align="left"><img src="<%=request.getContextPath()%>/images/9-25i.gif" width="5" height="7"></td>
          <td width="98%" background="<%=request.getContextPath()%>/images/9-25j.gif" valign="top" align="left"></td>
          <td valign="top" align="right" background="<%=request.getContextPath()%>/images/9-25j.gif" width="1%"><img src="<%=request.getContextPath()%>/images/9-25k.gif" width="7" height="7"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
