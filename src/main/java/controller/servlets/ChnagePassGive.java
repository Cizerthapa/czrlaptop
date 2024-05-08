package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChnagePassGive
 */
@WebServlet("/ChnagePassGive")
public class ChnagePassGive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChnagePassGive() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String user_Name = request.getParameter("userName");
		String fullName = request.getParameter("userFullName");
		String email = request.getParameter("userEmail");
		String contact_number = request.getParameter("contactNumber");
		String address = request.getParameter("address");

		// Set attributes to forward to the JSP
		request.setAttribute("userName", user_Name);
		request.setAttribute("userFullName", fullName);
		request.setAttribute("userEmail", email);
		request.setAttribute("contactNumber", contact_number);
		request.setAttribute("address", address);

		// Forward to the JSP for editing
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/changepassword.jsp");
		dispatcher.forward(request, response);
	}
}
