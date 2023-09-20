package com.sirma.javacourse.chatclient.model.client;

import com.sirma.commons.commandmessage.CommandMessage;
import com.sirma.commons.commandmessage.CommandMessageParser;
import javafx.concurrent.Task;

/**
 * Represents a callable task that reads a message from the server and
 * updates the app's controller with that message.
 */
public class ServerMessageTask extends Task<CommandMessage> {

    private final ChatClient client;

    public ServerMessageTask(ChatClient client) {
        this.client = client;
    }

    /**
     * Reads a message from the server and constructs a {@code CommandMessage} instance with it.
     * If the received message is null, the {@code CommandMessage}'s type will be {@code disconnected}
     * in order to inform the app about the disconnection.
     *
     * @return the newly constructed {@code CommandMessage}.
     */
    @Override
    protected CommandMessage call() {
        String message = client.receiveMessage();
        CommandMessage command;
        if (message == null) {
            command = new CommandMessage("disconnected");
        } else {
            command = CommandMessageParser.parseMessage(message);
        }
        return command;
    }
}
