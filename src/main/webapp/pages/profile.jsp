<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
<link rel="stylesheet" href="../css/profile.css">
<style>
    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }

    .profile-card {
        display: flex;
        align-items: center;
        margin-bottom: 20px;
    }

    .profile-details {
        flex: 1;
    }

    .btn-primary { 
        color: #dfe9f5;
        padding: 10px 20px;
        border-radius: 8px;
        background-color: #007bff;
        border: none;
        cursor: pointer;
        margin-right: 10px;
    }

    .btn-primary:last-child {
        margin-right: 0;
    }

    .btn-primary:hover {
        background-color: #0056b3;
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
</style>
</head>
<body>
    <div class="container">
        <div class="profile-card">
            <div class="profile-details">
                <h2>User Name: czradmin</h2>
                <p>Email: cizerthapa@gmail.com</p>
                <p>Location: Lalitpur, Nepal</p>
                <p>Joined: January 1, 1951</p>
            </div>
        </div>
        <div class="divide">
            <a href="../html/profileupdate.html" target="_self">
                <button
                style="
                background-color: #007bff;
                color: rgb(240, 243, 246);
                border-radius: 8px;
                height: 50px;
                width: 200px;
                font-size: 18px;
                font-family: Arial, sans-serif;"
                >
                Update Now
                </button>
            </a>
            </a>
        </div>
    </div>
</body>
</html>
