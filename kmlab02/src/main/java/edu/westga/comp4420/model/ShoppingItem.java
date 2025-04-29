package edu.westga.comp4420.model;

/**
* Model class ShoppingItem, used to create an item to add to a shopping list
*
* @author KYLE_M
* @version Spring 2025
*/
public class ShoppingItem {
	
	private String itemName = "";
	
	private int itemQuant = 0;
	
	/**
	 * Default constructor
	 * 
	 * @precondition name != null && quant >= 0
	 * @postcondition getItemName() == itemName && getItemQuant() == itemQuant
	 * 
	 * @param name - the name of the item to add to the list
	 * @param quant - the quantity of the item to add to the list
	 */
	public ShoppingItem(String name, int quant) {
		if (name == null || name.isEmpty() || quant < 0) {
			throw new IllegalArgumentException("All parameters must be properly filled out!");
		}
		this.itemName = name;
		this.itemQuant = quant;
	}

	/**
	 * Gets the item's name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the item's name
	 * 
	 * @param itemName - the name of the item
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Returns the amount of a particular item on a list
	 */
	public String getItemQuant() {
		int temp = this.itemQuant;
		String result = String.valueOf(temp);
		return result;
	}

	/**
	 * Sets the item's quantity
	 * 
	 * @param quant - the amount of a particular item on a list
	 */
	public void setItemQuant(int quant) {
		this.itemQuant = quant;
	}
	
	@Override
	public String toString() {
		return (this.itemName + ", QTY: " + this.itemQuant);
	}
}
