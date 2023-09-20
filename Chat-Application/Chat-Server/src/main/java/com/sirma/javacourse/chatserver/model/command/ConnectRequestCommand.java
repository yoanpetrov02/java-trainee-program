package com.sirma.javacourse.chatserver.model.command;

import com.sirma.javacourse.chatserver.model.server.ClientHandler;
import com.sirma.javacourse.chatserver.model.users.OnlineUsers;
import com.sirma.javacourse.chatserver.model.users.RegisteredUsers;
import com.sirma.commons.commandmessage.CommandMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Connect request command, encapsulating the logic that needs to be performed to validate a newly connected user.
 */
public class ConnectRequestCommand implements Command {

    private final CommandMessage command;
    private final ClientHandler handler;

    ConnectRequestCommand(CommandMessage command, ClientHandler handler) {
        this.command = command;
        this.handler = handler;
    }

    /**
     * Checks whether the client is valid and accepts/denies them.
     */
    @Override
    public void execute() {
        String username = command.getArguments()[0].toLowerCase();
        handler.setValidated(isValid(username));
        if (!handler.isValidated()) {
            denyClient();
            return;
        }
        acceptClient(username);
    }

    /**
     * Checks whether a client's username is valid. To be valid,
     * a client has to not be online and their username cannot contain illegal characters.
     *
     * @param username the client's username.
     * @return true if the client is valid, false otherwise.
     */
    private boolean isValid(String username) {
        return !containsIllegalChars(username)
                && !OnlineUsers.isClientOnline(username);
    }

    /**
     * Checks whether the username contains illegal characters.
     *
     * @param username the client's username.
     * @return true if the username is valid, false otherwise.
     */
    private boolean containsIllegalChars(String username) {
        return username.contains("[")
                || username.contains("]");
    }

    /**
     * Denies the client's connection request, closing the connection with them.
     */
    private void denyClient() {
        handler.send("connectRequest [deny]");
        handler.notifySubscriber("Client validation failed.");
        handler.closeConnection();
    }

    /**
     * Accepts the client's connection request, notifying all other clients about it
     * and sending the online and offline user lists to the client.
     *
     * @param username the client's username.
     */
    private void acceptClient(String username) {
        handler.send("connectRequest [accept]");
        handler.setClientUsername(username);
        OnlineUsers.sendToAll(String.format("addToUserList [%s]", username));
        OnlineUsers.addClient(username, handler);
        sendClientLists();
        handler.notifySubscriber("Client validation successful.");
        handler.notifySubscriber("%s was added to the online client list.".formatted(username));
    }

    /**
     * Sends the lists of online and offline users to the client.
     */
    private void sendClientLists() {
        String onlineUsersMessage = new CommandMessage(
                "onlineClientList",
                OnlineUsers.getOnlineUsers()).getMessage();
        String offlineUsersMessage = new CommandMessage(
                "offlineClientList",
                getOfflineUsers()).getMessage();
        handler.send(onlineUsersMessage);
        handler.send(offlineUsersMessage);
    }

    private String[] getOfflineUsers() {
        List<String> offlineUsers;
        offlineUsers = RegisteredUsers.getRegisteredUsers()
                .stream()
                .filter(u -> !OnlineUsers.isClientOnline(u))
                .collect(Collectors.toList());
        return offlineUsers.toArray(String[]::new);
    }
}
