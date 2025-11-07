package com.university.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDtoRequest {

   private String firstName;
   private String lastName;
   private String patronymic;
   private Integer age;


}
