package com.university.service;

import com.university.aop.Logged;
import com.university.entity.Discipline;
import com.university.repository.DisciplineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findByIdOptional;


@ApplicationScoped
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    @Inject
    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    //Read All
    @Logged
    public List<Discipline> getAll() {
        return disciplineRepository.getAll();
    }

    //Read By Id
    @Logged
    public Optional<Discipline> getById(Long id) {
        return disciplineRepository.getByIdDiscipline(id);
    }

    //Create

    @Logged
    public void add(Discipline discipline) {
        disciplineRepository.add(discipline);
    }

    //Edit

    @Logged
    public void updateDiscipline(Long id, Discipline disciplineParameter) {
        disciplineRepository.updateDiscipline(id, disciplineParameter);
    }

    //DeleteById

    @Logged
    public void deleteByIdDiscipline(Long id) {
        disciplineRepository.deleteByDisciplineId(id);


    }


}

