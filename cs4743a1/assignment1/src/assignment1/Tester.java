package assignment1;

import java.io.IOException;

public class Tester {
	
	public static void main(String args[]) {
		PartsInventoryModel pim = new PartsInventoryModel();
	//	PartsInventoryView pview = new PartsInventoryView(pim);
		for (int i = 1; i < 8; i++) {
			try {
				Part p = new Part(i, "MyPartName" + i, "MyPartNumber" + i, "Vendor" + i);
				pim.addPart(p);
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		pim.printInventory();
		pim.deletePart("MyPartName5");
		Part p = pim.findPartName("MyPartName3");
		try {
			pim.editPart(p, p.getQuantity(), p.getPartName(), p.getPartNumber(), "SomeOtherVendor");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		pim.printInventory();
	}
}
