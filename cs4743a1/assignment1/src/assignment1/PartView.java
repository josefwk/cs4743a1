package assignment1;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PartView extends JFrame {
	private PartsInventoryModel model;
	private PartsInventoryPanel panel;
	private JPanel partFrame;
	private JButton cancel, ok;
	private JLabel partName, partNumber, partVendor, partQuantity;
	private JTextField nameField, numberField, vendorField, quantityField;
	
	public PartView(PartsInventoryModel model) {
		super("My Selected Part");
			this.model = model;
			panel = new PartsInventoryPanel(model, this);
			this.add(panel, BorderLayout.CENTER);
			this.setSize(340, 250);
			this.setVisible(true);
			this.setLocation(900, 250);
			
			partFrame = new JPanel();
			partFrame.setBackground(Color.LIGHT_GRAY);
			partFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(partFrame);
			partFrame.setLayout(null);
			
			partName = new JLabel("Name");
			partName.setBounds(15, 15, 70, 30);
			partFrame.add(partName);
			
			partNumber = new JLabel("#");
			partNumber.setBounds(15, 45, 70, 30);
			partFrame.add(partNumber);
			
			partVendor = new JLabel("Vendor");
			partVendor.setBounds(15, 75, 70, 30);
			partFrame.add(partVendor);
			
			partQuantity = new JLabel("Quantity");
			partQuantity.setBounds(15, 105, 70, 30);
			partFrame.add(partQuantity);
			
			cancel = new JButton("Cancel");
			cancel.setBounds(225, 150, 75, 25);
			partFrame.add(cancel);
			
			ok = new JButton("OK");
			ok.setBounds(155, 150, 70, 25);
			partFrame.add(ok);
			
			nameField = new JTextField();
			nameField.setBounds(100, 20, 200, 20);
			partFrame.add(nameField);
			
			numberField = new JTextField();
			numberField.setBounds(100, 50, 200, 20);
			partFrame.add(numberField);
			
			vendorField = new JTextField();
			vendorField.setBounds(100, 80, 200, 20);
			partFrame.add(vendorField);
			
			quantityField = new JTextField();
			quantityField.setBounds(100, 110, 200, 20);
			partFrame.add(quantityField);
	}
	
	public void register(PartsInventoryController controller) {
		ok.addActionListener(controller);
		cancel.addActionListener(controller);
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public String getNumber() {
		return numberField.getText();
	}
	
	public String getVendor() {
		return vendorField.getText();
	}
	
	public Integer getQuantity() {
		return Integer.parseInt(quantityField.getText());
	}
}
