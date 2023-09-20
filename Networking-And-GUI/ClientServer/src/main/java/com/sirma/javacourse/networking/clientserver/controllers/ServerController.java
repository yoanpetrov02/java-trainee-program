package com.sirma.javacourse.networking.clientserver.controllers;

import com.sirma.javacourse.networking.clientserver.model.Server;
import com.sirma.javacourse.networking.clientserver.model.Subscriber;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements Subscriber {

	private Server server;

	@FXML
	private TextArea serverTextArea;

	@Override
	public void update(String message) {
		print(message);
	}

	public void initialize() {
		server = new Server();
		server.addSubscriber(this);
	}

	@FXML
	public void startServerOnClick() {
		if (server.isRunning()) {
			print("The server is already running!");
			return;
		}
		new Thread(() -> server.start()).start();
	}

	@FXML
	public void stopServerOnClick() {
		if (!server.isRunning()) {
			print("The server is not running!");
			return;
		}
		server.stop();
	}

	public Server getServer() {
		return server;
	}

	private void print(String message) {
		serverTextArea.appendText(message + System.lineSeparator());
	}
}
