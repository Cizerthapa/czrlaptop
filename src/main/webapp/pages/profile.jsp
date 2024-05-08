<%@page import="model.User"%>
<%@ page import="controller.database.DatabaseController"%>
<%@ page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Check if the user is logged in
// Assuming you have a session attribute named "loggedInUser" that stores the logged-in user's username
String loggedInUsername = (String) session.getAttribute(StringUtils.USERNAME);
if (loggedInUsername == null || loggedInUsername.isEmpty()) {
	// Redirect to login page if not logged in
	response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	return; // Stop further execution
}

// Initialize the database controller
DatabaseController dbController = new DatabaseController();

// Retrieve user profile information from the database based on the logged-in username
User userP = dbController.getUserProfile(loggedInUsername);

// Check if user profile is null
if (userP == null) {
	// Handle case where user profile is not found
	// For example, display an error message
	out.println("User profile not found");
	return; // Stop further execution
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/profile.css" />
</head>
<body>


	<div class="container">
		<div class="box">
			<h1>Profile</h1>
			<div class="image">
				<img
					src="${pageContext.request.contextPath}/resources/user/person.png"
					alt="Image">
			</div>
			<div class="user-details">
				<p>
					User name:
					<%=userP.getUsername()%></p>
				<p>
					First Name:
					<%=userP.getfirstName()%></p>
				<p>
					Email:
					<%=userP.getEmail()%></p>
				<p>
					Phone Number:
					<%=userP.getPhoneNumber()%></p>
				<p>
					DOB:
					<%=userP.getDob()%></p>
				<p>
					Address:
					<%=userP.getLocation()%></p>
			</div>
			<form action="${pageContext.request.contextPath}/EditProfileServlet"
				method="post">
				<input type="hidden" id="userName" name="userName"
					value="<%=userP.getUsername()%>"> <input type="hidden"
					id="userFirstName" name="userFullName"
					value="<%=userP.getfirstName()%>"> <input type="hidden"
					id="userEmail" name="userEmail" value="<%=userP.getEmail()%>">
				<input type="hidden" id="contactNumber" name="contactNumber"
					value="<%=userP.getPhoneNumber()%>"> <input type="hidden"
					id="address" name="address" value="<%=userP.getLocation()%>">
				<div class="updateButton">
					<button type="submit">Update</button>
				</div>
			</form>
			<form id="resetPasswordForm"
				action="${pageContext.request.contextPath}/ChnagedPassGive"
				method="post">
				<button type="submit" class="home-button">Reset Password</button>
			</form>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/pages/home.jsp">
		<button class="home-button">Go Back</button>
	</a>

</body>
<script>
	// Add an event listener to the form submission
	document
			.getElementById('resetPasswordForm')
			.addEventListener(
					'submit',
					function(event) {
						// Prevent the default form submission behavior
						event.preventDefault();

						// Redirect the user to changepassword.jsp
						window.location.href = "${pageContext.request.contextPath}/pages/changepassword.jsp";
					});
</script>
</html>