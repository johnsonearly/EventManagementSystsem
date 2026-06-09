package com.johnson.eventbookingsystem.controller;

import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.johnson.eventbookingsystem.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Event Management", description = "APIs for creating and retrieving event logs")
public class EventController {
    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Get all events", description = "Retrieves a complete list of all recorded events inside a standard response wrapper.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved event list")
    })
    public ResponseEntity<ResponseDTO<List<Event>>> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping
    @Operation(summary = "Create a new event", description = "Registers a brand new event record in the database system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payload configuration", content = @Content)
    })
    public ResponseEntity<ResponseDTO<Event>> create(@RequestBody Event event){
        return eventService.create(event);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID", description = "Fetches details of a single event matching the requested unique UUID identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully located"),
            @ApiResponse(responseCode = "400", description = "Event not found with the provided UUID matching the request context", content = @Content)
    })
    public ResponseEntity<ResponseDTO<Event>> getEventById(@PathVariable UUID id){
        return eventService.findById(id);
    }
}


