<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Updated Profile</title>
    <link rel="stylesheet" href="../stylesheets/edit.css">
</head>
<body>
   <section class="form-section">
    <div class="container">
        <div class="box">
            <h1>Edit Your Profile</h1>
            <form action="${pageContext.request.contextPath}/UpdateProfileServlet" method="post">
                <div class="form-group">
                    <label for="userName">Username:</label>
                    <input type="text" id="userName" name="userName" value="${updatedUserName}" readonly>
                </div>
                <div class="form-group">
                    <label for="userFullName">Full Name:</label>
                    <input type="text" id="userFullName" name="userFullName" value="${updatedFullName}" required>
                </div>
                <div class="form-group">
                    <label for="userEmail">Email:</label>
                    <input type="email" id="userEmail" name="userEmail" value="${updatedEmail}" required>
                </div>
                <div class="form-group">
                    <label for="contactNumber">Phone Number:</label>
                    <input type="text" id="contactNumber" name="contactNumber" value="${updatedContactNumber}" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" value="${updatedAddress}" required>
                </div>
                <div class="updateButton">
                    <button type="submit">Update Profile</button>
                </div>
            </form>
        </div>
    </div>
   </section>
</body>
</html>
