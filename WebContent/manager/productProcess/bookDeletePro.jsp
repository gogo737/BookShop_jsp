<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "master.ShopBookDBBean" %>

<% request.setCharacterEncoding("utf-8");%>

<%
  int book_id = Integer.parseInt(request.getParameter("book_id"));
  String book_kind = request.getParameter("book_kind");
 
  ShopBookDBBean bookProcess = ShopBookDBBean.getInstance();
  bookProcess.deleteBook(book_id); 

  response.sendRedirect("bookList.jsp?book_kind="+book_kind);
%>