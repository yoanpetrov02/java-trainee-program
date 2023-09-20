package com.sirma.commons.commandmessage;

import java.util.Arrays;

public class CommandMessage {

    private final String type;
    private final String[] arguments;
    private final String constructedMessage;

    public CommandMessage(String type, String... arguments) {
        this.type = type;
        this.arguments = arguments;
        constructedMessage = constructMessage();
    }

    public String getType() {
        return type;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getMessage() {
        return constructedMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CommandMessage)
                && type.equals(((CommandMessage) obj).getType())
                && Arrays.equals(arguments, ((CommandMessage) obj).arguments);
    }

    private String constructMessage() {
        StringBuilder message = new StringBuilder();
        message.append(type).append(" ");
        for (String arg : arguments) {
            message.append("[")
                    .append(arg)
                    .append("]")
                    .append(" ");
        }
        return message.toString();
    }
}
