package com.sirma.javacourse.chatserver.model.chat;

import java.util.Arrays;

/**
 * Represents a pair of users. The users are stored in a string array, which gets sorted on object creation to
 * ensure they are always in the same order. If two instances are constructed with the same arguments, but in a
 * different order, equals() will return true, and hashCode() will return the same value for both objects.
 * The hash code of this class' objects is used as a file name for chat histories.
 */
public class ChatPair {

    private final String[] users;

    public ChatPair(String user1, String user2) {
        users = new String[] {user1, user2};
        Arrays.sort(users);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChatPair o)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return (o.users[0].equals(users[0]) || o.users[0].equals(users[1]))
                && (o.users[1].equals(users[0]) || o.users[1].equals((users[1])));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(users);
    }
}
