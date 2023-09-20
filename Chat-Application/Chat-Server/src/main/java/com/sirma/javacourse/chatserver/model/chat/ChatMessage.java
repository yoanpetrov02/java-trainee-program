package com.sirma.javacourse.chatserver.model.chat;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a chat message with metadata (sender, recipient, time of sending).
 * Objects of this class are used in {@code ChatHistory} instances, so they don't
 * have their own serializer class.
 */
public class ChatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -2457379621301773503L;

    private final String timeOfSending;
    private final String sender;
    private final String recipient;
    private final String message;

    public ChatMessage(String timeOfSending, String sender, String recipient, String message) {
        this.timeOfSending = timeOfSending;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public String getTimeOfSending() {
        return timeOfSending;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String[] getArgumentsArray() {
        return new String[] {timeOfSending, sender, recipient, message};
    }
}
