//package controller.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import util.StringUtils;
//
//@WebFilter(urlPatterns = {"/pages/pfile.jsp", "/pages/.jsp", "/pages/home.jsp"})
//public class WelcomeFilter implements Filter {
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		HttpSession session = httpRequest.getSession(false);
//
//		boolean loggedIn = session != null && session.getAttribute("loggedIn") != null
//				&& (boolean) session.getAttribute("loggedIn");
//		String userNamePrint = session != null ? (String) session.getAttribute(StringUtils.USERNAME) : null;
//
//		System.out.println("user is : " + userNamePrint);
//		
//		// Check if the user is logged in
//		if (loggedIn) {
//			// User is authenticated allow access to pages
//			chain.doFilter(request, response);
//		} else {
//			// No User redirecting to login 
//			System.out.println("No Access!"); //perform Login
//			httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login.jsp");
//		}
//	}
//
//	public void destroy() {
//	}
//}
