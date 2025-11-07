package com.university.repository;

import com.university.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    //Read All
    public List<Student> getAll() {
        return listAll();
    }

    //Read By id
    public Optional<Student> getByIdStudent(Long id) {

        return findByIdOptional(id);
    }

    //Create
    @Transactional
    public void addStudent(Student student) {
        persist(student);
    }



    //Delete
    @Transactional
    public Optional<Student> deleteStudent(Long id) {

        return findByIdOptional(id).map(studentExist -> {
            delete(studentExist);
            return studentExist;
        });
    }

    //Edit
    @Transactional
    public Optional<Student> updateStudent(Long id, Student studentParameter) {
        return findByIdOptional(id).map(studentExist -> {
            studentExist.setFirstName(studentParameter.getFirstName());
            studentExist.setLastName(studentParameter.getLastName());
            studentExist.setPatronymic(studentParameter.getPatronymic());
            studentExist.setAge(studentParameter.getAge());
            persist(studentExist);
            return studentExist;

        });
    }

    //Search By FirstName
    public List<Student> findByFirstName(String firstName) {
        return list("firstName", firstName);
    }

}
