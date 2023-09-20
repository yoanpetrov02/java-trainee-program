package com.sirma.javacourse.networking.multicasting.model.sender.message;

/**
 * Represents a message that will be sent to a channel.
 */
public class ChannelMessage {

    public String message;
    public String channel;

    public ChannelMessage(String message, String channel) {
        this.message = message;
        this.channel = channel;
    }
}
