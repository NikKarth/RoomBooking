package com.roombooking.service;

import com.roombooking.domain.Room;
import com.roombooking.repository.BookingRepository;
import com.roombooking.repository.RoomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get all rooms with sorting
    public List<Room> getAllRooms(String sort, String dir) {
        Sort.Order order;
        switch (sort) {
            case "building":
                order = Sort.Order.by("building");
                break;
            case "roomNumber":
                order = Sort.Order.by("roomNumber");
                break;
            case "capacity":
                order = Sort.Order.by("capacity");
                break;
            case "whiteboard":
                order = Sort.Order.by("whiteboard");
                break;
            default:
                order = Sort.Order.by("name");
        }
        if ("desc".equalsIgnoreCase(dir)) {
            order = order.with(Sort.Direction.DESC);
        } else {
            order = order.with(Sort.Direction.ASC);
        }
        return roomRepository.findAll(Sort.by(order));
    }

    // Get room by id
    public Room getRoomById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    
    public List<Room> findAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return findAvailableRooms(date, startTime, endTime, null, null);
    }

    public List<Room> findAvailableRooms(LocalDate date, LocalTime startTime, LocalTime endTime,
                                         Integer minCapacity, Boolean whiteboardRequired) {

        List<Room> candidates;
        if (minCapacity != null) {
            candidates = roomRepository.findAvailableRooms(date, startTime, endTime, minCapacity);
        } else {
            // if no capacity filter, get all rooms and filter by availability
            candidates = roomRepository.findAll().stream()
                    .filter(room -> bookingRepository.findAll().stream()
                            .filter(b -> b.getRoom().getId().equals(room.getId()))
                            .noneMatch(b -> b.getDate().equals(date) &&
                                    b.getStartTime().isBefore(endTime) &&
                                    b.getEndTime().isAfter(startTime)
                            )
                    )
                    .collect(Collectors.toList());
        }

        if (whiteboardRequired != null && whiteboardRequired) {
            candidates = candidates.stream()
                    .filter(Room::isWhiteboard)
                    .collect(Collectors.toList());
        }

        return candidates;
    }
    //save a room to the db
    @SuppressWarnings("null")
    public Room saveRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        return roomRepository.save(room);
    }
}