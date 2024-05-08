<%@page import="util.StringUtils"%>
<%@page import="model.User"%>
<%@ page import="controller.database.DatabaseController" %>
<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Password Change</title>
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
<form action="${pageContext.request.contextPath}/EditProfileServlet" method="post">
                <input type="hidden" id="userName" name="userName" value="<%= userP.getUsername() %>">
                <input type="hidden" id="userFirstName" name="userFullName" value="<%= userP.getfirstName() %>">
                <input type="hidden" id="userEmail" name="userEmail" value="<%= userP.getEmail() %>">
                <input type="hidden" id="contactNumber" name="contactNumber" value="<%= userP.getPhoneNumber() %>">
                <input type="hidden" id="address" name="address" value="<%= userP.getLocation() %>">
            </form>
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
		<h2>Change Password</h2>
		<form action="../ChangePasswordServlet" method="post">
		
		<input type="hidden" id="userEmail" name="userEmail" value="<%= userP.getEmail() %>">
                <input type="hidden" id="contactNumber" name="contactNumber" value="<%= userP.getPhoneNumber() %>">
		
			<input type="password" name="current_password"
				placeholder="Current Password" required> <input
				type="password" name="new_password" placeholder="New Password"
				required> <input type="password" name="confirm_password"
				placeholder="Confirm New Password" required> <input
				type="submit" value="Change Password">
		
		<a href="../html/profile.html" target="_self">
			<button
				style="background-color: #f93838; color: rgb(240, 243, 246); border-radius: 8px; height: 30px; width: 200px; font-size: 18px; font-family: Arial, sans-serif; margin-top: 20px; margin-left: 95px;">
				Back</button>
		</a>
		</form>
	</div>
</body>
</html>
