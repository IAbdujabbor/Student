package com.university.dto;


import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TeacherDto {

    private String name;
    private Long age;
    private Set<StudentDto> students;
    private Set<TeacherDto> teachers;

}
