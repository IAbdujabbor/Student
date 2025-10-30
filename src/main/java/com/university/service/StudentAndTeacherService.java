package com.university.service;


import com.university.aop.Logged;
import com.university.entity.StudentAndTeacher;
import com.university.repository.StudentAndTeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentAndTeacherService {


    private final StudentAndTeacherRepository studentAndTeacherRepository;


    @Inject
    public StudentAndTeacherService(StudentAndTeacherRepository studentAndTeacherRepository) {
        this.studentAndTeacherRepository = studentAndTeacherRepository;
    }

    //Read All
    public List<StudentAndTeacher> getAll() {
        return studentAndTeacherRepository.getAll();
    }

    //Read By Id
    @Logged
    public Optional<StudentAndTeacher> getById(Long id) {
        return studentAndTeacherRepository.getById(id);

    }

    //Create
    @Logged
    public void add(StudentAndTeacher studentAndTeacher) {
         studentAndTeacherRepository.add(studentAndTeacher);
    }


    //Edit
    @Logged
    public Optional<StudentAndTeacher> updateStudentAndTeacher(Long id, StudentAndTeacher studentAndTeacherParameter) {
        return studentAndTeacherRepository.updateStudentAndTeacher(id, studentAndTeacherParameter);
    }


    //Delete
    @Logged
    public void deleteByIdStudentAndTeacher(Long id) {
         studentAndTeacherRepository.deleteByStudentAndTeacherId(id);
    }

}
