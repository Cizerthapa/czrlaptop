<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
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
        input[type="password"], input[type="submit"], input[type="text"] {
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
        <h2>Forget Password</h2>
        <form action="../ChangePasswordServlet" method="post">
            <input type="Text" name="emailandpassword" placeholder="Email Or Phone" required>
            <input type="password" name="new_password" placeholder="New Password" required>
            <input type="password" name="confirm_password" placeholder="Confirm New Password" required>
            <input type="submit" value="Change Password">
        </form>
        <a href="../html/profile.html" target="_self">
            <button style="
            background-color: #f93838;
            color: rgb(240, 243, 246);
            border-radius: 8px;
            height: 30px;
            width: 200px;
            font-size: 18px;
            font-family: Arial, sans-serif;
            margin-top: 20px;
            margin-left: 95px;"
            >
            Back
            </button>
        </a>
    </div>
</body>
</html>