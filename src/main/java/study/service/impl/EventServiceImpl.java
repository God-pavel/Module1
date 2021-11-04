package study.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.dao.EventDAO;
import study.model.Event;
import study.service.EventService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    private static Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public Event getEventById(final long eventId) {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        return eventDAO.getAll().stream()
                .filter(event -> event.getTitle().contains(title))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(final Date day, final int pageSize, final int pageNum) {
        return eventDAO.getAll().stream()
                .filter(event -> isSameDay(event.getDate(), day))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(final Event event) {
        LOGGER.info("Creating event {}", event.getTitle());
        return eventDAO.addEvent(event);
    }

    @Override
    public Event updateEvent(final Event event) {
        LOGGER.info("Updating event with id {}", event.getId());
        return eventDAO.addEvent(event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        LOGGER.info("Deleting event with id {}", eventId);
        return eventDAO.deleteEvent(eventId);
    }

    public void setEventDAO(final EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    private boolean isSameDay(final Date date1, final Date date2) {
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
