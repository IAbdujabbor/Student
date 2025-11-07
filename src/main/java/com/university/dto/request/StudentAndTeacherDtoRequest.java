package com.university.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndTeacherDtoRequest {
    private Long studentId;
    private Long teacherId;
}
