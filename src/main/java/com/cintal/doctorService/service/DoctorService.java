package com.cintal.doctorService.service;

import com.cintal.doctorService.models.Doctor;
import com.cintal.doctorService.models.DoctorSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctor() throws IOException;

    public DoctorSchedule getScheduleForDoctor(String day, String docName);
}
