package com.sirma.javacourse.networking.reversemessage.views;

import com.sirma.javacourse.networking.reversemessage.controllers.ServerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
                controller.getServer().stop();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
