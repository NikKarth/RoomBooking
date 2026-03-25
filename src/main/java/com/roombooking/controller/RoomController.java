package com.roombooking.controller;

import com.roombooking.domain.Room;
import com.roombooking.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Display rooms + sorting
    @GetMapping("/rooms")
    public String listRooms(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            Model model) {

        List<Room> rooms = roomService.getAllRooms(sort, dir);

        model.addAttribute("rooms", rooms);
        return "rooms"; // renders rooms.html
    }

    // Show room search page
    @GetMapping("/rooms/search")
    public String searchRoomPage() {
        return "room-search";
    }

    // search request
    @GetMapping("/rooms/search/results")
    public String searchResults(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(value = "capacity", required = false) Integer capacity,
            @RequestParam(value = "whiteboard", required = false) Boolean whiteboard,
            Model model) {

        List<Room> availableRooms = roomService.findAvailableRooms(date, startTime, endTime, capacity, whiteboard);

        model.addAttribute("rooms", availableRooms);
        model.addAttribute("date", date);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("capacity", capacity);
        model.addAttribute("whiteboard", whiteboard);

        if (availableRooms.isEmpty()) {
            model.addAttribute("message", "No rooms available for the selected date and time.");
        }

        return "room-search";
    }

    @GetMapping("/rooms/{roomId}")
    public String roomDetails(@PathVariable Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "room-details";
    }
}