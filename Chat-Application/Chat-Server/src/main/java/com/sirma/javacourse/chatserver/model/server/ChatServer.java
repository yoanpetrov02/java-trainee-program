package com.sirma.javacourse.chatserver.model.server;

import com.sirma.javacourse.chatserver.model.observer.Observable;
import com.sirma.javacourse.chatserver.model.observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

/**
 * Server class, responsible for accepting and managing client connections.
 */
public class ChatServer implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatServer.class);

    private ServerSocket serverSocket;
    private final String hostname;
    private final int port;
    private final List<ClientHandler> clients;
    private Subscriber appController;

    public ChatServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        clients = new Vector<>();
    }

    /**
     * Starts the server and listens for incoming connections.
     *
     * @throws IOException if an error occurs while starting the server.
     */
    public void start() throws IOException {
        if (isRunning()) {
            return;
        }
        InetAddress address = InetAddress.getByName(hostname);
        serverSocket = new ServerSocket(port, 50, address);
        notifySubscriber(String.format(
                "Server started on %s, port %s", hostname, port));
        listen();
    }

    /**
     * Stops the server if it's running and closes all client connections.
     */
    public void stop() {
        if (!isRunning()) {
            return;
        }
        for (ClientHandler client : clients) {
            client.closeConnection();
        }
        try {
            serverSocket.close();
        } catch (IOException ignored) {}
    }

    /**
     * Listens for incoming client connections and accepts them.
     */
    public void listen() {
        notifySubscriber("Listening for clients...");
        while (isRunning()) {
            try {
                acceptClient();
                notifySubscriber("A client has connected.");
            } catch (SocketException e) {
                notifySubscriber("Server closed.");
                return;
            } catch (IOException e) {
                LOGGER.error("Error while accepting clients.", e);
                return;
            }
        }
    }

    public boolean isRunning() {
        return serverSocket != null &&
                !serverSocket.isClosed();
    }

    @Override
    public void setSubscriber(Subscriber s) {
        appController = s;
    }

    /**
     * Updates the app's controller with the given message, and logs it.
     *
     * @param message the update message.
     */
    @Override
    public void notifySubscriber(String message) {
        if (appController != null) {
            appController.update(message);
        }
        LOGGER.info(message);
    }

    /**
     * Accepts a client and adds them to the client list.
     *
     * @throws IOException if an error occurs.
     */
    private void acceptClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        ClientHandler client = new ClientHandler(clientSocket);
        clients.add(client);

        if (appController != null) {
            client.setSubscriber(appController);
        }
        new Thread(client).start();
    }
}
