package com.sirma.javacourse.chatserver.model.serializers;

import com.sirma.javacourse.chatserver.model.users.UserList;

import java.io.*;

/**
 * Utility class, responsible for writing and reading {@code UserList} objects to/from files.
 */
public final class UserListSerializer {

    private UserListSerializer() {
        // utility class
    }

    public static void saveObject(String path, UserList o)
            throws IOException {
        try (FileOutputStream fileStream = new FileOutputStream(path);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(o);
        }
    }

    public static UserList retrieveObject(String path)
            throws IOException {
        try (FileInputStream fileStream = new FileInputStream(path);
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return (UserList) objectStream.readObject();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            return null;
        }
    }
}
