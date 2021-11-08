package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UpdateEventTest extends EventControllerTest{

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void updateEvent() throws Exception {
        mvc.perform(put("/api/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(event1())))
                .andExpect(status().isOk());
    }
}
