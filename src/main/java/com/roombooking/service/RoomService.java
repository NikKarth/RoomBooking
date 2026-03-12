package com.roombooking.service;

import com.roombooking.domain.Booking;
import com.roombooking.domain.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public RoomService() {
        // Sample rooms
        rooms.add(new Room(1L, "Conference Room A", "Building 1", "101", 100, true));
        rooms.add(new Room(2L, "Conference Room B", "Building 1", "102", 80, false));
        rooms.add(new Room(3L, "Meeting Room C", "Building 2", "201", 120, true));
    }

    // Original method (if you need)
    public List<Room> getAllRooms() {
        return rooms;
    }

    // NEW METHOD: sort by any column, ascending or descending
    public List<Room> getAllRooms(String sort, String dir) {

        Comparator<Room> comparator;

        switch (sort) {
            case "building":
                comparator = Comparator.comparing(Room::getBuilding);
                break;
            case "roomNumber":
                comparator = Comparator.comparing(Room::getRoomNumber);
                break;
            case "capacity":
                comparator = Comparator.comparingInt(Room::getCapacity);
                break;
            case "whiteboard":
                comparator = Comparator.comparing(Room::isWhiteboard);
                break;
            default:
                comparator = Comparator.comparing(Room::getName);
        }

        if ("desc".equalsIgnoreCase(dir)) {
            comparator = comparator.reversed();
        }

        return rooms.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Room getRoomById(Long id) {
        return rooms.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

        public List<Room> findAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return findAvailableRooms(date, startTime, endTime, null, null);
        }

        public List<Room> findAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime,
                         Integer minCapacity, Boolean whiteboardRequired) {

        return rooms.stream()
            .filter(room -> bookings.stream()
                .filter(b -> b.getRoom().getId().equals(room.getId()))
                .noneMatch(b -> b.getDate().equals(date) &&
                    b.getStartTime().isBefore(endTime) &&
                    b.getEndTime().isAfter(startTime)
                )
            )
            .filter(room -> minCapacity == null || room.getCapacity() >= minCapacity)
            .filter(room -> whiteboardRequired == null || !whiteboardRequired || room.isWhiteboard())
            .collect(Collectors.toList());
        }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}