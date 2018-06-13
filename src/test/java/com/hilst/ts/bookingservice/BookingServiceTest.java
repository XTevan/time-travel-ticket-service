package com.hilst.ts.bookingservice;

import com.hilst.ts.bookingservice.exception.BookingException;
import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.repository.BookingRepository;
import com.hilst.ts.bookingservice.service.BookingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
    private static final String PGI = "Jones1234";
    private static final Long ID_TICKET = 1L;
    private static final LocalDate DATE_TICKET = LocalDate.of(1892, 1, 1);
    private static final String PLACE = "Piccadilly Circus, London";
    private static final Ticket TICKET = new Ticket(ID_TICKET, PGI, PLACE, DATE_TICKET);
    private static final List<Ticket> TICKETS = Collections.singletonList(TICKET);

    @MockBean
    private BookingRepository repository;

    private BookingService service;

    @Test
    public void findAllShouldCallRepositoryOnce() {
        when(repository.findAll()).thenReturn(TICKETS);
        service = new BookingService(repository);
        List<Ticket> result = service.findAll();

        Assert.assertEquals(result.size(), TICKETS.size());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void findByIdShouldCallRepositoryOnceAndReturnModel() {
        when(repository.findById(ID_TICKET)).thenReturn(Optional.of(TICKET));
        service = new BookingService(repository);
        Optional<Ticket> result = service.findById(ID_TICKET);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().toString(), TICKET.toString());
        verify(repository, times(1)).findById(ID_TICKET);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void findByIdShouldCallRepositoryOnceAndReturnEmptyModel() {
        when(repository.findById(ID_TICKET)).thenReturn(Optional.empty());
        service = new BookingService(repository);
        Optional<Ticket> result = service.findById(ID_TICKET);

        Assert.assertFalse(result.isPresent());
        verify(repository, times(1)).findById(ID_TICKET);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void saveShouldCallRepositoryOnceAndReturnModel() {
        when(repository.save(TICKET)).thenReturn(TICKET);
        when(repository.findByPgiAndPlaceAndDate(TICKET.getPgi(),TICKET.getPlace(),TICKET.getDate())).thenReturn(Optional.empty());

        service = new BookingService(repository);
        Ticket result = service.save(TICKET);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.toString(), TICKET.toString());
        verify(repository, times(1)).save(TICKET);
        verify(repository,times(1)).findByPgiAndPlaceAndDate(TICKET.getPgi(),TICKET.getPlace(),TICKET.getDate());
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = BookingException.class)
    public void saveShouldCallRepositoryOnceAndThrowError()  {
        when(repository.save(TICKET)).thenReturn(TICKET);
        when(repository.findByPgiAndPlaceAndDate(TICKET.getPgi(),TICKET.getPlace(),TICKET.getDate())).thenReturn(Optional.of(TICKET));

        service = new BookingService(repository);
        Ticket result = service.save(TICKET);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.toString(), TICKET.toString());
        verify(repository, times(0)).save(TICKET);
        verify(repository,times(1)).findByPgiAndPlaceAndDate(TICKET.getPgi(),TICKET.getPlace(),TICKET.getDate());
        verifyNoMoreInteractions(repository);
    }
}
