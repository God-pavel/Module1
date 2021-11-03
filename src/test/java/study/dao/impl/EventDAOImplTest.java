package study.dao.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.model.Event;
import study.storage.Storage;

import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventDAOImplTest {

    @Mock
    private static Storage storage;
    @Mock
    private static Event event;

    @InjectMocks
    private static EventDAOImpl eventDAO;

    @BeforeAll
    static void init() {
        eventDAO = new EventDAOImpl();
        eventDAO.setRandom(new Random());
    }

    @Test
    void getAll_shouldReturnEvent_whenStorageReturnEvent() {
        when(storage.getEvents()).thenReturn(Map.of("1", event));

        assertTrue(eventDAO.getAll().contains(event));
        assertEquals(1, eventDAO.getAll().size());
    }

    @Test
    void getAll_shouldReturnEmptyCollection_whenStorageReturnEmptyMap() {
        when(storage.getEvents()).thenReturn(Map.of());

        assertEquals(0, eventDAO.getAll().size());
    }

    @Test
    void getEventById_shouldReturnEvent_whenStorageHasEvent() {
        when(storage.getEvents()).thenReturn(Map.of("1", event));

        assertEquals(event, eventDAO.getEventById(1));
    }

    @Test
    void getEventById_shouldReturnNull_whenStorageNotHasEvent() {
        when(storage.getEvents()).thenReturn(Map.of("2", event));

        assertNull(eventDAO.getEventById(1));
    }
}