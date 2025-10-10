package com.example.entity;

import java.util.HashSet;
import java.util.Set;
import com.example.entity.Student;



public class Teacher {

    private Long id;
    private String name;
    private Long age;
    private Set<Student> students = new HashSet<>();



    public Teacher(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }


    public void  addStudent(Student student){students.add(student);}

}
