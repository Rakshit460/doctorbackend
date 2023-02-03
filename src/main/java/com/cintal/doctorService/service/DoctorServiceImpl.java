package com.cintal.doctorService.service;

import com.cintal.doctorService.models.Doctor;
import com.cintal.doctorService.models.DoctorSchedule;
import com.cintal.doctorService.models.Slot;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static List<Doctor> doctors = new ArrayList<>();
    private List<Doctor> uniqueDoctor = new ArrayList<>();

    {
        // Reading file and map to model class
        HeaderColumnNameTranslateMappingStrategy<Doctor> strategy = createStrategy();
        doctors = this.mapCsvToDoctor(strategy);
    }


    @Override
    public List<Doctor> getAllDoctor() throws IOException {
        if (uniqueDoctor.isEmpty()) {
            // fetching unique doctors by grouping them and taking first from it
            uniqueDoctor = doctors.stream().collect(groupingBy(Doctor::getName))
                    .values()
                    .stream()
                    .flatMap(values -> values.stream().limit(1))
                    .collect(toList());
            uniqueDoctor.stream().forEach(doctor -> {
            });
        }
        return uniqueDoctor;
    }

    @Override
    public DoctorSchedule getScheduleForDoctor(String day, String docName) {
        //Doctor fetched from doctor list and for a particular day
        Doctor doctorFetched = doctors.stream()
                .filter(dc -> dc.getName().equals(docName) && dc.getWeekDay().equalsIgnoreCase(day))
                .findFirst().orElse(new Doctor());
        List<Slot> slots=new ArrayList<>();
        if(doctorFetched.getName() !=null){
            slots = this.createDoctorSlotFromTimeAvailability(doctorFetched.getAvailableAt(), doctorFetched.getAvailableUntil());
        }
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDoctorName(docName);
        doctorSchedule.setDay(day);
        doctorSchedule.setSlots(slots);
        return doctorSchedule;
    }

    private static HeaderColumnNameTranslateMappingStrategy<Doctor> createStrategy() {
        Map mapping = new HashMap();

        mapping.put("Name", "name");
        mapping.put("Timezone", "timeZone");
        mapping.put("Day of Week", "weekDay");
        mapping.put("Available at", "availableAt");
        mapping.put("Available until", "availableUntil");

        // HeaderColumnNameTranslateMappingStrategy
        // for Doctor class
        HeaderColumnNameTranslateMappingStrategy<Doctor> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Doctor>();
        strategy.setType(Doctor.class);
        strategy.setColumnMapping(mapping);
        return strategy;
    }

    private List<Doctor> mapCsvToDoctor(HeaderColumnNameTranslateMappingStrategy<Doctor> strategy) {

        InputStream in = getClass().getResourceAsStream("/doctors.csv");
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        // Create csvtobean and csvreader object
        CSVReader csvReader = new CSVReader(reader);
        CsvToBean csvToBean = new CsvToBean();
        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        return csvToBean.parse(strategy, csvReader);
    }

    private List<Slot> createDoctorSlotFromTimeAvailability(String startTime, String endTime) {
        // making current time fetched from csv in format of 24 hours example 4:30pm to 16:30
        // removing space from time if present in start so that parsing can be done easily
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        startTime = startTime.replaceAll("\\s", "");
        endTime = endTime.replaceAll("\\s", "");

        // Creating local time based on 24 hours format
        LocalTime stObj = LocalTime.parse(startTime, formatter);
        LocalTime endObj = LocalTime.parse(endTime, formatter);

        List<Slot> slots = new ArrayList<>();
        // Generating slots and adding in list
        while (!stObj.equals(endObj)) {
            LocalTime increasedTime = stObj.plusMinutes(30);
            Slot slot = new Slot();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mma");
            slot.setTo(increasedTime.format(dtf));
            slot.setFrom(stObj.format(dtf));
            slots.add(slot);
            stObj = increasedTime;
        }
        return slots;
    }

}
