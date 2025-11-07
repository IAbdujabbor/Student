package com.university.service;


import com.university.aop.Logged;
import com.university.dto.request.AttendanceDtoRequest;
import com.university.dto.response.*;
import com.university.dto.response.AttendanceDtoResponse;
import com.university.dto.request.AttendanceDtoRequest;
import com.university.entity.Attendance;
import com.university.entity.Discipline;
import com.university.entity.Student;
import com.university.exceptions.NotFoundException;
import com.university.mapper.AttendanceMapper;
import com.university.repository.AttendanceRepository;
import com.university.repository.DisciplineRepository;
import com.university.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Request;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttendanceService {


    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final DisciplineRepository disciplineRepository;

    @Inject
    StudentRepository studentRepository;


    @Inject
    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper, DisciplineRepository disciplineRepository) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.disciplineRepository = disciplineRepository;
    }

    //Read All
    @Logged
    public List<AttendanceDtoResponse> getAll() {
        return attendanceRepository.getAll()
                .stream()
                .map(attendanceMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //Read By id
    @Logged
    public Optional<AttendanceDtoResponse> getByIdAttendance(Long id) {
        return attendanceRepository.getByIdAttendance(id)
                .map(attendanceMapper::toResponseDto);

    }

    //Create
    @Logged
    @Transactional
    public AttendanceDtoResponse addAttendance(AttendanceDtoRequest request) throws NotFoundException {

        if (studentRepository.findByIdOptional(request.getStudentId()).isEmpty() || disciplineRepository.getByIdDiscipline(request.getDisciplineId()).isEmpty()) {
            throw new NotFoundException("Student or Discipline not found");
        }

        Attendance entity = attendanceMapper.toEntity(request);
        attendanceRepository.add(entity);
        return attendanceMapper.toResponseDto(entity);
    }

    //Edit
    @Logged
    public Optional<AttendanceDtoResponse> updateAttendance(Long id, AttendanceDtoRequest attendanceParameter) {
        Attendance updateEntity = attendanceMapper.toEntity(attendanceParameter);
        return attendanceRepository.updateAttendance(id, updateEntity)
                .map(attendanceMapper::toResponseDto);
    }

    //Delete
    @Logged
    public Optional<AttendanceDtoResponse> deleteById(Long id) {
        return attendanceRepository.deleteAttendanceById(id)
                .map(attendanceMapper::toResponseDto);
    }

}
