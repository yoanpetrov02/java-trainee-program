package com.sirma.javacourse.networking.multicasting.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that can be used to get all the available channels that messages can be sent to.
 */
public final class Channels {

    private static final Logger LOGGER = LoggerFactory.getLogger(Channels.class);

    private Channels() {}

    /**
     * Opens the channels.txt file and parses the channels in it, putting them in a {@code HashMap}.
     *
     * @return the newly created {@code HashMap}.
     */
    public static Map<String, Integer> getChannels() {
        File channels = getChannelFile();
        Map<String, Integer> channelMap = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(channels))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parsed = line.split(" ");
                channelMap.put(
                        parsed[0],
                        Integer.parseInt(parsed[1]));
            }
            return channelMap;
        } catch (IOException e) {
            LOGGER.error("An error occurred while retrieving the channels", e);
            return Collections.emptyMap();
        }
    }

    /**
     * Creates a {@code File} instance, representing the channels.txt file.
     *
     * @return the newly created {@code File}.
     */
    private static File getChannelFile() {
        try {
            return new File(Channels.class.
                    getResource("/channels.txt").toURI());
        } catch (URISyntaxException e) {
            LOGGER.error("An error occurred while finding the channels.txt file");
            return new File("");
        }
    }
}
