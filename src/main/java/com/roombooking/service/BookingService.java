package com.roombooking.service;

import com.roombooking.domain.Booking;
import com.roombooking.domain.UserAccount;
import com.roombooking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    //saves a booking
    public Booking saveBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        return bookingRepository.save(booking);
    }

    //gets all the bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(UserAccount user) {
        return bookingRepository.findByUser(user);
    }
    
    //deletes a booking by id
    public void deleteBooking(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        bookingRepository.deleteById(id);
    }
}