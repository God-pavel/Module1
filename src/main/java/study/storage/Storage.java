package study.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import study.model.Event;
import study.model.Ticket;
import study.model.User;
import study.model.impl.EventImpl;
import study.model.impl.UserImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Storage {

    private String usersFilePath;

    private String eventsFilePath;

    private ObjectMapper objectMapper;

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

    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void initializeStorage() throws IOException {

        final List<UserImpl> userList = objectMapper.readValue(getJsonString(usersFilePath), new TypeReference<List<UserImpl>>() {
        });

        final List<EventImpl> eventList = objectMapper.readValue(getJsonString(eventsFilePath), new TypeReference<List<EventImpl>>() {
        });

        users = userList.stream().collect(Collectors.toMap(user -> String.valueOf(user.getId()), Function.identity()));
        events = eventList.stream().collect(Collectors.toMap(event -> String.valueOf(event.getId()), Function.identity()));

        users.entrySet().forEach(System.out::println);
        events.entrySet().forEach(System.out::println);
    }

    private String getJsonString(final String filePath) throws IOException {
        System.out.println(filePath);
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void setUsersFilePath(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    public void setEventsFilePath(String eventsFilePath) {
        this.eventsFilePath = eventsFilePath;
    }
}
