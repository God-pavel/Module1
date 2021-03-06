package study.dao;

import java.util.Collection;

import study.model.Event;

public interface EventDAO {

    Collection<Event> getAll();
    Event getEventById(long eventId);
    Event addEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
