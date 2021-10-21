package src.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import src.dao.EventDAO;
import src.model.Event;
import src.service.EventService;

public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

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
        return eventDAO.addEvent(event);
    }

    @Override
    public Event updateEvent(final Event event) {
        return eventDAO.addEvent(event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        return eventDAO.deleteEvent(eventId);
    }

    private boolean isSameDay(final Date date1, final Date date2) {
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
