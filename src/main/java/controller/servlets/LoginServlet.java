package controller.servlets;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })
public class LoginServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
	
	public LoginServlet() {
		super();
	}
	
	public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Driver registered");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver Error");
        }
    }
	
	public void contextDestroyed(ServletContextEvent sce) {
	    try {
	        // JDBC driver (if needed)
	        java.sql.DriverManager.deregisterDriver(new com.mysql.cj.jdbc.Driver());
	    } catch (SQLException e) {
	        // Driver failure
	        e.printStackTrace();
	    }
	    
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.getWriter().append("Served at: ").append(request.getContextPath());
	    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String user_name = request.getParameter("username");
	    String pass_word = request.getParameter("password");
	    
	    int psd = dbController.getPassword(user_name,pass_word);
	    
	    System.out.println(psd);
	   if(psd == 1) {
		   System.out.println("Successful Login");
		   int adminChecker = dbController.isAdminCheck(user_name);
		   if(adminChecker == 1){
			   //User is administrator
			   HttpSession userSession = request.getSession();
               userSession.setAttribute("username", user_name);
            // Create a new cookie
               Cookie cookie = new Cookie("username", user_name);
               
               // Set the cookie's max age (in seconds). A value of -1 indicates that the cookie will persist until the browser is closed.
               cookie.setMaxAge(24 * 60 * 60); // 24 hours
               
               // Add the cookie to the response
               response.addCookie(cookie);
               
               // Write a message to the response
               response.getWriter().println("Cookie has been set!");
               userSession.setAttribute("id", userSession.getId());
               userSession.setMaxInactiveInterval(30*30);
               System.out.println("Redirecting to home page...");
               userSession.setAttribute("logIn", true);
               response.sendRedirect(request.getContextPath()+"/pages/dash.jsp");
		   } else {
			   //User is not administrator
			   HttpSession userSession = request.getSession();
               userSession.setAttribute("username", user_name);
               userSession.setMaxInactiveInterval(30*30);
               userSession.setAttribute("id", userSession.getId());
               System.out.println("Redirecting to home page...");
               userSession.setAttribute("logIn", true);
			   response.sendRedirect(request.getContextPath()+"/pages/welcome.jsp");
		   }
	   } else if(psd == 0){
		   //pass or user name not correct
		   request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
		   request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	   }
	   else if (psd == -1) {
           // User name not found
           request.setAttribute(StringUtils.MESSAGE_ERROR, "Create new account");
		   request.setAttribute(StringUtils.USERNAME, user_name);
		   request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	   } else {
		   //No login - server error
		   request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.setAttribute(StringUtils.USERNAME, user_name);
           request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
	   }
	}
}
