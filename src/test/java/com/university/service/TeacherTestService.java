package com.university.service;

import com.university.dto.request.TeacherDtoRequest;
import com.university.dto.response.TeacherDtoResponse;
import com.university.entity.Teacher;
import com.university.mapper.TeacherMapper;
import com.university.repository.TeacherRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class TeacherServiceTest {

    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        teacherRepository = Mockito.mock(TeacherRepository.class);
        teacherMapper = Mockito.mock(TeacherMapper.class);
        teacherService = new TeacherService(teacherRepository, teacherMapper);
    }

    @Test
    void testAddTeacher() {
        TeacherDtoRequest request = new TeacherDtoRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPatronymic("Smith");
        request.setAge(35);

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setPatronymic("Smith");
        teacher.setAge(35);

        when(teacherMapper.toEntity(request)).thenReturn(teacher);
        TeacherDtoResponse responseDto = new TeacherDtoResponse(1L, "John", "Doe", "Smith", 35);
        when(teacherMapper.toResponseDto(teacher)).thenReturn(responseDto);

        TeacherDtoResponse result = teacherService.addTeacher(request);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(teacherRepository).persist(teacher);
    }

    @Test
    void testGetAllTeachers() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");

        when(teacherRepository.getAll()).thenReturn(List.of(teacher));
        TeacherDtoResponse responseDto = new TeacherDtoResponse(1L, "John", "Doe", "Smith", 35);
        when(teacherMapper.toResponseDto(teacher)).thenReturn(responseDto);

        List<TeacherDtoResponse> result = teacherService.getAllTeachers();
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void testGetTeacherById_found() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherRepository.getByIdTeacher(1L)).thenReturn(Optional.of(teacher));
        TeacherDtoResponse responseDto = new TeacherDtoResponse(1L, "John", "Doe", "Smith", 35);
        when(teacherMapper.toResponseDto(teacher)).thenReturn(responseDto);

        Optional<TeacherDtoResponse> result = teacherService.getTeacherById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    void testUpdateTeacher_notFound() {
        TeacherDtoRequest request = new TeacherDtoRequest();
        request.setFirstName("Updated");

        when(teacherRepository.getByIdTeacher(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> teacherService.updateTeacher(1L, request));
    }

    @Test
    void testDeleteTeacher_found() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");

        when(teacherRepository.deleteByIdTeacher(1L)).thenReturn(Optional.of(teacher));
        TeacherDtoResponse responseDto = new TeacherDtoResponse(1L, "John", "Doe", "Smith", 35);
        when(teacherMapper.toResponseDto(teacher)).thenReturn(responseDto);

        TeacherDtoResponse result = teacherService.deleteTeacher(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(teacherRepository).deleteByIdTeacher(1L);
    }

    @Test
    void testDeleteTeacher_notFound() {
        when(teacherRepository.deleteByIdTeacher(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> teacherService.deleteTeacher(1L));
    }
}
