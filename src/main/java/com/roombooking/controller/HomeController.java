package com.roombooking.controller;

import com.roombooking.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BookingService service;

    public HomeController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("rooms", service.listRooms());
        return "rooms";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("rooms", service.listRooms());
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}