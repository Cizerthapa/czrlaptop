package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController;

    public DeleteServlet() {
        super();
        dbController = new DatabaseController(); // Initialize DatabaseController
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteString = request.getParameter("id_delete"); // Retrieve parameter named "id_delete"
        System.out.println(deleteString);
        if (deleteString != null && !deleteString.isEmpty()) {
            try {
                int queryResult = dbController.deleteLaptop(deleteString);
                System.out.println(queryResult);
                if (queryResult > 0) {
//                    ArrayList<Laptop> laptops = dbController.getAllLaptops();
//                    request.setAttribute("laptops", laptops);
                    response.sendRedirect(request.getContextPath()+"/pages/addproduct.jsp");
                } else {
                    // Provide feedback to the user if deletion failed
                    System.out.println("Failed to delete laptop");
                    request.getRequestDispatcher(request.getContextPath()+"/pages/addproduct.jsp");
                    // You might want to provide feedback to the user here
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle other unexpected exceptions here
            }
        } else {
            // Handle case when parameter is not provided
            System.out.println("No parameter provided");
            // You might want to provide feedback to the user here
        }
}

}
