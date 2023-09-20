package com.sirma.javacourse.chatclient.model.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Client class, responsible for connecting to a chat server and communicating with it.
 */
public class ChatClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ChatClient.class);

    private final String hostname;
    private final int port;
    private final String username;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected;

    /**
     * Constructs a {@code ChatClient} with the given hostname, port and username to connect to the server with.
     *
     * @param hostname the hostname of the server.
     * @param port the port on which the server is listening.
     * @param username the username of the client.
     */
    public ChatClient(String hostname, int port, String username) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
    }

    /**
     * Attempts to connect to the server using the hostname and port fields of the object.
     */
    public void connect() {
        connected = connectInner();
        try {
            initializeStreams();
        } catch (IOException e) {
            LOGGER.error("Error while initializing streams.", e);
            closeConnection();
        }
    }

    /**
     * Sends the given message to the server, if connected.
     *
     * @param message the message to send.
     */
    public void sendMessage(String message) {
        if (!isConnected()) {
            return;
        }
        out.println(message);
    }

    /**
     * Reads a message from the server. Closes the connection if a {@code SocketException} occurs.
     *
     * @return the read message, or null if an error occurs or the client is not connected to the server.
     */
    public String receiveMessage() {
        if (!isConnected()) {
            return null;
        }
        try {
            return in.readLine();
        } catch (SocketException e) {
            LOGGER.info("The connection was closed.");
            closeConnection();
        } catch (IOException e) {
            LOGGER.error("Error while reading messages from the server.", e);
        }
        return null;
    }

    /**
     * Closes the connection with the server,
     * sending an "end session" command and setting the connected flag to false.
     */
    public void closeConnection() {
        if (!isConnected()) {
            return;
        }
        sendMessage("endSession []");
        LOGGER.info("Connection with the server was closed.");
        connected = false;
        try {
            socket.close();
        } catch (IOException ignored) {}
    }

    public boolean isConnected() {
        return socket != null
                && in != null
                && out != null
                && !socket.isClosed()
                && connected;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Attempts to connect to the server using the hostname and port fields of the object.
     *
     * @return true if the connection was successful, false otherwise.
     */
    private boolean connectInner() {
        try {
            socket = new Socket(hostname, port);
            LOGGER.info("Connected to the server on {} at port {}.", hostname, port);
            return true;
        } catch (ConnectException e) {
            LOGGER.error("There is no server running on {} at port {}.", hostname, port);
        } catch (UnknownHostException e) {
            LOGGER.error("Unknown hostname: {}.", hostname);
        } catch (IOException e) {
            LOGGER.error("Error while connecting to the server.", e);
        }
        return false;
    }

    /**
     * Initializes the stream objects that are used to communicate with the server.
     *
     * @throws IOException if an error occurs while initializing the streams.
     */
    private void initializeStreams() throws IOException {
        if (socket != null && connected) {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }
    }
}
