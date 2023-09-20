package com.sirma.javacourse.chatserver.model.server;

import com.sirma.javacourse.chatserver.model.command.Command;
import com.sirma.javacourse.chatserver.model.command.CommandFactory;
import com.sirma.javacourse.chatserver.model.observer.Observable;
import com.sirma.javacourse.chatserver.model.observer.Subscriber;
import com.sirma.javacourse.chatserver.model.users.OnlineUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * A client handler, responsible for communicating with a client.
 */
public class ClientHandler
        extends Thread
        implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ClientHandler.class);

    private final Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientUsername;
    private boolean running;
    private boolean validated;
    private Subscriber appController;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        clientUsername = "unknown";
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
     * Initializes the streams and starts the message read loop.
     */
    @Override
    public void run() {
        running = true;
        try {
            initializeStreams();
        } catch (IOException e) {
            LOGGER.error("Error while retrieving the client's streams", e);
        }
        startReadLoop();
        closeConnection();
        OnlineUsers.sendToAll(String.format("removeFromUserList [%s]", clientUsername));
        OnlineUsers.removeClient(clientUsername);
    }

    /**
     * Sends a message to the client.
     *
     * @param message the message to send.
     */
    public void send(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    /**
     * Reads a message from the client.
     *
     * @return the received message.
     * @throws IOException if an error occurs while reading the message.
     */
    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void closeConnection() {
        if (!running) {
            return;
        }
        LOGGER.info("Closing the connection with the client...");
        running = false;
        try {
            clientSocket.close();
        } catch (IOException ignored) {}
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    /**
     * Takes the streams from the client's socket and initializes the handler's streams with them.
     *
     * @throws IOException if an error occurs while initializing the streams.
     */
    private void initializeStreams() throws IOException {
        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    /**
     * Starts a loop for reading messages from the client and executing {@code Commands},
     * depending on the read message.
     */
    private void startReadLoop() {
        while (running) {
            try {
                String message = receiveMessage();
                if (message == null) {
                    running = false;
                    break;
                }
                Command command = CommandFactory.getInstanceFor(message, this);
                if (command != null) {
                    command.execute();
                }
            } catch (SocketException e) {
                notifySubscriber("Connection with %s was closed.".formatted(clientUsername));
                return;
            } catch (IOException e) {
                LOGGER.error("Error while reading client messages", e);
                return;
            }
        }
    }
}
