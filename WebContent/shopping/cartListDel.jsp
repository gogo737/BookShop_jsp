<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "shopping.CartDBBean" %>

<%@ include file="../etc/color.jspf"%> 

<%
 String list = request.getParameter("list");
 String buyer = (String)session.getAttribute("id");
 String book_kind = request.getParameter("book_kind");
   
 if(session.getAttribute("id")==null){
	response.sendRedirect("shopMain.jsp");        
 }else{
	CartDBBean bookProcess = CartDBBean.getInstance();
	   
	if(list.equals("all"))
	   bookProcess.deleteAll(buyer);
	else
	   bookProcess.deleteList(Integer.parseInt(list));
	
	response.sendRedirect("cartList.jsp?book_kind=" + book_kind);
 }
%>