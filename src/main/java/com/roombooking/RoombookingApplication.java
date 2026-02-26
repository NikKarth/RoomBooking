package com.roombooking;

import com.roombooking.domain.Room;
import com.roombooking.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoombookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoombookingApplication.class, args);
    }

    // Seed 5 rooms on startup using setters
    @Bean
    CommandLineRunner initRooms(RoomRepository repository) {
        return args -> {
            Room r1 = new Room();
            r1.setName("Room A");
            r1.setLocation("First Floor");
            r1.setSize(2);
            repository.save(r1);

            Room r2 = new Room();
            r2.setName("Room B");
            r2.setLocation("Second Floor");
            r2.setSize(4);
            repository.save(r2);

            Room r3 = new Room();
            r3.setName("Room C");
            r3.setLocation("Third Floor");
            r3.setSize(3);
            repository.save(r3);

            Room r4 = new Room();
            r4.setName("Room D");
            r4.setLocation("First Floor");
            r4.setSize(5);
            repository.save(r4);

            Room r5 = new Room();
            r5.setName("Room E");
            r5.setLocation("Second Floor");
            r5.setSize(1);
            repository.save(r5);
        };
    }
}