    package com.university.entity;

import java.util.HashSet;
import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="teacher")
public class Teacher  {
    //
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator ="teacher_seq")
    @Id
    @SequenceGenerator(
            name = "teacher_seq",
            sequenceName = "TEACHER_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column(name="firstname", nullable = false)
    private String firstName;

    @Column(name="lastname", nullable = false)
    private String lastName;

    @Column(name="patronymic",  nullable=false)
    private String patronymic;

    @Column (name ="age",   nullable = false)
    public Integer age;

    @ManyToMany(mappedBy = "teacher")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Discipline> discipline = new HashSet<>();

}
