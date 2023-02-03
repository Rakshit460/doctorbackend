package com.cintal.doctorService.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String bookingId;
    private String userId;
    private String doctorName;
    private String day;
    private Slot slot;

}
