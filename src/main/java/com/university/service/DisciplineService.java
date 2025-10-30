package com.university.service;

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

    public List<Discipline> getAll() {
       return disciplineRepository.getAll();
   }

    public Optional<Discipline>  getById(Long id) {
       return disciplineRepository.findByIdOptional(id);
    }


    @Transactional
    public void add(Discipline discipline) {
        disciplineRepository.add(discipline);
    }



    public void deleteByIdDiscipline(Long id) {
        disciplineRepository.deleteByDisciplineId(id);


    }




}

