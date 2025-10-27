package com.university.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentDto {
    private Long id;
    private String name;
    private Long age;
    @Builder.Default
    private Set<String> teacherNames = new HashSet<>();
    // REMOVED: private Set<Teacher> teachers = new HashSet<>();
}