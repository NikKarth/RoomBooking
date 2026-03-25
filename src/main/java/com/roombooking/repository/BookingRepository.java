package com.roombooking.repository;

import com.roombooking.domain.Booking;
import com.roombooking.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// booking from db
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomIdAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId, LocalDate date, LocalTime endTime, LocalTime startTime);

    List<Booking> findByUser(UserAccount user);
}