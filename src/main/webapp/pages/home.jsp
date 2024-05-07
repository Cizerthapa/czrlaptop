<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="util.StringUtils"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home | CZR Laptops</title>
<link rel="stylesheet" href="../stylesheets/style.css">
</head>
<body>
	<%
	String userSession = (String) session.getAttribute(StringUtils.USERNAME);
	String idSession = (String) session.getAttribute("id");
	boolean userFound = userSession != null && !userSession.isEmpty();
	%>
	<header>
		<h1 class="logo">CZR Laptops</h1>
		<div class="nav middle">
			<ul>
				<li><a href="home.jsp">Home</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<li><a href="contact.jsp">Contact</a></li>

				<li>
					<%-- Check if user is logged in --%>
					<% if (session.getAttribute(StringUtils.USERNAME) != null) { %>
                    <a href="../logout">Logout</a>
                <% } else { %>
                    <a href="login.jsp">Login</a>
                <% } %>
				</li>
			</ul>
		</div>
		<div class="nav right">
			<ul>
				<li><a href="registernew.jsp">Register</a></li>
			</ul>
		</div>
	</header>
	<main>
		<section class="featured-products">
			<h2>Featured laptops</h2>
			<sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/czrlaptops" user="root" password="" />
			<sql:query dataSource="${dataSource}" var="laptops">
                SELECT * FROM laptop;
            </sql:query>
			<c:forEach var="laptop" items="${laptops.rows}">
				<div class="product">
					<img
						src="${pageContext.request.contextPath}/resources/user/${laptop.image}"
						alt="${laptop.laptopName}">
					<h3>${laptop.laptopName}</h3>
					<p>Price: ${laptop.unitPrice}</p>
					<a href="#" class="get-now-btn">Buy Now</a>
				</div>
			</c:forEach>
		</section>
	</main>
	<footer>
		<p>&copy; 2024 CZR Laptops. All rights reserved.</p>
	</footer>
</body>
</html>