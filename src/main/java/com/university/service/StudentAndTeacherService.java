package com.university.service;


import com.university.aop.Logged;
import com.university.dto.request.StudentAndTeacherDtoRequest;
import com.university.dto.response.StudentAndTeacherDtoResponse;
import com.university.dto.response.StudentDtoResponse;
import com.university.entity.Student;
import com.university.entity.StudentAndTeacher;
import com.university.entity.Teacher;
import com.university.mapper.StudentAndTeacherMapper;
import com.university.repository.StudentAndTeacherRepository;
import com.university.repository.StudentRepository;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudentAndTeacherService {


    private final StudentAndTeacherRepository studentAndTeacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentAndTeacherMapper studentAndTeacherMapper;

    @Inject
    public StudentAndTeacherService(StudentAndTeacherRepository studentAndTeacherRepository,
                                    StudentRepository studentRepository,
                                    TeacherRepository teacherRepository,
                                    StudentAndTeacherMapper studentAndTeacherMapper) {

        this.studentAndTeacherRepository = studentAndTeacherRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentAndTeacherMapper = studentAndTeacherMapper;
    }

    //Read All
    public List<StudentAndTeacherDtoResponse> getAll() {
        return studentAndTeacherRepository.getAll()
                .stream()
                .map(studentAndTeacherMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //Read By id
    @Logged
    public Optional<StudentAndTeacherDtoResponse> getByIdStudentAndTeacher(Long id) {
        return studentAndTeacherRepository.getByIdStudentAndTeacher(id)
                .map(studentAndTeacherMapper::toResponseDto);

    }

    //Create
    @Logged
    public StudentAndTeacherDtoResponse add(StudentAndTeacherDtoRequest StudentAndTeacherParameter) {
        StudentAndTeacher entity = studentAndTeacherMapper.toEntity(StudentAndTeacherParameter);
        studentAndTeacherRepository.add(entity);
        return studentAndTeacherMapper.toResponseDto(entity);
    }


    //Edit
    @Logged
    @Transactional
    public Optional<StudentAndTeacherDtoResponse> updateStudentAndTeacher(Long id, StudentAndTeacherDtoRequest studentAndTeacherParameter) {
        return studentAndTeacherRepository.updateStudentAndTeacher(id, studentAndTeacherMapper.toEntity(studentAndTeacherParameter))
                .map(studentAndTeacherMapper::toResponseDto);

    }


    //Delete
    @Logged
    public Optional<StudentAndTeacherDtoResponse> deleteByIdStudentAndTeacher(Long id) throws NotFoundException {
        return studentAndTeacherRepository.deleteByStudentAndTeacherId(id)
                .map(studentAndTeacherMapper::toResponseDto)
                .or(() -> {
                    throw new NotFoundException("StudentAndTeacher not found with id: " + id);
                });

    }

}
