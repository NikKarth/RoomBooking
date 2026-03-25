package com.roombooking.config;

import com.roombooking.domain.Room;
import com.roombooking.domain.UserAccount;
import com.roombooking.repository.RoomRepository;
import com.roombooking.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Initialize rooms and users for first time use
@Component
public class DataInitializer {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoomRepository roomRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        try {
            if (roomRepository.count() == 0) {
                roomRepository.save(new Room(null, "Conference Room A", "Building 1", "101", 100, true));
                roomRepository.save(new Room(null, "Conference Room B", "Building 1", "102", 80, false));
                roomRepository.save(new Room(null, "Meeting Room C", "Building 2", "201", 120, true));
            }

            if (userRepository.count() == 0) {
                userRepository.save(new UserAccount(null, "alice", passwordEncoder.encode("pass123"), "ROLE_USER"));
                userRepository.save(new UserAccount(null, "bob", passwordEncoder.encode("pass123"), "ROLE_USER"));
                userRepository.save(new UserAccount(null, "carol", passwordEncoder.encode("pass123"), "ROLE_USER"));
                userRepository.save(new UserAccount(null, "dave", passwordEncoder.encode("pass123"), "ROLE_USER"));
                userRepository.save(new UserAccount(null, "eve", passwordEncoder.encode("pass123"), "ROLE_USER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
