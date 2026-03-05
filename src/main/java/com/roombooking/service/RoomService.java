package com.roombooking.service;

import com.roombooking.domain.Booking;
import com.roombooking.domain.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public RoomService() {
        // Sample rooms
        rooms.add(new Room(1L, "Conference Room A", "Building 1", "101", 10, true));
        rooms.add(new Room(2L, "Conference Room B", "Building 1", "102", 8, false));
        rooms.add(new Room(3L, "Meeting Room C", "Building 2", "201", 12, true));
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public Room getRoomById(Long id) {
        return rooms.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Find rooms available at the given date and time.
     * Ignores capacity.
     */
    public List<Room> findAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return rooms.stream()
                .filter(room -> bookings.stream()
                        .filter(b -> b.getRoom().getId().equals(room.getId()))
                        .noneMatch(b -> b.getDate().equals(date) &&
                                !b.getEndTime().isBefore(startTime) &&
                                !b.getStartTime().isAfter(endTime)))
                .collect(Collectors.toList());
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}