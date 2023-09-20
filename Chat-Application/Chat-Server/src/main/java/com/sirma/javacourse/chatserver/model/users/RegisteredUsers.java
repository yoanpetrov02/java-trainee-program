package com.sirma.javacourse.chatserver.model.users;

import com.sirma.javacourse.chatserver.model.serializers.UserListSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Utility class, responsible for managing all registered users.
 * Whenever the app is started, this list is read from a file (if there are any previously registered users).
 */
public final class RegisteredUsers {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredUsers.class);

    private static UserList registeredUsers;

    static {
        try {
            registeredUsers = UserListSerializer.retrieveObject("registeredusers.dat");
        } catch (IOException e) {
            LOGGER.error("Error while loading user list from file.", e);
        }
        if (registeredUsers == null) {
            registeredUsers = new UserList();
        }
    }

    private RegisteredUsers() {
        // utility class
    }

    /**
     * Registers the given client if they aren't already registered, and saves the list.
     *
     * @param username the client's username.5
     */
    public static void registerClient(String username) {
        if (!isRegistered(username)) {
            registeredUsers.addUser(username);
            saveUsers();
        }
    }

    /**
     * Saves the user list to a file.
     */
    public static void saveUsers() {
        try {
            UserListSerializer.saveObject("registeredusers.dat", registeredUsers);
        } catch (IOException e) {
            LOGGER.error("Error while saving user list to file.", e);
        }
    }

    public static boolean isRegistered(String username) {
        return registeredUsers.containsUser(username);
    }

    public static List<String> getRegisteredUsers() {
        return registeredUsers.getUsers();
    }
}
