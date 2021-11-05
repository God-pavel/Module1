package study.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.dao.TicketDAO;
import study.model.Event;
import study.model.Ticket;
import study.model.User;
import study.service.EventService;
import study.service.TicketService;
import study.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private TicketDAO ticketDAO;
    private EventService eventService;
    private UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category) {
        if (isPlaceBooked(place)) {
            LOGGER.warn("Place {} already booked", place);
            throw new IllegalStateException();
        }
        LOGGER.info("Booking ticket for {}", userId);
        return ticketDAO.createTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .sorted(Comparator.comparing(ticket -> eventService.getEventById(ticket.getEventId()).getDate()))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .sorted(Comparator.comparing(ticket -> userService.getUserById(ticket.getUserId()).getEmail()))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        LOGGER.info("Canceling ticket with id {}", ticketId);
        return ticketDAO.deleteTicket(ticketId);
    }

    public void setTicketDAO(final TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    private boolean isPlaceBooked(int place) {
        return ticketDAO.getAllTickets().stream()
                .anyMatch(ticket -> ticket.getPlace() == place);
    }
}
