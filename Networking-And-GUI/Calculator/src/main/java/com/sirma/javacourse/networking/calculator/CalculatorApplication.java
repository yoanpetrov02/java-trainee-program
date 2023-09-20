package com.sirma.javacourse.networking.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of the calculator. Can be launched from an IDE using {@code CalculatorMockRunner}.
 */
public class CalculatorApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("calculator-view.fxml"));
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("calculator-style.css").toExternalForm());
		stage.setTitle("Calculator");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
