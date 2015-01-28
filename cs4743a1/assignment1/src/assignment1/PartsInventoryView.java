package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PartsInventoryView extends JFrame {	
	PartsInventoryModel model;
	PartsInventoryPanel panel;
	private JPanel inventoryFrame, inventoryPanel;
	private JButton addPart, editPart, deletePart, viewPart;
	private JTextArea inventoryArea;
	private JList inventoryList;
	private String[] names = {"ryan", "dallas", "austin"};
	
	public PartsInventoryView(PartsInventoryModel model) {
		super("Cabinetron");
		this.model = model;
		panel = new PartsInventoryPanel(model, this);
		this.add(panel, BorderLayout.CENTER);
		this.setSize(400, 500);
		this.setVisible(true);
		this.setLocation(400, 250);
		
		// Sets up the inventory frame 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryFrame = new JPanel();
		inventoryFrame.setBackground(Color.LIGHT_GRAY);
		inventoryFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(inventoryFrame);
		inventoryFrame.setLayout(null);
				
		// Creates and adds the "add" button to the inventory frame
		addPart = new JButton("Add");
		addPart.setBounds(15, 410, 70, 30);
		inventoryFrame.add(addPart);
		
		// Creates and adds the "delete" button to the inventory frame
		deletePart = new JButton("Delete");
		deletePart.setBounds(105, 410, 70, 30);
		inventoryFrame.add(deletePart);
		
		// Creates and adds the "view" button to the inventory frame
		viewPart = new JButton("View");
		viewPart.setBounds(295, 410, 70, 30);
		inventoryFrame.add(viewPart);
		
		// Creates the panel to hold the parts within the inventory frame
		inventoryPanel = new JPanel();
		inventoryPanel.setBackground(Color.WHITE);
		inventoryPanel.setBounds(15, 15, 350, 350);
		inventoryFrame.add(inventoryPanel);
		inventoryPanel.setLayout(null);
		
		inventoryList = new JList(names);
		inventoryList.setBackground(Color.WHITE);
		inventoryList.setBounds(15, 15, 350, 350);
		inventoryFrame.add(inventoryList);
		inventoryList.setLayout(new FlowLayout());
		
		// Creates the scroll pane to navigate a large inventory in the inventory panel
		//inventoryArea = new JTextArea();
		//inventoryArea.setEditable(false);
		//inventoryScroll = new JScrollPane(inventoryArea);
		//inventoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//inventoryScroll.setBounds(5, 0, 345, 345);
		//inventoryPanel.add(inventoryScroll);
		//inventoryArea.addMouseListener(l);
		//inventoryArea.append("test");
	}
	
	public void register(PartsInventoryController controller) {
		addPart.addActionListener(controller);
		deletePart.addActionListener(controller);
		viewPart.addActionListener(controller);
	}
}
