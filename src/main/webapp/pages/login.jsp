<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<%
String messageError = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
String username = (String) request.getAttribute(StringUtils.USERNAME);
String successFulParam = request.getParameter(StringUtils.SUCCESS);
%>
<!DOCTYPE html>
<html>
<head>
<title>CZR Laptop | Login</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/login.css">
<style>
.main-nav {
	list-style-type: none;
	margin: 0 auto; /* Centering the navigation bar horizontally */
	padding: 0;
	overflow: hidden;
	background-color: #B0B9AB;
	text-align: center; /* Centering the text within the navigation bar */
	border-radius: 10px;
}

.main-nav li {
	display: inline-block; /* Displaying list items inline */
}

.main-nav li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.main-nav li a:hover {
	background-color: #111;
}
</style>
</head>
<body>
	<div class="container">
		<ul class="main-nav">
			<li><a
				href="<%=request.getContextPath() + StringUtils.PAGE_URL_HOME%>">Home</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Portfolio</a></li>
			<li><a href="#">Contact</a></li>
		</ul>
	</div>
	<div class="container">

		<form class="form-control" action="../LoginServlet" method="post">
			<h1>Login</h1>
			<label for="usernameType">Username</label> <input id="usernameType"
				name="username" placeholder="Enter username" type="text">
			<p id="demo" style="color: red;"></p>
			<label for="passwordType">Password</label> <input type="password"
				id="passwordType" name="password" placeholder="Enter password">
			<p id="pwdemo" style="color: red;"></p>
			<button class="btn" type="submit">Submit</button>
			<p>
				Don't have an account? <a href="../html/signup.html">Sign up</a>
			</p>
		</form>
		<%
		if (messageError != null) {
		%>
		<p><%=messageError%></p>
		<%
		}
		%>
		<%
		if (successFulParam != null && successFulParam.equals(StringUtils.TRUE)) {
		%>
		<h2 class="success-msg">Registration Successful!</h2>
		<%
		}
		%>
	</div>
</body>
</html>
