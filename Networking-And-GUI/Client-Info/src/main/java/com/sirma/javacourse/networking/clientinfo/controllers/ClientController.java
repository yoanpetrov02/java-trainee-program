package com.sirma.javacourse.networking.clientinfo.controllers;

import com.sirma.javacourse.networking.clientinfo.model.Subscriber;
import com.sirma.javacourse.networking.clientinfo.model.client.Client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller class for the client side of the application.
 */
public class ClientController implements Subscriber {

	private Client client;

	@FXML private TextArea clientTextArea;

	/**
	 * Initializes the client instance and adds the controller to its {@code Subscriber} list.
	 */
	public void initialize() {
		client = new Client();
		client.addSubscriber(this);
	}

	/**
	 * Connects the client to the server, if the server is running, and starts reading messages from it.
	 */
	public void connectButtonOnClick() {
		if (client.isConnected()) {
			print("This client is already connected to a server!");
			return;
		}
		client.startConnection("localhost");
		startMessageReadThread();
	}

	/**
	 * Displays the message in the app's {@code TextArea}.
	 *
	 * @param message the update message.
	 */
	@Override
	public void update(String message) {
		print(message);
	}

	public Client getClient() {
		return client;
	}

	/**
	 * Appends the given message to the text area in the UI, and a new line after it.
	 *
	 * @param message the message to be appended.
	 */
	private void print(String message) {
		Platform.runLater(() -> clientTextArea.appendText(
				String.format("%s%n", message)));
	}

	/**
	 * Creates a new thread and listens to server messages from it.
	 */
	private void startMessageReadThread() {
		if (client.isConnected()) {
			new Thread(() -> client.listenForMessages()).start();
		}
	}
}
