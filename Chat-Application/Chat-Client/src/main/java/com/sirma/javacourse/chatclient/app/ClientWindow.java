package com.sirma.javacourse.chatclient.app;

import com.sirma.commons.locale.LanguageChanger;
import com.sirma.javacourse.chatclient.controllers.ClientWindowController;
import com.sirma.javacourse.chatclient.model.client.ChatClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Responsible for loading the main view of the client app. Here, the user can text
 * other users and see which clients are online/offline.
 */
public final class ClientWindow {

    private static Alert exitConfirmationAlert;

    /**
     * Starts the app by loading its .fxml file.
     *
     * @param client the client object to start the app with.
     * @throws IOException if an error occurs while starting the app.
     */
    public static void startClient(ChatClient client) throws IOException {
        FXMLLoader loader = new FXMLLoader(ClientWindow.class.getResource("views/client-window-view.fxml"));
        LanguageChanger.setBaseResourcePackage("com.sirma.javacourse.chatclient.app.locale.ClientWindow");
        initExitAlert();

        Scene scene = new Scene(loader.load());
        ClientWindowController controller = loader.getController();

        Stage stage = new Stage();
        stage.titleProperty().bind(LanguageChanger.createStringBinding("window.title"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest((e) -> {
            if (!confirmCloseProgram()) {
                e.consume();
                return;
            }
            controller.disconnect();
        });

        stage.show();
        controller.setClient(client);
        controller.startReadTaskLoop();
    }

    private static void initExitAlert() {
        exitConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitConfirmationAlert.titleProperty().bind(
                LanguageChanger.createStringBinding("exitwindow.title"));
        exitConfirmationAlert.headerTextProperty().bind(
                LanguageChanger.createStringBinding("exitwindow.header"));
        exitConfirmationAlert.contentTextProperty().bind(
                LanguageChanger.createStringBinding("exitwindow.content"));
    }

    private static boolean confirmCloseProgram() {
        exitConfirmationAlert.showAndWait();

        return exitConfirmationAlert.getResult().getButtonData() == ButtonBar.ButtonData.OK_DONE;
    }
}
