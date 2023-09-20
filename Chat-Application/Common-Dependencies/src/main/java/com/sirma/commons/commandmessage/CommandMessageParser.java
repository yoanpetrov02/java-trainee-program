package com.sirma.commons.commandmessage;

import java.util.ArrayList;
import java.util.List;

public class CommandMessageParser {

    public static CommandMessage parseMessage(String message) {
        String trimmed = message.trim();
        String type = getType(trimmed);
        String[] arguments = getArguments(
                getArgumentsString(trimmed, type));
        return new CommandMessage(type, arguments);
    }

    private static String getType(String message) {
        return message.substring(0, message.indexOf(' '));
    }

    private static String getArgumentsString(String message, String type) {
        return message.substring(type.length() + 1);
    }

    private static String[] getArguments(String argumentsString) {
        List<String> arguments = new ArrayList<>();
        for (int i = 0; i < argumentsString.length();) {
            int leftBracketIndex = argumentsString.indexOf('[', i);
            int rightBracketIndex = argumentsString.indexOf(']', i);

            String currentArgument =
                    argumentsString.substring(leftBracketIndex + 1, rightBracketIndex);

            arguments.add(currentArgument);
            i = rightBracketIndex + 1;
        }
        return arguments.toArray(String[]::new);
    }
}
