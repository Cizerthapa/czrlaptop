package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.User;

/**
 * implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String user_Name = request.getParameter("userName");
		String fullName = request.getParameter("userFullName");
		String email = request.getParameter("userEmail");
		String contact_number = request.getParameter("contactNumber");
		String address = request.getParameter("address");

		// Update the database with the new values
		User userChange = new User();
		userChange.setUsername(user_Name);
		userChange.setFirstName(fullName);
		userChange.setEmail(email);
		userChange.setPhoneNumber(contact_number);
		userChange.setLocation(address);

		// Update the user profile in the database
		int result = dbController.updateProfile(userChange);

		// Redirect back to the original JSP page with success or error message
		if (result == 1) {
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?success=true");
			System.out.println("changed");
		} else {
			response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?error=true");
			System.out.println("not changed");
		}
	}
}
