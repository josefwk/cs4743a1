package assignment1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PartsInventoryModel {
	private List<Part> partsInventory;
	private Comparator<Part> sortingMode = Part.PartNameDescending; // default sort
	
	public PartsInventoryModel() {
		partsInventory = new ArrayList<Part>();
	}
	
	public void addPart(Part p) throws Exception {
		try {
			addPart(p.getQuantity(), p.getPartName(), p.getPartNumber(), p.getVendor());
		}
		catch (IOException e) {
			throw new IOException(e);
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void addPart(Integer quantity, String partName, String partNumber) throws Exception {
		try {
			addPart(quantity, partName, partNumber, "");
		}
		catch (IOException e) {
			throw new IOException(e);
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void addPart(Integer quantity, String partName, String partNumber, String vendor) throws Exception, IOException {
		if (quantity <= 0) {
			throw new IOException("Quantity for a newly listed item must be greater than zero.");
		}
		try {
			Part p = new Part(quantity, partName, partNumber, vendor);
			if (findPartName(p.getPartName()) != null) {
				throw new Exception("Part name \"" + p.getPartName() + "\" is already listed in inventory.");
			}
			partsInventory.add(p);
		}
		catch (IOException e) {
			throw new IOException(e);
		}
	}
	
	public Part findPartName(String partName) {
		if (partName.length() > Part.getMaxPartNameLength()) {
			partName = partName.substring(0, Part.getMaxPartNameLength());
		}
		for (Part part : partsInventory) { // this is O(n)
			if (part.getPartName().equals(partName)) {
				return part;
			}
		}
		return null;
	}
	
	public Part findPartNumber(String partNumber) {
		if (partNumber.length() > Part.getMaxPartNumberLength()) {
			partNumber = partNumber.substring(0, Part.getMaxPartNumberLength());
		}
		for (Part part : partsInventory) { // this is O(n)
			if (part.getPartNumber().equals(partNumber)) {
				return part;
			}
		}
		return null;
	}
	
	public void printInventory() {
		String horizontalSeparator = "";
		int recordNum = 1;
		for (int i = 0; i < 80; i++) {
			horizontalSeparator += '-';
		}
		System.out.printf("%8s   %17s   %17s   %8s   %17s\n",
				"Record #", "Part #", "Part Name", "Quantity", "Vendor");
		System.out.println(horizontalSeparator);
		for (Part part : partsInventory) {
			System.out.printf("%8s | %17s | %17s | %8s | %17s\n",
					recordNum++, part.getPartNumber(), part.getPartName(), part.getQuantity(), part.getVendor());
			System.out.println(horizontalSeparator);
		}
	}
	
	public int getSize() {
		return partsInventory.size();
	}
	
	public void sortByQuantityDescending() {
		sortingMode = Part.QuantityDescending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByQuantityAscending() {
		sortingMode = Part.QuantityAscending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByPartNameDescending() {
		sortingMode = Part.PartNameDescending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByPartNameAscending() {
		sortingMode = Part.PartNameAscending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByPartNumberDescending() {
		sortingMode = Part.PartNumberDescending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByPartNumberAscending() {
		sortingMode = Part.PartNumberAscending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByVendorDescending() {
		sortingMode = Part.VendorDescending;
		partsInventory.sort(sortingMode);
	}
	
	public void sortByVendorAscending() {
		sortingMode = Part.VendorAscending;
		partsInventory.sort(sortingMode);
	}
	
	
}
