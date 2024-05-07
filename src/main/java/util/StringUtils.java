package util;

import java.io.File;

public class StringUtils {

	// Start SQL Queries

	public static final String INSERT_CUSTOMER = "INSERT INTO `customer`(`user_name`, `f_name`, `l_name`, `dateofbirth`, `gender`, `email`, `address`, `contactNumber`, `password`, `role`, `image`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_LOGIN_CUSTOMER_INFO = "SELECT * from CUSTOMER Where user_name = ? AND password = ?";

	public static final String GET_LOGIN_PASSWORD_INFO = "SELECT password from CUSTOMER Where user_name = ? AND password = ?";

	public static final String GET_CUSTOMER_USR_COUNT = "SELECT COUNT(*) FROM CUSTOMER WHERE user_name = ?";

	public static final String GET_LOGIN_CUSTOMER_USERNAME = "SELECT * from CUSTOMER Where user_name = ?";

	public static final String GET_ALL_CUSTOMER_INFO = "SELECT * FROM CUSTOMER_info";

	public static final String GET_ALL_CUSTOMER_USERNAME = "SELECT user_name FROM CUSTOMER_info";

	public static final String GET_USERNAME = "SELECT `user_name` FROM `CUSTOMER` WHERE user_name = ?";

	public static final String GET_NAMES = "SELECT `fName`,`lName` FROM `CUSTOMER` WHERE user_name = ?";

	public static final String GET_PHONE = "SELECT `phone` FROM `CUSTOMER` WHERE phone = ?";

	public static final String GET_EMAIL = "SELECT email FROM CUSTOMER WHERE email = ?";
	
	public static final String INSERT_LAPTOP = "INSERT INTO `laptop`(`laptopName`, `unitPrice`, `stockLevel`, `laptopDescription`, `processor`, `RAM`, `Storage`, `Screen`, `graphics`,`image`) VALUES (?,?,?,?,?,?,?,?,?,?)";

	// End SQL Queries
	// Start messages
	// End messages
	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_NAME = "user_name";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUBJECT = "subject";
	public static final String ROLE = "role";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	// End: Parameter names

	// Start: Validation Messages

	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";

	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/registernew.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_HOME = "/pages/home.jsp";
	public static final String PAGE_URL_ADDPRODUCT = "/pages/addproduct.jsp";
	public static final String PAGE_URL_INDEX = "/index.jsp";
	// End: JSP Route

	// Start: Servlet Route

	// End: Servlet Route
	public static final String IMAGE_DIR = "Users\\cizer\\eclipse-workspace\\CZRLaptopsNew\\src\\main\\webapp\\resources\\user\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String STUDENT_LISTS = "studentLists";
	public static final String SLASH = "/";
	// End: Normal Text
	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/Login";
	public static final String SERVLET_URL_REGISTER = "/RegisterServelet";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOMEPRD = "/HomeProductServlet";
	// End: Servlet Route
}
