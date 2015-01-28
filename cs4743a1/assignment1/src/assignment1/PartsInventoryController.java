package assignment1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PartsInventoryController implements ActionListener, ListSelectionListener {
	private PartsInventoryModel partsInventoryModel;
	private PartsInventoryView inventoryView;
	private PartView partView;
	private boolean passesRequirements;
	private JButton lastButtonClicked;
	private Color lastButtonOriginalColor;
	private Color highlightedColor = new Color(255, 255, 153);
	private List<Part> selectedParts = null;
	
		public PartsInventoryController(PartsInventoryModel inventoryModel, PartsInventoryView inventoryView) {
			lastButtonClicked = null;
			this.partsInventoryModel = inventoryModel;
			this.inventoryView = inventoryView;
			passesRequirements = true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch(command) {
				case "Add": 
					partView = new PartView(partsInventoryModel);
					partView.register(this);
					break;
				case "Delete":
					if (selectedParts != null) {
						for (Part p : selectedParts) {
							partsInventoryModel.deletePart(p);
						}
						selectedParts = null;
						inventoryView.updatePanel();
						inventoryView.repaint();
					}
					break;
				case "Edit":
					break;
				case "View":
					if (selectedParts != null) {
						for (Part p : selectedParts) {
							partView = new PartView(partsInventoryModel);
							partView.register(this);
							partView.disableEdit();
							partView.setName(p.getPartName());
							partView.setNumber(p.getPartNumber());
							partView.setVendor(p.getVendor());
							partView.setQuantity(p.getQuantity());
						}
					selectedParts = null;
					inventoryView.updatePanel();
					inventoryView.repaint();
					}
					break;
				case "OK":
					try {
						Part part = new Part(partView.getQuantity(), partView.getName(), partView.getNumber(), partView.getVendor());		
						partsInventoryModel.addPart(part);
						partView.dispose();
						inventoryView.updatePanel();
						inventoryView.repaint();
					}
					catch (NumberFormatException noint) {
						partView.setErrorMessage("Error: quantity must be a positive integer.");
						//partView.setErrorMessage(noint.getMessage());
						//System.out.println(noint.getMessage());
					}
					catch (IOException ioex) {
						partView.setErrorMessage(ioex.getMessage());
						//System.out.println(ioex.getMessage());
					}
					catch (Exception ex) {
						partView.setErrorMessage(ex.getMessage());
						//System.out.println(ex.getMessage());
					}
					/*
					try {
						if (passesRequirements == true) {
							if (partView.getNumber().length() > Part.getMaxPartNumberLength()) {
								System.out.println("Number length exceeds maximum. Must be " + Part.getMaxPartNumberLength() + " characters or less. Try again.");
								passesRequirements = false;
							}	
							if (partView.getName().length() > Part.getMaxPartNameLength()) {
								System.out.println("Name length exceeds maximum. Must be " + Part.getMaxPartNameLength() + " characters or less. Try again.");
								passesRequirements = false;
							}
							if (partView.getVendor().length() > Part.getMaxVendorLength()) {
								System.out.println("Vendor length exceeds maximum. Must be " + Part.getMaxVendorLength() + " characters or less. Try again.");
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
							Part part = new Part(partView.getQuantity(), partView.getName(), partView.getNumber(), partView.getVendor());		
							partsInventoryModel.addPart(part);
							partView.dispose();
							inventoryView.updatePanel();
							inventoryView.repaint();

						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					*/
					break;
				case "Cancel":
					partView.dispose();
					break;
				case "Part Name":
					ChangeButtonColors(e);
					partsInventoryModel.sortByPartName();
					inventoryView.updatePanel();
					inventoryView.repaint();
					break;
				case "Part #":
					ChangeButtonColors(e);
					partsInventoryModel.sortByPartNumber();
					inventoryView.updatePanel();
					inventoryView.repaint();
					break;
				case "Vendor":
					ChangeButtonColors(e);
					partsInventoryModel.sortByVendor();
					inventoryView.updatePanel();
					inventoryView.repaint();
					break;
				case "Quantity":
					ChangeButtonColors(e);
					partsInventoryModel.sortByQuantity();
					inventoryView.updatePanel();
					inventoryView.repaint();
					break;
					
			}
		}
		
		// restores previous button clicked to its original color; changes color of current button clicked 
		void ChangeButtonColors(ActionEvent e) {
			if (lastButtonClicked != null) {
				lastButtonClicked.setBackground(lastButtonOriginalColor);
			}
			lastButtonClicked = (JButton) e.getSource();
			lastButtonOriginalColor = lastButtonClicked.getBackground();
			lastButtonClicked.setBackground(highlightedColor); // indicates the most recently clicked button
		}

		// When the user clicks on an element in the inventory list, event is triggered: gets Part from index of list element
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				JList<Part> j = (JList<Part>) e.getSource();
				//selectedPart = (Part) j.getModel().getElementAt(j.getSelectedIndex()); // only works on first selected element
				selectedParts = j.getSelectedValuesList(); // operates on all selected elements (Shift + Click or Ctrl + Click)
			}
		}
}
