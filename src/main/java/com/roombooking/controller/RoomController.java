package com.roombooking.controller;

import com.roombooking.domain.Room;
import com.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Display all rooms
    @GetMapping("/rooms")
    public String listRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms"; // renders rooms.html
    }

    // Show room search page
    @GetMapping("/rooms/search")
    public String searchRoomPage() {
        return "room-search"; // renders room-search.html
    }

    // Room details page
    @GetMapping("/rooms/{roomId}")
    public String roomDetails(@PathVariable Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "room-details"; // you can create room-details.html if needed
    }
}