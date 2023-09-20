package com.sirma.javacourse.networking.reversemessage.views;

import com.sirma.javacourse.networking.reversemessage.controllers.ClientController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            if (controller.getClient().isConnected()) {
                controller.getClient().stopConnection();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
