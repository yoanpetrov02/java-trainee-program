package com.sirma.javacourse.networking.multicasting.model.sender.message;

import java.io.*;

/**
 * Generates {@code ChannelMessages} by reading messages from a file.
 */
public class ChannelMessageGenerator {

    private final File messageFile;
    private BufferedReader reader;
    private final String channelName;

    public ChannelMessageGenerator(File messageFile, String channelName) throws FileNotFoundException {
        this.messageFile = messageFile;
        reader = new BufferedReader(new FileReader(messageFile));
        this.channelName = channelName;
    }

    /**
     * Reads the next line from the file and constructs a {@code ChannelMessage} with the line and the channel's name.
     *
     * @return the generated {@code ChannelMessage}.
     */
    public ChannelMessage getMessage() {
        try {
            String line = reader.readLine();
            if (line == null) {
                reader = new BufferedReader(new FileReader(messageFile));
                line = reader.readLine();
            }
            return new ChannelMessage(line, channelName);
        } catch (IOException e) {
            // log
            return null;
        }
    }
}
