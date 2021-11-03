package study.dao.impl;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import study.dao.TicketDAO;
import study.model.Ticket;
import study.model.impl.TicketImpl;
import study.storage.Storage;

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
        long ticketId = random.nextLong();

        while (isTicketIdNotUnique(ticketId)) {
            ticketId = random.nextLong();
        }

        return TicketImpl.builder()
                .id(ticketId)
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
    }

    private boolean isTicketIdNotUnique(final long ticketId){
        return getAllTickets().stream()
                .anyMatch(ticket -> ticket.getId() == ticketId);
    }
}