package edu.westga.comp4420.model;

import java.util.ArrayList;

/**
* Model class ShoppingList, used to create a shopping list
*
* @author KYLE_M
* @version Spring 2025
*/
public class ShoppingList {
	
	private ArrayList<ShoppingItem> list;
	
	/**
	 * Instantiates a new shopping list
	 * 
	 * @preconiditon none
	 * @postcondition none
	 */
	public ShoppingList() {
		this.list = new ArrayList<ShoppingItem>();
	}
	
	/**
	 * Adds a new item to the shopping list
	 * 
	 * @precondition isDuplicate == false
	 * @postconiditon a new item is added
	 * 
	 * @param item - the item to add
	 * @return true if added, false otherwise
	 */
	public boolean add(ShoppingItem item) {
		if (this.isDuplicate(item)) {
			throw new IllegalArgumentException("This item has already been added to the shopping list!");
		}
		return this.list.add(item);
	}
	
	/**
	 * Checks to see if the given item is a duplicate or not
	 * 
	 * @precondition list.size() > 0
	 * @postcondition
	 * 
	 * @param item - the item to check
	 * @return true if the item is a duplicate, false otherwise
	 */
	public boolean isDuplicate(ShoppingItem item) {
		if (this.list.size() > 0) {
			for (ShoppingItem temp : this.list) {
				if (item.getItemName() == temp.getItemName() && item.getItemQuant() == temp.getItemQuant()) {
					return true;
				}
			}
		}
		return false;
	}
}
