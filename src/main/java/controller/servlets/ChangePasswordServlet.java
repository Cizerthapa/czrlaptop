package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DatabaseController dbController = new DatabaseController();
	
    public ChangePasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String emailAndPassword = request.getParameter("emailandpassword");
		String email = request.getParameter("userEmail");
		
		System.out.println(email);
		
		
		String password = request.getParameter("new_password");
		String rePassword = request.getParameter("confirm_password");
		
		System.out.println("Email and Password: " + emailAndPassword);
		System.out.println("New Password: " + password);
		System.out.println("Confirm Password: " + rePassword);
		
		if(password.equalsIgnoreCase(rePassword)) {
			int rssd = dbController.updatePasswordFromEmail(email, password);
			System.out.println("Password change from email: "+ rssd);
			if(rssd == 0 || rssd < 0){
				int newrssd  = dbController.updatePasswordFromPhone(email, password);
				System.out.println("Password change from phone: "+ newrssd);
			} else {
				System.out.println("");
			}			
		}
	}

}
