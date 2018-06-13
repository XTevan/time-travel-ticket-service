package com.hilst.ts.bookingservice.service;

import com.hilst.ts.bookingservice.exception.BookingException;
import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookingService {
    private final BookingRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class.getName());

    @Autowired
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findByPgi(String pgi) {
        return repository.findByPgi(pgi);
    }

    public List<Ticket> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Ticket> findByPlace(String place) {
        return repository.findByPlace(place);
    }

    public Optional<Ticket> findById(Long id) {
        return repository.findById(id);
    }

    public Ticket save(Ticket ticket) {


        if (!repository.findByPgiAndPlaceAndDate(ticket.getPgi(), ticket.getPlace(), ticket.getDate()).isPresent()) {
            try {
                ticket = repository.save(ticket);
            } catch (Exception e) {
                LOGGER.error("Error saving ticket", e);
                throw new BookingException(e.getMessage());
            }
        } else {
            throw new BookingException("You have already been there!");
        }
        return ticket;
    }

    public List<Ticket> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

}
