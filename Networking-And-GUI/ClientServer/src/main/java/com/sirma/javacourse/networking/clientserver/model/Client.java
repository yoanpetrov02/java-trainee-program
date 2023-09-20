package com.sirma.javacourse.networking.clientserver.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements a client, which can connect to a server and receive messages from it.
 */
public class Client implements Observable {

	private static final Logger LOGGER =
				LoggerFactory.getLogger(Client.class);

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	private final ArrayList<Subscriber> subscribers;

	public Client() {
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
	 * Connects to the server at localhost. The port is automatically searched for in a range between 7000 and 7020.
	 */
	public void startConnection() {
		for (int i = 7000; i <= 7020; i++) {
			try {
				clientSocket = new Socket("localhost", i);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				notifySubscribers("Connected to the server at port " + i);
				return;
			} catch (ConnectException e) {
				continue;
			} catch (IOException e) {
				notifySubscribersError("An i/o error occurred while connecting to the server", e);
			}
		}
		notifySubscribers("No server was found in the port range!");
	}

	/**
	 * Reads a message sent from the server.
	 *
	 * @return the next line of data in the input stream.
	 */
	public String receiveMessage() {
		try {
			return in.readLine();
		} catch (IOException e) {
			notifySubscribersError("An error occurred while reading the message from the server", e);
			return null;
		}
	}

	public boolean isConnected() {
		return clientSocket != null &&
				!clientSocket.isClosed() &&
				clientSocket.isConnected();
	}

	/**
	 * Disconnects from the server and closes all streams.
	 */
	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			notifySubscribers("Disconnected from the server.");
		} catch (IOException e) {
			notifySubscribersError("An error occurred while closing the connection.", e);
		}
	}
}
