package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.model.*;

class TestAddItem {

	@Test
	void TestItemCreation() {
		ShoppingItem i1 = new ShoppingItem("Cheese", 0);
		assertEquals("Cheese", i1.getItemName());
		assertEquals(0, i1.getItemQuant());
		
		ShoppingItem i2 = new ShoppingItem("1 Gallon 2% Milk", 2);
		assertEquals("1 Gallon 2% Milk", i2.getItemName());
		assertEquals(2, i2.getItemQuant());
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				()->{
					ShoppingItem i3 = new ShoppingItem(null, -1);
					assertEquals(null, i3.getItemName());
					assertEquals(-1, i3.getItemQuant());
				});
				assertEquals("All parameters must be properly filled out!", ex.getMessage());
				
		IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
				()->{
					ShoppingItem i4 = new ShoppingItem("", -2);
					assertEquals("", i4.getItemName());
					assertEquals(-2, i4.getItemQuant());
				});
				assertEquals("All parameters must be properly filled out!", ex2.getMessage());
		
		// Tests the item setters + toString()
		i1.setItemName("Great Value Cheese");
		i1.setItemQuant(1);
		assertEquals("Great Value Cheese", i1.getItemName());
		assertEquals(1, i1.getItemQuant());
		assertEquals("Great Value Cheese, QTY: 1", i1.toString());
	}
	
	@Test
	void TestItemList() {
		ShoppingList listTest = new ShoppingList();
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				()->{
					ShoppingItem i1 = new ShoppingItem("Smuckers Uncrustables 10ct", 2);
					ShoppingItem i2 = new ShoppingItem("Pasta Sauce", 1);
					
					listTest.add(i1);
					listTest.add(i2);
					
					listTest.add(i1);
				});
				assertEquals("This item has already been added to the shopping list!", ex.getMessage());
	}
}
