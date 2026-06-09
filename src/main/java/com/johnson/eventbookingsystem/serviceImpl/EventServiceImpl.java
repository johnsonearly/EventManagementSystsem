package com.johnson.eventbookingsystem.serviceImpl;

import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Event;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.johnson.eventbookingsystem.repository.EventRepository;
import com.johnson.eventbookingsystem.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public ResponseEntity<ResponseDTO<Event>>create(@Valid Event event) {
        log.info("EventServiceImpl:EventServiceImpl:create");
        ResponseDTO<Event> responseDTO = new ResponseDTO<>();
        try {
            if (event.getDate().isBefore(LocalDate.now())) {
                responseDTO.setMessage("Date of event creation can not be in the past");
                responseDTO.setData(event);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
            }
            eventRepository.save(event);
            responseDTO.setMessage("Event created successfully");
            responseDTO.setData(event);


        }catch (Exception e){
            log.error("Something wrong happened  in create event service ", e);
            throw new RuntimeException(e.getMessage());

        }
        return ResponseEntity.ok(responseDTO);

    }

    @Override
    public ResponseEntity<ResponseDTO<Event>> findById(UUID id) {
       log.info("EventServiceImpl:EventServiceImpl:findById");
       ResponseDTO<Event> responseDTO = new ResponseDTO<>();
       try {
            Optional<Event> eventOptional = eventRepository.findById(id);
            if (eventOptional.isEmpty()) {
                responseDTO.setMessage("Event with id " + id + " not found");
                responseDTO.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
            }
            responseDTO.setMessage("Event with id " + id + " found");
            responseDTO.setData(eventOptional.get());



       }catch (Exception e){
           log.error("Something wrong happened  in get event service ", e);
           throw new RuntimeException(e.getMessage());
       }
       return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO<List<Event>>> getAllEvents() {
        log.info("EventServiceImpl:EventServiceImpl:getAllEvents");
        ResponseDTO<List<Event>> responseDTO = new ResponseDTO<>();
        try {
            List<Event> eventList = eventRepository.findAll();
            if (eventList.isEmpty()) {
                responseDTO.setMessage("No events found");
                responseDTO.setData(null);
                return ResponseEntity.ok(responseDTO);
            }
            responseDTO.setMessage("Events found");
            responseDTO.setData(eventList);

        }catch (Exception e){
            log.error("Something wrong happened  in get event service ", e);
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(responseDTO);

    }
}
