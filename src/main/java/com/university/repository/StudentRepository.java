package com.university.repository;

import com.university.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public List<Student> getAll() {
        return listAll();
    }

    @Transactional
    public void addStudent(Student student) {
        persist(student);
    }

    public Optional<Student> getById(Long id) {
        return findByIdOptional(id);
    }

    @Transactional
    public Boolean deleteStudent(Long id) {

        return deleteById(id);
    }

    @Transactional
    public Optional<Student> updateByFirstName(Long id, Student studentParameter) {
        return findByIdOptional(id).map(studentExist -> {
            studentExist.setFirstName(studentParameter.getFirstName());
            studentExist.setLastName(studentParameter.getLastName());
            studentExist.setPatronymic(studentParameter.getPatronymic());
            studentExist.setAge(studentParameter.getAge());
            persist(studentExist);
            return studentExist;

        });
    }

    public List<Student> findByFirstName(String firstName) {
        return list("firstName", firstName);
    }

}
