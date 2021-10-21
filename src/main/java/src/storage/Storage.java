package src.storage;

import javax.annotation.PostConstruct;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import src.model.Event;
import src.model.Ticket;
import src.model.User;

public class Storage {
    
    @Value("${storage.dataFilePath}")
    private String dataFilePath;
    
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
    private void initializeStorage(){
        events = null;
        tickets = null;
        users = null;
    }
}
