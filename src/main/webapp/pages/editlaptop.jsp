<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Laptop</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/editLaptop.css" />
<style>
    /* Add your custom CSS styles here */
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }
    .update_Laptop {
        width: 400px;
        margin: 50px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .update_form_group {
        margin-bottom: 20px;
    }
    label {
        font-weight: bold;
    }
    input[type="text"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-size: 16px;
    }
    button[type="submit"] {
        width: 100%;
        padding: 10px 0;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    button[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="update_Laptop">
        <h2>Update Laptop</h2>
        <form action="${pageContext.request.contextPath}/UpdateLaptopServlet" method="post">
            <div class="update_form_group">
                <label for="laptop_name">Laptop Name:</label>
                <input type="text" id="laptop_name" name="laptop_name" value="${laptop_name}" required>
            </div>
            <div class="update_form_group">
                <label for="price">Laptop Price:</label>
                <input type="text" id="price" name="price" value="${price}" required>
            </div>
            <div class="update_form_group">
                <label for="stockLevel">stockLevel:</label>
                <input type="text" id="stockLevel" name="stockLevel" value="${stockLevel}" required>
            </div>
            <div class="update_form_group">
                <label for="color">laptopDescription:</label>
                <input type="text" id="laptopDescription" name="laptopDescription" value="${laptopDescription}" required>
            </div>
            <div class="update_form_group">
                <label for="processor">processor:</label>
                <input type="text" id="processor" name="processor" value="${processor}" required>
            </div>
            <div class="update_form_group">
                <label for="graphics">graphics:</label>
                <input type="text" id="graphics" name="graphics" value="${graphics}" required>
            </div>
            <div class="update_form_group">
                <label for="RAM">RAM:</label>
                <input type="text" id="RAM" name="RAM" value="${RAM}" required>
            </div>
            <div class="update_form_group">
                <button type="submit">Update Laptop</button>
            </div>
            
        </form>
    </div>
</body>
</html>
</html>
