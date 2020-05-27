<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "master.ShopBookDBBean" %>
<%@ page import = "master.ShopBookDataBean" %>
<%@ page import = "java.text.NumberFormat" %>

<%@ include file="../etc/color.jspf"%> 

<html>
<head>
<title>Book Shopping Mall</title>
<link href="../etc/style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<h3>신간소개</h3>
<%
  ShopBookDataBean bookLists[] = null;
  int number =0;
  String book_kindName="";
  
  ShopBookDBBean bookProcess = ShopBookDBBean.getInstance();
  for(int i=1; i<=3;i++){
	  bookLists = bookProcess.getBooks(i+"00",3);
     
     if(bookLists[0].getBook_kind().equals("100")){
	      book_kindName="문학";
     }else if(bookLists[0].getBook_kind().equals("200")){
	      book_kindName="외국어";  
     }else if(bookLists[0].getBook_kind().equals("300")){
	      book_kindName="컴퓨터";
     }
%>
  <br><font size="-1"><b><%=book_kindName%> 분류의 신간목록: 
  <a href="list.jsp?book_kind=<%=bookLists[0].getBook_kind()%>">더보기</a>
  </b></font>
<%             
  //for(int j=0;j<bookLists.length;j++){
	for(int j=0;j<1;j++){
%>


    <table style= "border-right:none; border-left:none; margin-top:10; margin-left:100; margin-bottom:20;"> 
      <tr height="30" bgcolor="<%=value_c%>"> 
        <td rowspan="4"  width="100">
          <a href="bookContent.jsp?book_id=<%=bookLists[j].getBook_id()%>&book_kind=<%=bookLists[0].getBook_kind()%>">
             <img src="../imageFile/<%=bookLists[j].getBook_image()%>"  
             border="0" width="60" height="90"></a></td> 
       <td width="350"><font size="+1"><b>
          <a href="bookContent.jsp?book_id=<%=bookLists[j].getBook_id()%>&book_kind=<%=bookLists[0].getBook_kind()%>">
              <%=bookLists[j].getBook_title() %></a></b></font></td> 
       <td rowspan="4" width="100">
          <%if(bookLists[j].getBook_count()==0){ %>
              <b>일시품절</b>
          <%}else{ %>
               &nbsp;
          <%} %>
       </td>
     </tr>        
     <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">출판사 : <%=bookLists[j].getPublishing_com()%></td> 
     </tr>
     <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">저자 : <%=bookLists[j].getAuthor()%></td>
     </tr>
     <tr height="30" bgcolor="<%=value_c%>"> 
       <td width="350">정가 :<%=NumberFormat.getInstance().format(bookLists[j].getBook_price())%>원<br>
                판매가 : <b><font color="red">
       <%=NumberFormat.getInstance().format((int)(bookLists[j].getBook_price()*((double)(100-bookLists[j].getDiscount_rate())/100)))%>
            </font></b>원</td> 
     </tr> 
     </table>
     <br>
<%
  }
}
%>

</body>
</html>