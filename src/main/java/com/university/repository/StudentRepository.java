package com.university.repository;

import com.university.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public List<Student> getAll(){
            return listAll();
    }

    public void addStudent (Student student){
        persist(student);
    }

    public Optional<Student> getById(Long id){
        return findByIdOptional(id);
    }

    public void deleteStudent(Long id){
        deleteById(id);
    }

}
