package edu.westga.comp4420.view;

import edu.westga.comp4420.model.ShoppingList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private ListView<ShoppingList> shoppingListView;

    @FXML
    private Button submitBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Label welcomeLbl;
    
    @FXML
    void initalize() {
    	
    }
}
