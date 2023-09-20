package com.sirma.javacourse.networking.multicasting.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View class of the sender's app. Responsible for loading the UI.
 */
public class ChannelSenderApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sender-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Sender");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
