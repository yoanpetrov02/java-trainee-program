package com.sirma.javacourse.chatclient.app.locale;

import java.util.ListResourceBundle;

/**
 * ClientWindow English language resource bundle.
 */
public class ClientWindow_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"window.title", "Chat client"},
                {"label.onlineusers", "Online"},
                {"label.offlineusers", "Offline"},
                {"button.sendbutton", "Send"},
                {"button.reconnectbutton", "Reconnect"},
                {"error.chathistory", "Failed to load the chat history."},
                {"error.usermessage", "The recipient does not exist!"},
                {"error.title", "Error"},
                {"error.header", "An error has occurred."},
                {"exitwindow.title", "Exit"},
                {"exitwindow.header", "Exit client"},
                {"exitwindow.content", "Are you sure you want to close the chat client?"}
        };
    }
}
