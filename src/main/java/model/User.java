package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class User {
	private String firstName;
	private String lastname;
	private String location;
	private LocalDate dob;
	private String gender;
	private String email;
	private String phoneNumber;
	private String username;
	private String password;
	private String imageUrlFromPart;
	private String role;
	private Part imagePart;
	public User(String firstName, String lastname, String location, LocalDate dob, String gender,
			String email, String phoneNumber, String username, String password, String role, Part imagePart) {
		super();
		this.firstName = firstName;
		this.lastname = lastname;
		this.location = location;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.role = role;
		this.imagePart = imagePart;
		this.imageUrlFromPart = getImageUrl(imagePart);
		
	}
	public Part getImagePart() {
		return imagePart;
	}
	public void setImagePart(Part imagePart) {
		this.imagePart = imagePart;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role){
		this.role = role;
	}
	public String getRole(){
		return role;
	}
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart= "download.png";
		}
		return imageUrlFromPart;
	}

}
