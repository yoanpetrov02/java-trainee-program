package com.sirma.javacourse.networking.multicasting.controllers;

import com.sirma.javacourse.networking.multicasting.model.observer.Subscriber;
import com.sirma.javacourse.networking.multicasting.model.sender.message.ChannelMessageDispatcher;
import com.sirma.javacourse.networking.multicasting.model.sender.message.ChannelMessageGenerator;
import com.sirma.javacourse.networking.multicasting.model.sender.MessageDistributor;
import com.sirma.javacourse.networking.multicasting.model.sender.MulticastSender;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Controller class for the sender app. Handles all user input from the UI.
 */
public class ChannelSenderController implements Subscriber {

    private ChannelMessageDispatcher dispatcher;
    private MulticastSender sender;
    private MessageDistributor distributor;

    @FXML
    private TextArea textArea;
    private Alert errorAlert;

    /**
     * Initializes important objects when the app is started.
     */
    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("An error has occurred.");

        initializeDispatcher();
        sender = new MulticastSender();

        distributor = new MessageDistributor();
        distributor.setSubscriber(this);
        distributor.registerComponent(dispatcher);
        distributor.registerComponent(sender);
    }

    /**
     * Handles clicks on the send button in the UI, calling the dispatcher's sendRandomMessage() method.
     */
    @FXML
    public void sendButtonOnClick() {
        dispatcher.sendRandomMessage();
    }

    @Override
    public void update(String message) {
        print(message);
    }

    /**
     * Appends the given message to the {@code TextArea} in the UI.
     *
     * @param message the message to be appended.
     */
    public void print(String message) {
        textArea.appendText("%s%n".formatted(message));
    }

    /**
     * Shows the given message in an error {@code Alert} pop-up window.
     *
     * @param message the error message.
     */
    public void showError(String message) {
        errorAlert.setContentText(message);
        errorAlert.show();
    }

    /**
     * Initializes the {@code ChannelMessageDispatcher} of the app,
     * creating the {@code ChannelMessageGenerators} associated with it.
     */
    private void initializeDispatcher() {
        try {
            dispatcher = new ChannelMessageDispatcher();
            File news = new File(MessageDistributor.class.
                    getResource("/news-messages.txt").toURI());
            File sports = new File(MessageDistributor.class.
                    getResource("/sports-messages.txt").toURI());
            dispatcher.addGenerator(new ChannelMessageGenerator(news, "news"));
            dispatcher.addGenerator(new ChannelMessageGenerator(sports, "sports"));
        } catch (URISyntaxException | NullPointerException | FileNotFoundException e) {
            showError("There was a problem locating the message files.");
        }
    }
}
