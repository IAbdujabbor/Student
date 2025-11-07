package com.university.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineDtoRequest {
    private String disciplineName;
    private Long teacherId;
}
