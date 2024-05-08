<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="util.StringUtils"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About Us</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/aboutus.css" />
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f2f2f2;
}

.container {
	max-width: 400px;
	margin: 100px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
}

input[type="password"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

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
<header>
    <h1>Welcome to CZR Laptop</h1>
</header>

<div class="Content_Wrapper">
    <h2>Our Journey</h2>
    <p>Discover the origins of CZR Laptop, your go-to destination for high-quality laptops that cater to your computing needs. At CZR Laptop, we believe that technology is about more than just functionality; it is also about enhancing your lifestyle and productivity.</p>
    <p>LaptopVault, founded in 2024, has grown from a small startup to a renowned brand trusted by tech enthusiasts worldwide. Our mission is to provide cutting-edge laptops that empower individuals and businesses alike.</p>
    <br>
    <div class="laptopgallery">
      <div class="laptopItem">
        <img src="../resources/images/1.jpg" alt="Laptop Product 1">
      </div>
      <div class="laptopItem">
        <img src="../resources/images/2.jpg" alt="Laptop Product 2">
      </div>
      <div class="laptopItem">
        <img src="../resources/images/3.jpg" alt="Laptop Product 3">
      </div>
    </div>
    <h2>Our Principles</h2>
    <ul>
      <li><strong>Excellence:</strong> We are committed to offering laptops of the highest quality, meticulously engineered for performance, durability, and user satisfaction.</li>
      <li><strong>Diversity:</strong> We recognize the diverse needs of our customers and strive to offer a wide range of laptops catering to various preferences, budgets, and usage scenarios.</li>
      <li><strong>Innovation:</strong> We are dedicated to continuous innovation, staying at the forefront of technological advancements to bring you the latest features, designs, and capabilities.</li>
      <li><strong>Sustainability:</strong> We are conscious of our environmental impact and endeavor to adopt eco-friendly practices throughout our product lifecycle, from design to packaging and disposal.</li>
    </ul>
    
</div>
</body>
</html>
