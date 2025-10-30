package com.university.mapper;

import com.university.dto.StudentDto;
import com.university.entity.Student;
import com.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "cdi")
public interface StudentMapper {


    //    @Mapping(target = "teacherNames", source =  "teachers")
    StudentDto toDto(Student student);

    default Set<String> map(Set<Teacher> teachers) {
        // if (teachers == null) return new HashSet<>();
        return null;
        //teachers.stream()
        //  .map(Teacher::getName) // Teacher classida getName() bo‘lishi kerak
        //  .collect(Collectors.toSet());

    }
}
