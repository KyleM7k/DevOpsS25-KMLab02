package edu.westga.comp4420.view;

import java.util.Optional;

import edu.westga.comp4420.model.*;
import edu.westga.comp4420.viewmodel.MainWindowViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class MainWindow {

    @FXML
    private AnchorPane MainWindow;

    @FXML
    private Label itemNameLbl;

    @FXML
    private TextField itemNameTxtBox;

    @FXML
    private Label itemQuantLbl;

    @FXML
    private TextField itemQuantTxtBox;

    @FXML
    private ListView<ShoppingItem> shoppingListView;

    @FXML
    private Button submitBtn;

    @FXML
    private Button updateBtn;
    
    @FXML
    private Button removeBtn;

    @FXML
    private Label welcomeLbl;
    
    private MainWindowViewModel viewmodel;
    
    private ObjectProperty<ShoppingItem> selectedItemProperty;
    
    private final AlertProperty alertProperty;
    
    /**
     * Default constructor
     * 
     * @precondition none
     * @postcondition none
     */
    public MainWindow() {
    	this.alertProperty = new AlertProperty();
    	this.viewmodel = new MainWindowViewModel();
    	this.selectedItemProperty = new SimpleObjectProperty<ShoppingItem>();
    }
    
    
    @FXML
    void initialize() {
    	this.bindToViewModel();
    	this.setupListeners();
    	this.setupListenerForAlerts();
    	this.itemQuantTxtBox.textProperty().set("0");
    }
    
    @FXML
    void addButtonClick(ActionEvent event) {
    	try {
    		this.viewmodel.add();
    		this.itemNameTxtBox.textProperty().set("");
    		this.itemQuantTxtBox.textProperty().set("0");
		} catch (Exception ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Item Add Error",
					"Can't add the item: " + ex.getMessage());
		}
    }

    @FXML
    void removeButtonClick(ActionEvent event) {
		try {
			this.viewmodel.remove(this.selectedItemProperty.get());
	    	this.itemNameTxtBox.textProperty().set("");
			this.itemQuantTxtBox.textProperty().set("0");
		} catch (Exception ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Item Remove Error",
					"Can't remove the item: " + ex.getMessage());
		}
    }
    
    @FXML
    void updateButtonClick(ActionEvent event) {
    	try {
    		this.viewmodel.edit(this.selectedItemProperty.get());
    		this.itemNameTxtBox.textProperty().set("");
    		this.itemQuantTxtBox.textProperty().set("0");
		} catch (Exception ex) {
			this.alertProperty.set(AlertProperty.ERROR, "Item Edit Error", "Can't edit the item: " + ex.getMessage());
		}
    }
    
    private void bindToViewModel() {
    	this.itemNameTxtBox.textProperty().bindBidirectional(this.viewmodel.getItemNameProperty());
    	this.itemQuantTxtBox.textProperty().bindBidirectional(this.viewmodel.getItemQuantProperty());
    	this.shoppingListView.itemsProperty().bind(this.viewmodel.getShoppingListProperty());
    	this.viewmodel.getSelectedItemProperty().bindBidirectional(this.selectedItemProperty);
    }
    
    private void setupListeners() {
    	this.shoppingListView.getSelectionModel().selectedItemProperty().addListener((observable, oldval, newval) -> {
    		if (newval != null) {
    			this.itemNameTxtBox.textProperty().set(newval.getItemName());
    			this.itemQuantTxtBox.textProperty().set(newval.getItemQuant());
    			this.selectedItemProperty.set(newval);
    		}
    	});
    }
    
    private void setupListenerForAlerts() {
		this.alertProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() == AlertProperty.ERROR) {
					MainWindow.this.showAlert(AlertType.ERROR);
				} else if (newValue.intValue() == AlertProperty.INFORMATION) {
					MainWindow.this.showAlert(AlertType.INFORMATION);
				} else if (newValue.intValue() == AlertProperty.CONFIRMATION) {
					MainWindow.this.showAlert(AlertType.CONFIRMATION);
				}
			}
		});
	}
    
    private void showAlert(Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		Window owner = this.MainWindow.getScene().getWindow();
		alert.initOwner(owner);
		if (!this.alertProperty.getTitle().isEmpty()) {
			alert.setTitle(this.alertProperty.getTitle());
		}
		alert.setHeaderText(this.alertProperty.getHeader());
		alert.setContentText(this.alertProperty.getContent());
		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertType == AlertType.CONFIRMATION && alertResult.get() == ButtonType.CANCEL) {
			this.alertProperty.setResult("cancel");
		} else {
			this.alertProperty.setResult("ok");
		}
		this.alertProperty.setType(AlertProperty.NO_ALERT);
	}
}
