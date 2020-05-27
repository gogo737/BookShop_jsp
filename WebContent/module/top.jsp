<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../etc/color.jspf"%> 
<html>
<head>
<link href="../etc/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<%
 try{
   if(session.getAttribute("id")==null){%>
   <ul class="list-group list-group-horizontal-sm">
   <li class="list-group-item list-group-item-action list-group-item-warning"><a href="../shopping/list.jsp?book_kind=all">전체목록</a>&nbsp;
   </ul>
   <br>
      
   <form name="inform" method="post" action="loginPro.jsp">
         아이디: <input type="text" name="id" size="15" maxlength="50">&nbsp;&nbsp;&nbsp;&nbsp;
         비밀번호:  <input type="password" name="passwd" size="15" maxlength="16">
    <input type="submit" name="Submit" class="btn btn-Warning" value="로그인">
   </form>
   <font color="red">* 반드시 로그인을 하셔야 쇼핑을 하실 수 있습니다.*</font>    
 <%}else{%>
 <ul class="list-group list-group-horizontal-sm">
    <li class="list-group-item list-group-item-action list-group-item-warning"><a href="../shopping/list.jsp?book_kind=all">전체목록</a>&nbsp;
    <li class="list-group-item list-group-item-action list-group-item-info"><a href="../shopping/cartList.jsp?book_kind=all">장바구니</a>&nbsp;
    <li class="list-group-item list-group-item-action list-group-item-danger"><a href="../shopping/buyList.jsp">구매목록</a>&nbsp;
  </ul>
  
    <br><br> 
    <b><%=session.getAttribute("id")%></b> 님 , 즐거운 쇼핑시간이 되세요.
       
    <input type="button"  value="로그아웃" class="btn btn-Warning"
        onclick="javascript:window.location='../shopping/logout.jsp'">
     
     
 <%}
 }catch(NullPointerException e){
	 e.printStackTrace();
 }
 %>
</body>
</html>