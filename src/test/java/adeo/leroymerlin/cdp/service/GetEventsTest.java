package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.Event;
import adeo.leroymerlin.cdp.EventRepository;
import adeo.leroymerlin.cdp.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetEventsTest extends EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        List<Event> events = new ArrayList<>();
        events.add(event1());
        events.add(event2());
        when(eventRepository.findAllBy()).thenReturn(events);
    }

    @Test
    public void findAllEvents() {
        List<Event> eventsFound = eventService.getEvents();
        Assert.assertEquals(2, eventsFound.size());
    }

    @Test
    public void findAllEventsVide() {
        when(eventRepository.findAllBy()).thenReturn(Collections.emptyList());
        List<Event> eventsFound = eventService.getEvents();
        Assert.assertEquals(0, eventsFound.size());
    }
}
