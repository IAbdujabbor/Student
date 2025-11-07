package com.university.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "discipline")

public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discipline_seq")
    @SequenceGenerator(
            name = "discipline_seq",
            sequenceName = "DISCIPLINE_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "discipline_name", nullable = false, unique = true)
    private String disciplineName;

    @ManyToOne

    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Attendance> attendance = new HashSet<>();


}
