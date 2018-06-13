package com.hilst.ts.bookingservice.model;




import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Pgi can't be null")
    @Size(min = 5, max = 10, message = "Pgi must have at least 5 and at most 10 chars")
    @Pattern(regexp = "^[a-zA-Z][\\d|a-zA-Z]{4,9}",message = "Pgi must be alphanumeric, starting with a letter")
    private String pgi;

    @NotNull(message = "Place can't be null")
    private String place;
    @NotNull(message = "Date can't be null")
    private LocalDate date;

    public Ticket() {}

    public Ticket(Long id, String pgi, String place, LocalDate date) {
        this.id = id;
        this.pgi = pgi;
        this.place = place;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
