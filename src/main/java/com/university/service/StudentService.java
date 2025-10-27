package com.university.service;

import com.university.dto.StudentDto;
import com.university.entity.Student;
import com.university.entity.Teacher;
import com.university.mapper.StudentMapper;
import com.university.aop.Logged;
import com.university.repository.StudentRepository;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Data


@ApplicationScoped
public class StudentService {


    private final StudentRepository studentRepository ;

    @Inject
    public StudentService(StudentRepository studentRepository ) {
        this.studentRepository = studentRepository;

    }

    @Inject
    StudentMapper mapper;

    private  Map<Long, Student> studentMap = new HashMap<>();
    private Long idCounter = 1L;


    @Logged
    public List<Student> getAll() {
        return studentRepository.getAll();
//        return studentMap.values().stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());


    }

    @Logged
    public Optional<Student> getById(Long id){
        return studentRepository.getById(id);
    }

    @Transactional
    public void addStudent(Student student) {
      studentRepository.addStudent(student);
    }



    @Logged
    public StudentDto create(String name, Long age) {
        return null;
////        """
////        if (age == null) age = 18L;
////        Student student = new Student(idCounter++, name, age, new HashSet<>());
////        studentMap.put(student.getId(), student);
////        return mapper.toDto(student);}
////        """
        }
    @Logged
    public StudentDto update(Long id, String name) {
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be null or empty");}
//
//        Student student = studentMap.get(id);
//
//        if (student != null) {
//            student.setName(name);
//            return mapper.toDto(student);
//        } else {
//            return null;}
//    }
   return null; }
    @Logged
    public StudentDto studentSearch(String name) {
        return null;
//        return studentMap.values().stream()
//                .map(mapper::toDto)
//                .filter(s -> s.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
    }

    @Logged
    public Student delete(Long id) {
        return null;
        //return studentMap.remove(id);
    }

    @Logged
    public Student getStudent(Long id) {
        //return studentMap.get(id);
        return null;
    }
}
