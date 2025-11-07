package com.university.repository;

import com.university.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {


    //Read All
    public List<Teacher> getAll() {
        return listAll();
    }

    //Read By id
    public Optional<Teacher> getByIdTeacher(Long id) {
        return findByIdOptional(id);
    }

    //Search
    public List<Teacher> findByFirstName(String firstName) {
        return list("firstName", firstName);
    }

    //Create
    @Transactional
    public void addTeacher(Teacher teacher) {
        persist(teacher);
    }

    //Delete
    @Transactional
    public Optional<Teacher> deleteByIdTeacher(Long id) {
        return findByIdOptional(id).map(teacherExist->{
            delete(teacherExist);
            return teacherExist;
        });
    }

    //Update
    @Transactional
    public Optional<Teacher> updateTeacher(Long id, Teacher teacherParameter) {
        return findByIdOptional(id).map(teacherExisting -> {
            teacherExisting.setFirstName(teacherParameter.getFirstName());
            teacherExisting.setLastName(teacherParameter.getLastName());
            teacherExisting.setPatronymic(teacherParameter.getPatronymic());
            teacherExisting.setAge(teacherParameter.getAge());
            persist(teacherExisting);
            return teacherExisting;

        });
    }



}
