package com.university.service;

import com.university.aop.Logged;
import com.university.entity.Teacher;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;
@Data
@ApplicationScoped
public class TeacherService {

    private final TeacherRepository teacherRepository;


    @Inject
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }



    @Transactional
    public void addTeacher(Teacher teacher){
        teacherRepository.addTeacher(teacher);

    }

    @Logged
    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAll();
    }

    @Transactional
    public void deleteTeacher(Long id){
       boolean deleted =  teacherRepository.deleteByIdTeacher(id);
       if(!deleted){
           throw new IllegalArgumentException("not found " + id);
       }
    }


}
