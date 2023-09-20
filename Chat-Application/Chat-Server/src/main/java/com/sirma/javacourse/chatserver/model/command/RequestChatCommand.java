package com.sirma.javacourse.chatserver.model.command;

import com.sirma.javacourse.chatserver.model.chat.ChatHistory;
import com.sirma.javacourse.chatserver.model.chat.ChatMessage;
import com.sirma.javacourse.chatserver.model.chat.ChatPair;
import com.sirma.javacourse.chatserver.model.serializers.ChatHistorySerializer;
import com.sirma.javacourse.chatserver.model.server.ClientHandler;
import com.sirma.commons.commandmessage.CommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Request chat command, encapsulating the logic that needs to be performed
 * when a user requests their chat history with another user.
 */
public class RequestChatCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestChatCommand.class);

    private final CommandMessage command;
    private final ClientHandler handler;

    RequestChatCommand(CommandMessage command, ClientHandler handler) {
        this.command = command;
        this.handler = handler;
    }

    /**
     * Sends the chat history, with the recipient in the command's arguments, to the user.
     */
    @Override
    public void execute() {
        String recipient = command.getArguments()[0].toLowerCase();
        sendChatHistoryToUser(recipient);
    }

    /**
     * Retrieves the chat history from a file and sends it to the user.
     *
     * @param recipient the recipient to retrieve the chat history with.
     */
    private void sendChatHistoryToUser(String recipient) {
        ChatPair pair = new ChatPair(handler.getClientUsername(), recipient);
        int fileName = pair.hashCode();
        Path filePath = Path.of("chats/" + fileName);
        ChatHistory history = retrieveChatHistoryFromFile(filePath);
        if (history == null) {
            LOGGER.error("Failed to retrieve the chat history of {} and {}.",
                    handler.getClientUsername(), recipient);
            handler.send("chatHistoryError []");
            return;
        }
        sendAllMessages(history);
    }

    /**
     * Retrieves a chat history from the given path.
     *
     * @param filePath the path of the file where the history is kept.
     * @return the retrieved {@code ChatHistory} object, or null if an error occurs.
     */
    private ChatHistory retrieveChatHistoryFromFile(Path filePath) {
        if (!Files.exists(filePath)) {
            return new ChatHistory();
        }
        try {
            return ChatHistorySerializer.retrieveObject(filePath.toString());
        } catch (IOException e) {
            LOGGER.error("Error while retrieving chat history from file.", e);
            return null;
        }
    }

    /**
     * Sends all messages in the given chat history to the user.
     *
     * @param history the chat history.
     */
    private void sendAllMessages(ChatHistory history) {
        ChatMessage[] messages = history.getMessagesArray();
        if (messages.length < 1) {
            return;
        }
        for (ChatMessage message : messages) {
            CommandMessage messageToSend = new CommandMessage(
                    "chatHistoryMessage", message.getArgumentsArray());
            handler.send(messageToSend.getMessage());
        }
    }
}
