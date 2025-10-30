package com.university.repository;

import com.university.entity.StudentAndTeacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentAndTeacherRepository implements PanacheRepository<StudentAndTeacher> {



    public List<StudentAndTeacher> getAll() {
        return find("from StudentAndTeacher").list();
    }

    @Transactional
    public StudentAndTeacher add(StudentAndTeacher studentAndTeacher){
        persist(studentAndTeacher);
        return studentAndTeacher;
    }

    @Transactional
    public Optional<StudentAndTeacher>deleteByStudentAndTeacherId(Long id){
        return findByIdOptional(id).map(studentAndTeacherExist->{
            delete(studentAndTeacherExist);
            return studentAndTeacherExist;
        });}

    @Transactional
    public Optional<StudentAndTeacher> updateStudentAndTeacher(Long id , StudentAndTeacher studentAndTeacherParameter ){

        return findByIdOptional(id).map(studentAndTeacherExist -> {
            studentAndTeacherExist.setStudentId(studentAndTeacherParameter.getStudentId());
            studentAndTeacherExist.setTeacherId(studentAndTeacherExist.getTeacherId());
            persist(studentAndTeacherExist);
            return studentAndTeacherExist;
        });

    }

    }


