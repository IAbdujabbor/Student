package com.university.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndTeacherDtoResponse {
    private Long id;
    private Long studentId;
    private Long teacherId;
}
