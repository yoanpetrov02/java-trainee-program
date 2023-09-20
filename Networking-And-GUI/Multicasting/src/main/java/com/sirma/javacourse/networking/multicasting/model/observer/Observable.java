package com.sirma.javacourse.networking.multicasting.model.observer;

/**
 * An Observable object listeners subscribed to it. Whenever a change in the state of the observed object happens,
 * it notifies its subscribers by calling their update() method.
 */
public interface Observable {

    void setSubscriber(Subscriber s);

    /**
     * Notifies all subscribers with the given message by calling their update() method.
     *
     * @param message the message that will be sent to all subscribers.
     */
    void notifySubscribers(String message);
}
