<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "master.ShopBookDBBean" %>
<%@ page import = "master.ShopBookDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.NumberFormat" %>

<%@ include file="../etc/color.jspf"%> 

<%
  String book_kind = request.getParameter("book_kind");
%>

<html>
<head>
<title>Book Shopping Mall</title>
<link href="../etc/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
</head>
<body bgcolor="<%=bodyback_c%>">
<table style="margin-left: auto; margin-right: auto; border:#FFF;">
<tr>
<td width="150" valign="top">
  <jsp:include page="../module/left.jsp" flush="false" />
</td>
 <td width="700">
        
<%
 List<ShopBookDataBean> bookLists = null;
 ShopBookDataBean bookList = null;
 String book_kindName="";
  
 ShopBookDBBean bookProcess = ShopBookDBBean.getInstance();
 
 bookLists = bookProcess.getBooks(book_kind);
 if(book_kind.equals("100"))
	      book_kindName="문학";
 else if(book_kind.equals("200"))
	      book_kindName="외국어";  
 else if(book_kind.equals("300"))
	      book_kindName="컴퓨터";
 else if(book_kind.equals("all"))
	      book_kindName="전체";
%>
 <h3><b><%=book_kindName%> 분류의 목록</b></h3>
 <ul class="list-group list-group-horizontal-sm">
 <li class="list-group-item list-group-item-action list-group-item-warning"><a href="shopMain.jsp">메인으로</a>
 </ul>
<%
 for(int i=0;i<bookLists.size();i++){
    bookList = (ShopBookDataBean)bookLists.get(i);
%>
  <table style="margin-left: auto; margin-right: auto; border-right:none; border-left:none;"> 
    <tr height="30" bgcolor="<%=value_c%>"> 
      <td rowspan="4"  width="100">
         <a href="bookContent.jsp?book_id=<%=bookList.getBook_id()%>&book_kind=<%=book_kind%>">
         <img src="../imageFile/<%=bookList.getBook_image()%>" border="0" width="60" height="90"></a></td> 
      <td width="350"><font size="+1"><b>
         <a href="bookContent.jsp?book_id=<%=bookList.getBook_id()%>&book_kind=<%=book_kind%>">
         <%=bookList.getBook_title() %></a></b></font></td> 
      <td rowspan="4" width="100"  align="center"  valign="middle">
        <%if(bookList.getBook_count()==0){ %>
              <b>일시품절</b>
        <%}else{ %>
              &nbsp;
        <%} %>
      </td>
    </tr>        
    <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">출판사 : <%=bookList.getPublishing_com()%></td> 
    </tr>
    <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">저자 : <%=bookList.getAuthor()%></td>
    </tr>
    <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">정가 : <%=NumberFormat.getInstance().format(bookList.getBook_price())%><br>
                     판매가 : <b><font color="red">
         <%=NumberFormat.getInstance().format((int)(bookList.getBook_price()*((double)(100-bookList.getDiscount_rate())/100)))%>
         </font></b></td> 
    </tr> 
  </table>
  <br>
<%
 } 
%>
</td>
</tr>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.js"></script>
</body>
</html>