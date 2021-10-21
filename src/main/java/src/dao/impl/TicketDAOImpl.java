package src.dao.impl;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import src.dao.TicketDAO;
import src.model.Ticket;
import src.model.impl.TicketImpl;
import src.storage.Storage;

public class TicketDAOImpl implements TicketDAO {

    private Storage storage;
    private Random random;

    public void setStorage(final Storage storage) {
        this.storage = storage;
    }

    public void setRandom(final Random random) {
        this.random = random;
    }

    @Override
    public Ticket createTicket(final long userId,
                               final long eventId,
                               final int place,
                               final Ticket.Category category) {
        final Ticket ticket = buildTicket(userId, eventId, place, category);
        return storage.getTickets().put(String.valueOf(ticket.getId()), ticket);
    }

    @Override
    public Collection<Ticket> getAllTickets() {
        return storage.getTickets().values();
    }

    @Override
    public boolean deleteTicket(final long ticketId) {
        return Objects.nonNull(storage.getTickets().remove(String.valueOf(ticketId)));
    }

    private Ticket buildTicket(final long userId,
                               final long eventId,
                               final int place,
                               final Ticket.Category category) {
        return TicketImpl.builder()
                .id(random.nextLong())
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
    }
}
