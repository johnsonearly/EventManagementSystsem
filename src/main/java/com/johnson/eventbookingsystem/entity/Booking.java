package com.johnson.eventbookingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn
    private Event eventId;
    private String attendeeName;
    @Email
    private String attendeeEmail;
    private LocalDateTime bookedAt = LocalDateTime.now();
}
