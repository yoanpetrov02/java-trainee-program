package com.sirma.javacourse.networking.reversemessage.controllers;

import com.sirma.javacourse.networking.reversemessage.model.ReverseClient;
import com.sirma.javacourse.networking.reversemessage.model.memento.MessageHistory;
import com.sirma.javacourse.networking.reversemessage.model.observer.Subscriber;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Handles user input for the client's app.
 */
public class ClientController implements Subscriber {

    private ReverseClient client;
    private MessageHistory history;

    @FXML
    private TextArea clientTextArea;
    @FXML
    private TextField inputField;

    public ReverseClient getClient() {
        return client;
    }

    /**
     * Initializes objects connected to the app's functionality whenever it starts.
     */
    public void initialize() {
        client = new ReverseClient();
        client.addSubscriber(this);
        history = new MessageHistory();
        inputField.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER -> sendButtonOnClick();
                case UP -> inputField.setText(history.last());
                case DOWN -> inputField.setText(history.next());
            }
        });
        Platform.runLater(() -> inputField.requestFocus());
    }

    /**
     * Connects the client to the server. If it's already connected, a message is shown instead.
     */
    public void connectButtonOnClick() {
        if (client.isConnected()) {
            print("This client is already connected to the server!");
            return;
        }
        new Thread(() -> client.startConnection()).start();
    }

    /**
     * Takes the string from app's input {@code TextField} and sends it to the server.
     * After that, the field's content is emptied.
     */
    public void sendButtonOnClick() {
        String message = inputField.getText();
        inputField.setText("");
        history.add(message);

        if (client.isConnected()) {
            Platform.runLater(() -> client.sendMessage(message));
        } else {
            print("The client is not connected to the server!");
        }
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
        clientTextArea.appendText(
                String.format("%s%n", message));
    }
}
