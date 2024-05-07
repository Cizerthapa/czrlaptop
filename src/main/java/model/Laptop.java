package model;

import java.io.File;

import javax.servlet.http.Part;

import util.StringUtils;

public class Laptop {
	private String laptopName;
	private String laptop_name;
	private double unitPrice;
	private int price;
	private int stockLevel;
	private String laptopDescription;
	private String processor;
	private String rAM;
	private String storage;
	private String screen;
	private String graphics;
	private Part img;
	private String laptopImageUrlStr;
	private String RAM;

	public Laptop(String laptopName, double unitPrice, int stockLevel, String laptopDescription, String processor,
			String rAM, String storage, String screen, String graphics, Part laptopImageUrlStr) {
		this.laptopName = laptopName;
		this.unitPrice = unitPrice;
		this.stockLevel = stockLevel;
		this.laptopDescription = laptopDescription;
		this.processor = processor;
		this.rAM = rAM;
		this.storage = storage;
		this.screen = screen;
		this.graphics = graphics;
		this.laptopImageUrlStr = getImageUrl(laptopImageUrlStr);
	}

	public Laptop() {
	}

	public Laptop(String laptop_name, int price ,int stockLevel,String laptopDescription ,String processor,String graphics,String RAM) {
		 this.laptop_name =laptop_name;
		 this.price=price;
		 this.stockLevel =  stockLevel;
		 this.laptopDescription = laptopDescription; 
		 this.processor = processor;
		 this.graphics = graphics;
		 this.RAM = RAM;
    }

	public String getLaptop_name() {
		return laptop_name;
	}

	public void setLaptop_name(String laptop_name) {
		this.laptop_name = laptop_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getrAM() {
		return rAM;
	}

	public void setrAM(String rAM) {
		this.rAM = rAM;
	}
	
	public String getRAM1() {
		return RAM;
	}

	public void setRAM1(String RAM) {
		this.RAM = RAM;
	}

	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		this.img = img;
	}

	public String getLaptopImageUrlStr() {
		return laptopImageUrlStr;
	}

	public void setLaptopImageUrlStr(String laptopImageUrlStr) {
		this.laptopImageUrlStr = laptopImageUrlStr;
	}

	public String getLaptopName() {
		return laptopName;
	}

	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	public String getLaptopDescription() {
		return laptopDescription;
	}

	public void setLaptopDescription(String laptopDescription) {
		this.laptopDescription = laptopDescription;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getRAM() {
		return rAM;
	}

	public void setRAM(String rAM) {
		this.rAM = rAM;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getGraphics() {
		return graphics;
	}

	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}

	public String getLaptopImageUrl() {
		return laptopImageUrlStr;
	}

	public void setLaptopImageUrl(String laptopImageUrl) {
		this.laptopImageUrlStr = laptopImageUrl;
	}

	public String getUserImageUrl() {
		return laptopImageUrlStr;
	}

	public void setUserImageUrl(Part part) {
		this.laptopImageUrlStr = getImageUrl(part);
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.laptopImageUrlStr = imageUrl;
	}

	private String getImageUrl(Part part) {
		if (part == null) {
			return "default_image.jpg"; // Return a default image URL or handle this case as per your requirement
		} else {
			String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
			File fileSaveDir = new File(savePath);
			String laptopImageUrl = null;
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			String contentDisp = part.getHeader("content-disposition");
			String[] items = contentDisp.split(";");
			for (String s : items) {
				if (s.trim().startsWith("filename")) {
					laptopImageUrl = s.substring(s.indexOf("=") + 2, s.length() - 1);
				}
			}
			// Check if laptopImageUrl is null or empty after the loop
			if (laptopImageUrl == null || laptopImageUrl.isEmpty()) {
				laptopImageUrl = "download.jpg";
			}
			return laptopImageUrl;
		}
	}
}