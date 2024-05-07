package controller.servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.Laptop;
import util.StringUtils;

@WebServlet("/UpdateServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController dbController;

	public UpdateServlet() {
		super();
		this.dbController = new DatabaseController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    Part user_image = request.getPart("laptop_image");

	    String laptopName = request.getParameter("laptopName");
		double unitPrice = 0.0; // Default value in case of null
		String unitPriceParam = request.getParameter("unitPrice");
		int stockLevel = 0; // Default value in case of null
		String stockLevelParam = request.getParameter("stockLevel");

		if (unitPriceParam != null && !unitPriceParam.trim().isEmpty()
		        && stockLevelParam != null && !stockLevelParam.trim().isEmpty()) {
		    try {
		        unitPrice = Double.parseDouble(unitPriceParam);
		        stockLevel = Integer.parseInt(stockLevelParam);
		    } catch (NumberFormatException e) {
		        // Handles parsing error
		        e.printStackTrace();
		    }
		}

		String laptopDescription = request.getParameter("laptopDescription");
		String processor = request.getParameter("processor");
		String RAM = request.getParameter("rAM");
		String storage = request.getParameter("storage");
		String screen = request.getParameter("screen");
		String graphics = request.getParameter("graphics");

		Laptop model = new Laptop(laptopName, unitPrice, stockLevel, laptopDescription,
		        processor, RAM, storage, screen, graphics, user_image);

		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
		String fileName = model.getUserImageUrl();
		if (!fileName.isEmpty() && fileName != null)
		    user_image.write(savePath + fileName);

		int result = dbController.addProduct(model);

		if (result > 0) {
		    // Successful addition, redirect to admin page or display success message
			ArrayList<Laptop> laptops = dbController.getAllLaptops();
            request.setAttribute("laptops", laptops);
            request.getRequestDispatcher("/pages/addproduct.jsp").forward(request, response);
		} else {
		    // Error adding product, handle accordingly
		    // For example, display error message to user or redirect to error page
			response.sendRedirect(request.getContextPath()+"/pages/error.html");
		}
		} catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADDPRODUCT).forward(request, response);
			System.out.println("Exception");
		}
	}
}
