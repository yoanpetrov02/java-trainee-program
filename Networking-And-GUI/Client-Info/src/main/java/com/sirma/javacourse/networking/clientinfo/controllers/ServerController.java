package com.sirma.javacourse.networking.clientinfo.controllers;

import com.sirma.javacourse.networking.clientinfo.model.Subscriber;
import com.sirma.javacourse.networking.clientinfo.model.server.Server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Controller class for the server side of the application.
 */
public class ServerController implements Subscriber {

	private Server server;

	@FXML private TextArea serverTextArea;
	@FXML private Button startButton;
	@FXML private Button stopButton;

	/**
	 * Initializes the server instance and adds the controller to its {@code Subscriber} list.
	 */
	public void initialize() {
		server = new Server();
		server.addSubscriber(this);
	}

	/**
	 * Starts the server in a new thread, hides the start button and shows the stop button.
	 */
	public void startButtonOnClick() {
		new Thread(() -> server.startServer()).start();
		startButton.setVisible(false);
		stopButton.setVisible(true);
	}

	/**
	 * Stops the server, hides the stop button and shows the start button.
	 */
	public void stopButtonOnClick() {
		server.stopServer();
		startButton.setVisible(true);
		stopButton.setVisible(false);
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

	public Server getServer() {
		return server;
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
