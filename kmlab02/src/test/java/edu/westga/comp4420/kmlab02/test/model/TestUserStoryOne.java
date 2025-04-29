package test;

import edu.westga.comp4420.*;
import edu.westga.comp4420.view.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;
import java.io.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.jupiter.api.Test;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.util.WaitForAsyncUtils;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import org.testfx.matcher.base.WindowMatchers;

/**
 * Contains TestFX tests for the view classes
 * 
 * @author KYLE_M
 * @version Spring 2025
 */
public class TestUserStoryOne extends ApplicationTest  {

	final String ITEMS_LISTVIEW_ID = "#shoppingListView";
	
	@Override
	public void start(Stage stage) throws IOException {
		(new App()).start(stage);
		
	}
	
	@Test
	public void addValidItem() {
		this.clickOn("#itemNameTxtBox");
		this.type(KeyCode.C);
		this.type(KeyCode.H);
		this.type(KeyCode.E);
		this.type(KeyCode.E);
		this.type(KeyCode.S);
		this.type(KeyCode.E);
		this.clickOn("#submitBtn");
		
		FxAssert.verifyThat(ITEMS_LISTVIEW_ID, NodeMatchers.isNotNull());
		clickOn(ITEMS_LISTVIEW_ID);
		ListView itemsListView = this.find(ITEMS_LISTVIEW_ID);
		WaitForAsyncUtils.waitForFxEvents();
		assertEquals(1, itemsListView.getItems().size());
	}
	
	@Test
	public void addInvalidItem() {
		this.clickOn("#submitBtn");
		FxAssert.verifyThat(window("Item Add Error"), WindowMatchers.isShowing());
	}
	
	private <T extends Node> T find(final String query) {
		return (T) lookup(query).queryAll().iterator().next();
	}
}