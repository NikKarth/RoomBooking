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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    // empty for db
    public Booking() {
    }

    // store by id and time
    public Booking(Room room, UserAccount user, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.room = room;
        this.user = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }
}