package com.hilst.ts.bookingservice.repositories;

import com.hilst.ts.bookingservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends MongoRepository<Ticket,UUID> {
    public List<Ticket> findByPgi(String pgi);
    public List<Ticket> findByDate(LocalDate date);
    public List<Ticket> findByPlace(String place);

}
