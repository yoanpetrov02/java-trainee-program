package com.sirma.javacourse.networking.multicasting.model.sender.message;

import com.sirma.javacourse.networking.multicasting.model.mediator.Component;
import com.sirma.javacourse.networking.multicasting.model.mediator.Mediator;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Dispatches messages to a {@code Mediator}, which in term decides which channel to sent them to.
 */
public class ChannelMessageDispatcher implements Component {

    private Mediator mediator;
    private final List<ChannelMessageGenerator> generators;

    public ChannelMessageDispatcher() {
        generators = new ArrayList<>();
    }

    @Override
    public void setMediator(Mediator m) {
        mediator = m;
    }

    @Override
    public String getName() {
        return "dispatcher";
    }

    public void addGenerator(ChannelMessageGenerator generator) {
        generators.add(generator);
    }

    /**
     * Gets a random {@code ChannelMessage} from a randomly picked
     * generator and dispatches it to the {@code Mediator}.
     *
     * @return the generated {@code ChannelMessage}.
     */
    public ChannelMessage sendRandomMessage() {
        ChannelMessage message = getRandomGenerator().getMessage();
        mediator.notifyMediator(message);
        return message;
    }

    /**
     * Picks a random generator from this object's generator list and returns it.
     *
     * @return the randomly picked generator.
     */
    private ChannelMessageGenerator getRandomGenerator() {
        return generators.get(
                RandomUtils.nextInt(0, generators.size()));
    }
}
