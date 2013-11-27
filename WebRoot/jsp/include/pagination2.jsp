<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.device.util.Pagination,java.net.*,java.util.Enumeration"%>
<%

  if(request.getAttribute("pagination")==null )
   return;
  Pagination pagination=(Pagination)request.getAttribute("pagination");
  String baseurl=request.getParameter("baseurl");
  if(baseurl.indexOf("?") > 0 )  baseurl+="&";
  else baseurl+="?";
  Enumeration xenum=request.getParameterNames();
  while(xenum.hasMoreElements()){
     String name=(String)xenum.nextElement();
     if(name.equals("pagination.pageNO")||name.equals("baseurl")||name.equals("submit")) continue;
     String value=URLEncoder.encode(request.getParameter( name));
     baseurl+=name+"="+value+"&";
  }
  baseurl=baseurl+"pagination.pageNO=";
%>
<div class="page_w">
<div class="page_font">总共15条记录</div>
<div class="page_r">
<ul>
<li>
<div class="select68">
<select name="select" class="select68">
    <option>第1页</option>
    <option>第2页</option>
    <option>第3页</option>
  </select>
  </div>
</li>
<li>每页30条,共1页</li>  
<li><a href="#" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><ins>末页</ins><img src="<%=request.getContextPath() %>/images/page/page_ico4.gif"/></span></a></li>
<li><a href="#" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><ins>下一页</ins><img src="<%=request.getContextPath() %>/images/page/page_ico3.gif"/></span></a></li>
<li><a href="#" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><img src="<%=request.getContextPath() %>/images/page/page_ico2.gif"/><ins>上一页</ins></span></a></li>
<li><a href="#" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><img src="<%=request.getContextPath() %>/images/page/page_ico1.gif"/><ins>首页</ins></span></a></li>
</ul>
</div>
</div>

<!--con end-->
</div>
</div>


<script>
<!--
var baseurl='<%=baseurl%>';
    function goPage(pageNO){
      if(pageNO>0&&pageNO<=<%=pagination.getPageSum()%>&&pageNO!=<%=pagination.getPageNO()%>){
				self.location=baseurl+pageNO;
			}
    }

    function go(pageform){
    	pageNO=pageform.pageNO.value;
      if(pageNO>0&&pageNO<=<%=pagination.getPageSum()%>&&pageNO!=<%=pagination.getPageNO()%>){
				self.location=baseurl+pageNO;
			}
    	return false;
    }
-->
</script>
