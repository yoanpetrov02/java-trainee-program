package com.sirma.javacourse.chatserver.model.users;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Represents a user list. The user can add or remove usernames to/from it.
 * As a Serializable object, an instance of this class can be saved/retrieved using {@code UserListSerializer}.
 */
public class UserList implements Serializable {

    @Serial
    private static final long serialVersionUID = -2382679705178218361L;

    private final List<String> users;

    public UserList() {
        users = new Vector<>();
    }

    public void addUser(String user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(String user) {
        users.remove(user);
    }

    public boolean containsUser(String user) {
        return users.contains(user);
    }

    public List<String> getUsers() {
        return users;
    }
}
