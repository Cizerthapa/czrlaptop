<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="util.StringUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard | CZR Laptops</title>
<link rel="stylesheet" href="../stylesheets/admin.css" />
<style>
    .products {
        text-align: center;
        padding: 10px 0;
    }
    
    .product-container {
        display: flex;
        justify-content: center;
        gap: 20px; /* Adjust the gap between product cards */
        flex-wrap: wrap;
    }
    
    .product-card {
        width: 300px; /* Set the width of each product card */
        background-color: #f9f9f9;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add shadow effect */
        text-align: left; /* Align text to the left within the card */
    }
    
    .product-card img {
        width: 100%; /* Make sure the image fills the container */
        border-radius: 5px; /* Add rounded corners to the image */
        margin-bottom: 10px; /* Add some space between image and text */
    }
    
    .product-card h3 {
        font-size: 20px;
        margin-bottom: 10px;
    }
    
    .product-card p {
        margin-bottom: 5px;
    }
    
    .product-card p:first-child {
        font-weight: bold; /* Make the first paragraph bold (e.g., Price) */
    }
    
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<%
        String userSession = (String) session.getAttribute(StringUtils.USERNAME);
		String idSession = (String) session.getAttribute("id");
    %>
<section class="main">
    <div class="product-container">
    <div class="product-card">
        <i class="fas fa-laptop-code"></i>
        <h1>Users</h1>
        <h3>10,000</h3>
    </div>
    <div class="product-card">
        <i class="fab fa-wordpress"></i>
        <h1>Monthly Revenue</h1>
        <h3>$75,000</h3>
    </div>
    <div class="product-card">
        <i class="fas fa-palette"></i>
        <h1>Total Order</h1>
        <h3>25,000</h3>
    </div>
    </div>

    <div class="product-container">
        <div class="product-card">
            <i class="fas fa-laptop-code"></i>
            <h1>New Orders</h1>
            <h3>50</h3>
        </div>
        <div class="product-card">
            <i class="fab fa-wordpress"></i>
            <h1>Date Today</h1>
        <h3>28th Nov 2002</h3>
        </div>
        <div class="product-card">
            
            <h1>Total Revenue</h1>
            <h3>$225,000</h3>
        </div>
        </div>

    <section class="products">
    <h2>Trending Products</h2>
    <div class="product-container">
        <div class="product-card">
        <img src="../resources/user/3lap.jpg" alt="Laptop 1">
        <h3>Laptop 1</h3>
        <p>Price: $50</p>
        <p>Brand: XYZ</p>
        <p>Color: Red</p>
        <p>Stock Quantity: 100</p>
        <p>Size: M</p>
        <p>Country of Origin: ABC Country of Origin</p>
        </div>
        <div class="product-card">
        <img src="../resources/user/1lap.jpg" alt="Laptop 2">
        <h3>Laptop 2</h3>
        <p>Price: $60</p>
        <p>Brand: ABC</p>
        <p>Color: Blue</p>
        <p>Stock Quantity: 80</p>
        <p>Size: L</p>
        <p>Country of Origin: XYZ Country of Origin</p>
        </div>
        <div class="product-card">
        <img src="../resources/user/2lap.jpg" alt="Laptop 3">
        <h3>Laptop 3</h3>
        <p>Price: $55</p>
        <p>Brand: PQR</p>
        <p>Color: Green</p>
        <p>Stock Quantity: 120</p>
        <p>Size: S</p>
        <p>Country of Origin: PQR Country of Origin</p>
        </div>
    </div>
    <button onclick="window.location.href='${pageContext.request.contextPath}/pages/addproduct.jsp'" style="background-color: #4CAF50; /* Green background */
               border: none; /* Remove border */
               color: white; /* White text */
               padding: 15px 32px; /* Padding */
               text-align: center; /* Center text */
               text-decoration: none; /* Remove underline */
               display: inline-block; /* Make it inline */
               font-size: 16px; /* Font size */
               margin: 4px 2px; /* Margin */
               cursor: pointer; /* Add cursor pointer */
               border-radius: 10px; /* Rounded corners */">
    Add Laptop
</button>
    </section>
</section>
</div>
</body>
</html>