package com.sirma.javacourse.chatclient.app;

import com.sirma.javacourse.chatclient.controllers.LauncherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Responsible for loading the client app's launcher.
 * Using it, the user can choose a hostname, a port and a username to connect to the server with.
 */
public class ClientLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/launcher-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Client Launcher");
        stage.setScene(scene);
        stage.setResizable(false);
        ((LauncherController) loader.getController()).setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
