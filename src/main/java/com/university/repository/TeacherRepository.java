package com.university.repository;

import com.university.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {

    @Transactional
    public void addTeacher(Teacher teacher) {
        persist(teacher);
    }

    public List<Teacher> getAll() {
        return listAll();
    }

    public Optional<Teacher> getById(Long id) {
        return findByIdOptional(id);
    }

    @Transactional
    public boolean deleteByIdTeacher(Long id) {
        return deleteById(id);
    }

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


    public List<Teacher> findByFirstName(String firstName) {
        return list("firstName", firstName);
    }
}
