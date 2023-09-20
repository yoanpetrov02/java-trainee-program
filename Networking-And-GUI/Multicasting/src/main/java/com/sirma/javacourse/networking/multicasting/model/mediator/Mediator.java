package com.sirma.javacourse.networking.multicasting.model.mediator;

import com.sirma.javacourse.networking.multicasting.model.sender.message.ChannelMessage;

/**
 * A mediator serves as a way to reduce tight coupling between objects. Components can be
 * registered, and whenever some interaction happens between them, this communication
 * happens via the mediator's notifyMediator() method. This way, the components depend
 * only on the mediator itself.
 */
public interface Mediator {

    void notifyMediator(ChannelMessage m);

    void registerComponent(Component c);
}
