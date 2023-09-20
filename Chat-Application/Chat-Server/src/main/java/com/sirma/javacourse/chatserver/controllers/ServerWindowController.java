package com.sirma.javacourse.chatserver.controllers;

import com.sirma.javacourse.chatserver.model.observer.Subscriber;
import com.sirma.javacourse.chatserver.model.server.ChatServer;
import com.sirma.commons.locale.LanguageChanger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Controller class for {@code ServerWindow}. Responsible for handling user input.
 */
public class ServerWindowController implements Subscriber {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ServerWindowController.class);

    private static final SimpleDateFormat DATE_FORMATTER =
            new SimpleDateFormat("HH:mm:ss");

    @FXML private TextArea serverTextArea;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private SplitMenuButton languageChooser;

    private ChatServer server;
    private Alert errorAlert;

    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);

        LanguageChanger.bindUIElement(startButton, "button.start");
        LanguageChanger.bindUIElement(stopButton, "button.stop");
    }

    /**
     * Starts the server in a new thread if it's not already running.
     */
    @FXML
    public void startServerOnClick() {
        if (server.isRunning()) {
            displayError(LanguageChanger.get("error.start"));
            return;
        }
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                displayError(LanguageChanger.get("error.io"));
                LOGGER.error("Error while starting the server", e);
            }
        }).start();
    }

    /**
     * Stops the server if it's running.
     */
    @FXML
    public void stopServerOnClick() {
        if (!server.isRunning()) {
            displayError(LanguageChanger.get("error.stop"));
            return;
        }
        server.stop();
    }

    /**
     * Sets the UI's language to English.
     */
    @FXML
    public void setToEnglish() {
        if ("English".equals(languageChooser.getText())) {
            return;
        }
        languageChooser.setText("English");
        LanguageChanger.setLocale(new Locale("en"));
    }

    /**
     * Sets the UI's language to Bulgarian.
     */
    @FXML
    public void setToBulgarian() {
        if ("Български".equals(languageChooser.getText())) {
            return;
        }
        languageChooser.setText("Български");
        LanguageChanger.setLocale(new Locale("bg"));
    }

    public void setServer(ChatServer server) {
        this.server = server;
        server.setSubscriber(this);
    }

    public void stopServer() {
        server.stop();
    }

    /**
     * Updates the UI by printing the message in the app's text area.
     *
     * @param message the update message.
     */
    @Override
    public void update(String message) {
        Platform.runLater(() -> print(message));
    }

    /**
     * Prints the given message in the app's text area.
     *
     * @param message the message to print.
     */
    private void print(String message) {
        serverTextArea.appendText(
                "[%s]: %s%n".formatted(getCurrentTime(), message));
    }

    /**
     * Uses the date formatter to return a formatted string of the current time.
     *
     * @return the current time as a formatted string.
     */
    private String getCurrentTime() {
        return DATE_FORMATTER.format(
                new Date(System.currentTimeMillis()));
    }

    private void displayError(String errorMessage) {
        errorAlert.setTitle(LanguageChanger.get("error.title"));
        errorAlert.setHeaderText(LanguageChanger.get("error.header"));
        errorAlert.setContentText(errorMessage);
        errorAlert.show();
    }
}
