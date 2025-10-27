package com.university.repository;

import com.university.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {

    public void  addTeacher(Teacher teacher){persist(teacher);}

    public List<Teacher> getAll(){
        return listAll();}

    public Optional<Teacher> getById(Long id){return  findByIdOptional(id);}

   public boolean deleteTeacher(Long id){
        return deleteById(id);
   }

    }
