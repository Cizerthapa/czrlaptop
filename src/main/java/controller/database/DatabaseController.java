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

	public int userAdd(User userModel) {
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(StringUtils.INSERT_CUSTOMER);

            statement.setString(1, userModel.getUsername());
            statement.setString(2, userModel.getfirstName());
            statement.setString(3, userModel.getLastname());
            statement.setDate(4, Date.valueOf(userModel.getDob()));
            statement.setString(5, userModel.getGender());
            statement.setString(6, userModel.getEmail());
            statement.setString(7, userModel.getLocation());
            statement.setString(8, userModel.getPhoneNumber());
            statement.setString(9, userModel.getPassword());
            statement.setString(10, "user");
            statement.setString(11, userModel.getImageUrlFromPart());

            int result = statement.executeUpdate();

            return result > 0 ? 1 : 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return -1;
        }
    }

	public int getLoginStatus(String userName, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(StringUtils.GET_LOGIN_CUSTOMER_INFO);
            statement.setString(1, userName);
            statement.setString(2, PasswordEncryptionWithAes.encrypt(userName, password));
            ResultSet rs = statement.executeQuery();
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

	public int getPassword(String userName, String password) {
        boolean checker = isuserNameExists(userName);
        if (checker) {
            System.out.println("There is user");
        }

        String usr = userName;
        String pass = password;

        System.out.println(usr + " " + pass);
        // Try-catch block to handle potential SQL or ClassNotFound exceptions
        try {
            // Prepare a statement using the predefined query for login check
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM CUSTOMER WHERE user_name = ?");

            // Set the userName in the first parameter of the prepared statement
            statement.setString(1, userName);

            // Execute the query and store the result set
            ResultSet result = statement.executeQuery();

            // Check if there's a record returned from the query
            if (result.next()) {
                // Get the userName from the database
                String userDatabase = result.getString(StringUtils.USER_NAME);

                // Get the password from the database
                String encryptedPwd = result.getString(StringUtils.PASSWORD);
                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDatabase);
                // Check if the userName and password match the credentials from the database
                if (userDatabase.equals(userName) && decryptedPwd.equals(password)) {
                    // Login successful, return 1
                    return 1;
                } else {
                    // userName or password mismatch, return 0
                    return 0;
                }
            } else {
                // userName not found in the database, return -1
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

	public boolean isuserNameExists(String userName) {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(StringUtils.GET_CUSTOMER_USR_COUNT)) {
            statement.setString(1, userName);
            try (ResultSet rs = statement.executeQuery()) {
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

	public String getuserNameFromEmail(String email) {
		try (Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT user_name FROM CUSTOMER WHERE email = ?")) {
			statement.setString(1, email);
			try (ResultSet rs = statement.executeQuery()) {
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

	public String getuserNameFromPhone(String phone) {
		try (Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT user_name FROM CUSTOMER WHERE contactNumber = ?")) {
			statement.setString(1, phone);
			try (ResultSet rs = statement.executeQuery()) {
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

	public String getuserName(String userName, String password) {
		try (Connection con = getConnection()) {
			PreparedStatement statement = con.prepareStatement(StringUtils.GET_LOGIN_CUSTOMER_INFO);
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// User name and password match in the database
				return userName;
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

	public String getNames(String userName) {
		try (Connection con = getConnection()) {
			PreparedStatement statement = con.prepareStatement(StringUtils.GET_NAMES);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
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

	public int checkuserNameFromDb(String userName) {
		try (Connection con = getConnection()) {
			PreparedStatement statement = con.prepareStatement(StringUtils.GET_USERNAME);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
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
			PreparedStatement statement = con.prepareStatement(StringUtils.GET_EMAIL);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
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
			PreparedStatement statement = con.prepareStatement(StringUtils.GET_PHONE);
			statement.setString(1, phone);
			ResultSet rs = statement.executeQuery();
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

	public boolean isExistsEmail(String email) {
		try (Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE email = ?")) {
			statement.setString(1, email);
			try (ResultSet rs = statement.executeQuery()) {
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
				PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE contactNumber = ?")) {
			statement.setString(1, phoneNumber);
			try (ResultSet rs = statement.executeQuery()) {
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
				PreparedStatement statement = con.prepareStatement("UPDATE CUSTOMER SET password = ? WHERE email = ?")) {

			if (isExistsEmail(email)) {
				statement.setString(1, PasswordEncryptionWithAes.encrypt(getuserNameFromEmail(email), password));
				statement.setString(2, email);

				int result = statement.executeUpdate();

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
				PreparedStatement statement = con
						.prepareStatement("UPDATE CUSTOMER SET password = ? WHERE contactNumber = ?")) {

			if (isPhoneNumberExists(phone)) {
				statement.setString(1, PasswordEncryptionWithAes.encrypt(getuserNameFromPhone(phone), password));
				statement.setString(2, phone);

				int result = statement.executeUpdate();

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

	public int isAdmin(String userName) {
		try (Connection con = getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT role FROM CUSTOMER WHERE user_name = ?")) {
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
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

	public int addLaptop(Laptop laptopModel) {
		try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(StringUtils.INSERT_LAPTOP)) {
			statement.setString(1, laptopModel.getLaptopName());
			statement.setDouble(2, laptopModel.getUnitPrice());
			statement.setInt(3, laptopModel.getStockLevel());
			statement.setString(4, laptopModel.getLaptopDescription());
			statement.setString(5, laptopModel.getProcessor());
			statement.setString(6, laptopModel.getRAM());
			statement.setString(7, laptopModel.getStorage());
			statement.setString(8, laptopModel.getScreen());
			statement.setString(9, laptopModel.getGraphics());
			statement.setString(10, laptopModel.getUserImageUrl());

			int result = statement.executeUpdate();

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
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM laptop");
				ResultSet rs = statement.executeQuery()) {

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
	         PreparedStatement statement = con.prepareStatement("DELETE FROM laptop WHERE laptopName = ?")) {
	    	statement.setString(1, laptopName);
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -1; // Or handle the error appropriately based on your application's logic
	    }
	}
	public int updateLaptop(Laptop laptop) {
        try (Connection con = getConnection();
        		PreparedStatement statement = con.prepareStatement("UPDATE `laptop` SET  `unitPrice`=?, `stockLevel`=?, `laptopDescription`=?, `processor`=?, `RAM`=?, `Storage`=?, `graphics`=? WHERE `laptopName`=?")) {
        	statement.setInt(1, laptop.getPrice());
        	statement.setInt(2, laptop.getStockLevel());
        	statement.setString(3, laptop.getLaptopDescription());
        	statement.setString(4, laptop.getProcessor());
        	statement.setString(5, laptop.getRAM1());
        	statement.setString(6, laptop.getStorage());
        	statement.setString(7, laptop.getGraphics());
        	statement.setString(8, laptop.getLaptop_name());

            int result = statement.executeUpdate();

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
	public int checkAndUpdatePassword(String usrName, String newPass) {
        try (Connection con = getConnection()) {
            // Check if the username exists in the database
            if (isuserNameExists(usrName)) {
                // Username exists, update the password
                return updatePwd(usrName, newPass);
            } else {
                // Username not found, return -1
                return -1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -3; // Error
        }
    }
	public int updatePwd(String usrName, String newPass) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE customer SET password = ? WHERE user_name = ?")) {
            st.setString(1, PasswordEncryptionWithAes.encrypt(usrName, newPass));
            st.setString(2, usrName);

            int result = st.executeUpdate();

            if (result > 0) {
                return 1; // Password updated successfully
            } else {
                return 0; // No rows affected (username not found)
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
	public User getUserLoginInfo(User user) {
        String userDb;
		try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM user WHERE phoneNumber = ?");

            // Set the username in the first parameter of the prepared statement
            st.setString(1, user.getUsername());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                userDb = rs.getString("userName");

                String encryptedPwd = rs.getString("password");

                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

                if (userDb.equalsIgnoreCase(user.getUsername()) && decryptedPwd != null && decryptedPwd.equals((user).getPassword())) {
                    String role = rs.getString("role"); // Assuming 'role' is the column name for the user's role
                    if (role != null) {
                        // User role found, return login result with role
                        return new User(userDb,1, role); // 1 indicates successful login
                    } else {
                        // Role not found, return login result without role
                        return new User(userDb,1, null); // 1 indicates successful login
                    }
                } else {
                    // User name or password mismatch, return login result without role
                    return new User(userDb,0, null); // 0 indicates username or password mismatch
                }
            } else {
                // User name not found in the database, return login result without role
                return new User("No User",-1, null); // -1 indicates username not found
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new User("No User",-2, null); // -2 indicates error
        }
    }
	public User getUserProfile(String username) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM customer WHERE user_name = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
            	User user = new User();
            	user.setUsername(rs.getString("user_name"));
            	user.setFirstName(rs.getString("f_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("contactNumber"));
                user.setDob(rs.getDate("dateofbirth").toLocalDate());
                user.setLocation(rs.getString("address"));
                user.setImageUrlFromPart("image");
                return user;
            } else {
                // User not found in the database
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
	public int updateProfile(User user) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE customer SET f_Name = ?, email = ?, contactNumber = ?, address = ? WHERE user_name"
             		+ "= ?")) {         
            st.setString(1, user.getFirstName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPhoneNumber());
            st.setString(4, user.getLocation());
            st.setString(5, user.getUsername());

            return st.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
}
