package com.roombooking.config;

import com.roombooking.domain.Room;
import com.roombooking.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RoomRepository roomRepository;

    public DataInitializer(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void init() {
        if (roomRepository.count() == 0) {
            roomRepository.save(new Room(null, "Conference Room A", "Building 1", "101", 100, true));
            roomRepository.save(new Room(null, "Conference Room B", "Building 1", "102", 80, false));
            roomRepository.save(new Room(null, "Meeting Room C", "Building 2", "201", 120, true));
        }
    }
}
