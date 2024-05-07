package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.Laptop;

@WebServlet("/HomeProductServlet")
public class HomeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbController = new DatabaseController();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Laptop> laptops = dbController.getAllLaptops();
        request.setAttribute("laptops", laptops);
        request.getRequestDispatcher("/pages/addproduct.jsp").forward(request, response);
	}
}
