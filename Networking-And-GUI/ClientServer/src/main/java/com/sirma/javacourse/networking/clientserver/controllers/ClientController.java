package com.sirma.javacourse.networking.clientserver.controllers;

import com.sirma.javacourse.networking.clientserver.model.Client;
import com.sirma.javacourse.networking.clientserver.model.Subscriber;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ClientController implements Subscriber {

	private Client client;

	@FXML
	private TextArea clientTextArea;

	public void initialize() {
		client = new Client();
		client.addSubscriber(this);
	}

	@Override
	public void update(String message) {
		print(message);
	}

	@FXML
	public void connectToServerOnClick() {
		if (client.isConnected()) {
			print("This client is already connected to the server!");
			return;
		}
		client.startConnection();
		if (client.isConnected()) {
			print(client.receiveMessage());
			client.stopConnection();
		}
	}

	private void print(String message) {
		clientTextArea.appendText(message + System.lineSeparator());
 	}
}
