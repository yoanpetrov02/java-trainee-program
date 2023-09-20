package com.sirma.javacourse.chatserver.model.command;

import com.sirma.javacourse.chatserver.model.server.ClientHandler;
import com.sirma.commons.commandmessage.CommandMessage;
import com.sirma.commons.commandmessage.CommandMessageParser;

/**
 * Factory method class for {@code Command} instances.
 */
public final class CommandFactory {

    private CommandFactory() {
        // utility class
    }

    /**
     * Constructs a {@code Command} implementation, based on the given command type.
     *
     * @param commandType the type of the command to get a {@code Command} instance for.
     * @param handler the {@code ClientHandler} to perform actions on.
     * @return the newly constructed {@code Command} implementation, or null if the type is invalid.
     */
    public static Command getInstanceFor(String commandType, ClientHandler handler) {
        CommandMessage parsed = CommandMessageParser.parseMessage(commandType);
        CommandType type = CommandType.valueOfLabel(parsed.getType());
        if (type == null) {
            return null;
        }
        switch (type) {
            case CONNECT_REQUEST -> {
                if (handler.isValidated()) {
                    handler.send("systemMessage [User already validated!]");
                    break;
                }
                return new ConnectRequestCommand(parsed, handler);
            }
            case USER_MESSAGE -> {
                return new UserMessageCommand(parsed, handler);
            }
            case REQUEST_CHAT -> {
                return new RequestChatCommand(parsed, handler);
            }
            case END_SESSION -> {
                return new EndSessionCommand(handler);
            }
        }
        return null;
    }
}
