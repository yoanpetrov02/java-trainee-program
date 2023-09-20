package com.sirma.javacourse.networking.clientinfo.views;

import com.sirma.javacourse.networking.clientinfo.controllers.ServerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the server's side of the application. It is responsible for loading the views and styling them.
 */
public class ServerApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("server-view.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setTitle("Server");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setOnCloseRequest(windowEvent -> {
			Platform.exit();
			ServerController controller = loader.getController();
			if (controller.getServer().isRunning()) {
				controller.getServer().stopServer();
			}
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
