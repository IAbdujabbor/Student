package com.university.entity;


import jakarta.persistence.*;

import java.io.Serializable;



public class StudentAndTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator ="student_and_teacher")
    @SequenceGenerator(
            name = "student_and_teacher",
            sequenceName = "STUDENT_AND_TEACHER",
            allocationSize = 1

    )
    private Long id;
    private Long studentId;
    private Long teacherId;
}
