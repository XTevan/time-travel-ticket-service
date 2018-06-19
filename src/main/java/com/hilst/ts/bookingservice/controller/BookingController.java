package com.hilst.ts.bookingservice.controller;

import com.hilst.ts.bookingservice.exception.TicketNotFoundException;
import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/3ts")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Ticket findOne(@PathVariable("id") Long id) {
        return service.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
    }

    @GetMapping
    public Iterable<Ticket> findAll() {
        return service.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket book(@Valid @RequestBody Ticket ticket) {
        return service.save(ticket);
    }

}
