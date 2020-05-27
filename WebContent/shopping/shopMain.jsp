<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../etc/color.jspf"%> 

<html>
<head>
<meta charset="UTF-8">
<title>Book Shopping Mall</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
<link href="../etc/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table style="margin: 0 auto; border:#FFF;">
<tr>
   <td width="150" valign="top">
	    <img src="../module/logo.png" border="0" width="150" height="120">
	</td>
    <td>
        <jsp:include page="../module/top.jsp" flush="false" /> 
     </td>   
</tr>
<tr>
    <td width="150" valign="top">
        <jsp:include page="../module/left.jsp" flush="false" />
    </td>
    <td width="700" valign="top">
        <jsp:include page="introList.jsp" flush="false"/>
    </td>
</tr>
<tr>
    <td width="150" valign="top">
	    <img src="../module/logo.png" border="0" width="90" height="60">
	</td>
	<td width="700" valign="top">
        <jsp:include page="../module/bottom.jsp" flush="false" />
    </td>
</tr>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.js"></script>
</body>
</html>