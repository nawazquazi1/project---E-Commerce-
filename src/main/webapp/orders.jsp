<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.model.*"%>
	<%
	user auth = (user) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth",auth);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order</title>
<%@ include file="includes/head.jsp" %>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<%@ include file="includes/head.jsp" %>
</body>
</html>