package assignment1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PartsInventoryController implements ActionListener {
	private PartsInventoryModel partsInventoryModel;
	private PartsInventoryView inventoryView;
	private PartView partView;
	private List<Part> partsInventory;
	private Part part;
	private boolean passesRequirements;
	
		public PartsInventoryController(PartsInventoryView inventoryView) {
			this.inventoryView = inventoryView;
			partsInventoryModel = new PartsInventoryModel();
			passesRequirements = true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch(command) {
				case "Add": 
					partView = new PartView(partsInventoryModel);
					partView.register(this);
					//if (command == "OK") {
					//	System.out.println("controller");
						// Part p = new Part();
					//}
					System.out.println("Add");
					break;
				case "Delete":
					System.out.println("Delete");
					break;
				case "Edit":
					break;
				case "View":
					System.out.println("View");
					break;
				case "OK":
					try {
						if (passesRequirements == true) {
							if (partView.getNumber().length() > 20) {
								System.out.println("Number length exceeds maximum. Must be 20 characters or less. Try again.");
								passesRequirements = false;
							}	
							if (partView.getName().length() > 255) {
								System.out.println("Name length exceeds maximum. Must be 255 characters or less. Try again.");
								passesRequirements = false;
							}
							if (partView.getVendor().length() > 255) {
								System.out.println("Vendor length exceeds maximum. Must be 255 characters or less. Try again.");
								passesRequirements = false;
							}
							if (partView.getQuantity().intValue() < 1) {
								System.out.println("Quantity is less than 1. Must be greater than 0. Try again.");
								passesRequirements = false;
							}
							if (passesRequirements == false)
								partView.dispose();
						} 
						if (passesRequirements == true) {
							part = new Part(partView.getQuantity(), partView.getName(), partView.getNumber(), partView.getVendor());		
							partsInventoryModel.addPart(part);
							partView.dispose();
						}
						passesRequirements = true;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;
				case "Cancel":
					partView.dispose();
			}
			
		}
}
