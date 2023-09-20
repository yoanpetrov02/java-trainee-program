package com.sirma.javacourse.networking.multicasting.views;

import com.sirma.javacourse.networking.multicasting.controllers.ChannelReceiverController;
import com.sirma.javacourse.networking.multicasting.model.receiver.MulticastReceiver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View class of the receiver's app. Responsible for loading the UI.
 */
public class ChannelReceiverApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receiver-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Receiver");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            ChannelReceiverController controller = loader.getController();
            MulticastReceiver receiver = controller.getReceiver();
            if (receiver != null && receiver.isConnected()) {
                controller.getReceiver().disconnect();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
