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

    @Bean
    CommandLineRunner initRooms(RoomRepository repository) {
        return args -> {
            if (repository.count() == 0) { // seed only if DB empty
                Room r1 = new Room();
                r1.setName("Room A");
                r1.setBuilding("Building 1");
                r1.setRoomNumber("101");
                r1.setCapacity(200);
                r1.setWhiteboard(true);
                repository.save(r1);

                Room r2 = new Room();
                r2.setName("Room B");
                r2.setBuilding("Building 1");
                r2.setRoomNumber("102");
                r2.setCapacity(140);
                r2.setWhiteboard(false);
                repository.save(r2);

                Room r3 = new Room();
                r3.setName("Room C");
                r3.setBuilding("Building 2");
                r3.setRoomNumber("201");
                r3.setCapacity(35);
                r3.setWhiteboard(true);
                repository.save(r3);

                Room r4 = new Room();
                r4.setName("Room D");
                r4.setBuilding("Building 2");
                r4.setRoomNumber("202");
                r4.setCapacity(150);
                r4.setWhiteboard(false);
                repository.save(r4);

                Room r5 = new Room();
                r5.setName("Room E");
                r5.setBuilding("Building 3");
                r5.setRoomNumber("301");
                r5.setCapacity(100);
                r5.setWhiteboard(true);
                repository.save(r5);
            }
        };
    }
}