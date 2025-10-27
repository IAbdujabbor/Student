package com.university.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="discipline")
public class Discipline  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discipline_seq")
    @SequenceGenerator(
            name = "discipline_seq",
            sequenceName = "DISCIPLINE_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column( name = "disipline_name", nullable = false, unique=true)
    private String disciplineName ;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval =true)
    private Set<Attendance> attendance = new HashSet<>();


}
