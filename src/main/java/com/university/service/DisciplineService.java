package com.university.service;

import com.university.aop.Logged;
import com.university.dto.request.DisciplineDtoRequest;
import com.university.dto.response.DisciplineDtoResponse;
import com.university.entity.Discipline;
import com.university.entity.Teacher;
import com.university.exceptions.NotFoundException;
import com.university.mapper.DisciplineMapper;
import com.university.repository.DisciplineRepository;
import com.university.repository.TeacherRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findByIdOptional;


@ApplicationScoped
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;
    private final TeacherRepository teacherRepository;

    @Inject
    public DisciplineService(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper, TeacherRepository teacherRepository) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
        this.teacherRepository = teacherRepository;
    }


    //Read All
    @Logged
    public List<DisciplineDtoResponse> getAll() {
        return disciplineRepository.getAll()
                .stream()
                .map(disciplineMapper::toResponseDto)
                .toList();
    }

    //Read By id
    @Logged
    public Optional<DisciplineDtoResponse> getByIdDiscipline(Long id) {
        return disciplineRepository.getByIdDiscipline(id)
                .map(disciplineMapper::toResponseDto);
    }
    //Create

    // Service
    @Logged
    public DisciplineDtoResponse add(DisciplineDtoRequest disciplineRequest) throws NotFoundException {
        Teacher teacher = teacherRepository.findByIdOptional(disciplineRequest.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Teacher not found with id " + disciplineRequest.getTeacherId()));

        Discipline discipline = new Discipline();
        discipline.setDisciplineName(disciplineRequest.getDisciplineName());
        discipline.setTeacher(teacher);
        disciplineRepository.persist(discipline);

        return disciplineMapper.toResponseDto(discipline);
    }

    //Edit

    @Logged
    @Transactional
    public DisciplineDtoResponse updateDiscipline(Long id, DisciplineDtoRequest disciplineParameter) throws NotFoundException {
        Discipline discipline = disciplineRepository.getByIdDiscipline(id)
                .orElseThrow(() -> new NotFoundException("Discipline not found id: " + id));
        discipline.setDisciplineName(disciplineParameter.getDisciplineName());
        discipline.setTeacher(discipline.getTeacher());
        disciplineRepository.persist(discipline);

        return disciplineMapper.toResponseDto(discipline);
    }

    //Delete

    @Logged
    @Transactional
    public Optional<DisciplineDtoResponse> deleteByIdDiscipline(Long id) {
        return disciplineRepository.getByIdDiscipline(id)
                .map(discipline -> {
                    disciplineRepository.delete(discipline);
                    return disciplineMapper.toResponseDto(discipline);
                });
    }


}

