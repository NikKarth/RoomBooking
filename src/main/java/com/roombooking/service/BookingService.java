package com.roombooking.service;

import com.roombooking.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void deleteBooking(int index) {
        if (index >= 0 && index < bookings.size()) {
            bookings.remove(index);
        }
    }

    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }
}