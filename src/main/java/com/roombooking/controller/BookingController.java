package com.roombooking.controller;

import com.roombooking.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public String bookRoom(@RequestParam Long roomId) {
        service.bookRoom(roomId);
        return "redirect:/dashboard";
    }
}