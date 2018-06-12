package com.hilst.ts.bookingservice.controllers;

import com.hilst.ts.bookingservice.model.Ticket;
import com.hilst.ts.bookingservice.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/3ts")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Ticket getTicket() {
        return new Ticket(UUID.fromString("a7652132-43ac-4309-9ed9-80be4644f001"),"A12a5g68e", "Brazil", LocalDate.of(2001, 06, 25));
    }

}
