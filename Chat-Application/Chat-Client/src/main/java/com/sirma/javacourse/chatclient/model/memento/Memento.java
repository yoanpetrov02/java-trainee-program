package com.sirma.javacourse.chatclient.model.memento;

/**
 * Keeps a history of {@code String} objects. The user can retrieve the last, next or current object in the history.
 */
public interface Memento {

    void add(String s);

    /**
     * Returns the last string before the current one.
     */
    String last();

    /**
     * Returns the next string after the current one.
     */
    String next();
}
