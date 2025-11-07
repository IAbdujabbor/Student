package com.university.service;

import com.university.dto.request.StudentAndTeacherDtoRequest;
import com.university.dto.response.StudentAndTeacherDtoResponse;
import com.university.entity.StudentAndTeacher;
import com.university.exceptions.NotFoundException;
import com.university.mapper.StudentAndTeacherMapper;
import com.university.repository.StudentAndTeacherRepository;
import com.university.repository.StudentRepository;
import com.university.repository.TeacherRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class StudentAndTeacherServiceTest {

    private StudentAndTeacherRepository studentAndTeacherRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private StudentAndTeacherMapper studentAndTeacherMapper;
    private StudentAndTeacherService studentAndTeacherService;

    @BeforeEach
    void setUp() {
        studentAndTeacherRepository = mock(StudentAndTeacherRepository.class);
        studentRepository = mock(StudentRepository.class);
        teacherRepository = mock(TeacherRepository.class);
        studentAndTeacherMapper = mock(StudentAndTeacherMapper.class);

        studentAndTeacherService = new StudentAndTeacherService(
                studentAndTeacherRepository,
                studentRepository,
                teacherRepository,
                studentAndTeacherMapper
        );
    }

    // ✅ GetAll
    @Test
    void testGetAll() {
        StudentAndTeacher entity = new StudentAndTeacher();
        StudentAndTeacherDtoResponse dto = new StudentAndTeacherDtoResponse();

        when(studentAndTeacherRepository.getAll()).thenReturn(List.of(entity));
        when(studentAndTeacherMapper.toResponseDto(entity)).thenReturn(dto);

        List<StudentAndTeacherDtoResponse> result = studentAndTeacherService.getAll();

        assertEquals(1, result.size());
        verify(studentAndTeacherRepository).getAll();
    }

    // ✅ GetById - found
    @Test
    void testGetByIdStudentAndTeacher_found() {
        StudentAndTeacher entity = new StudentAndTeacher();
        StudentAndTeacherDtoResponse dto = new StudentAndTeacherDtoResponse();

        when(studentAndTeacherRepository.getByIdStudentAndTeacher(1L)).thenReturn(Optional.of(entity));
        when(studentAndTeacherMapper.toResponseDto(entity)).thenReturn(dto);

        Optional<StudentAndTeacherDtoResponse> result = studentAndTeacherService.getByIdStudentAndTeacher(1L);

        assertTrue(result.isPresent());
        verify(studentAndTeacherRepository).getByIdStudentAndTeacher(1L);
    }

    // ✅ GetById - not found
    @Test
    void testGetByIdStudentAndTeacher_notFound() {
        when(studentAndTeacherRepository.getByIdStudentAndTeacher(1L)).thenReturn(Optional.empty());

        Optional<StudentAndTeacherDtoResponse> result = studentAndTeacherService.getByIdStudentAndTeacher(1L);

        assertTrue(result.isEmpty());
    }

    // ✅ Add
    @Test
    void testAddStudentAndTeacher() {
        StudentAndTeacherDtoRequest request = new StudentAndTeacherDtoRequest();
        StudentAndTeacher entity = new StudentAndTeacher();
        StudentAndTeacherDtoResponse dto = new StudentAndTeacherDtoResponse();

        when(studentAndTeacherMapper.toEntity(request)).thenReturn(entity);
        when(studentAndTeacherMapper.toResponseDto(entity)).thenReturn(dto);

        StudentAndTeacherDtoResponse result = studentAndTeacherService.add(request);

        assertNotNull(result);
        verify(studentAndTeacherRepository).add(entity);
    }

    // ✅ Update - success
    @Test
    void testUpdateStudentAndTeacher_success() {
        StudentAndTeacherDtoRequest request = new StudentAndTeacherDtoRequest();
        StudentAndTeacher entity = new StudentAndTeacher();
        StudentAndTeacherDtoResponse dto = new StudentAndTeacherDtoResponse();

        when(studentAndTeacherMapper.toEntity(request)).thenReturn(entity);
        when(studentAndTeacherRepository.updateStudentAndTeacher(1L, entity)).thenReturn(Optional.of(entity));
        when(studentAndTeacherMapper.toResponseDto(entity)).thenReturn(dto);

        Optional<StudentAndTeacherDtoResponse> result = studentAndTeacherService.updateStudentAndTeacher(1L, request);

        assertTrue(result.isPresent());
        verify(studentAndTeacherRepository).updateStudentAndTeacher(1L, entity);
    }

    // ✅ Update - not found
    @Test
    void testUpdateStudentAndTeacher_notFound() {
        StudentAndTeacherDtoRequest request = new StudentAndTeacherDtoRequest();
        StudentAndTeacher entity = new StudentAndTeacher();

        when(studentAndTeacherMapper.toEntity(request)).thenReturn(entity);
        when(studentAndTeacherRepository.updateStudentAndTeacher(1L, entity)).thenReturn(Optional.empty());

        Optional<StudentAndTeacherDtoResponse> result = studentAndTeacherService.updateStudentAndTeacher(1L, request);

        assertTrue(result.isEmpty());
    }

    // ✅ Delete - success
    @Test
    void testDeleteByIdStudentAndTeacher_success() {
        StudentAndTeacher entity = new StudentAndTeacher();
        StudentAndTeacherDtoResponse dto = new StudentAndTeacherDtoResponse();

        when(studentAndTeacherRepository.deleteByStudentAndTeacherId(1L)).thenReturn(Optional.of(entity));
        when(studentAndTeacherMapper.toResponseDto(entity)).thenReturn(dto);

        Optional<StudentAndTeacherDtoResponse> result = studentAndTeacherService.deleteByIdStudentAndTeacher(1L);

        assertTrue(result.isPresent());
        verify(studentAndTeacherRepository).deleteByStudentAndTeacherId(1L);
    }

    // ✅ Delete - not found (throws exception)
    @Test
    void testDeleteByIdStudentAndTeacher_notFound() {
        when(studentAndTeacherRepository.deleteByStudentAndTeacherId(1L)).thenReturn(Optional.empty());

        jakarta.ws.rs.NotFoundException exception = assertThrows(
                jakarta.ws.rs.NotFoundException.class,
                () -> studentAndTeacherService.deleteByIdStudentAndTeacher(1L)
        );

        assertTrue(exception.getMessage().contains("not found"));
    }
}
