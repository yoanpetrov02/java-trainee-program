package com.sirma.javacourse.networking.reversemessage.model;

import com.sirma.javacourse.networking.reversemessage.model.observer.Observable;
import com.sirma.javacourse.networking.reversemessage.model.observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * A reverse server can handle multiple clients. Whenever a client sends message, it gets sent back reversed.
 */
public class ReverseServer implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReverseServer.class);
    private static final String MESSAGE_FORMAT = "Server> %s";

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients;
    private int clientAmount;
    private Subscriber appController;

    public ReverseServer() {
        clients = new ArrayList<>();
        clientAmount = 0;
    }

    /**
     * Starts the server.
     */
    public void start() {
        if (startAtFirstFreePort()) {
            listen();
        }
    }

    /**
     * Listens for client connections.
     */
    public void listen() {
        while (true) {
            try {
                acceptClient();
                notifySubscribers("A client has connected.");
            } catch (SocketException e) {
                notifySubscribers("Server closed.");
                return;
            } catch (IOException e) {
                LOGGER.error("Error while accepting client.", e);
                return;
            }
        }
    }

    /**
     * Closes all client connections and stops the server.
     */
    public void stop() {
        notifySubscribers("Stopping server...");
        closeClientConnections();
        clients.clear();
        try {
            serverSocket.close();
        } catch (IOException ignored) {}
    }

    public boolean isRunning() {
        return serverSocket != null &&
                !serverSocket.isClosed();
    }

    @Override
    public void addSubscriber(Subscriber s) {
        appController = s;
    }

    @Override
    public void notifySubscribers(String message) {
        String m = String.format(MESSAGE_FORMAT, message);
        if (appController != null) {
            appController.update(m);
        }
        LOGGER.info(m);
    }

    /**
     * Looks for a free port, and starts the server on it.
     *
     * @return true if the server was successfully started, false otherwise.
     */
    private boolean startAtFirstFreePort() {
        for (int i = 7000; i <= 7020; i++) {
            try {
                serverSocket = new ServerSocket(i);
                notifySubscribers(String.format("Started server at port %d.", i));
                return true;
            } catch (SocketException portNotFree) {
                continue;
            } catch (IOException e) {
                LOGGER.error("Error while finding free port for the server.", e);
            }
        }
        notifySubscribers("No free port was found to start the server.");
        return false;
    }

    /**
     * Accepts a client and assigns a {@code ClientHandler} to it.
     *
     * @throws IOException if an i/o error occurs while accepting the client.
     */
    private void acceptClient() throws IOException {
        ClientHandler client = new ClientHandler(serverSocket.accept(), ++clientAmount);
        clients.add(client);
        if (appController != null) {
            client.addSubscriber(appController);
        }
        client.start();
    }

    /**
     * Closes all client connections.
     */
    private void closeClientConnections() {
        clients.forEach(client -> {
            client.closeConnection();
            try {
                client.join();
            } catch (InterruptedException e) {
                LOGGER.error("Error while waiting for client handler to close the connection.", e);
            }
        });
    }
}
