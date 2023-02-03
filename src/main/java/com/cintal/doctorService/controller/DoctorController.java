package com.cintal.doctorService.controller;


import com.cintal.doctorService.models.Doctor;
import com.cintal.doctorService.models.DoctorSchedule;
import com.cintal.doctorService.models.ResponseEnvelope;
import com.cintal.doctorService.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    private ResponseEnvelope getAllDoctor() throws IOException {
        List<Doctor> doctors = doctorService.getAllDoctor();
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setResult(doctors);
        responseEnvelope.setStatus(200);
        return responseEnvelope;
    }

    @GetMapping("/doctors/name/{docName}/day/{day}")
    private ResponseEnvelope getSlotOfADoctor(@PathVariable String docName, @PathVariable String day) {

        DoctorSchedule doctorSchedule = doctorService.getScheduleForDoctor(day, docName);
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setResult(doctorSchedule);
        responseEnvelope.setStatus(200);
        return responseEnvelope;
    }

}
