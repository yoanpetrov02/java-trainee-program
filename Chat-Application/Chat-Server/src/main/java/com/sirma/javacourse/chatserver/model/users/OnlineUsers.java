package com.sirma.javacourse.chatserver.model.users;

import com.sirma.javacourse.chatserver.model.server.ClientHandler;

import java.util.Hashtable;
import java.util.Map;

/**
 * Utility class, responsible for managing the online users.
 */
public final class OnlineUsers {

    private static final Map<String, ClientHandler> ONLINE_CLIENTS = new Hashtable<>();

    private OnlineUsers() {
        // utility class
    }

    /**
     * Sends a message to the given user, if they are online.
     *
     * @param username the user to send the message to.
     * @param message the message to send.
     */
    public static void sendTo(String username, String message) {
        if (ONLINE_CLIENTS.containsKey(username)) {
            ONLINE_CLIENTS.get(username).send(message);
        }
    }

    /**
     * Sends the given message to all online users.
     *
     * @param message the message to send.
     */
    public static void sendToAll(String message) {
        ONLINE_CLIENTS.forEach((username, handler) ->
                handler.send(message));
    }

    /**
     * Adds a client to the online clients map, and registers them.
     *
     * @param username the user to add.
     * @param handler the {@code ClientHandler} instance for that user.
     */
    public static void addClient(String username, ClientHandler handler) {
        if (!isClientOnline(username)) {
            ONLINE_CLIENTS.put(username, handler);
        }
        RegisteredUsers.registerClient(username);
    }

    /**
     * Removes a client from the online clients map.
     *
     * @param username the user to remove.
     */
    public static void removeClient(String username) {
        ONLINE_CLIENTS.remove(username);
    }

    /**
     * Checks whether the given client is online.
     *
     * @param username the username of the client.
     * @return true if the client is in the online clients map, false otherwise.
     */
    public static boolean isClientOnline(String username) {
        return ONLINE_CLIENTS.containsKey(username);
    }

    /**
     * Returns the online clients' usernames under the form of a string array.
     *
     * @return the usernames of all online users.
     */
    public static String[] getOnlineUsers() {
        return ONLINE_CLIENTS.keySet().toArray(String[]::new);
    }
}
