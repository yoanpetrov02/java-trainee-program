package com.sirma.javacourse.networking.multicasting.controllers;

import com.sirma.javacourse.networking.multicasting.model.Channels;
import com.sirma.javacourse.networking.multicasting.model.observer.Subscriber;
import com.sirma.javacourse.networking.multicasting.model.receiver.MulticastReceiver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Controller class for the receiver app. Handles all user input from the UI.
 */
public class ChannelReceiverController implements Subscriber {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChannelReceiverController.class);

    private MulticastReceiver receiver;
    private Map<String, Integer> channels;
    private String previousChannel;
    private String currentChannel;

    @FXML
    private ComboBox<String> channelList;
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

        channels = Channels.getChannels();
        channelList.getItems().addAll(channels.keySet());
    }

    /**
     * Handles clicks on the connect button in the UI, connecting the user to the channel of their choice.
     * If they haven't chosen a channel, an error message appears. Otherwise, they get connected to the
     * channel they have selected from the {@code ComboBox}.
     */
    @FXML
    public void connectButtonOnClick() {
        if (currentChannel == null) {
            showError("Please, select a channel before connecting!");
            return;
        }
        if (receiver != null && receiver.isConnected()) {
            if (shouldDisconnect()) {
                receiver.disconnect();
            } else {
                return;
            }
        }
        connect();
    }

    /**
     * Handles action events in the channel {@code ComboBox}. Whenever the user selects a different channel,
     * the current channel and the previous channel get updated, tracking the different selections.
     */
    @FXML
    public void comboBoxOnChannelChanged() {
        previousChannel = currentChannel;
        currentChannel = channelList.getValue();
    }

    @Override
    public void update(String message) {
        print(message);
    }

    public MulticastReceiver getReceiver() {
        return receiver;
    }

    /**
     * Appends the given message to the {@code TextArea} in the UI.
     *
     * @param message the message to be appended.
     */
    private void print(String message) {
        textArea.appendText("%s%n".formatted(message));
    }

    /**
     * Shows the given message in an error {@code Alert} pop-up window.
     *
     * @param message the error message.
     */
    private void showError(String message) {
        errorAlert.setContentText(message);
        errorAlert.show();
        LOGGER.error(message);
    }

    /**
     * Checks whether the {@code MulticastReceiver} should disconnect from the current channel before
     * connecting to a different one.
     *
     * @return false if the receiver doesn't have to disconnect, true if it should.
     */
    private boolean shouldDisconnect() {
        if (previousChannel == null || previousChannel.equals(currentChannel)) {
            return false;
        }
        return true;
    }

    /**
     * Creates a new {@code MulticastReceiver} and connects it to the currently selected channel.
     */
    private void connect() {
        try {
            receiver = new MulticastReceiver(channels.get(currentChannel));
            receiver.setSubscriber(this);
            receiver.start();
            print("Connected to channel [%s].".formatted(currentChannel));
            previousChannel = currentChannel;
        } catch (IOException e) {
            showError("There was a problem while connecting to the selected channel.");
        }
    }
}
