<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../etc/color.jspf"%>

<%
   String managerId ="";
   try{
	   managerId = (String)session.getAttribute("managerId");
	   
	   if(managerId==null || managerId.equals("")){
          response.sendRedirect("logon/managerLoginForm.jsp");
	   }else{
%>
<html>
<head>
<title>책관리</title>
</head>
<body>
  <form method="post" action="logon/managerLogout.jsp" >
  <table style="margin:0 auto;  text-align:center;">
  <tr>
  <td><b>관리작업중.. </b><input type="submit" value="로그아웃"></td>
  </tr>
  </table>
  </form>
    
   <table style="margin:0 auto;  text-align:center;">
     <tr><td align="center" bgcolor="<%=bar%>">상품관련 작업</td></tr>
     <tr><td>
     <a href='productProcess/bookRegisterForm.jsp'>상품등록</a></td></tr>
     <tr><td>
     <a href='productProcess/bookList.jsp?book_kind=all'>상품수정/삭제</a>
     </td></tr>
   </table><br><br>
   
   <table style="margin:0 auto; text-align:center;">
     <tr><td align="center" bgcolor="<%=bar%>">구매된 상품관련 작업</td></tr>
     <tr><td><a href='orderedProduct/orderedList.jsp'>전체구매목록 확인</a></td></tr>
   </table>
</body>
</html>
<% 
	   }
    }catch(Exception e){
		e.printStackTrace();
	}
%>