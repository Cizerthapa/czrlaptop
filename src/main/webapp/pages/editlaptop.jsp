<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Laptop</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/editLaptop.css" />
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