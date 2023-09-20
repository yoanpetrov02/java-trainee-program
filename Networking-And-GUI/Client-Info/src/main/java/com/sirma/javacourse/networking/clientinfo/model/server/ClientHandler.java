package com.sirma.javacourse.networking.clientinfo.model.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ClientHandler instance is responsible for handling a single server client. This way, the {@code Server} can delegate
 * message sending to the handlers, and the logic is better separated.
 */
public class ClientHandler {

	private static final Logger LOGGER =
				LoggerFactory.getLogger(ClientHandler.class);

	private final Socket clientSocket;
	private PrintWriter out;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			LOGGER.error("Error while sending message to the client", e);
		}
	}

	/**
	 * Sends a message to this handler's client.
	 *
	 * @param message the message to be sent.
	 */
	public void sendMessage(String message) {
		out.println(message);
	}

	/**
	 * Closes the connection with the client.
	 */
	public void closeConnection() {
		try {
			out.close();
			clientSocket.close();
		} catch (IOException ignored) {}
	}
}
