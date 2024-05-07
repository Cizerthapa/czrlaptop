package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Laptop;
import model.PasswordEncryptionWithAes;
import model.User;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/czrlaptops";
			return DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public int addUser(User userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_CUSTOMER);

			st.setString(1, userModel.getUsername());
			st.setString(2, userModel.getfirstName());
			st.setString(3, userModel.getLastname());
			st.setDate(4, Date.valueOf(userModel.getDob()));
			st.setString(5, userModel.getGender());
			st.setString(6, userModel.getEmail());
			st.setString(7, userModel.getLocation());
			st.setString(8, userModel.getPhoneNumber());
			st.setString(9, userModel.getPassword());
			st.setString(10, "user");
			st.setString(11, userModel.getImageUrlFromPart());

			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return -1;
		}
	}

	public int getLoginInfo(String username, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_CUSTOMER_INFO);
			st.setString(1, username);
			st.setString(2, PasswordEncryptionWithAes.encrypt(username, password));
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name and password match in the database
				return 1;
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			ex.getMessage();
			return -1;
		}
	}

	public int getPassword(String username, String password) {
		boolean checker = isUsernameExists(username);
		if (checker) {
			System.out.println("There is user");
		}

		String usr = username;
		String pass = password;

		System.out.println(usr + " " + pass);
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement("SELECT * FROM CUSTOMER WHERE user_name = ?");

			// Set the username in the first parameter of the prepared statement
			st.setString(1, username);

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USER_NAME);

				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb.equals(username) && decryptedPwd.equals(password)) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	public boolean isUsernameExists(String username) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(StringUtils.GET_CUSTOMER_USR_COUNT)) {
			st.setString(1, username);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
		}
		return false;
	}

	public String getUserNameFromEmail(String email) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("SELECT user_name FROM CUSTOMER WHERE email = ?")) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String usr = rs.getString(1);
					return usr;
				} else {
					return "no user";
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return "-1";
		}
	}

	public String getUserNameFromPhone(String phone) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("SELECT user_name FROM CUSTOMER WHERE contactNumber = ?")) {
			st.setString(1, phone);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String usr = rs.getString(1);
					return usr;
				} else {
					return "no user";
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return "-1";
		}
	}

	public String getUserName(String username, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_CUSTOMER_INFO);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name and password match in the database
				return username;
			} else {
				// No matching record found
				return "";
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());// Log the exception for debugging
			return null;
		}
	}

	public String getNames(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_NAMES);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name match in the database
				String fName = rs.getString("fName");
				String lName = rs.getString("lName");
				return fName + " " + lName; // Assuming you want to return the full name
			} else {
				// No matching record found
				return "";
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());// Log the exception for debugging
			return null;
		}
	}

	public int checkUserNameFromDb(String userName) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USERNAME);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name match in the database
				return 1;
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());// Log the exception for debugging
			return -1;
		}
	}

	public int checkEmailFromDb(String email) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_EMAIL);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name match in the database
				return 1;
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());// Log the exception for debugging
			return -1;
		}
	}

	public int checkPhoneFromDb(String phone) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_PHONE);
			st.setString(1, phone);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name match in the database
				return 1;
			} else {
				// No matching record found
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());// Log the exception for debugging
			return -1;
		}
	}

	public boolean isEmailExists(String email) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE email = ?")) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean isPhoneNumberExists(String phoneNumber) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE contactNumber = ?")) {
			st.setString(1, phoneNumber);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public int updatePasswordFromEmail(String email, String password) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE CUSTOMER SET password = ? WHERE email = ?")) {

			if (isEmailExists(email)) {
				st.setString(1, PasswordEncryptionWithAes.encrypt(getUserNameFromEmail(email), password));
				st.setString(2, email);

				int result = st.executeUpdate();

				if (result > 0) {
					return 1;
				} else {
					// Not found
					return 0;
				}
			} else {
				return -2;
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int updatePasswordFromPhone(String phone, String password) {
		try (Connection con = getConnection();
				PreparedStatement st = con
						.prepareStatement("UPDATE CUSTOMER SET password = ? WHERE contactNumber = ?")) {

			if (isPhoneNumberExists(phone)) {
				st.setString(1, PasswordEncryptionWithAes.encrypt(getUserNameFromPhone(phone), password));
				st.setString(2, phone);

				int result = st.executeUpdate();

				if (result > 0) {
					return 1;
				} else {
					// Not found
					return 0;
				}
			} else {
				return -2;
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int isAdmin(String username) {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("SELECT role FROM CUSTOMER WHERE user_name = ?")) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// User name match in the database
				String thisRole = rs.getString("role");
				if (thisRole.equalsIgnoreCase("admin")) {
					return 1;
				} else {
					return 0;
				}
			} else {
				// No matching record found
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public int addProduct(Laptop laptopModel) {
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(StringUtils.INSERT_LAPTOP)) {
			st.setString(1, laptopModel.getLaptopName());
			st.setDouble(2, laptopModel.getUnitPrice());
			st.setInt(3, laptopModel.getStockLevel());
			st.setString(4, laptopModel.getLaptopDescription());
			st.setString(5, laptopModel.getProcessor());
			st.setString(6, laptopModel.getRAM());
			st.setString(7, laptopModel.getStorage());
			st.setString(8, laptopModel.getScreen());
			st.setString(9, laptopModel.getGraphics());
			st.setString(10, laptopModel.getUserImageUrl());

			int result = st.executeUpdate();

			if (result > 0) {
				return 1; // Success
			} else {
				return 0; // No rows affected
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1; // Error
		}
	}

	public ArrayList<Laptop> getAllLaptops() {
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM laptop");
				ResultSet rs = stmt.executeQuery()) {

			ArrayList<Laptop> laptops = new ArrayList<>();
			while (rs.next()) {
				Laptop laptop = new Laptop();
				laptop.setLaptopName(rs.getString("laptopName"));
				laptop.setUnitPrice(rs.getDouble("unitPrice"));
				laptop.setStockLevel(rs.getInt("stockLevel"));
				laptop.setLaptopDescription(rs.getString("laptopDescription"));
				laptop.setProcessor(rs.getString("processor"));
				laptop.setRAM(rs.getString("RAM"));
				laptop.setStorage(rs.getString("Storage"));
				laptop.setScreen(rs.getString("Screen"));
				laptop.setGraphics(rs.getString("graphics"));
				laptop.setLaptopImageUrl(rs.getString("image"));
				laptops.add(laptop);
			}
			return laptops;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Exception in DatabaseController");
			return null;
		}
	}

	public int deleteLaptop(String laptopName) throws ClassNotFoundException {
	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement("DELETE FROM laptop WHERE laptopName = ?")) {
	        st.setString(1, laptopName);
	        int rowsAffected = st.executeUpdate();
	        return rowsAffected;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -1; // Or handle the error appropriately based on your application's logic
	    }
	}
	public int updateLaptop(Laptop laptop) {
        try (Connection con = getConnection();
        		PreparedStatement st = con.prepareStatement("UPDATE `laptop` SET  `unitPrice`=?, `stockLevel`=?, `laptopDescription`=?, `processor`=?, `RAM`=?, `Storage`=?, `graphics`=? WHERE `laptopName`=?")) {
            st.setInt(1, laptop.getPrice());
            st.setInt(2, laptop.getStockLevel());
            st.setString(3, laptop.getLaptopDescription());
            st.setString(4, laptop.getProcessor());
            st.setString(5, laptop.getRAM1());
            st.setString(6, laptop.getStorage());
            st.setString(7, laptop.getGraphics());
            st.setString(8, laptop.getLaptop_name());

            int result = st.executeUpdate();

            if (result > 0) {
                System.out.println("Successful");
                return 1; // Success
            } else {
                System.out.println("No record update");
                return 0; // No rows affected
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
}
