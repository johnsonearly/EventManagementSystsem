package com.johnson.eventbookingsystem.controller;

import com.johnson.eventbookingsystem.dto.BookingDTO;
import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.johnson.eventbookingsystem.service.BookingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/events/{id}/bookings")
    public ResponseEntity<ResponseDTO<List<Booking>>> findAllBookings(@PathVariable UUID id) {
        return bookingService.getAllBookings(id);

    }
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<ResponseDTO<Booking>> deleteBooking(@PathVariable UUID id) {
        return bookingService.deleteBooking(id);
    }
    @PostMapping("/events/{id}/bookings")
    public ResponseEntity<ResponseDTO<Booking>> saveBooking(@PathVariable UUID id, @RequestBody BookingDTO booking) {
        return bookingService.create(id,booking);
    }



}
