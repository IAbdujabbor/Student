package com.university.service;


import com.university.entity.Attendance;
import com.university.repository.AttendanceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AttendanceService {


    private AttendanceRepository attendanceRepository;

    @Inject
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAll(){
        return attendanceRepository.getAll();

    }


    public void addAttendance(Attendance attendance){
        attendanceRepository.add(attendance);
    }
    public Optional<Attendance> deleteById(Long id){
        return attendanceRepository.deleteAttendanceById(id);
    }

}
