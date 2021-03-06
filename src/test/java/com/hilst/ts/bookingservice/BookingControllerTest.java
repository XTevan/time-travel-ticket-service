package com.hilst.ts.bookingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hilst.ts.bookingservice.controller.BookingController;
import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookingController.class, secure = false)
public class BookingControllerTest {
    private static final String PGI = "Jones1234";
    private static final Long ID_TICKET = 1L;
    private static final LocalDate DATE_TICKET = LocalDate.of(1892, 1, 1);
    private static final String PLACE = "Piccadilly Circus, London";
    private static final Ticket TICKET = new Ticket(ID_TICKET, PGI, PLACE, DATE_TICKET);
    private static final List<Ticket> TICKETS = Collections.singletonList(TICKET);

    private ObjectMapper serializer;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService service;

    public BookingControllerTest() {
        serializer = new ObjectMapper();
        serializer.registerModule(new JavaTimeModule());
        serializer.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


    @Test
    public void findAllShouldCallServiceOnceAndReturn() throws Exception {
        when(service.findAll()).thenReturn(TICKETS);

        mockMvc.perform(get("/api/3ts")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(TICKETS.size())))
                .andExpect(jsonPath("$[0].place", is(PLACE)));
        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void findOneShouldCallServiceOnceAndReturnModel() throws Exception {
        when(service.findById(ID_TICKET)).thenReturn(Optional.of(TICKET));

        mockMvc.perform(get("/api/3ts/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.place", is(PLACE)));
        verify(service, times(1)).findById(ID_TICKET);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void findOneShouldCallServiceOnceAndReturnNotFound() throws Exception {
        when(service.findById(ID_TICKET)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/3ts/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(service, times(1)).findById(ID_TICKET);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookShouldCallServiceOnce() throws Exception {
        when(service.save(any(Ticket.class))).thenReturn(TICKET);
        String jsonBody = serializer.writeValueAsString(TICKET);


        mockMvc.perform(post("/api/3ts")
                .content(jsonBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(service, times(1)).save(any(Ticket.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookShouldCallServiceOnceAndReturnError() throws Exception {
        when(service.save(any(Ticket.class))).thenReturn(TICKET);
        String jsonBody = serializer.writeValueAsString(new Ticket());


        mockMvc.perform(post("/api/3ts")
                .content(jsonBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(0)).save(any(Ticket.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookMissingPgiShouldCallServiceOnceAndReturnError() throws Exception {
        when(service.save(any(Ticket.class))).thenReturn(TICKET);
        String jsonBody = serializer.writeValueAsString(new Ticket(null, "", PLACE, DATE_TICKET));


        mockMvc.perform(post("/api/3ts")
                .content(jsonBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(0)).save(any(Ticket.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookMissingPlaceShouldCallServiceOnceAndReturnError() throws Exception {
        when(service.save(any(Ticket.class))).thenReturn(TICKET);
        String jsonBody = serializer.writeValueAsString(new Ticket(null, PGI, null, DATE_TICKET));


        mockMvc.perform(post("/api/3ts")
                .content(jsonBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(0)).save(any(Ticket.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookMissingDateShouldCallServiceOnceAndReturnError() throws Exception {
        when(service.save(any(Ticket.class))).thenReturn(TICKET);
        String jsonBody = serializer.writeValueAsString(new Ticket(null, PGI, PLACE, null));


        mockMvc.perform(post("/api/3ts")
                .content(jsonBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(service, times(0)).save(any(Ticket.class));
        verifyNoMoreInteractions(service);
    }


}
