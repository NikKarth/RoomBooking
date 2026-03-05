package com.roombooking.repository;

import com.roombooking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.capacity >= :capacity " +
           "AND r.id NOT IN (" +
           "SELECT b.room.id FROM Booking b WHERE b.date = :date AND " +
           "(:startTime < b.endTime AND :endTime > b.startTime)" +
           ")")
    List<Room> findAvailableRooms(@Param("date") LocalDate date,
                                  @Param("startTime") LocalTime startTime,
                                  @Param("endTime") LocalTime endTime,
                                  @Param("capacity") int capacity);
}