package src.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import src.dao.TicketDAO;
import src.model.Event;
import src.model.Ticket;
import src.model.User;
import src.service.TicketService;

public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    
    @Override
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category) {
        if(isPlaceBooked(place)){
            throw new IllegalStateException();
        }
        
        return ticketDAO.createTicket(userId, eventId, place, category);
    }

    //todo
    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .sorted((t1, t2) -> t1.getEventId())
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
    //todo

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
    @Override
    public boolean cancelTicket(final long ticketId) {
        return ticketDAO.deleteTicket(ticketId);
    }

    private boolean isPlaceBooked(int place) {
        return ticketDAO.getAllTickets().stream()
                .anyMatch(ticket -> ticket.getPlace() == place);
    }
}
