package com.sirma.javacourse.chatserver.model.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum for all command types that can be handled by the server.
 */
public enum CommandType {

    CONNECT_REQUEST("connectRequest"),
    USER_MESSAGE("userMessage"),
    REQUEST_CHAT("requestChat"),
    END_SESSION("endSession");

    private static final Map<String, CommandType> TYPES = new HashMap<>();

    static {
        for (CommandType c : values()) {
            TYPES.put(c.label, c);
        }
    }

    public final String label;

    CommandType(String label) {
        this.label = label;
    }

    public static CommandType valueOfLabel(String label) {
        return TYPES.get(label);
    }
}
