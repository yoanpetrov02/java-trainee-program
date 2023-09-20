package com.sirma.javacourse.networking.clientserver.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a server, which listens to a port, accepts multiple clients and sends a message to them when
 * they connect.
 */
public class Server implements Observable {

	private static final Logger LOGGER =
				LoggerFactory.getLogger(Server.class);

	private static final SimpleDateFormat DATE_FORMATTER =
			new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy z");

	private ServerSocket serverSocket;

	private final ArrayList<Subscriber> subscribers;

	public Server() {
		subscribers = new ArrayList<>();
	}

	@Override
	public void addSubscriber(Subscriber s) {
		subscribers.add(s);
	}

	@Override
	public void notifySubscribers(String message) {
		for (Subscriber s : subscribers) {
			s.update(message);
		}
		LOGGER.info(message);
	}

	@Override
	public void notifySubscribersError(String errorMessage, Throwable e) {
		for (Subscriber s : subscribers) {
			s.update(errorMessage);
		}
		LOGGER.error(errorMessage, e);
	}

	/**
	 * Starts the server.
	 */
	public void start() {
		startAtFirstFreePort(7000, 7020);
		listen();
	}

	/**
	 * Continuously listens for new clients and accepts them by creating a {@code ClientHandler} for them.
	 */
	public void listen() {
		notifySubscribers("Listening for clients.");
		while (!serverSocket.isClosed()) {
			try {
				new ClientHandler(this, serverSocket.accept()).start();
			} catch (SocketException e) {
				break;
			} catch (IOException e) {
				notifySubscribersError("An error occurred while accepting the client", e);
			}
		}
	}

	/**
	 * Stops the server.
	 */
	public void stop() {
		try {
			notifySubscribers("Stopping server...");
			if (isRunning()) {
				serverSocket.close();
			}
			notifySubscribers("The server was stopped.");
		} catch (IOException e) {
			notifySubscribersError("An error occurred while closing the server", e);
		}
	}

	public boolean isRunning() {
		return serverSocket != null &&
				!serverSocket.isClosed();
	}

	/**
	 * Searches the given interval and starts the server with the first free port found.
	 *
	 * @param start the start of the interval to search, inclusive.
	 * @param end the end of the interval to search, inclusive.
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
				LOGGER.info("An i/o error occurred while starting the server", e);
			}
		}
	}

	/**
	 * Returns the current time under the form of a formatted string.
	 *
	 * @return the current time.
	 */
	private static String getCurrentTime() {
		return DATE_FORMATTER.format(
				new Date(System.currentTimeMillis()));
	}

	/**
	 * Handles a single client of the server.
	 */
	private static class ClientHandler extends Thread {

		private final Socket clientSocket;
		private final Server server;

		public ClientHandler(Server server, Socket clientSocket) {
			this.clientSocket = clientSocket;
			this.server = server;
		}

		/**
		 * Writes a message to the client's output stream.
		 */
		@Override
		public void run() {
			server.notifySubscribers("Client connected.");
			sendMessageToClient();
		}

		private void sendMessageToClient() {
			try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
				out.println("Hello! Current time: " + getCurrentTime());
				server.notifySubscribers("Sent message to client!");
			} catch (IOException e) {
				server.notifySubscribersError("An error occurred while communicating with the client", e);
			}
		}
	}
}
