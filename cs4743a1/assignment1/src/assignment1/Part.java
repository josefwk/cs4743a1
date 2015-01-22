package assignment1;

import java.io.IOException;
import java.util.Comparator;

public class Part implements Comparable<Part> {
	Integer quantity = 0;
	String partName = "";
	String partNumber = "";
	String vendor = "";
	
	private static int maxPartNameLength = 255;
	private static int maxPartNumberLength = 20;
	private static int maxVendorLength = 255;
	
	public Part(Integer quantity, String partName, String partNum) throws IOException {
		try {
			setQuantity(quantity);
			setPartName(partName);
			setPartNumber(partNum);
		}
		catch (IOException e) {
			throw new IOException("Exception thrown during Part creation: \n\t" + e);
		}
	}
	
	public Part(Integer quantity, String partName, String partNum, String vendor) throws IOException {
		this(quantity, partName, partNum);
		setVendor(vendor);
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public String getPartName() {
		return this.partName;
	}
	
	public String getPartNumber() {
		return this.partNumber;
	}
	
	public String getVendor() {
		return this.vendor;
	}
	
	public static int getMaxPartNameLength() {
		return maxPartNameLength;
	}
	
	public static int getMaxPartNumberLength() {
		return maxPartNumberLength;
	}
	
	public static int getMaxVendorLength() {
		return maxVendorLength;
	}
	
	void setQuantity(Integer quantity) throws IOException {
		if (quantity < 0) {
			throw new IOException("Error: cannot assign negative value to quantity.");
		}
		else {
			this.quantity = quantity;
		}
	}
	
	void setPartName(String partName) throws IOException {
		if (partName.length() > maxPartNameLength) {
			throw new IOException("Error: part name is too long (" + maxPartNameLength + " characters max).");
		}
		else {
			this.partName = partName;
		}
	}
	
	void setPartNumber(String partNumber) throws IOException {
		if (partNumber.length() > maxPartNumberLength) {
			throw new IOException("Error: part number is too long (" + maxPartNumberLength + " characters max).");
		}
		else {
			this.partNumber = partNumber;
		}
	}
	
	void setVendor(String vendor) throws IOException {
		if (vendor.length() > maxVendorLength) {
			throw new IOException("Error: part name is too long (" + maxVendorLength + " characters max).");
		}
		else {
			this.vendor = vendor;
		}
	}

	// used to sort by part name in descending order
	public static Comparator<Part> QuantityDescending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			Integer quantity1 = part.getQuantity();
			Integer quantity2 = anotherPart.getQuantity();
			return quantity1.compareTo(quantity2);
		}
	};
	
	// used to sort by part name in ascending order
	public static Comparator<Part> QuantityAscending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			Integer quantity1 = part.getQuantity();
			Integer quantity2 = anotherPart.getQuantity();
			return quantity2.compareTo(quantity1);
		}
	};
	// used to sort by part name in descending order
	public static Comparator<Part> PartNameDescending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String partName1 = part.getPartName().toUpperCase();
			String partName2 = anotherPart.getPartName().toUpperCase();
			return partName1.compareTo(partName2);
		}
	};
	
	// used to sort by part name in ascending order
	public static Comparator<Part> PartNameAscending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String partName1 = part.getPartName().toUpperCase();
			String partName2 = anotherPart.getPartName().toUpperCase();
			return partName2.compareTo(partName1);
		}
	};
	
	// used to sort by part number in descending order
	public static Comparator<Part> PartNumberDescending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String partNumber1 = part.getPartNumber().toUpperCase();
			String partNumber2 = anotherPart.getPartNumber().toUpperCase();
			return partNumber1.compareTo(partNumber2);
		}
	};
	
	// used to sort by part number in ascending order
	public static Comparator<Part> PartNumberAscending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String partNumber1 = part.getPartNumber().toUpperCase();
			String partNumber2 = anotherPart.getPartNumber().toUpperCase();
			return partNumber2.compareTo(partNumber1);
		}
	};
	
	// used to sort by part number in ascending order
	public static Comparator<Part> VendorDescending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String vendor1 = part.getVendor().toUpperCase();
			String vendor2 = anotherPart.getVendor().toUpperCase();
			return vendor1.compareTo(vendor2);
		}
	};
	
	// used to sort by part number in ascending order
	public static Comparator<Part> VendorAscending = new Comparator<Part>() {
		public int compare(Part part, Part anotherPart) {
			String vendor1 = part.getVendor().toUpperCase();
			String vendor2 = anotherPart.getVendor().toUpperCase();
			return vendor2.compareTo(vendor1);
		}
	};
	
	@Override
	public int compareTo(Part o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
