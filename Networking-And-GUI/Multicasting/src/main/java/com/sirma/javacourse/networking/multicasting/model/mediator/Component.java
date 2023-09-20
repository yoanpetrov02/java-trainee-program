package com.sirma.javacourse.networking.multicasting.model.mediator;


/**
 * Represents a component of a mediator object.
 */
public interface Component {

    void setMediator(Mediator m);

    String getName();
}
