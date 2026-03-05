package com.roombooking.service;

import com.roombooking.domain.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public RoomService() {
        // Initialize some rooms
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
                .orElse(null); // could throw exception if preferred
    }
}