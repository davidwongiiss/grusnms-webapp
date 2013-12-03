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
	<div class="page_font">
		�ܹ�<%=pagination.getRecordSum()%>����¼
	</div>
	<div class="page_r">
		<ul>
			<li>
				ÿҳ<%= pagination.getPageCount() %>��,��<%=pagination.getPageSum()%>ҳ
			</li>
			<li>
				<a href="javascript:goPage(1)" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><img	src="<%= request.getContextPath() %>/images/page/page_ico1.gif" />
						<ins>
							��ҳ
						</ins>
				</span>
				</a>
			</li>
			<li>
			<li>
			<%
 				if (pagination.getPageNO() > 1 && pagination.getPageNO() <= pagination.getPageSum()) {
 			%>
				<a href="javascript:goPage(<%=pagination.getPrePageNO()%>)" class="page_btn"
					onMouseDown="this.className='page_btn_d'"><span><img src="<%= request.getContextPath() %>/images/page/page_ico2.gif" /><ins>��һҳ</ins>
				</span>
				</a>
			<%
		 	} else {
		 	%> 
		 	<a href="javascript:void(0);" class="page_btn"
					onMouseDown="this.className='page_btn_d'">
		 		<span><img src="<%= request.getContextPath() %>/images/page/page_ico2.gif" /><ins>��һҳ</ins></span>
		 	</a>	
		 	 <%
		 	}%>
			</li>
			<li>
				 <%
				 	if (pagination.getNextPageNO() < pagination.getPageSum()
				 			&& pagination.getPageNO() < pagination.getPageSum()) {
				 %> 
				<a href="javascript:goPage(<%=pagination.getNextPageNO()%>)" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><ins>��һҳ</ins>
						<img src="<%= request.getContextPath() %>/images/page/page_ico3.gif" />
				</span>
				</a>
				<%}else{ %>
				<a href="javascript:void(0);" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><ins>��һҳ</ins>
						<img src="<%= request.getContextPath() %>/images/page/page_ico3.gif" />
				</span>
				</a>
				<%} %>
			</li>
			<li>
				<a href="javascript:goPage(<%=pagination.getPageSum()%>)" class="page_btn" onMouseDown="this.className='page_btn_d'"><span><ins>ĩҳ</ins>
						<img src="<%= request.getContextPath() %>/images/page/page_ico4.gif" />
				</span>
				</a>
			</li>
			<span>
				<form name="pageform" style="margin: 0px" onsubmit="javascript:return go(this)"><input type=text
					name="pageNO" value="<%=pagination.getNextPageNO()%>" size=3> <input type=submit value="go">
				</form>
			</span>			
			</li>
		</ul>
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
