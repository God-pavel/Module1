package study.facade.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.model.Event;
import study.model.Ticket;
import study.model.User;
import study.model.impl.EventImpl;
import study.model.impl.UserImpl;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/ApplicationContext.xml")
class BookingFacadeImplTest {

    @Autowired
    private BookingFacadeImpl bookingFacade;

    @Test
    void realLifeScenario() {
        final User newUser = bookingFacade.createUser(new UserImpl("David", "email"));

        assertEquals("David", bookingFacade.getUserByEmail("email").getName());

        final Event newEvent = bookingFacade.createEvent(new EventImpl("NewEvent", Date.from(Instant.now())));

        assertEquals(1, bookingFacade.getEventsByTitle("NewEvent", 1, 1).size());

        final Ticket newTicket = bookingFacade.bookTicket(newUser.getId(), newEvent.getId(), 11, Ticket.Category.BAR);

        assertEquals(bookingFacade.getBookedTickets(newEvent, 1, 1), bookingFacade.getBookedTickets(newUser, 1, 1));

        bookingFacade.cancelTicket(newTicket.getId());

        assertEquals(Collections.EMPTY_LIST, bookingFacade.getBookedTickets(newUser, 1, 1));
        assertEquals(Collections.EMPTY_LIST, bookingFacade.getBookedTickets(newEvent, 1, 1));
    }
}