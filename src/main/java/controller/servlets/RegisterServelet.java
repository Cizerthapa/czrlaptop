package controller.servlets;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.PasswordEncryptionWithAes;
import model.User;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class RegisterServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	public RegisterServelet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private int checkString(String check) {
		if (check.isEmpty()) {
			return -1;
		} else if (symbolValidate(check)) {
			return -1;
		} else {
			return 0;
		}
	}

	private boolean symbolValidate(String n) {
		// Variable that stores the integer value of the character (32 - 47 | 58 - 64 |
		// 91 - 96 | 123 - 126)
		int[][] ranges = { { 33, 47 }, { 58, 64 }, { 91, 96 }, { 123, 126 } };
		// Calculate the total size of the array and Create the array
		int totalSize = 0;
		for (int[] range : ranges) {
			totalSize += range[1] - range[0] + 1;
		}
		int[] intArray = new int[totalSize];

		// Assign value in the array
		int currentIndex = 0;
		for (int[] range : ranges) {
			for (int i = range[0]; i <= range[1]; i++) {
				intArray[currentIndex++] = i;
			}
		}
		// Get the length of the string using the length method
		int length = n.length();

		// loop to iterate over each char in string
		for (int i = 0; i < length; i++) {
			// Use the charAt() method to get the character at the current index
			int currentChar = (int) n.charAt(i);
			for (int j = 0; j < intArray.length; j++) {
				if (currentChar == intArray[j]) {
					System.out.println("Validation error");
					return true;
				}
			}
		}
		return false;
	}

	private int checkUsername(String usrNm) {
		byte usrNmLength = (byte) usrNm.length();
		byte rqrdNum = 6;
		if (usrNmLength >= rqrdNum && checkString(usrNm) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	private int checkPhone(String number) {
		// Ensure the length of the number is at least 14
		if (number.length() >= 14) {
			// Iterate over each character of the number starting from the second character
			for (int i = 1; i < number.length(); i++) {
				// Check if each character is a digit
				if (!Character.isDigit(number.charAt(i))) {
					return -1; // Return -1 if any character is not a digit
				}
			}
			// Check if the number starts with '+'
			if (number.startsWith("+")) {
				return 0; // Return 0 if the number meets the criteria
			}
		}
		return -1; // Return -1 if the length is less than 14 or doesn't start with '+'
	}

	private int genderCheck(String check) {
		if (check.equalsIgnoreCase("male") || check.equalsIgnoreCase("female")) {
			return 0;
		}
		return -1;
	}

	private static boolean isSpecialCharacter(char ch) {
		// Define your set of special characters
		String specialChars = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";

		// Check if the character is a special character
		return specialChars.contains(String.valueOf(ch));
	}

	private static boolean isValidPassword(String password) {
		// Check if password length is greater than 6 characters
		if (password.length() <= 6) {
			return false;
		}

		// Check if password contains at least one number, one special character, and
		// one capital letter
		boolean hasNumber = false;
		boolean hasSpecialChar = false;
		boolean hasCapitalLetter = false;

		for (char ch : password.toCharArray()) {
			if (Character.isDigit(ch)) {
				hasNumber = true;
			} else if (isSpecialCharacter(ch)) {
				hasSpecialChar = true;
			} else if (Character.isUpperCase(ch)) {
				hasCapitalLetter = true;
			}

			// If all required conditions are met, return true
			if (hasNumber && hasSpecialChar && hasCapitalLetter) {
				return true;
			}
		}
		// If any of the conditions are not met, return false
		return false;
	}

	private int checkDate(LocalDate dob) {
		LocalDate currentDate = LocalDate.now();
		int age = currentDate.getYear() - dob.getYear();
		if (age < 18) {
			return -1; // Person is under 18
		} else {
			return 0; // Person is 18 or older
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_name = request.getParameter("username");
		String first_name = request.getParameter("firstName");
		String last_name = request.getParameter("lastName");
		String dobString = request.getParameter("birthday");
		LocalDate dob = LocalDate.parse(dobString);
		String gender = request.getParameter("gender");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		String phone_number = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String repassword = request.getParameter("retypePassword");
		Part imagePart = request.getPart("user_image");

		User model = new User(first_name, last_name, location, dob, gender, email, phone_number, user_name,
				PasswordEncryptionWithAes.encrypt(user_name, password), "user", imagePart);

		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
		String fileName = model.getImageUrlFromPart();
		if (!fileName.isEmpty() && fileName != null)
			imagePart.write(savePath + fileName);
		System.out.println(model.getImageUrlFromPart());

		System.out.println("user_name: " + user_name);
		System.out.println("first_name: " + first_name);
		System.out.println("last_name: " + last_name);
		System.out.println("dob: " + dob);
		System.out.println("gender: " + gender);
		System.out.println("email: " + email);
		System.out.println("phone_number: " + phone_number);
		System.out.println("password: " + password);
		System.out.println("repassword: " + repassword);

		System.out.println("start");

		if (checkUsername(user_name) == -1) {
			String errorMessage = "Invalid User name. Please don't enter symbols and numerical value.";
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (checkPhone(phone_number) == -1) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid number. Phone Number must be of 14 characters.");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!isValidPassword(password)) {
			request.setAttribute(StringUtils.MESSAGE_ERROR,
					"Invalid password. Password must contain at least one uppercase letter, one number, and one special character.");
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!password.equalsIgnoreCase(repassword)) {
			String errorMessage = "Password and Retype Password do not match.";
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (dbController.isuserNameExists(user_name)) {
			String errorMessage = "Username already exists. Please choose a different username.";
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		// Check if email already exists
		if (dbController.isExistsEmail(email)) {
			String errorMessage = "Email already exists. Please use a different email address.";
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		// Check if phone number already exists
		if (dbController.isPhoneNumberExists(phone_number)) {
			String errorMessage = "Phone number already exists. Please use a different phone number.";
			request.setAttribute(StringUtils.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (checkUsername(user_name) == 0 && checkString(first_name) == 0 && checkString(last_name) == 0
				&& checkPhone(phone_number) == 0 && genderCheck(gender) == 0 && isValidPassword(password)
				&& checkDate(dob) == 0) {
			System.out.println("checking process");
			// request.setAttribute("error-message", fileName);
			if (dbController.checkuserNameFromDb(user_name) == 1 && dbController.checkEmailFromDb(email) == 1
					&& dbController.checkPhoneFromDb(phone_number) == 1) {
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_REGISTER);
				System.out.println("Already Enrolled");
			} else {
				System.out.println("Here in password check");
				if (password.equalsIgnoreCase(repassword)) {
					int result = dbController.userAdd(model);
					System.out.println(result);
					response.sendRedirect(request.getContextPath()+"/pages/login.jsp");				}
			}
		}
	}
}
