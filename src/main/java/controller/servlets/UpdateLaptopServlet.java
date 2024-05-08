package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Laptop;

/**
 * Servlet implementation class UpdateLaptopServlet
 */
@WebServlet("/UpdateLaptopServlet")
public class UpdateLaptopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbController = new DatabaseController();

	public UpdateLaptopServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String laptop_name = request.getParameter("laptop_name");
		String price = request.getParameter("price");
		String stockLevel = request.getParameter("stockLevel");
		String laptopDescription = request.getParameter("laptopDescription");
		String processor = request.getParameter("processor");
		String graphics = request.getParameter("graphics");
		String RAM = request.getParameter("RAM");

		// Parse price from string to double

		// Update the database with the new values
		int result = dbController.updateLaptop(new Laptop(laptop_name, Integer.parseInt(price),
				Integer.parseInt(stockLevel), laptopDescription, processor, graphics, RAM));

		// Redirect back to the original JSP page with success or error message
		if (result == 1) {
			response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?success=true");
		} else {
			response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?error=true");
			System.out.println("servlet error");
		}
		doGet(request, response);
	}
}
