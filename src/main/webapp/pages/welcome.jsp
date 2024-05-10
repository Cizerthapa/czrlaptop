<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="util.StringUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome | CZRLaptops</title>
<link rel="stylesheet" href="../stylesheets/landing.css">
<style>
.welcome-container {
	text-align: center;
	margin-top: 50px;
}

.welcome-container h1 {
	color: #333;
	font-size: 24px;
}

.welcome-container p {
	color: #666;
	font-size: 16px;
}

.home-button {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	text-decoration: none;
	cursor: pointer;
	font-size: 16px;
	margin-top: 20px;
}

.home-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
    String userSession = (String) session.getAttribute(StringUtils.USERNAME);
%>
	<% if (userSession != null && !userSession.isEmpty()) { %>
	<div class="welcome-container">
		<h1>
			Hello
			<%= userSession %>.
		</h1>
		<a href="${pageContext.request.contextPath}/pages/home.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
		<br>
		<a href="${pageContext.request.contextPath}/pages/profile.jsp">
			<button class="home-button">Profile</button>
		</a>
	</div>
	<% } else {%>
	<div class="welcome-container">
		<h1 style="color: grey">
			Please Login/ register
		</h1>
		<a href="${pageContext.request.contextPath}/pages/home.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
	</div> <% }%>

	<section class="hero">
		<div class="container">
			<h2 style="color: grey">Welcome to CZR Laptops</h2>
			<p style="color: grey">Explore our wide range of laptops at unbeatable prices</p>
			<a href="../pages/home.jsp" class="btn">Shop Now</a>
		</div>
	</section>
	<img style="max-width: 100%; max-height: 100%; width: auto; height: auto;" alt="img" src="${pageContext.request.contextPath}/resources/images/pexels.jpg">
	<section class="features">
		<div class="container">
			<div class="feature-box">
				<h3>Free Shipping</h3>
				<p>We offer free shipping!</p>
			</div>
			<div class="feature-box">
				<h3>24/7 Customer Support</h3>
				<p>Our customer support team is available anytime</p>
			</div>
			<div class="feature-box">
				<h3>Money Back Guarantee</h3>
				<p>Not satisfied? Get a full refund</p>
			</div>
		</div>
	</section>

	<footer>
		<div class="container">
			<p>&copy; 2024 CZR Laptops. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>