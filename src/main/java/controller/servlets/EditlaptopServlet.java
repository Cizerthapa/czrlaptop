package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;

/**
 * Servlet implementation class EditlaptopServlet
 */
@WebServlet("/EditlaptopServlet")
public class EditlaptopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditlaptopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet(request, response); // This line is not needed, since you're already handling doPost()

        String laptop_name = request.getParameter("laptop_name");
        String price = request.getParameter("price");
        String stockLevel = request.getParameter("stockLevel");
        String laptopDescription = request.getParameter("laptopDescription");
        String processor = request.getParameter("processor");
        String graphics = request.getParameter("graphics");
        String RAM = request.getParameter("RAM");

        // Set attributes to forward to the JSP
        request.setAttribute("laptop_name", laptop_name);
        request.setAttribute("price", price);
        request.setAttribute("stockLevel", stockLevel);
        request.setAttribute("laptopDescription", laptopDescription);
        request.setAttribute("processor", processor);
        request.setAttribute("graphics", graphics);
        request.setAttribute("RAM", RAM);

        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/editlaptop.jsp");
        dispatcher.forward(request, response);
    }

}
