package com.sirma.javacourse.chatclient.controllers;

import com.sirma.javacourse.chatclient.model.client.ChatClient;
import com.sirma.javacourse.chatclient.model.client.ServerMessageTask;
import com.sirma.commons.commandmessage.CommandMessage;
import com.sirma.commons.locale.LanguageChanger;
import com.sirma.javacourse.chatclient.model.memento.Memento;
import com.sirma.javacourse.chatclient.model.memento.SentMessageHistory;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Controller class for {@code ClientWindow}. Responsible for handling user input.
 */
public class ClientWindowController {

    private static final SimpleDateFormat DATE_FORMATTER =
            new SimpleDateFormat("HH:mm:ss");

    private static final int MAX_TEXT_LENGTH = 200;

    @FXML
    private VBox chatWindow;
    @FXML
    private ListView<String> onlineUsers;
    @FXML
    private ListView<String> offlineUsers;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private TextField messageTextField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label onlineUsersLabel;
    @FXML
    private Label offlineUsersLabel;
    @FXML
    private Button sendButton;
    @FXML
    private Button reconnectButton;
    @FXML
    private SplitMenuButton languageChooser;

    private ChatClient client;
    private Memento history;
    private Alert errorAlert;
    private String selectedUser;
    private boolean connected = true;

    public void initialize() {
        errorAlert = new Alert(Alert.AlertType.ERROR);

        messageTextField.setOnKeyTyped((e) -> {
            if (messageTextField.getText() == null) {
                return;
            }
            if (messageTextField.getText().length() > MAX_TEXT_LENGTH) {
                messageTextField.setText(
                        messageTextField.getText().substring(0, MAX_TEXT_LENGTH)
                );
                messageTextField.positionCaret(messageTextField.getText().length());
            }
        });
        messageTextArea.setWrapText(true);

        history = new SentMessageHistory();
        messageTextField.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case ENTER -> sendButton.fire();
                case UP -> messageTextField.setText(history.last());
                case DOWN -> messageTextField.setText(history.next());
            }
        });

        LanguageChanger.bindUIElement(onlineUsersLabel, "label.onlineusers");
        LanguageChanger.bindUIElement(offlineUsersLabel, "label.offlineusers");
        LanguageChanger.bindUIElement(sendButton, "button.sendbutton");
        LanguageChanger.bindUIElement(reconnectButton, "button.reconnectbutton");
    }

    /**
     * Selects a user from the online list.
     */
    @FXML
    public void selectOnlineUser() {
        selectUser(onlineUsers.getSelectionModel().getSelectedItem());
    }

    /**
     * Selects a user from the offline list.
     */
    @FXML
    public void selectOfflineUser() {
        selectUser(offlineUsers.getSelectionModel().getSelectedItem());
    }

    /**
     * Sends a chat message to the selected user after validating them.
     */
    @FXML
    public void sendMessage() {
        if (!validateRecipient()) {
            return;
        }
        if (messageTextField.getText() == null) {
            return;
        }
        String chatMessage = StringUtils.capitalize(messageTextField.getText().trim());
        if (StringUtils.isEmpty(chatMessage)) {
            return;
        }
        messageTextField.clear();
        String time = getCurrentTime();
        CommandMessage message = new CommandMessage(
                "userMessage", selectedUser, time, chatMessage);
        client.sendMessage(message.getMessage());
        messageTextArea.appendText(String.format("[%s] %s: %s%n",
                time, client.getUsername(), chatMessage));
        history.add(chatMessage);
    }

    /**
     * Sets the language of the UI to English.
     */
    @FXML
    public void setToEnglish() {
        if ("English".equals(languageChooser.getText())) {
            return;
        }
        languageChooser.setText("English");
        LanguageChanger.setLocale(new Locale("en"));
    }

    /**
     * Sets the language of the UI to Bulgarian.
     */
    @FXML
    public void setToBulgarian() {
        if ("Български".equals(languageChooser.getText())) {
            return;
        }
        languageChooser.setText("Български");
        LanguageChanger.setLocale(new Locale("bg"));
    }

    /**
     * Makes an attempt to reconnect to the server in case the connection with it was lost.
     */
    @FXML
    public void attemptReconnect() {
        client = new ChatClient(client.getHostname(), client.getPort(), client.getUsername());
        client.connect();
        if (client.isConnected()) {
            connected = true;
            client.sendMessage("connectRequest [%s]".formatted(client.getUsername()));
            startReadTaskLoop();
        }
    }

    public void setClient(ChatClient client) {
        this.client = client;
    }

    /**
     * Closes the connection with the server.
     */
    public void disconnect() {
        client.closeConnection();
    }

    /**
     * Starts a loop that generates read tasks, reads messages from the server and updates the app
     * based on the type/arguments of the received commands.
     */
    public void startReadTaskLoop() {
        usernameLabel.setText(client.getUsername());
        new Thread(() -> {
            while (connected) {
                Thread task = generateReadTask();
                if (task == null) {
                    return;
                }
                task.start();
                try {
                    task.join();
                } catch (InterruptedException ignored) {}
            }
            Platform.runLater(this::setToReconnectMode);
        }).start();
    }

    /**
     * Marks the given username as the currently selected user and loads the chat history with that user.
     *
     * @param selectedItem the selected user.
     */
    private void selectUser(String selectedItem) {
        if (selectedItem == null) {
            return;
        }
        if (selectedItem.equals(selectedUser)) {
            if (chatWindow.isVisible()) {
                return;
            }
        }
        selectedUser = selectedItem;
        loadChat();
        chatWindow.setVisible(true);
    }

    /**
     * Checks whether the selected user is a valid recipient.
     *
     * @return true if they exist anywhere in the online or offline user lists, false otherwise.
     */
    private boolean validateRecipient() {
        return onlineUsers.getItems().contains(selectedUser)
                || offlineUsers.getItems().contains(selectedUser);
    }

    /**
     * Sends a chat request command to the server to retrieve the chat history with the selected user.
     */
    private void loadChat() {
        messageTextArea.clear();
        client.sendMessage("requestChat [%s]".formatted(selectedUser));
    }

    /**
     * Uses the date formatter to return a formatted string of the current time.
     *
     * @return the current time as a formatted string.
     */
    private String getCurrentTime() {
        return DATE_FORMATTER.format(
                new Date(System.currentTimeMillis()));
    }

    /**
     * Generates a {@code ServerMessageTask} and an object property to bind
     * the task's value property to.
     *
     * @return a {@code Thread} object with the created task.
     */
    private Thread generateReadTask() {
        if (client == null || !client.isConnected()) {
            return null;
        }
        ServerMessageTask task = new ServerMessageTask(client);
        ObjectProperty<CommandMessage> property = generateProperty();
        property.bind(task.valueProperty());
        return new Thread(task);
    }

    /**
     * Generates an {@code ObjectProperty} and adds a listener to it, which calls
     * parseMessage() whenever the value is changed.
     *
     * @return the created {@code ObjectProperty}.
     */
    private ObjectProperty<CommandMessage> generateProperty() {
        ObjectProperty<CommandMessage> property = new SimpleObjectProperty<>();
        property.addListener((observableValue, commandMessage, t1) ->
                handleCommand(property.get()));
        return property;
    }

    private void setToReconnectMode() {
        reconnectButton.setVisible(true);
        chatWindow.setVisible(false);
        onlineUsers.getItems().clear();
        offlineUsers.getItems().clear();
        selectedUser = "";
    }

    /**
     * Parses a message from the server, acting based on the type of command.
     *
     * @param command the command message, containing the type of the command and the arguments.
     */
    private void handleCommand(CommandMessage command) {
        switch (command.getType()) {
            case "connectRequest" -> handleConnectRequest(command.getArguments());
            case "onlineClientList" -> handleOnlineClientList(command.getArguments());
            case "offlineClientList" -> handleOfflineClientList(command.getArguments());
            case "addToUserList" -> handleAddToUserList(command.getArguments());
            case "removeFromUserList" -> handleRemoveFromUserList(command.getArguments());
            case "chatHistoryError" -> handleChatHistoryError();
            case "chatHistoryMessage" -> handleChatHistoryMessage(command.getArguments());
            case "userMessageError" -> handleUserMessageError();
            case "userMessage" -> handleUserMessage(command.getArguments());
            case "disconnected" -> connected = false;
        }
    }

    /**
     * Handles a connect request response,
     * hiding the reconnect button and starting a new message read loop.
     *
     * @param args the command's arguments.
     */
    private void handleConnectRequest(String[] args) {
        String response = args[0];
        if ("accept".equals(response)) {
            reconnectButton.setVisible(false);
            startReadTaskLoop();
        }
    }

    /**
     * Handles an online client list message, updating the online users list.
     *
     * @param args the command's arguments.
     */
    private void handleOnlineClientList(String[] args) {
        onlineUsers.getItems().clear();
        for (String username : args) {
            if (!client.getUsername().equals(username)) {
                onlineUsers.getItems().add(username);
            }
        }
    }

    /**
     * Handles an offline client list message, updating the offline users list.
     *
     * @param args the command's arguments.
     */
    private void handleOfflineClientList(String[] args) {
        offlineUsers.getItems().clear();
        for (String username : args) {
            if (!client.getUsername().equals(username)) {
                offlineUsers.getItems().add(username);
            }
        }
    }

    /**
     * Handles an add to user list message, adding the given user to the online user list
     * and removing them from the offline user list.
     *
     * @param args the command's arguments.
     */
    private void handleAddToUserList(String[] args) {
        String userToAdd = args[0];
        if (userToAdd.equals(selectedUser)) {
            chatWindow.setVisible(false);
        }
        if (!onlineUsers.getItems().contains(userToAdd)) {
            onlineUsers.getItems().add(userToAdd);
        }
        offlineUsers.getItems().remove(userToAdd);
    }

    /**
     * Handles a remove from user list message, removing the given user from the online user list
     * and adding them to the offline user list.
     *
     * @param args the command's arguments.
     */
    private void handleRemoveFromUserList(String[] args) {
        String userToRemove = args[0];
        if (userToRemove.equals(selectedUser)) {
            chatWindow.setVisible(false);
        }
        if (onlineUsers.getItems().contains(userToRemove)) {
            onlineUsers.getItems().remove(userToRemove);
            offlineUsers.getItems().add(userToRemove);
        }
    }

    /**
     * Handles a chat history error, showing the error on the screen and hiding the chat window.
     */
    private void handleChatHistoryError() {
        String errorMessage = LanguageChanger.get("error.chathistory");
        displayError(errorMessage);
        chatWindow.setVisible(false);
    }

    /**
     * Handles a chat history message, adding that message to the chat window's text area.
     *
     * @param args the command's arguments.
     */
    private void handleChatHistoryMessage(String[] args) {
        String timeOfSending = args[0];
        String sender = args[1];
        String message = args[3];
        String formatted = String.format("[%s] %s: %s%n",
                timeOfSending, sender, message);
        messageTextArea.appendText(formatted);
    }

    /**
     * Handles a user message error, showing the error on the screen and clearing the chat text area.
     */
    private void handleUserMessageError() {
        String errorMessage = LanguageChanger.get("error.usermessage");
        displayError(errorMessage);
        messageTextArea.clear();
    }

    /**
     * Handles a user message command, adding the message to the chat window's text area
     * if the selected user is the one the message came from.
     *
     * @param args the command's arguments.
     */
    private void handleUserMessage(String[] args) {
        String sender = args[0];
        String timeOfSending = args[1];
        String message = args[2];
        if (!sender.equals(selectedUser)) {
            return;
        }
        String formatted = String.format("[%s] %s: %s%n",
                timeOfSending, sender, message);
        messageTextArea.appendText(formatted);
    }

    /**
     * Displays an error message on the screen.
     *
     * @param message the error message to display.
     */
    private void displayError(String message) {
        Platform.runLater(() -> {
            errorAlert.setTitle(LanguageChanger.get("error.title"));
            errorAlert.setHeaderText(LanguageChanger.get("error.header"));
            errorAlert.setContentText(message);
            errorAlert.show();
        });
    }
}
