package com.hilst.ts.bookingservice.repository;

import com.hilst.ts.bookingservice.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Ticket, Long> {
    public Optional<Ticket> findById(Long id);

    public List<Ticket> findByPgi(String pgi);

    public List<Ticket> findByDate(LocalDate date);

    public List<Ticket> findByPlace(String place);

    public Optional<Ticket> findByPgiAndPlaceAndDate(String pgi, String place, LocalDate date);

}
