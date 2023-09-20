package com.sirma.javacourse.chatserver.controllers;

import com.sirma.javacourse.chatserver.app.ServerWindow;
import com.sirma.javacourse.chatserver.model.server.ChatServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Controller class for {@code ServerLauncher}.
 * Responsible for validating the user's input and for starting the server application.
 */
public class LauncherController {

    @FXML
    private TextField hostnameField;
    @FXML
    private TextField portField;

    private Alert errorAlert;
    private Stage stage;

    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("An error has occurred");
    }

    /**
     * Validates the user's input and starts the server app.
     */
    @FXML
    public void launchApp() {
        if (!validateFields()) {
            return;
        }
        ChatServer server = new ChatServer(
                hostnameField.getText().trim(),
                Integer.parseInt(portField.getText()));

        startApp(server);
        if (stage != null) {
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Validates the user's input in the text fields.
     *
     * @return true if the input is valid, false otherwise.
     */
    private boolean validateFields() {
        return validateHostname()
                && validatePort();
    }

    /**
     * Checks whether the hostname is empty or contains only whitespace.
     *
     * @return true if the hostname is valid, false otherwise.
     */
    private boolean validateHostname() {
        if (StringUtils.isEmpty(hostnameField.getText())) {
            displayError("Please, enter a hostname!");
            return false;
        }
        if (StringUtils.isEmpty(hostnameField.getText().trim())) {
            displayError("Please, enter a valid hostname!");
            return false;
        }
        return true;
    }

    /**
     * Checks whether the port is valid.
     * A valid port string is not empty, is parsable, and is between 0 and 65535, inclusive.
     *
     * @return true if the port is valid, false otherwise.
     */
    private boolean validatePort() {
        String port = portField.getText().trim();
        int portNumber;
        if (StringUtils.isEmpty(port)) {
            displayError("Please, enter a port number!");
            return false;
        }
        try {
            portNumber = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            displayError("The port number should only contain digits!");
            return false;
        }
        if (portNumber < 0 || portNumber > 65535) {
            displayError("The port number should be between 0 and 65535, inclusive!");
            return false;
        }
        return true;
    }

    /**
     * Starts the server app.
     *
     * @param server the server instance to start the app with.
     */
    private void startApp(ChatServer server) {
        Platform.runLater(() -> {
            try {
                ServerWindow.startApp(server);
            } catch (IOException e) {
                displayError("Error while starting the server app.");
            }
        });
    }

    /**
     * Displays an error message on the screen.
     *
     * @param errorMessage the error message to show.
     */
    private void displayError(String errorMessage) {
        errorAlert.setContentText(errorMessage);
        errorAlert.show();
    }
}
