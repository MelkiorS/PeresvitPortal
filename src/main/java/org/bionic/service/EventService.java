package org.bionic.service;

import org.bionic.entity.Event;

import java.util.List;

/**
 * Event service by MMaximov on 03.11.16.
 */

public interface EventService {

    List<Event> findAll();
    Event create(Event e);
    Event delete(Event e);
    Event update(Event e);
    Event findById(long id);

}
