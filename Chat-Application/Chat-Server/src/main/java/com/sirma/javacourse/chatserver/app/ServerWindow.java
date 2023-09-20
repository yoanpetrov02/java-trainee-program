package com.sirma.javacourse.chatserver.app;

import com.sirma.commons.locale.LanguageChanger;
import com.sirma.javacourse.chatserver.controllers.ServerWindowController;
import com.sirma.javacourse.chatserver.model.server.ChatServer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for loading the main view of the server app. Here, the user can start/stop the server
 * and observe its state via the text area.
 */
public class ServerWindow {

    private static Alert exitConfirmationAlert;

    /**
     * Starts the app by loading its .fxml file.
     *
     * @param server the server object to start the app with.
     * @throws IOException if an error occurs while starting the app.
     */
    public static void startApp(ChatServer server) throws IOException {
        ensureNeededDirectoriesExist();
        FXMLLoader loader = new FXMLLoader(ServerWindow.class.getResource("views/server-window-view.fxml"));
        LanguageChanger.setBaseResourcePackage("com.sirma.javacourse.chatserver.app.locale.ServerWindow");
        initExitAlert();

        Scene scene = new Scene(loader.load());
        ServerWindowController controller = loader.getController();
        controller.setServer(server);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.titleProperty().bind(LanguageChanger.createStringBinding("window.title"));
        stage.setOnCloseRequest((e) -> {
            if (!confirmCloseProgram()) {
                e.consume();
                return;
            }
            controller.stopServer();
        });
        stage.show();

    }

    /**
     * Checks if the needed files for the server's functions exist,
     * and creates them if they don't.
     *
     * @throws IOException if an error occurs during file creation.
     */
    private static void ensureNeededDirectoriesExist() throws IOException {
        Path path = Path.of("chats");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
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
