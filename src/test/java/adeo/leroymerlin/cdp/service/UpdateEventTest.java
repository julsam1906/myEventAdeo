package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.Event;
import adeo.leroymerlin.cdp.EventRepository;
import adeo.leroymerlin.cdp.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class UpdateEventTest extends EventServiceTest{

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    private Event event;

    @Before
    public void setUp(){
        event = event1();
    }

    @Test
    public void updateEvent(){
        try {
            eventService.update(event, 1L);
        } catch (Exception e){
            fail("Une erreur est survenue");
        }
    }
}
