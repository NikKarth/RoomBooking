package com.roombooking.service;

import com.roombooking.domain.Room;
import com.roombooking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final RoomRepository repository;

    public BookingService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> listRooms() {
        return repository.findAll();
    }

    public Optional<Room> bookRoom(Long roomId) {
        Optional<Room> roomOpt = repository.findById(roomId);
        roomOpt.ifPresent(room -> {
            room.setBooked(true);
            repository.save(room);
        });
        return roomOpt;
    }
}