package com.university.repository;

import com.university.entity.Discipline;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class DisciplineRepository implements PanacheRepository<Discipline> {


    public List<Discipline> getAll() {
        return listAll();
    }

    @Transactional
    public Optional<Discipline> deleteByDisciplineId(Long id) {
        return findByIdOptional(id).map(disciplineExist->{delete(
                disciplineExist);
            return disciplineExist;});
    }

    public List<Discipline> findByName(String disciplineName) {
        return list("disciplineName", disciplineName);
    }

    @Transactional
    public Discipline add(Discipline discipline) {
        persist(discipline);
        return discipline;
    }

    @Transactional
    public Optional<Discipline> updateDiscipline(Long id, Discipline disciplineParameter) {
        return findByIdOptional(id).map(disciplineExist -> {
            disciplineExist.setDisciplineName(disciplineParameter.getDisciplineName());
            persist(disciplineExist);
            return disciplineExist;
        });
    }

    public List<Discipline> findByDisciplineName(String disciplineName) {
        return list("disciplineName", disciplineName);
    }

}
