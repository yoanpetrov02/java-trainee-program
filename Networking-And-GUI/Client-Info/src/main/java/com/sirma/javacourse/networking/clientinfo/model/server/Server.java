package com.sirma.javacourse.networking.clientinfo.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sirma.javacourse.networking.clientinfo.model.Observable;
import com.sirma.javacourse.networking.clientinfo.model.Subscriber;

/**
 * This class implements a server. Clients can connect to the server and receive messages from it. Whenever a new client
 * connects, the total client amount is raised by one, and a message is sent to tell them which client
 * # they are. Every existing client gets a message when a new client connects.
 */
public class Server implements Observable {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(Server.class);

	private ServerSocket serverSocket;
	private final List<ClientHandler> clients;
	private int clientAmount;
	private Subscriber appController;

	public Server() {
		clients = Collections.synchronizedList(new ArrayList<>());
		clientAmount = 0;
	}

	/**
	 * Starts the server and begins listening for connections.
	 */
	public void startServer() {
		startAtFirstFreePort(7000, 7020);
		listen();
	}

	/**
	 * Closes all existing connections and closes the server socket.
	 */
	public void stopServer() {
		clients.forEach((client) -> client.sendMessage("The server was stopped."));
		clients.forEach(ClientHandler::closeConnection);
		try {
			serverSocket.close();
		} catch (IOException ignored) {}
	}

	public boolean isRunning() {
		return serverSocket != null &&
				!serverSocket.isClosed();
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
	 * Notifies all subscribers with the given message by calling their update() method.
	 *
	 * @param message the message that will be sent to all subscribers.
	 */
	@Override
	public void notifySubscribers(String message) {
		appController.update(message);
		LOGGER.info(message);
	}

	/**
	 * Looks through all ports between the given values, and binds the server socket to the first free port in the range.
	 *
	 * @param start the beginning port, inclusive.
	 * @param end the ending port, inclusive.
	 */
	private void startAtFirstFreePort(int start, int end) {
		for (int i = start; i <= end; i++) {
			try {
				serverSocket = new ServerSocket(i);
				notifySubscribers("Server started at port " + i);
				return;
			} catch (SocketException e) {
				continue;
			} catch (IOException e) {
				LOGGER.error("I/O error while starting the server", e);
			}
		}
		notifySubscribers("No free port was found!");
	}

	/**
	 * Continuously listens for connections. Whenever a client connects, the server creates a new {@code ClientHandler}
	 * instance for them, sends them a message to tell them which client # they are, and notifies all other existing
	 * clients about the new client.
	 */
	private void listen() {
		while (!serverSocket.isClosed()) {
			try {
				ClientHandler handler = new ClientHandler(serverSocket.accept());
				handler.sendMessage("Hello! You are client #%d.".formatted(++clientAmount));
				notifyClientsNewClient();
				notifySubscribers("Client #%d has connected.".formatted(clientAmount));
				clients.add(handler);
			} catch (SocketException e) {
				notifySubscribers("The server was stopped.");
			} catch (IOException e) {
				LOGGER.error("I/O exception while listening", e);
			}
		}
	}

	/**
	 * Sends a message to each client through their {@code ClientHandler} instance.
	 */
	private void notifyClientsNewClient() {
		clients.forEach(clientHandler ->
				clientHandler.sendMessage(
						"Client #%d has connected.".formatted(clientAmount)));
	}
}
