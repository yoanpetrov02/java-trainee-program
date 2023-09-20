package com.sirma.javacourse.networking.reversemessage.model;

import com.sirma.javacourse.networking.reversemessage.model.observer.Observable;
import com.sirma.javacourse.networking.reversemessage.model.observer.Subscriber;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Represents a {@code ReverseServer}'s client.
 */
public class ReverseClient implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReverseClient.class);
    private static final String MESSAGE_FORMAT = "Client> %s";
    private static final String CLOSE_MESSAGE = ".";

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected;
    private Subscriber appController;

    /**
     * Opens the connection with the server and starts listening for messages.
     */
    public void startConnection() {
        try {
            if (connectToServer()) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                listenForMessages();
            }
        } catch (IOException e) {
            LOGGER.error("Error while reading messages from the server.", e);
        }
    }

    /**
     * Reads messages from the server until the connection gets closed.
     */
    public void listenForMessages() {
        while (isConnected()) {
            try {
                readMessage();
            } catch (SocketException e) {
                break;
            } catch (IOException e) {
                LOGGER.error("I/O exception while reading messages from the server.", e);
            }
        }
    }

    /**
     * Disconnects from the server and closes all streams.
     */
    public void stopConnection() {
        try {
            socket.close();
            in.close();
            out.close();
            socket = null;
            connected = false;
            notifySubscribers("Disconnected from the server.");
        } catch (IOException e) {
            LOGGER.error("An error occurred while closing the connection.", e);
        }
    }

    /**
     * Sends a message to the server.
     *
     * @param message the message to be sent.
     */
    public void sendMessage(String message) {
        out.println(message);
        notifySubscribers(message);
    }

    public boolean isConnected() {
        return socket != null && connected;
    }

    @Override
    public void addSubscriber(Subscriber s) {
        appController = s;
    }

    @Override
    public void notifySubscribers(String message) {
        String m = String.format(MESSAGE_FORMAT, message);
        if (appController != null) {
            Platform.runLater(() -> appController.update(m));
        }
        LOGGER.info(m);
    }

    /**
     * Looks through the ports to find which port the server was started on,
     * and connects to it, if it manages to find it.
     *
     * @return true if the connection was successful, false otherwise.
     */
    private boolean connectToServer() {
        for (int i = 7000; i <= 7020; i++) {
            try {
                socket = new Socket("localhost", i);
                notifySubscribers(String.format("Connected to the server at port %d.", i));
                connected = true;
                return true;
            } catch (ConnectException e) {
                continue;
            } catch (IOException e) {
                LOGGER.error("Error while connecting to the server.", e);
                return false;
            }
        }
        notifySubscribers("Could not find the server's port.");
        return false;
    }

    /**
     * Attempts to read a message sent from the server and outputs it.
     *
     * @throws IOException if an i/o error occurs.
     */
    private void readMessage() throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            if (CLOSE_MESSAGE.equals(line)) {
                stopConnection();
                return;
            }
            notifySubscribers(line);
        }
    }
}
