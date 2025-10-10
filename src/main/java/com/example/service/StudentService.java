package com.example.service;

import com.example.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;
import com.example.aop.LoggingInterceptor;
import java.util.*;





@ApplicationScoped
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long idCounter = 1L;


    @LoggingInterceptor
    public List getAll() {

        return studentMap.values().stream().toList();

    }

    @LoggingInterceptor
    public Student create(String name ,Long age) {
        if (age == null){
            age= 18L;

        }
       // studentMap.entrySet().stream().peek(entry -> entry.getValue().setName(name));
        Student student = new Student(idCounter++, name ,age);
        studentMap.put(student.getId(), student);
        return  student;
    }
    @LoggingInterceptor
    public Student update(Long id, String name ) {

      return studentMap.entrySet().stream()
                .filter(s->s.getValue().getId().equals(id))
                .map(Map.Entry::getValue)
                .peek(s-> s.setName(name))
                .findFirst()
                .orElse(null);




    }


    public Student StudentSearch(String name){
       return studentMap.entrySet().stream()
                .filter(s->s.getValue().getName().equalsIgnoreCase(name))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);


        }


    public Student delete(Long id ){
            return  studentMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().equals(id))
                        .map(entry -> studentMap.remove(entry.getKey()))
                        .findFirst()
                        .orElse(null);

}

    public Student getStudent(Long id) {
      Student student = studentMap.get(id);
      return studentMap.entrySet().stream()
              .filter(entry -> entry.getKey().equals(id))
              .map(Map.Entry::getValue)
              .findFirst()
              .orElse(null);
    }


}
