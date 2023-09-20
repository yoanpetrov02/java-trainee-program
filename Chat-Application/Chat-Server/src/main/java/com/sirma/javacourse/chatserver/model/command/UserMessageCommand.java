package com.sirma.javacourse.chatserver.model.command;

import com.sirma.javacourse.chatserver.model.chat.ChatHistory;
import com.sirma.javacourse.chatserver.model.chat.ChatMessage;
import com.sirma.javacourse.chatserver.model.chat.ChatPair;
import com.sirma.javacourse.chatserver.model.serializers.ChatHistorySerializer;
import com.sirma.javacourse.chatserver.model.server.ClientHandler;
import com.sirma.javacourse.chatserver.model.users.OnlineUsers;
import com.sirma.javacourse.chatserver.model.users.RegisteredUsers;
import com.sirma.commons.commandmessage.CommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * User message command, encapsulating the logic that needs to be performed
 * when a user sends a message to another user.
 */
public class UserMessageCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageCommand.class);

    private final CommandMessage command;
    private final ClientHandler handler;

    UserMessageCommand(CommandMessage command, ClientHandler handler) {
        this.command = command;
        this.handler = handler;
    }

    /**
     * Constructs the chat message and sends it to the recipient.
     */
    @Override
    public void execute() {
        String recipient = command.getArguments()[0].toLowerCase();
        String timeOfSending = command.getArguments()[1];
        String chatMessage = command.getArguments()[2];
        ChatMessage message = new ChatMessage(
                timeOfSending,
                handler.getClientUsername(),
                recipient,
                chatMessage);
        sendUserMessage(message);
    }

    /**
     * Checks whether the recipient is valid,
     * sends the message to them if they are, and saves the chat history in the file.
     *
     * @param message the message to send to them.
     */
    private void sendUserMessage(ChatMessage message) {
        if (!isRecipientValid(message.getRecipient())) {
            return;
        }

        String userMessage = new CommandMessage(
                "userMessage",
                message.getSender(),
                message.getTimeOfSending(), message.getMessage())
                .getMessage();

        OnlineUsers.sendTo(message.getRecipient(), userMessage);
        saveHistory(message);
    }

    /**
     * Checks whether the recipient is a valid user.
     *
     * @param recipient the recipient to validate.
     * @return true if the recipient is a registered user, false otherwise.
     */
    private boolean isRecipientValid(String recipient) {
        if (!RegisteredUsers.isRegistered(recipient)) {
            String errorMessage = new CommandMessage(
                    "userMessageError",
                    "The recipient does not exist!")
                    .getMessage();
            handler.send(errorMessage);
            return false;
        }
        return true;
    }

    /**
     * Retrieves the chat history from the file, adds the given message to it and saves it back to the file.
     *
     * @param message the new message to add to the history.
     */
    private void saveHistory(ChatMessage message) {
        ChatPair pair = new ChatPair(message.getSender(), message.getRecipient());
        int chatID = pair.hashCode();
        Path filePath = Path.of("chats/" + chatID);
        ChatHistory history = loadHistory(filePath);
        if (history == null) {
            LOGGER.error("Failed to save chat history of {} and {}.",
                    message.getSender(), message.getRecipient());
            return;
        }
        history.addMessage(message);
        try {
            ChatHistorySerializer.saveObject(filePath.toString(), history);
        } catch (IOException e) {
            LOGGER.error("Error while saving chat history to file.", e);
        }
    }

    /**
     * Loads the {@code ChatHistory} from the file at the given path.
     *
     * @param path the path of the file that contains the {@code ChatHistory} object.
     * @return the loaded {@code ChatHistory} object, or null if an error occurs.
     */
    private ChatHistory loadHistory(Path path) {
        if (!Files.exists(path)) {
            return new ChatHistory();
        }
        try {
            return ChatHistorySerializer.retrieveObject(path.toString());
        } catch (IOException e) {
            LOGGER.error("Error while retrieving chat history from file.", e);
            return null;
        }
    }
}
