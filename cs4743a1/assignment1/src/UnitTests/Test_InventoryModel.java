package UnitTests;
import assignment1.PartsInventoryModel;
import assignment1.Part;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Test_InventoryModel {

	PartsInventoryModel pim;
	Integer quantity;
	String partName;
	String partNumber;
	String vendor;
	Part p;
	
	@Before 
	public void setUp() {
		quantity = 1;
		partName = "The Part Name v1.0";
		partNumber = "18J-2015A1";
		vendor = "The_Vendor @ 1 UTSA Cir";
		pim = new PartsInventoryModel();
	}
	
	@Test
	public void testInventoryModel_AddPartAsObject() {
		try {
			p = new Part(quantity, partName, partNumber);
			pim.addPart(p);
			assertTrue(pim.getSize() == 1);
			assertTrue(pim.findPartName(partName).getPartName().equals(partName)); // If found, a Part is returned - validate partName match
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test (expected = Exception.class)
	public void testInventoryModel_AddPartAsNullObject() throws Exception {
		try {
			p = null;
			pim.addPart(p);
			fail("Should have thrown an exception: null Part object was added.");
		}
		catch (IOException e) {
			fail("Should not have thrown an IOException, just an Exception: null Part object was added.");
		}
		catch (Exception e) {
			throw new Exception(e);
		}	
	}
	
	@Test
	public void testInventoryModel_AddPart() {
		try {
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test (expected = IOException.class)
	public void testInventoryModel_AddPartWithNegativeQuantity() throws IOException {
		try {
			pim.addPart(-1, partName, partNumber, vendor);
			fail("Should have thrown an exception: Part with negative quantity was added.");
		}
		catch (IOException e) {
			throw new IOException(e);
		}
		catch (Exception e) {
			fail("Should have thrown an IOException, not an Exception: Part with negative quantity was added.");
		}	
	}
	
	@Test (expected = IOException.class)
	public void testInventoryModel_AddPartWithZeroQuantity() throws IOException {
		try {
			pim.addPart(-1, partName, partNumber, vendor);
			fail("Should have thrown an IOException: Part with zero quantity was added.");
		}
		catch (IOException e) {
			throw new IOException(e);
		}
		catch (Exception e) {
			fail("Should have thrown an IOException, not an Exception: Part with negative quantity was added.");
		}	
	}
	
	// To test exception chaining from the Part class through the PartsInventoryModel class
	@Test (expected = IOException.class)
	public void testInventoryModel_AddPartWithPartNumberExceedingMaxLength() throws IOException {
		String longPartNumber = "";
		for (int i = 0; i < Part.getMaxPartNumberLength(); i++) {
			longPartNumber = longPartNumber + "A"; // add one letter to the string
		}
		try {
			pim.addPart(-1, partName, longPartNumber, vendor);
			fail("Should have thrown an IOException: Part with partNumber exceeding max length was added.");
		}
		catch (IOException e) {
			throw new IOException(e);
		}
		catch (Exception e) {
			fail("Should have thrown an IOException, not an Exception: Part with partNumber exceeding max length was added.");
		}	
	}
	
	@Test (expected = Exception.class)
	public void testInventoryModel_AddPartWithDuplicateName() throws Exception {
		try {
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
			pim.addPart(quantity, partName, partNumber, vendor);
			fail("Should have thrown an exception: attempted to add Part with duplicate name.");
		}
		catch (IOException e) {
			fail("Should have thrown an Exception, not an IOException: attempted to add Part with duplicate name.");
		}
		catch (Exception e) {
			throw new Exception(e);
		}	
	}
	
	
	@Test
	public void testInventoryModel_AddOneThousandUniqueParts() {
		try {
			for (int i = 0; i < 1000; i++) {
				pim.addPart(quantity + i, partName + "_" + i, partNumber + "_" + i, vendor);
			}
			assertTrue(pim.getSize() == 1000);
			assertTrue(pim.findPartName(partName + "_" + 500) != null); // should find a valid Part object
			assertTrue(pim.findPartName("NoSuch" + "_" + 500) == null); // should not find a partName that was not added
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test
	public void testInventoryModel_DeletePartByObjectReference() {
		try {
			Part p = null;
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
			assertTrue((p = pim.findPartName(partName)) != null); // should find a valid Part object
			pim.deletePart(p);
			assertTrue(pim.getSize() == 0);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test
	public void testInventoryModel_DeletePartByPartName() {
		try {
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
			assertTrue(pim.findPartName(partName) != null); // should find a valid Part object
			pim.deletePart(partName);
			assertTrue(pim.getSize() == 0);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test
	public void testInventoryModel_DeleteNonExistentPart() {
		try {
			Part p = new Part(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 0);
			pim.deletePart(p);
			assertTrue(pim.getSize() == 0);
			pim.deletePart(partName);
			assertTrue(pim.getSize() == 0);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test
	public void testInventoryModel_EditPartWithObjectReference() {
		try {
			Part partOriginal = null;
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
			assertTrue((partOriginal = pim.findPartName(partName)) != null); // should find a valid Part object
			Part partReplace = new Part(42, "ThisNewPartName", partNumber, "DifferentVendor");
			pim.editPart(partOriginal, partReplace);
			assertTrue(pim.findPartName(partName) == null); // should not find the old Part name
			assertTrue(pim.findPartName("ThisNewPartName") != null); // should find the new Part name
			assertTrue(pim.getSize() == 1);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}
	
	@Test
	public void testInventoryModel_EditPartWithParameters() {
		try {
			Part partOriginal = null;
			pim.addPart(quantity, partName, partNumber, vendor);
			assertTrue(pim.getSize() == 1);
			assertTrue((partOriginal = pim.findPartName(partName)) != null); // should find a valid Part object
			pim.editPart(partOriginal, 42, "ThisNewPartName", partNumber, "DifferentVendor");
			assertTrue(pim.findPartName(partName) == null); // should not find the old Part name
			assertTrue(pim.findPartName("ThisNewPartName") != null); // should find the new Part name
			assertTrue(pim.getSize() == 1);
		}
		catch (IOException e) {
			fail("IOException thrown during unexceptional part creation: \n\t" + e);
		}
		catch (Exception e) {
			fail("Exception thrown during unexceptional part creation: \n\t" + e);
		}	
	}

}
