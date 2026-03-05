package com.roombooking.controller;

import com.roombooking.domain.Room;
import com.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Display all rooms
    @GetMapping("/rooms")
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms"; // renders rooms.html
    }

    // Show room search page
    @GetMapping("/rooms/search")
    public String searchRoomPage() {
        return "room-search"; // renders room-search.html
    }

    // Handle search request (date + time only)
    @GetMapping("/rooms/search/results")
    public String searchResults(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            Model model) {

        List<Room> availableRooms = roomService.findAvailableRooms(date, startTime, endTime);
        model.addAttribute("rooms", availableRooms);
        model.addAttribute("date", date);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);

        if (availableRooms.isEmpty()) {
            model.addAttribute("message", "No rooms available for the selected date and time.");
        }

        return "room-search"; // reuse same page
    }

    // Room details page
    @GetMapping("/rooms/{roomId}")
    public String roomDetails(@PathVariable Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "room-details";
    }
}