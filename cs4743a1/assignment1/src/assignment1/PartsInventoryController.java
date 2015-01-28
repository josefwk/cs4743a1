package assignment1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PartsInventoryController implements ActionListener {
	private PartsInventoryModel partsInventoryModel;
	private PartsInventoryView inventoryView;
	private PartView partView;
	private List<Part> partsInventory;
	
		public PartsInventoryController(PartsInventoryView inventoryView) {
			this.inventoryView = inventoryView;
			partsInventoryModel = new PartsInventoryModel();	
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
						partsInventoryModel.addPart(partView.getQuantity(), partView.getName(), partView.getNumber(), partView.getVendor());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println(partView.getQuantity());
					break;
				case "Cancel":
					partView.dispose();
			}
			
		}
}
