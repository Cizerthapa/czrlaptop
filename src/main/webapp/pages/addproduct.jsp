<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="util.StringUtils"%>

<!DOCTYPE html>
<html>
<%
String contextPath = request.getContextPath();
%>
<%
String messageError = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
String username = (String) request.getAttribute(StringUtils.USERNAME);
String successFulParam = request.getParameter(StringUtils.SUCCESS);
%>

<head>
<meta charset="UTF-8">
<title>Product Add | CZR Laptops</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/addproduct.css">
</head>
<body>
<button onclick="window.location.href='${pageContext.request.contextPath}/pages/dash.jsp'" style="margin-top: 20px; position: absolute;">Go Back</button>

	<div class="laptop-container">
		<h1 style="text-align: center;">Laptop Management</h1>
		<div class="laptop-form-container">
			<h2 style="text-align: center;">Add a New Laptop</h2>
			<form action="../UpdateServlet" method="post"
				enctype="multipart/form-data">
				<label for="laptopName">Laptop Name:</label> <input type="text"
					id="laptopName" name="laptopName"><br> <label
					for="unitPrice">Unit Price:</label> <input type="text"
					id="unitPrice" name="unitPrice"><br> <label
					for="stockLevel">Stock Level:</label> <input type="text"
					id="stockLevel" name="stockLevel"><br> <label
					for="laptopDescription">Laptop Description:</label>
				<textarea id="laptopDescription" name="laptopDescription"></textarea>
				<br> <label for="processor">Processor:</label> <input
					type="text" id="processor" name="processor"><br> <label
					for="rAM">RAM:</label> <input type="text" id="rAM" name="rAM"><br>

				<label for="storage">Storage:</label> <input type="text"
					id="storage" name="storage"><br> <label for="screen">Screen:</label>
				<input type="text" id="screen" name="screen"><br> <label
					for="graphics">Graphics:</label> <input type="text" id="graphics"
					name="graphics"><br> <label for="Upload Profile">Upload
					Profile</label> <input type="file" id="laptop_image" name="laptop_image"
					accept="image/png, image/jpeg, image/jpg" required> <br>
				<button type="submit" name="add_laptop">Add Laptop</button>
			</form>
			<%
			if (messageError != null) {
			%>
			<p style="">
				<%
				out.println(messageError);
				%>
			</p>
			<%
			}
			if (successFulParam != null && successFulParam.equals(StringUtils.TRUE)) {
			%>
			<h2 class="success-msg">Registration Successful!</h2>
			<%
			}
			%>
		</div>
		<sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
			url="jdbc:mysql://localhost:3306/czrlaptops" user="root" password="" />

		<c:catch var="sqlException">
			<sql:query dataSource="${dataSource}" var="laptops">
            SELECT * FROM laptop;
        </sql:query>
		</c:catch>

		<c:if test="${not empty sqlException}">
			<p style="color: red;">An error occurred while fetching data from
				the database.</p>
		</c:if>

		<div class="laptop-container">
			<h1>Laptop Management</h1>
			<div class="laptop-form-container">
				<!-- Form to add a new laptop -->
			</div>
			<div class="laptop-table-container">
				<table class="laptop-table">
					<thead>
						<tr>
							<th>Laptop Image</th>
							<th>Laptop Name</th>
							<th>Laptop Price</th>
							<th>Laptop Stock Level</th>
							<th>Laptop Description</th>
							<th>Laptop Processor</th>
							<th>Laptop Graphics</th>
							<th>Laptop Ram</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty laptops.rows}">
							<tr>
								<td colspan="8">No products found.</td>
							</tr>
						</c:if>
						<c:if test="${not empty laptops.rows}">
							<c:forEach var="laptop" items="${laptops.rows}">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/user/${laptop.image}"
										height="100" alt="laptop Image"></td>
									<td>${laptop.laptopName}</td>
									<td>${laptop.unitPrice}</td>
									<td>${laptop.stockLevel}</td>
									<td>${laptop.laptopDescription}</td>
									<td>${laptop.processor}</td>
									<td>${laptop.graphics}</td>
									<td>${laptop.RAM}</td>
									<td>
										<form action="${pageContext.request.contextPath}/DeleteServlet" method="post">
											<input type="hidden" id="id_delete" name="id_delete"
												value="${laptop.laptopName}">
											<button class="delete-btn" type="submit">Delete</button>
										</form>
										<form
											action="${pageContext.request.contextPath}/EditlaptopServlet"
											method="Post">
											<input type="hidden" id="laptop_name" name="laptop_name"
												value="${laptop.laptopName}"> <input type="hidden"
												id="price" name="price" value="${laptop.unitPrice}">
											<input type="hidden" id="stockLevel" name="stockLevel"
												value="${laptop.stockLevel}"> <input type="hidden"
												id="laptopDescription" name="laptopDescription"
												value="${laptop.laptopDescription}"> <input
												type="hidden" id="processor" name="processor"
												value="${laptop.processor}"> <input type="hidden"
												id="graphics" name="graphics" value="${laptop.graphics}">

											<input type="hidden" id="RAM" name="RAM"
												value="${laptop.RAM}">
											<button type="submit" class="edit-btn">Edit</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>