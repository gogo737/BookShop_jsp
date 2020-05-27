<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.Timestamp" %>

<%@ include file="../../etc/color.jspf"%> 

<%
   String managerId ="";
   try{
	   managerId = (String)session.getAttribute("managerId");
	   if(managerId==null || managerId.equals("")){
           response.sendRedirect("../logon/managerLoginForm.jsp");
	   }else{
%>
<html>
<head>
<title>상품등록</title>
<link href="../../etc/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../etc/script.js"></script>
</head>
<body bgcolor="<%=bodyback_c%>">
<p>책 등록</p>
<br>

<form method="post" name="writeform" 
     action="bookRegisterPro.jsp"  enctype="multipart/form-data">
<table style="margin-left: auto; margin-right: auto;">
   <tr>
    <td align="right" colspan="2" bgcolor="<%=value_c%>">
	    <a href="../managerMain.jsp"> 관리자 메인으로</a> 
   </td>
   </tr>
   <tr>
    <td  width="100"  bgcolor="<%=value_c%>">분류 선택</td>
    <td  width="400" align="left">
       <select name="book_kind">
           <option value="100">문학</option>
           <option value="200">외국어</option>
           <option value="300">컴퓨터</option>
       </select>
    </td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">제 목</td>
    <td  width="400" align="left">
        <input type="text" size="50" maxlength="50" name="book_title"></td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">가 격</td>
    <td  width="400" align="left">
        <input type="text" size="10" maxlength="9" name="book_price">원</td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">수량</td>
    <td  width="400" align="left">
        <input type="text" size="10" maxlength="5" name="book_count">권</td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">저자</td>
    <td  width="400" align="left">
        <input type="text" size="20" maxlength="30" name="author"></td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">출판사</td>
    <td  width="400" align="left">
        <input type="text" size="20" maxlength="30" name="publishing_com"></td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">출판일</td>
    <td  width="400" align="left">
        <select name="publishing_year">
        <%
        Timestamp nowTime  = new Timestamp(System.currentTimeMillis());
        int lastYear = Integer.parseInt(nowTime.toString().substring(0,4));
           for(int i=lastYear;i>=2010;i--){
        %>
             <option value="<%=i %>"><%=i %></option>
        <%} %>
        </select>년
        
        <select name="publishing_month">
        <%
           for(int i=1;i<=12;i++){
        %>
             <option value="<%=i %>"><%=i %></option>
        <%} %>
        </select>월
        
        <select name="publishing_day">
        <%
           for(int i=1;i<=31;i++){
        %>
             <option value="<%=i %>"><%=i %></option>
        <%} %>
        </select>일
     </td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">이미지</td>
    <td  width="400" align="left">
        <input type="file" name="book_image"></td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>">내 용</td>
    <td  width="400" align="left">
     <textarea name="book_content" rows="13" cols="40"></textarea> </td>
  </tr>
 <tr>
    <td  width="100"  bgcolor="<%=value_c%>">할인율</td>
    <td  width="400" align="left">
        <input type="text" size="5" maxlength="2" name="discount_rate">%</td>
  </tr>
<tr>      
 <td colspan=2 bgcolor="<%=value_c%>" align="center"> 
  <input type="button" value="책등록" onclick="checkForm(this.form)">  
  <input type="reset" value="다시작성">
</td></tr></table>         
</form>      
</body>
</html>
<% 
	}
}catch(Exception e){
	e.printStackTrace();
}
%>