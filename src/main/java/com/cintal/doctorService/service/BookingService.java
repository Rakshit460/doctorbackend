package com.cintal.doctorService.service;

import com.cintal.doctorService.models.Booking;

import java.util.List;

public interface BookingService {
    public Booking bookSlotOfDoc(String user, String doc, Booking booking);
    public List<Booking> getAllBookingForUser(String userId);
}
