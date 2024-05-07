<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="util.StringUtils"%>
	
<!DOCTYPE html>
<%
	String messageError = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
%>
 <%
String contextPath = request.getContextPath();
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - CT</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/register.css">
</head>
<body>
	<div class="registration-container">
		<h1 class="registration-title">Register</h1>
		<form action="../RegisterServlet" method="post"
			enctype="multipart/form-data">
			<div class="form-row">
				<div class="form-column">
					<label for="firstName" class="form-label">First Name:</label> <input
						type="text" id="firstName" name="firstName" required
						class="form-input">
				</div>
				<div class="form-column">
					<label for="lastName" class="form-label">Last Name:</label> <input
						type="text" id="lastName" name="lastName" required
						class="form-input">
				</div>
			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="username" class="form-label">Username:</label> <input
						type="text" id="username" name="username" required
						class="form-input">
				</div>
				<div class="form-column">
					<label for="birthday" class="form-label">Birthday:</label> <input
						type="date" id="birthday" name="birthday" required
						class="form-input">
				</div>

			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="gender" class="form-label">Gender:</label> <select
						id="gender" name="gender" required class="form-select">
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="form-column">
					<label for="email" class="form-label">Email:</label> <input
						type="email" id="email" name="email" placeholder="abc@xyz.com"
						required class="form-input">
				</div>
			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="location" class="form-label">Location</label> <input
						type="location" id="location" name="location"
						placeholder="Kathmandu, Nepal" required class="form-input">
				</div>
			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="phoneNumber" class="form-label">Phone Number:</label> <input
						type="tel" id="phoneNumber" name="phoneNumber"
						placeholder="+9779800000000" required class="form-input">
				</div>
				<div class="form-column">
					<label for="subject" class="form-label">Subject:</label> <select
						id="subject" name="subject" required class="form-select">
						<option value="computing">Computing</option>
						<option value="multimedia">Multi-media</option>
						<option value="networking">Networking</option>
						<option value="MAD">Mobile Application Development</option>
					</select>
				</div>
			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="password" class="form-label">Password:</label> <input
						type="password" id="password" name="password" required
						class="form-input">
				</div>
				<div class="form-column">
					<label for="retypePassword" class="form-label">Retype
						Password:</label> <input type="password" id="retypePassword"
						name="retypePassword" required class="form-input">
				</div>
			</div>
			<div class="form-row">
				<div class="form-column">
					<label for="image" class="form-label">Image:</label> <input
						type="file" id="user_image" name="user_image" class="form-input">
				</div>
			</div>
			<%
			if (messageError != null) {
			%>
			<p style="color: red">
				<%=messageError%>
			</p>
			<%
			}
			%>
			<div style="color: red;">
				<p class="error-message"></p>
			</div>
			<button type="submit" class="submit-button">Submit</button>
		</form>
	</div>
</body>
</html>
