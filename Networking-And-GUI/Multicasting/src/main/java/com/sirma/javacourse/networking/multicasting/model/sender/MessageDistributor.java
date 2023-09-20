package com.sirma.javacourse.networking.multicasting.model.sender;

import com.sirma.javacourse.networking.multicasting.model.Channels;
import com.sirma.javacourse.networking.multicasting.model.mediator.Component;
import com.sirma.javacourse.networking.multicasting.model.mediator.Mediator;
import com.sirma.javacourse.networking.multicasting.model.observer.Observable;
import com.sirma.javacourse.networking.multicasting.model.observer.Subscriber;
import com.sirma.javacourse.networking.multicasting.model.sender.message.ChannelMessage;
import com.sirma.javacourse.networking.multicasting.model.sender.message.ChannelMessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Distributes {@code ChannelMessages} to their specified channels.
 */
public class MessageDistributor implements Mediator, Observable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDistributor.class);

    private ChannelMessageDispatcher dispatcher;
    private MulticastSender packetSender;
    private Map<String, Integer> channels;

    private Subscriber appController;

    /**
     * Sends the given {@code ChannelMessage} to its associated channel's port.
     *
     * @param m the message to be sent.
     */
    @Override
    public void notifyMediator(ChannelMessage m) {
        channels = Channels.getChannels();
        try {
            packetSender.sendPacket(
                    m.message,
                    channels.get(m.channel));
            notifySubscribers("[%s]: %s".formatted(m.channel, m.message));
        } catch (IOException e) {
            LOGGER.error("An error occurred while sending the message", e);
        } catch (NullPointerException e) {
            LOGGER.error("There is no port associated with the given channel's name", e);
        }
    }

    /**
     * Registers the given component as a field of this object.
     *
     * @param c the component to be registered.
     */
    @Override
    public void registerComponent(Component c) {
        c.setMediator(this);

        switch (c.getName()) {
            case "dispatcher" ->
                    dispatcher = (ChannelMessageDispatcher) c;
            case "sender" ->
                    packetSender = (MulticastSender) c;
        }
    }

    @Override
    public void setSubscriber(Subscriber s) {
        appController = s;
    }

    @Override
    public void notifySubscribers(String message) {
        appController.update(message);
        LOGGER.info(message);
    }
}
