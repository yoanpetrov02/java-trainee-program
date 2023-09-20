package com.sirma.javacourse.chatserver.app;

import com.sirma.javacourse.chatserver.controllers.LauncherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Responsible for loading the server app's launcher.
 * Using it, the user can choose a hostname and a port to start the server on.
 */
public class ServerLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/launcher-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Server Launcher");
        stage.setScene(scene);
        stage.setResizable(false);
        ((LauncherController) loader.getController()).setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
