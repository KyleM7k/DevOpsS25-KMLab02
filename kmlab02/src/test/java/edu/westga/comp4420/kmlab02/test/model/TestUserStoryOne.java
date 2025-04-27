package test;

import edu.westga.comp4420.*;
import edu.westga.comp4420.view.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 * Contains TestFX tests for the view classes
 * 
 * @author KYLE_M
 * @version Spring 2025
 */
public class TestUserStoryOne extends ApplicationTest  {

	@Override
	public void start(Stage stage) throws IOException {
		(new App()).start(stage);
	}
}