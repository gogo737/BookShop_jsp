<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../etc/color.jspf"%> 

<%
String managerId ="";
try{
	managerId = (String)session.getAttribute("managerId");
	if(managerId==null || managerId.equals("")){
       response.sendRedirect("../logon/managerLoginForm.jsp");
	}else{
       int book_id = Integer.parseInt(request.getParameter("book_id"));
       String book_kind = request.getParameter("book_kind");
%>

<html>
<head>
<title>책삭제</title>
<link href="../../etc/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<p>책삭제</p>
<br>
<form method="POST" name="delForm"  
   action="bookDeletePro.jsp?book_id=<%= book_id%>&book_kind=<%=book_kind%>" 
   onsubmit="return deleteSave()"> 
 <table style="margin: 0 auto;">
 <tr>
     <td align="right"  bgcolor="<%=value_c%>">
	    <a href="../managerMain.jsp"> 관리자 메인으로</a> &nbsp;
	    <a href="bookList.jsp?book_kind=<%=book_kind%>">목록으로</a>
     </td>
  </tr>
  
 <tr height="30">
    <td align=center bgcolor="<%=value_c%>">
      <input type="submit" value="삭제" >  
   </td>
 </tr>  
</table> 
</form>
</body>
</html>
<% 
   }
}catch(Exception e){
	e.printStackTrace();
}
%>