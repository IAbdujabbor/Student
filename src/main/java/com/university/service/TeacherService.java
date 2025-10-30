package com.university.service;

import com.university.aop.Logged;
import com.university.entity.Teacher;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@ApplicationScoped
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Inject
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    //Read All
    @Logged
    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAll();
    }

    //Read By id
    @Logged
    public Optional<Teacher> getTeacherById(Long id) {
       return teacherRepository.getByIdTeacher(id);
    }

    //Create
    @Logged
    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);

    }

    //Delete
    @Logged
    public void deleteTeacher(Long id) {
        boolean deleted = teacherRepository.deleteByIdTeacher(id);
        if (!deleted) {
            throw new IllegalArgumentException("not found " + id);
        }
    }
}
