package assignment1;

import java.io.IOException;

public class Tester {
	private static PartsInventoryView partsInventoryView;
	private static PartsInventoryController partsInventoryController;
	private static PartsInventoryModel partsInventoryModel;
	
	public static void main(String args[]) {
		partsInventoryModel = new PartsInventoryModel();
	
		for (int i = 1; i < 8; i++) {
			try {
				Part p = new Part(i, "MyPartName" + i, "MyPartNumber" + i, "Vendor" + i);
				partsInventoryModel.addPart(p);
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		partsInventoryView = new PartsInventoryView(partsInventoryModel);
		
		partsInventoryController = new PartsInventoryController(partsInventoryModel, partsInventoryView);
			
		partsInventoryView.register(partsInventoryController);
		
		
		
	}
}
