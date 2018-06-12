package com.hilst.ts.bookingservice.services;

import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    private BookingRepository repository;

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
    public Optional<Ticket> findById(UUID id) {
        return repository.findById(id);
    }
}
