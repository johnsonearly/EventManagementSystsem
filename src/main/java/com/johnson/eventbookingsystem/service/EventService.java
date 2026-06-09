package com.johnson.eventbookingsystem.service;

import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EventService {
    ResponseEntity<ResponseDTO<Event>> create(Event event);
    ResponseEntity<ResponseDTO<Event>> findById(UUID id);
    ResponseEntity<ResponseDTO<List<Event>>> getAllEvents();

}
