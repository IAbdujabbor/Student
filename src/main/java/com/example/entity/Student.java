package com.example.entity;
import com.example.entity.Teacher;

import java.util.HashSet;
import java.util.Set;

import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class Student {


    private Long id;
    private String name;
    private Long age;
    private Set<Teacher> teachers =new HashSet<>();
    public Student(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Set<Teacher> addTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teachers;
    }
}