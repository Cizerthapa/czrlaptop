<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="util.StringUtils"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%
// Get the session and request objects
HttpSession userSession = request.getSession();
String currentUser = (String) userSession.getAttribute("id");
String contextPath = request.getContextPath();

// Retrieve the login status from the session
Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<%
// Get the request object
HttpServletRequest requestl = (HttpServletRequest) pageContext.getRequest();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CZR Laptop | Login</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/header.css">
</head>
<body>
	<div id="header">
		<header style="background-color: #B0B9AB" class="header">
			<h1 class="logo"></h1>
			<ul class="main-nav">
				<li><a href=""><img style="height: 38px; width: 72px;"
						src="../resources/images/image.png" /></a></li>
				<li><a
					href="<%=request.getContextPath() + StringUtils.PAGE_URL_HOME%>">Home</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Portfolio</a></li>
				<li><a href="#">Contact</a></li>
				<li>
					<%-- Check if the user is logged in --%> <%
 if (loggedIn != null && loggedIn) {
 %>
					<form
						action="<%=request.getContextPath() + StringUtils.SERVLET_URL_LOGOUT%>"
						method="get">
						<input type="submit" value="Logout" />
					</form> <%
 } else {
 %>
					<form
						action="<%=request.getContextPath() + StringUtils.PAGE_URL_LOGIN%>"
						method="post">
						<input type="submit" value="Login" />
					</form> <%
 }
 %>
				</li>
			</ul>
		</header>
	</div>
</body>
</html>
