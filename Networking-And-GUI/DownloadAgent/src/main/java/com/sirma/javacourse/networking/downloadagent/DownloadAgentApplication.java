package com.sirma.javacourse.networking.downloadagent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main application class. Use {@code MockRunner} to run without getting an error.
 */
public class DownloadAgentApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("download-agent-view.fxml"));
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("Download agent");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
