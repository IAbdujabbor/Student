package com.university.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDtoResponse {

    private Long id;
    private Long studentId;
    private String studentName;
    private Long disciplineId;
    private String disciplineName;

}
