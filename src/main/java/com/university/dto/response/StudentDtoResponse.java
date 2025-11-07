package com.university.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Integer Age;
}
