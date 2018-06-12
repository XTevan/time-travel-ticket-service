package com.hilst.ts.bookingservice.model;


import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

public class Ticket {
    @Id
    private UUID id;
    private String pgi;
    private String place;
    private LocalDate date;

    public Ticket(UUID id, String pgi, String place, LocalDate date) {
        this.id = id;
        this.pgi = pgi;
        this.place = place;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPgi() {
        return pgi;
    }

    public void setPgi(String pgi) {
        this.pgi = pgi;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "pgi='" + pgi + '\'' +
                ", place='" + place + '\'' +
                ", date=" + date +
                '}';
    }
}
