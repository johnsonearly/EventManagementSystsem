package com.johnson.eventbookingsystem.serviceImpl;

import com.johnson.eventbookingsystem.dto.BookingDTO;
import com.johnson.eventbookingsystem.dto.ResponseDTO;
import com.johnson.eventbookingsystem.entity.Booking;
import com.johnson.eventbookingsystem.entity.Event;
import com.johnson.eventbookingsystem.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.johnson.eventbookingsystem.repository.BookingRepository;
import com.johnson.eventbookingsystem.service.BookingService;
import com.johnson.eventbookingsystem.service.EventService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final EventService eventService;


    @Override
    public ResponseEntity<ResponseDTO<Booking>> create( UUID id, BookingDTO booking) {
        log.debug("create(Booking booking)");
        ResponseDTO<Booking> responseDTO = new ResponseDTO<>();
        Booking bookingEntity = new Booking();
        try{
            Event event = Objects.requireNonNull(eventService.findById(id).getBody()).getData();
            if(event != null) {


                if (event.getStatus() == Status.CLOSED) {
                    responseDTO.setMessage("Event has been closed");
                    responseDTO.setData(null);
                    return ResponseEntity.ok(responseDTO);
                }
                if (event.getTotalSeats() <= event.getBookedSeats()) {
                    responseDTO.setMessage("Total number of seats is less than number of booked seats");
                    responseDTO.setData(null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

                }
                if (bookingRepository.findByAttendeeEmail(booking.getAttendeeEmail())) {
                    responseDTO.setMessage("Email already exists");
                    responseDTO.setData(null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

                }
            }
            bookingEntity.setAttendeeEmail(booking.getAttendeeEmail());
            bookingEntity.setAttendeeName(booking.getAttendeeName());
            bookingEntity.setEventId(event);
            bookingRepository.save(bookingEntity);
            responseDTO.setMessage("Booking made successfully");
            responseDTO.setData(bookingEntity);

        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO<Booking>> deleteBooking(UUID id) {
        ResponseDTO<Booking> responseDTO = new ResponseDTO<>();
        try{
            Optional<Booking> booking = bookingRepository.findById(id);
            if(booking.isEmpty()){
                responseDTO.setMessage("Booking not found");
                responseDTO.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);

            }
            responseDTO.setMessage("Booking has been deleted");
            bookingRepository.deleteById(id);
            responseDTO.setData(null);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO<List<Booking>>> getAllBookings(UUID id) {
        ResponseDTO<List<Booking>> responseDTO = new ResponseDTO<>();
        try{
            Event event = Objects.requireNonNull(eventService.findById(id).getBody()).getData();
            if(event != null) {
                List<Booking> bookings = bookingRepository.findAllByEventId(event);
                if (bookings.isEmpty()) {
                    responseDTO.setMessage("No Bookings found");
                    responseDTO.setData(null);
                    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
                }
                responseDTO.setMessage("Bookings found");
                responseDTO.setData(bookings);
            }

        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }
}
