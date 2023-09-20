package com.sirma.javacourse.chatclient.controllers;

import com.sirma.javacourse.chatclient.app.ClientWindow;
import com.sirma.javacourse.chatclient.model.client.ChatClient;
import com.sirma.commons.commandmessage.CommandMessage;
import com.sirma.commons.commandmessage.CommandMessageParser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Controller class for {@code ClientLauncher}.
 * Responsible for validating the user's input and for starting the client application.
 */
public class LauncherController {

    @FXML
    private TextField hostnameField;
    @FXML
    private TextField portField;
    @FXML
    private TextField usernameField;

    private Stage stage;
    private Alert errorAlert;
    private ChatClient client;

    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("An error has occurred.");
    }

    /**
     * Validates the user's input and starts the client app.
     */
    @FXML
    public void launchApp() {
        if (!validateFields()) {
            return;
        }
        client = new ChatClient(
                hostnameField.getText().trim(),
                Integer.parseInt(portField.getText()),
                usernameField.getText().trim().toLowerCase());
        client.connect();
        if (!client.isConnected()) {
            displayError("Could not connect to the server.");
            return;
        }
        if (!attemptUserValidation()) {
            displayError("Unsuccessful user validation.");
            return;
        }
        startClient();
        if (stage != null) {
            stage.close();
        }
    }

    public void setStage(Stage appStage) {
        this.stage = appStage;
    }

    /**
     * Validates the user's input in the text fields.
     *
     * @return true if the input is valid, false otherwise.
     */
    private boolean validateFields() {
        return validateHostname()
                && validatePort()
                && validateUsername();
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
     * Checks whether the username is empty or contains only whitespace.
     *
     * @return true if the username is valid, false otherwise.
     */
    private boolean validateUsername() {
        if (StringUtils.isEmpty(usernameField.getText())) {
            displayError("Please, enter a username!");
            return false;
        }
        if (StringUtils.isEmpty(usernameField.getText().trim())) {
            displayError("Please, enter a valid username!");
            return false;
        }
        return true;
    }

    /**
     * Attempts to validate the client after connecting to the server.
     *
     * @return true if the client was successfully validated, false otherwise.
     */
    private boolean attemptUserValidation() {
        client.sendMessage("connectRequest [%s]".formatted(client.getUsername()));
        String response = client.receiveMessage();
        if (response == null) {
            return false;
        }
        CommandMessage parsed = CommandMessageParser.parseMessage(response);
        return "connectRequest".equals(parsed.getType())
                && "accept".equals(parsed.getArguments()[0]);
    }

    /**
     * Starts the {@code ClientWindow} app.
     */
    private void startClient() {
        Platform.runLater(() -> {
            try {
                ClientWindow.startClient(client);
            } catch (IOException e) {
                displayError("Error while starting the chat client.");
            }
        });
    }

    /**
     * Displays an error message on the screen.
     *
     * @param errorMessage the error message to show.
     */
    private void displayError(String errorMessage) {
        Platform.runLater(() -> {
            errorAlert.setContentText(errorMessage);
            errorAlert.show();
        });
    }
}
