package com.roombooking.controller;

import com.roombooking.domain.Booking;
import com.roombooking.domain.Room;
import com.roombooking.service.BookingService;
import com.roombooking.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/room/{roomId}")
    public String bookRoom(
            @PathVariable Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {

        Room room = roomService.getRoomById(roomId);

        if (room == null) {
            return "redirect:/rooms";
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setDate(date);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);

        bookingService.saveBooking(booking);

        return "redirect:/dashboard";
    }

    @PostMapping("/delete")
    public String deleteBooking(@RequestParam Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/dashboard";
    }
}