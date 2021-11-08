package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.EventRepository;
import adeo.leroymerlin.cdp.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class DeleteEventTest  extends EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void delete(){
        try {
            eventService.delete(1L);
        } catch (Exception e){
            fail();
        }
    }
}
