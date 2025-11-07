package com.university.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceDtoRequest {

    private Long studentId;
    private Long disciplineId;


}
