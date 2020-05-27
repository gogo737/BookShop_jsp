<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "shopping.CartDBBean" %>

<%@ include file="../etc/color.jspf"%> 

<%
 String cart_id = request.getParameter("cart_id");
 String buy_count = request.getParameter("buy_count");
 String book_kind = request.getParameter("book_kind");
 
 if(session.getAttribute("id")==null){
	response.sendRedirect("shopMain.jsp");        
 }else{
	CartDBBean bookProcess = CartDBBean.getInstance();
    bookProcess.updateCount(Integer.parseInt(cart_id), Byte.parseByte(buy_count));
    response.sendRedirect("cartList.jsp?book_kind=" + book_kind);
 }
%>