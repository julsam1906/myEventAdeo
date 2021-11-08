package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.Event;
import adeo.leroymerlin.cdp.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FilteredEventTest extends EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Before
    public void setUp(){
        List<Event> events = new ArrayList<>();
        events.add(event1());
        when(eventService.getFilteredEvents(Mockito.anyString())).thenReturn(events);
    }

    @Test
    public void getFilteredEvent() throws Exception {
        mockMvc.perform(get("/api/events/search/Jul")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @Test
    public void getFilteredEventReturnEmpty() throws Exception {
        when(eventService.getFilteredEvents(Mockito.anyString())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/events/search/Jul")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(0))).andDo(print());
    }
}
