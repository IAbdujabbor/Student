package com.university.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import javax.security.auth.Subject;

@Entity
@Table(name = "attendance")
public class Attendance  {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE ,generator =  "attendance_seq")
    @SequenceGenerator(

            name = "attendance_seq",
            sequenceName  = "ATTENDANCE_SEQ",
            allocationSize = 1

    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stundet_id", nullable =false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private  Discipline discipline;

}
