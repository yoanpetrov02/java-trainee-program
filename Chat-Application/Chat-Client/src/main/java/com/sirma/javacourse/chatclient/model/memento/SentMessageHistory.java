package com.sirma.javacourse.chatclient.model.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps a history of the last sent messages, and returns them on demand.
 */
public class SentMessageHistory implements Memento {

    private static final int DEFAULT_CAPACITY = 5;

    private List<String> messages;
    private int pointer;
    private final int maxCapacity;

    public SentMessageHistory() {
        this(DEFAULT_CAPACITY);
    }

    public SentMessageHistory(int maxCapacity) {
        messages = new ArrayList<>();
        pointer = -1;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Adds a message to the history.
     *
     * @param message the message to be added.
     */
    @Override
    public void add(String message) {
        if (!isTheSameAsLast(message)) {
            messages.add(message);
        }
        if (messages.size() > maxCapacity) {
            messages = messages.subList(1, messages.size());
        }
        pointer = messages.size();
    }

    @Override
    public String last() {
        if (pointer - 1 >= 0) {
            pointer--;
        }
        return current();
    }

    @Override
    public String next() {
        if (pointer + 1 < maxCapacity) {
            pointer++;
        }
        return current();
    }

    private String current() {
        try {
            return messages.get(pointer);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private boolean isTheSameAsLast(String message) {
        if (messages.isEmpty()) {
            return false;
        }
        return message.equals(
                messages.get(messages.size() - 1));
    }
}
