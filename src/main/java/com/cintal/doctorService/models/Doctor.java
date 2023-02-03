package com.cintal.doctorService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private String name;
    private String timeZone;
    private String weekDay;
    private String availableAt;
    private String availableUntil;
}
