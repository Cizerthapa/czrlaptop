package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_LOGOUT)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
        	System.out.println("Terminated session");
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath()+"/pages/welcome.jsp");
    }
}