package edu.westga.comp4420.viewmodel;

import edu.westga.comp4420.model.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Viewmodel class MainWindowViewModel
 * 
 * @author KYLE_M
 * @version Spring 2025
 */
public class MainWindowViewModel {
	
	private final StringProperty itemNameProperty;
	private final StringProperty itemQuantProperty;
	private final ObjectProperty<ShoppingItem> selectedItemProperty;
	private ListProperty<ShoppingItem> shoppingListProperty;
	private ShoppingList list;
	
	/**
	 * Instantiates a new view model
	 * 
	 * @precondition none
	 * @postcondition itemNameProperty != null && itemQuantProperty != null
	 */
	public MainWindowViewModel() {
		this.itemNameProperty = new SimpleStringProperty();
		this.itemQuantProperty = new SimpleStringProperty();
		this.selectedItemProperty = new SimpleObjectProperty<ShoppingItem>();
		this.shoppingListProperty = new SimpleListProperty<ShoppingItem>();
		this.list = new ShoppingList();
	}
	
	/**
	 * Returns the item name property
	 * 
	 * @return the item name property
	 */
	public StringProperty getItemNameProperty() {
		return this.itemNameProperty;
	}

	/**
	 * Returns the item quantity property
	 * 
	 * @return the quantity property
	 */
	public StringProperty getItemQuantProperty() {
		return this.itemQuantProperty;
	}

	/**
	 * Returns the selected item property
	 * 
	 * @return the selected item property
	 */
	public ObjectProperty<ShoppingItem> getSelectedItemProperty() {
		return this.selectedItemProperty;
	}

	/**
	 * Returns the shopping list property
	 * 
	 * @return the shopping list property
	 */
	public ListProperty<ShoppingItem> getShoppingListProperty() {
		return this.shoppingListProperty;
	}

	/**
	 * Returns the shopping list
	 * 
	 * @return the shopping list
	 */
	public ShoppingList getList() {
		return this.list;
	}
	
	/**
	 * Adds an item to the shopping list
	 * 
	 * @return true if added, false otherwise
	 */
	public boolean add() {
		try {
			String itemName = this.itemNameProperty.getValue();
			String itemQuant = this.itemQuantProperty.getValue();
			ShoppingItem item = new ShoppingItem(itemName, this.isNumber(itemQuant));
			boolean result = this.list.add(item);
			this.updateShoppingList();
			return result;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	/**
	 * Removes the specified item from the shopping list
	 * 
	 * @param item - the item to add
	 * @return true if removed, false if otherwise
	 */
	public boolean remove(ShoppingItem item) {
		if (item == null) {
			throw new NullPointerException("You must select an item before you can remove it!");
		}
		boolean result = this.list.remove(item);
		this.updateShoppingList();
		return result;
	}
	
	/**
	 * Edits the selected item and then updates the list
	 */
	public void edit(ShoppingItem item) {
		if (item == null) {
			throw new NullPointerException("You must select an item before you can edit it!");
		}
		String itemName = this.itemNameProperty.getValue();
		String itemQuant = this.itemQuantProperty.getValue();
		item.setItemName(itemName);
		item.setItemQuant(this.isNumber(itemQuant));
		this.updateShoppingList();
	}

	private void updateShoppingList() {
		this.shoppingListProperty.set(FXCollections.observableArrayList(this.list.getList()));
	}
	
	private int isNumber(String quant) {
		try {
			int result = Integer.parseInt(quant);
			if (result < 0) {
				throw new IllegalArgumentException("Quantity cannot be a negative number!");
			}
			return result;
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Quantity value must be a valid number!");
		}
	}
}
