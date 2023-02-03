package com.cintal.doctorService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSchedule {
    private String doctorName;
    private String day;
    private List<Slot> slots=new ArrayList<>();
}
