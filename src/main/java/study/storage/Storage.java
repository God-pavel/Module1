package study.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import study.model.Event;
import study.model.Ticket;
import study.model.User;
import study.model.impl.EventImpl;
import study.model.impl.UserImpl;

import javax.annotation.PostConstruct;
import java.util.Map;

@PropertySource("classpath:application.properties")
public class Storage {

    @Value("${storage.data}")
    private String data;

    private Map<String, Event> events;
    private Map<String, Ticket> tickets;
    private Map<String, User> users;

    public Map<String, Event> getEvents() {
        return events;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    @PostConstruct
    private void initializeStorage() {
        events = Map.of("1", EventImpl.builder().id(1).title("1").date(null).build());
        tickets = Map.of();
        users = Map.of("1", UserImpl.builder().id(1).email("1").name("1").build());
    }
}
