package com.university.service;

import com.university.aop.Logged;
import com.university.dto.request.TeacherDtoRequest;
import com.university.dto.response.TeacherDtoResponse;
import com.university.entity.Teacher;
import com.university.mapper.TeacherMapper;
import com.university.repository.StudentRepository;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@ApplicationScoped
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Inject
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    //Read All
    @Logged
    public List<TeacherDtoResponse> getAllTeachers() {
        return teacherRepository.getAll()
                .stream()
                .map(teacherMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    //Read By id
    @Logged
    public Optional<TeacherDtoResponse> getTeacherById(Long id) {
        return teacherRepository.getByIdTeacher(id)
                .map(teacherMapper::toResponseDto);
    }

    //Create
    @Logged
    @Transactional
    public TeacherDtoResponse addTeacher(TeacherDtoRequest teacherParameter) {
        Teacher entity = teacherMapper.toEntity(teacherParameter);
        teacherRepository.persist(teacherMapper.toEntity(teacherParameter));
        return teacherMapper.toResponseDto(entity);

    }

    //Edit
    @Logged
    @Transactional
    public TeacherDtoResponse updateTeacher(Long id, TeacherDtoRequest teacherParameter) {
        Teacher teacher = teacherRepository.getByIdTeacher(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found with id " + id));
        teacher.setFirstName(teacherParameter.getFirstName());
        teacher.setLastName(teacherParameter.getLastName());
        teacher.setPatronymic(teacherParameter.getPatronymic());
        teacher.setAge(teacherParameter.getAge());

        teacherRepository.persist(teacher);
        return teacherMapper.toResponseDto(teacher);
    }

    //Delete
    @Logged
    @Transactional
    public TeacherDtoResponse deleteTeacher(Long id) {
        return teacherRepository.deleteByIdTeacher(id)
                .map(teacherMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("Teacher not found with id " + id));
    }
}
