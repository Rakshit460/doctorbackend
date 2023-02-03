package com.cintal.doctorService.service;

import com.cintal.doctorService.exception.BookingAlreadyDoneException;
import com.cintal.doctorService.models.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    List<Booking> bookings = new ArrayList<>();

    @Override
    public Booking bookSlotOfDoc(String user, String doc, Booking booking) {
        if(!bookings
                .stream()
                .anyMatch(bok->bok.getUserId().equals(user) && bok.getDoctorName().equals(doc) && bok.getSlot().getFrom().equals(booking.getSlot().getFrom()) && bok.getSlot().getTo().equals(booking.getSlot().getTo()))){
            Booking bookingCreate= new Booking();
            bookingCreate.setBookingId(UUID.randomUUID().toString());
            bookingCreate.setUserId(user);
            bookingCreate.setSlot(booking.getSlot());
            bookingCreate.setDoctorName(doc);
            bookingCreate.setDay(booking.getDay());
            bookings.add(bookingCreate);
            return booking;
        }else {
            throw new BookingAlreadyDoneException("Booking already present for slot"+booking.getSlot().getFrom()+ "-"+booking.getSlot().getTo()+ " with doctor : "+ doc);
        }

    }

    @Override
    public List<Booking> getAllBookingForUser(String userId) {

        return bookings
                .stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
