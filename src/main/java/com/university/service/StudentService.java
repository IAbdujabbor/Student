package com.university.service;


import com.university.dto.request.StudentDtoRequest;
import com.university.dto.response.StudentDtoResponse;
import com.university.entity.Student;
import com.university.entity.Teacher;
//import com.university.mapper.StudentMapper;
import com.university.aop.Logged;
import com.university.mapper.StudentMapper;
import com.university.repository.StudentRepository;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Data


@ApplicationScoped
public class StudentService {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Inject
    public StudentService(StudentRepository studentRepository,StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    //Read All
    @Logged
    public List<StudentDtoResponse> getAll() {
        return studentRepository.getAll()
                .stream()
                .map(studentMapper::toResponseDto)
                .collect(Collectors.toList());

    }

    // Read By id
    @Logged
    public Optional<StudentDtoResponse> getByIdStudent(Long id) {
        return studentRepository.getByIdStudent(id)
                .map(studentMapper::toResponseDto);
    }

    //Search
    @Logged
    public StudentDtoResponse studentSearchFirstName(String name) {
        return null;
//        return studentMap.values().stream()
//                .map(mapper::toDto)
//                .filter(s -> s.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
    }




//Create
    @Logged
    @Transactional
    public StudentDtoResponse addStudent(StudentDtoRequest student) {
        Student entity = studentMapper.toEntity(student);
        studentRepository.persist(entity);
        return studentMapper.toResponseDto(entity);
    }

    //Edit
    @Logged
    @Transactional
    public StudentDtoResponse updateStudent(Long id, StudentDtoRequest studentParameter) {
    Student student = studentRepository.getByIdStudent(id)
            .orElseThrow(()-> new NotFoundException("Student not found with id " + id));

    student.setFirstName(studentParameter.getFirstName());
    student.setLastName(studentParameter.getLastName());
    student.setPatronymic(studentParameter.getPatronymic());
    student.setAge(studentParameter.getAge());

    studentRepository.persist(student);

    return studentMapper.toResponseDto(student);
    }


    //Delete
    @Logged
    @Transactional

    public StudentDtoResponse deleteStudent(Long id) throws NotFoundException {
        return studentRepository.deleteStudent(id)
                .map(studentMapper::toResponseDto)
                .orElseThrow(()-> new NotFoundException("Student not found wit id" + id));

    }



}
