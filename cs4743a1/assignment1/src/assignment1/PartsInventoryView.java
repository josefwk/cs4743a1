package assignment1;

import java.awt.BorderLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class PartsInventoryView extends JFrame {
	
	PartsInventoryModel model;
	PartsInventoryPanel panel;
	
	public PartsInventoryView(PartsInventoryModel model) {
		super("Parts Inventory View");
		this.model = model;
		panel = new PartsInventoryPanel(model, this);
		this.add(panel, BorderLayout.CENTER);
		this.setSize(400, 400);
		this.setVisible(true);
	}
}
