package com.sirma.javacourse.networking.clientinfo.model.client;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.javacourse.networking.clientinfo.model.Observable;
import com.sirma.javacourse.networking.clientinfo.model.Subscriber;

/**
 * This class implements a client, which can connect to a server and continuously receive messages from it.
 */
public class Client implements Observable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Client.class);

	private static final String SERVER_STOPPED = "The server was stopped.";

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	private boolean connected;

	private Subscriber appController;

	/**
	 * Connects to the server with the given host. The port is automatically searched for in a range between 7000 and 7020.
	 *
	 * @param host the server's host.
	 */
	public void startConnection(String host) {
		for (int i = 7000; i <= 7020; i++) {
			try {
				clientSocket = new Socket(host, i);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				notifySubscribers("Connected to the server at port %d".formatted(i));
				connected = true;
				return;
			} catch (ConnectException e) {
				continue;
			} catch (IOException e) {
				LOGGER.error("An i/o exception occurred while connecting to the server", e);
				return;
			}
		}
		notifySubscribers("No server was found in the port range");
	}

	/**
	 * Continuously listens for messages, and outputs them, until the connection gets closed.
	 */
	public void listenForMessages() {
		while (isConnected()) {
			try {
				readMessage();
			} catch (SocketException e) {
				break;
			} catch (IOException e) {
				LOGGER.error("I/O exception while reading messages from the server", e);
			}
		}
	}

	/**
	 * Disconnects from the server and closes all streams.
	 */
	public void stopConnection() {
		if (isConnected()) {
			try {
				clientSocket.close();
				in.close();
				out.close();
				clientSocket = null;
				connected = false;
				notifySubscribers("Disconnected from the server.");
			} catch (IOException e) {
				LOGGER.error("An error occurred while closing the connection", e);
			}
		}
	}

	/**
	 * Checks whether the client is connected to a server.
	 *
	 * @return true if the client is connected, false otherwise.
	 */
	public boolean isConnected() {
		return connected && clientSocket != null;
	}

	/**
	 * Adds a subscriber to this object's subscriber list.
	 *
	 * @param s the new subscriber.
	 */
	@Override
	public void addSubscriber(Subscriber s) {
		appController = s;
	}

	/**
	 * Notifies all subscribers with the given message by calling their update() method, and then logs the message.
	 *
	 * @param message the message that will be sent to all subscribers.
	 */
	@Override
	public void notifySubscribers(String message) {
		appController.update(message);
		LOGGER.info(message);
	}

	/**
	 * Attempts to read a message sent from the server and outputs it.
	 *
	 * @throws IOException if an i/o error occurs.
	 */
	private void readMessage() throws IOException {
		String line;
		while ((line = in.readLine()) != null) {
			if (SERVER_STOPPED.equals(line)) {
				stopConnection();
				return;
			}
			notifySubscribers(line);
		}
	}
}