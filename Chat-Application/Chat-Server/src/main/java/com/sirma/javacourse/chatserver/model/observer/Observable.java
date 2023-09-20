package com.sirma.javacourse.chatserver.model.observer;

/**
 * An Observable object can be "observed" by a {@code Subscriber}. Whenever a change in the state of the observable
 * object happens, it notifies its subscribers using one of the notify methods.
 */
public interface Observable {

    void setSubscriber(Subscriber subscriber);

    void notifySubscriber(String message);
}
