package com.university.repository;

import com.university.entity.StudentAndTeacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentAndTeacherRepository implements PanacheRepository<StudentAndTeacher> {


    //Read All
    public List<StudentAndTeacher> getAll() {
        return find("from StudentAndTeacher").list();
    }


    //Read By Id
    public Optional<StudentAndTeacher> getByIdStudentAndTeacher(Long id) {
        return findByIdOptional(id);
    }

    //Create
    @Transactional
    public StudentAndTeacher add(StudentAndTeacher studentAndTeacher) {
        persist(studentAndTeacher);
        return studentAndTeacher;
    }

    //Remove
    @Transactional
    public Optional<StudentAndTeacher> deleteByStudentAndTeacherId(Long id) {
        return findByIdOptional(id).map(studentAndTeacherExist -> {
            delete(studentAndTeacherExist);
            return studentAndTeacherExist;
        });
    }

    //Edit
    @Transactional
    public Optional<StudentAndTeacher> updateStudentAndTeacher(Long id, StudentAndTeacher studentAndTeacherParameter) {

        return findByIdOptional(id).map(studentAndTeacherExist -> {
            studentAndTeacherExist.setStudentId(studentAndTeacherParameter.getStudentId());
            studentAndTeacherExist.setTeacherId(studentAndTeacherParameter.getTeacherId());
            persist(studentAndTeacherExist);
            return studentAndTeacherExist;
        });

    }

}


