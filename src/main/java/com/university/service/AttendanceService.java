package com.university.service;


import com.university.aop.Logged;
import com.university.entity.Attendance;
import com.university.repository.AttendanceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AttendanceService {


    private final AttendanceRepository attendanceRepository;

    @Inject
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    //Read All
    @Logged
    public List<Attendance> getAll(){
        return attendanceRepository.getAll();
    }

    //Read By Id
    @Logged
    public void getByIdAttendance(Long id){
         attendanceRepository.getByIdAttendance(id);
    }

    //Create
    @Logged
    public void addAttendance(Attendance attendance){
        attendanceRepository.add(attendance);
    }

    //Edit
    @Logged
    public Optional<Attendance> updateAttendance(Long id, Attendance attendanceParameter){
       return attendanceRepository.updateAttendance(id, attendanceParameter);
    }

    //Delete
    @Logged
    public Optional<Attendance> deleteById(Long id){
        return attendanceRepository.deleteAttendanceById(id);
    }

}
