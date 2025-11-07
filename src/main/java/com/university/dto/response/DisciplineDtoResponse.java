package com.university.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisciplineDtoResponse {
    private Long id;
    private String disciplineName;
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
}
