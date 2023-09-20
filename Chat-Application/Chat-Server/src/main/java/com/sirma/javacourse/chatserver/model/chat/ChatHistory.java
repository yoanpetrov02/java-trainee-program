package com.sirma.javacourse.chatserver.model.chat;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chat history. A user can add messages to it or retrieve them as an array.
 * As a {@code Serializable} object, a chat history can be saved in a file and retrieved later
 * using {@code ChatHistorySerializer}.
 */
public class ChatHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = -3703850530639738020L;

    private final List<ChatMessage> messages;

    public ChatHistory() {
        messages = new ArrayList<>();
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public ChatMessage[] getMessagesArray() {
        return messages.toArray(ChatMessage[]::new);
    }
}
