package com.sirma.javacourse.networking.reversemessage.model;

import com.sirma.javacourse.networking.reversemessage.model.observer.Observable;
import com.sirma.javacourse.networking.reversemessage.model.observer.Subscriber;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Handles a single client of a {@code ReverseServer}, listening to messages from it and sending them back reversed.
 */
public class ClientHandler
        extends Thread
        implements Observable {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ClientHandler.class);
    private static final String MESSAGE_FORMAT = "Client#%d> %s";
    private static final String CLOSE_MESSAGE = ".";

    private final Socket client;
    private final int clientNumber;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Subscriber appController;

    public ClientHandler(Socket client, int clientNumber) {
        this.client = client;
        this.clientNumber = clientNumber;
    }

    /**
     * Starts the handler thread, greets the client and starts reading messages from them until the connection gets closed.
     */
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            greet();
            startReadLoop();
        } catch (SocketException e) {
            notifySubscribers("Connection with the client was closed.");
        } catch (IOException e) {
            LOGGER.error("Error while reading messages from the client.", e);
        }
    }

    /**
     * Closes the connection with the client.
     */
    public void closeConnection() {
        if (running) {
            try {
                out.println(CLOSE_MESSAGE);
                client.close();
                running = false;
                notifySubscribers("The client has disconnected.");
            } catch (IOException ignored) {}
        }
    }

    @Override
    public void addSubscriber(Subscriber s) {
        appController = s;
    }

    @Override
    public void notifySubscribers(String message) {
        String m = String.format(MESSAGE_FORMAT, clientNumber, message);
        if (appController != null) {
            appController.update(m);
        }
        LOGGER.info(m);
    }

    /**
     * Sends a greeting message to the user.
     */
    private void greet() {
        out.println(String.format("Hello! You are client #%d.", clientNumber));
    }

    /**
     * Continuously reads messages from the client and sends them back reversed.
     *
     * @throws IOException if an i/o error occurs.
     */
    private void startReadLoop() throws IOException {
        running = true;
        while (running) {
            String message = readClientMessage();
            if (message == null || CLOSE_MESSAGE.equals(message)) {
                closeConnection();
            } else {
                writeReversedMessage(message);
            }
        }
    }

    /**
     * Reads a single message from the client.
     *
     * @return the read message.
     * @throws IOException if an i/o error occurs.
     */
    private String readClientMessage() throws IOException {
        String line = in.readLine();
        if (line != null) {
            notifySubscribers("Message from the client: " + line);
        }
        return line;
    }

    /**
     * Reverses the given message and writes it reversed to the client's {@code OutputStream}.
     *
     * @param message the message to be written.
     */
    private void writeReversedMessage(String message) {
        String reversed = "The reverse of the given message is: " +
                StringUtils.reverse(message);
        out.println(reversed);
        notifySubscribers("Wrote reversed message to client: " + reversed);
    }
}
