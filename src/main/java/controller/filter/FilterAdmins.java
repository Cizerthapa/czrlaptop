package controller.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.Laptop;
import util.StringUtils;

public class FilterAdmins implements Filter {
	private DatabaseController dbController;

	public void init(FilterConfig filterConfig) throws ServletException {
		dbController = new DatabaseController();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = session != null && session.getAttribute("loggedIn") != null
				&& (boolean) session.getAttribute("loggedIn");
		String username = session != null ? (String) session.getAttribute(StringUtils.USERNAME) : null;
		String userRole = session != null ? (String) session.getAttribute(StringUtils.ROLE) : null;

		System.out.println("Username: " + username); // Add logging
		System.out.println("Role: " + userRole); // Add logging

		// Check if the user is logged in and has the admin role
		if (isLoggedIn && "admin".equals(userRole)) {
			// User is authenticated and is an admin, allow access to admin panel
			if (httpRequest.getRequestURI().endsWith("/pages/addproduct.jsp")) {
				// Fetch all helmets from the database
				ArrayList<Laptop> helmets = dbController.getAllLaptops();
				request.setAttribute("helmets", helmets);
			}
			chain.doFilter(request, response);
		} else {
			// User is not authenticated as admin, redirect to login page
			System.out.println("Admin access denied!"); // Add logging
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login.jsp");
		}
	}

	public void destroy() {
	}
}
