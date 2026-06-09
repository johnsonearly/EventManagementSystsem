package com.johnson.eventbookingsystem.entity;

import com.johnson.eventbookingsystem.enums.Status;
import jakarta.persistence.*;
import lombok.Data;



import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private String venue;
    private int totalSeats;
    private int bookedSeats;
    private Status status;

}
