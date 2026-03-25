package com.roombooking.controller;

import com.roombooking.domain.UserAccount;
import com.roombooking.repository.UserRepository;
import com.roombooking.service.BookingService;
import com.roombooking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final UserRepository userRepository;

    // loads all the rooms and shows it
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }
    //loads bookings for the logged-in user on the dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserAccount user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));

        model.addAttribute("bookings", bookingService.getBookingsByUser(user));
        return "dashboard";
    }
}