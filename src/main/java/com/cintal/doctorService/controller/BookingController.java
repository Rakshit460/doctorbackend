package com.cintal.doctorService.controller;

import com.cintal.doctorService.models.Booking;
import com.cintal.doctorService.models.ResponseEnvelope;
import com.cintal.doctorService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookings/doctor/{doc}/user/{user}")
    public ResponseEnvelope bookAppointment(@RequestBody Booking booking,
                                            @PathVariable String doc,
                                            @PathVariable String user) {
        Booking booking1 = bookingService.bookSlotOfDoc(user, doc, booking);
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setResult(null);
        responseEnvelope.setStatus(201);
        return responseEnvelope;
    }

    @GetMapping("/bookings/user/{userName}")
    public ResponseEnvelope getBookingForAUser(@PathVariable String userName) {
        List<Booking> bookings = bookingService.getAllBookingForUser(userName);
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setResult(bookings);
        responseEnvelope.setStatus(200);
        return responseEnvelope;
    }
}
