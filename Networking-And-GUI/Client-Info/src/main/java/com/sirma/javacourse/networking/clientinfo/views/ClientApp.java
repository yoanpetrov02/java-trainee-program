package com.sirma.javacourse.networking.clientinfo.views;

import com.sirma.javacourse.networking.clientinfo.controllers.ClientController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the client's side of the application. It is responsible for loading the views and styling them.
 */
public class ClientApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("client-view.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setTitle("Client");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setOnCloseRequest(windowEvent -> {
			Platform.exit();
			ClientController controller = loader.getController();
			controller.getClient().stopConnection();
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

