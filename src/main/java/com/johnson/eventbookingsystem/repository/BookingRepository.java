package com.johnson.eventbookingsystem.repository;

import com.johnson.eventbookingsystem.entity.Booking;
import com.johnson.eventbookingsystem.entity.Event;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    boolean findByAttendeeEmail(@Email String attendeeEmail);


    List<Booking> findAllByEventId(Event event);
}
