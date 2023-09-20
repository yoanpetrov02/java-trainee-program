package com.sirma.javacourse.chatserver.app.locale;

import java.util.ListResourceBundle;

/**
 * ServerWindow English language resource bundle.
 */
public class ServerWindow_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"window.title", "Server"},
                {"button.start", "Start"},
                {"button.stop", "Stop"},
                {"error.start", "The server is already running!"},
                {"error.stop", "The server is not running!"},
                {"error.title", "Error"},
                {"error.header", "An error has occurred."},
                {"error.io", "An error occurred while starting the server."},
                {"exitwindow.title", "Exit"},
                {"exitwindow.header", "Exit the server app"},
                {"exitwindow.content", "Are you sure you want to close the server application?"}
        };
    }
}
