package com.university.service;

import com.university.dto.request.DisciplineDtoRequest;
import com.university.dto.response.DisciplineDtoResponse;
import com.university.entity.Discipline;
import com.university.entity.Teacher;
import com.university.exceptions.NotFoundException;
import com.university.mapper.DisciplineMapper;
import com.university.repository.DisciplineRepository;
import com.university.repository.TeacherRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class DisciplineServiceTest {

    private DisciplineRepository disciplineRepository;
    private DisciplineMapper disciplineMapper;
    private TeacherRepository teacherRepository;
    private DisciplineService disciplineService;

    @BeforeEach
    void setUp() {
        disciplineRepository = Mockito.mock(DisciplineRepository.class);
        disciplineMapper = Mockito.mock(DisciplineMapper.class);
        teacherRepository = Mockito.mock(TeacherRepository.class);
        disciplineService = new DisciplineService(disciplineRepository, disciplineMapper, teacherRepository);
    }

    @Test
    void testGetAll() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineName("Math");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        discipline.setTeacher(teacher);

        when(disciplineRepository.getAll()).thenReturn(List.of(discipline));

        DisciplineDtoResponse responseDto = new DisciplineDtoResponse(
                1L,
                "Math",
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
        when(disciplineMapper.toResponseDto(discipline)).thenReturn(responseDto);

        List<DisciplineDtoResponse> result = disciplineService.getAll();
        assertEquals(1, result.size());
        assertEquals("Math", result.get(0).getDisciplineName());
    }

    @Test
    void testGetByIdDiscipline_found() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineName("Math");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        discipline.setTeacher(teacher);

        when(disciplineRepository.getByIdDiscipline(1L)).thenReturn(Optional.of(discipline));

        DisciplineDtoResponse responseDto = new DisciplineDtoResponse(
                1L,
                "Math",
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
        when(disciplineMapper.toResponseDto(discipline)).thenReturn(responseDto);

        Optional<DisciplineDtoResponse> result = disciplineService.getByIdDiscipline(1L);
        assertTrue(result.isPresent());
        assertEquals("Math", result.get().getDisciplineName());
    }

    @Test
    void testAddDiscipline() throws NotFoundException {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        DisciplineDtoRequest request = new DisciplineDtoRequest();
        request.setDisciplineName("Physics");
        request.setTeacherId(1L);

        Discipline discipline = new Discipline();
        discipline.setDisciplineName("Physics");
        discipline.setTeacher(teacher);

        when(teacherRepository.findByIdOptional(1L)).thenReturn(Optional.of(teacher));

        DisciplineDtoResponse responseDto = new DisciplineDtoResponse(
                1L,
                "Physics",
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
        when(disciplineMapper.toResponseDto(discipline)).thenReturn(responseDto);

        DisciplineDtoResponse result = disciplineService.add(request);

        assertNotNull(result);
        assertEquals("Physics", result.getDisciplineName());
        verify(disciplineRepository).persist(discipline);
    }

    @Test
    void testAddDiscipline_teacherNotFound() {
        DisciplineDtoRequest request = new DisciplineDtoRequest();
        request.setDisciplineName("Physics");
        request.setTeacherId(1L);

        when(teacherRepository.findByIdOptional(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> disciplineService.add(request));
    }

    @Test
    void testUpdateDiscipline() throws NotFoundException {
        Discipline discipline = new Discipline();
        discipline.setDisciplineName("OldName");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        discipline.setTeacher(teacher);

        DisciplineDtoRequest updateRequest = new DisciplineDtoRequest();
        updateRequest.setDisciplineName("NewName");

        when(disciplineRepository.getByIdDiscipline(1L)).thenReturn(Optional.of(discipline));

        DisciplineDtoResponse responseDto = new DisciplineDtoResponse(
                1L,
                "NewName",
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
        when(disciplineMapper.toResponseDto(discipline)).thenReturn(responseDto);

        DisciplineDtoResponse result = disciplineService.updateDiscipline(1L, updateRequest);
        assertEquals("NewName", result.getDisciplineName());
        verify(disciplineRepository).persist(discipline);
    }

    @Test
    void testUpdateDiscipline_notFound() {
        DisciplineDtoRequest updateRequest = new DisciplineDtoRequest();
        updateRequest.setDisciplineName("NewName");

        when(disciplineRepository.getByIdDiscipline(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> disciplineService.updateDiscipline(1L, updateRequest));
    }

    @Test
    void testDeleteByIdDiscipline() {
        Discipline discipline = new Discipline();
        discipline.setDisciplineName("Math");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        discipline.setTeacher(teacher);

        when(disciplineRepository.getByIdDiscipline(1L)).thenReturn(Optional.of(discipline));

        DisciplineDtoResponse responseDto = new DisciplineDtoResponse(
                1L,
                "Math",
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
        when(disciplineMapper.toResponseDto(discipline)).thenReturn(responseDto);

        Optional<DisciplineDtoResponse> result = disciplineService.deleteByIdDiscipline(1L);
        assertTrue(result.isPresent());
        assertEquals("Math", result.get().getDisciplineName());
        verify(disciplineRepository).delete(discipline);
    }
}
