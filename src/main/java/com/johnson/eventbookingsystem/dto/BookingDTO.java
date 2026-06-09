package com.johnson.eventbookingsystem.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class BookingDTO {
    private String attendeeName;
    @Email
    private String attendeeEmail;
}
