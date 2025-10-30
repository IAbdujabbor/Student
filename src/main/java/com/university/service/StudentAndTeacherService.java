package com.university.service;


import com.university.entity.StudentAndTeacher;
import com.university.repository.StudentAndTeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentAndTeacherService {

    private final StudentAndTeacherRepository studentAndTeacherRepository;

    @Inject
    public StudentAndTeacherService(StudentAndTeacherRepository studentAndTeacherRepository) {
        this.studentAndTeacherRepository = studentAndTeacherRepository;
    }








}
