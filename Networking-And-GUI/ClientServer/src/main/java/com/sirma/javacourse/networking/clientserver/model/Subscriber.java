package com.sirma.javacourse.networking.clientserver.model;

/**
 * A Subscriber can "observe" an {@code Observable} object, which sends updates to it through the update() method.
 */
public interface Subscriber {

	void update(String message);
}
