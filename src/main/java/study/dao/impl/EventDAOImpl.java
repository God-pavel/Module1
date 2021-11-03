package study.dao.impl;

import study.dao.EventDAO;
import study.model.Event;
import study.storage.Storage;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class EventDAOImpl implements EventDAO {

    private Storage storage;
    private Random random;

    public void setStorage(final Storage storage) {
        this.storage = storage;
    }

    public void setRandom(final Random random) {
        this.random = random;
    }


    @Override
    public Collection<Event> getAll() {
        return storage.getEvents().values();
    }

    @Override
    public Event getEventById(final long eventId) {
        return storage.getEvents().get(String.valueOf(eventId));
    }

    @Override
    public Event addEvent(final Event event) {
        long eventId = random.nextLong();

        while (getEventById(eventId) != null) {
            eventId = random.nextLong();
        }

        event.setId(eventId);

        return storage.getEvents().put(String.valueOf(event.getId()), event);
    }

    @Override
    public Event updateEvent(final Event event) {
        return storage.getEvents().computeIfPresent(String.valueOf(event.getId()), (k, v) -> event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        return Objects.nonNull(storage.getEvents().remove(String.valueOf(eventId)));
    }
}
