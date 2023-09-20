package com.sirma.javacourse.networking.reversemessage.controllers;

import com.sirma.javacourse.networking.reversemessage.model.ReverseServer;
import com.sirma.javacourse.networking.reversemessage.model.observer.Subscriber;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Handles user input for the server's app.
 */
public class ServerController implements Subscriber {

    private ReverseServer server;

    @FXML
    public Button startButton;
    @FXML
    public Button stopButton;
    @FXML
    private TextArea serverTextArea;

    public ReverseServer getServer() {
        return server;
    }

    /**
     * Initializes objects connected to the app's functionality whenever it starts.
     */
    public void initialize() {
        server = new ReverseServer();
        server.addSubscriber(this);
    }

    /**
     * Starts the server.
     */
    public void startButtonOnClick() {
        new Thread(() -> server.start()).start();
        startButton.setVisible(false);
        stopButton.setVisible(true);
    }

    /**
     * Stops the server.
     */
    public void stopButtonOnClick() {
        Platform.runLater(() -> server.stop());
        stopButton.setVisible(false);
        startButton.setVisible(true);
    }

    /**
     * Updates the app with a message from the controller's associated {@code Observable} object.
     *
     * @param message the update message.
     */
    @Override
    public void update(String message) {
        print(message);
    }

    /**
     * Appends the given message to the text area in the UI, and a new line after it.
     *
     * @param message the message to be appended.
     */
    private void print(String message) {
        serverTextArea.appendText(
                String.format("%s%n", message));
    }
}
