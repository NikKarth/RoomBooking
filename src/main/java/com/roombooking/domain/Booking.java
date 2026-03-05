package com.roombooking.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Booking() {
    }

    public Booking(Room room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}