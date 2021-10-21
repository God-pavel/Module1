package src.dao;

import java.util.Collection;

import src.model.Ticket;

public interface TicketDAO {
    Ticket createTicket(long userId, long eventId, int place, Ticket.Category category);
    
    Collection<Ticket> getAllTickets();
    
    boolean deleteTicket(long ticketId);
}
