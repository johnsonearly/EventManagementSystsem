package com.johnson.eventbookingsystem.service;

import com.johnson.eventbookingsystem.dto.BookingDTO;
import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface BookingService  {
    ResponseEntity<ResponseDTO<Booking>> create(UUID id, BookingDTO booking);
    ResponseEntity<ResponseDTO<Booking>> deleteBooking(UUID id);
    ResponseEntity<ResponseDTO<List<Booking>>> getAllBookings(UUID id);
}
