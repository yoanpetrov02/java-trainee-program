package com.sirma.javacourse.chatserver.model.command;

import com.sirma.javacourse.chatserver.model.server.ClientHandler;
import com.sirma.javacourse.chatserver.model.users.OnlineUsers;

/**
 * End session command, encapsulating the logic that needs to be performed when a client closes the connection.
 */
public class EndSessionCommand implements Command {

    private final ClientHandler handler;

    EndSessionCommand(ClientHandler handler) {
        this.handler = handler;
    }

    /**
     * Closes the connection with the client and notifies all other clients about it.
     */
    @Override
    public void execute() {
        handler.notifySubscriber("[%s]: The client closed the connection."
                .formatted(handler.getClientUsername()));
        handler.closeConnection();
        OnlineUsers.sendToAll(String.format("removeFromUserList [%s]", handler.getClientUsername()));
        OnlineUsers.removeClient(handler.getClientUsername());
    }
}
