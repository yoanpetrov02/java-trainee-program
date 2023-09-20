package com.sirma.javacourse.chatserver.model.serializers;

import com.sirma.javacourse.chatserver.model.chat.ChatHistory;

import java.io.*;

/**
 * Utility class, responsible for writing and reading {@code ChatHistory} objects to/from files.
 */
public final class ChatHistorySerializer {

    private ChatHistorySerializer() {
        // utility class
    }

    public static void saveObject(String path, ChatHistory o)
            throws IOException {
        try (FileOutputStream fileStream = new FileOutputStream(path);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(o);
        }
    }

    public static ChatHistory retrieveObject(String path)
            throws IOException {
        try (FileInputStream fileStream = new FileInputStream(path);
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return (ChatHistory) objectStream.readObject();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            return null;
        }
    }
}
