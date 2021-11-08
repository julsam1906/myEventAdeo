package adeo.leroymerlin.cdp.service;


import adeo.leroymerlin.cdp.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilteredEventsTest extends EventServiceTest{

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Before
    public void setUp(){
        List<Event> events = new ArrayList<>();
        events.add(event1());
        events.add(event2());
        when(eventRepository.findAllBy()).thenReturn(events);
    }

    @Test
    public void getFilteredEvents(){
        List<Event> events = eventService.getFilteredEvents("Jul");
        assertNotNull(events);
        assertEquals(1, events.size());
        Event eventFiletered = events.get(0);
        assertEquals("event1 [1]", eventFiletered.getTitle());
        assertEquals("test", eventFiletered.getImgUrl());
        assertEquals(1, eventFiletered.getBands().size());
        Band band = eventFiletered.getBands().stream().findFirst().orElse(null);
        assertNotNull(band);
        assertEquals("Band1 [1]", band.getName());
        Member member = band.getMembers().stream().findFirst().orElse(null);
        assertNotNull(member);
        assertEquals("Julien", member.getName());
    }

    @Test
    public void filteredEventNotFound(){
        List<Event> events = eventService.getFilteredEvents("XXX");
        assertEquals(0, events.size());
    }
}
