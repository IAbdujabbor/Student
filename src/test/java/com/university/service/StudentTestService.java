package com.university.service;

import com.university.dto.request.StudentDtoRequest;
import com.university.dto.response.StudentDtoResponse;
import com.university.entity.Student;
import com.university.mapper.StudentMapper;
import com.university.repository.StudentRepository;
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
class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        studentMapper = Mockito.mock(StudentMapper.class);
        studentService = new StudentService(studentRepository, studentMapper);
    }

    @Test
    void testAddStudent() {
        StudentDtoRequest request = new StudentDtoRequest();
        request.setFirstName("Wagner");
        request.setLastName("Thomas");
        request.setPatronymic("Schwarz");
        request.setAge(21);

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Wagner");
        student.setLastName("Thomas");
        student.setPatronymic("Schwarz");
        student.setAge(21);

        when(studentMapper.toEntity(request)).thenReturn(student);
        StudentDtoResponse responseDto = new StudentDtoResponse(1L, "Wagner", "Thomas", "Schwarz", 21);
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        StudentDtoResponse result = studentService.addStudent(request);

        assertNotNull(result);
        assertEquals("Wagner", result.getFirstName());
        assertEquals("Thomas", result.getLastName());
        verify(studentRepository).persist(student);
    }

    @Test
    void testGetAll() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Wagner");
        when(studentRepository.getAll()).thenReturn(List.of(student));

        StudentDtoResponse responseDto = new StudentDtoResponse(1L, "Wagner", "Thomas", "Schwarz", 21);
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        List<StudentDtoResponse> result = studentService.getAll();
        assertEquals(1, result.size());
        assertEquals("Wagner", result.get(0).getFirstName());
    }

    @Test
    void testGetByIdStudent_found() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.getByIdStudent(1L)).thenReturn(Optional.of(student));

        StudentDtoResponse responseDto = new StudentDtoResponse(1L, "Wagner", "Thomas", "Schwarz", 21);
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        Optional<StudentDtoResponse> result = studentService.getByIdStudent(1L);
        assertTrue(result.isPresent());
        assertEquals("Wagner", result.get().getFirstName());
    }

    @Test
    void testUpdateStudent_success() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("OldName");

        StudentDtoRequest request = new StudentDtoRequest();
        request.setFirstName("NewName");

        when(studentRepository.getByIdStudent(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toResponseDto(student))
                .thenReturn(new StudentDtoResponse(1L, "NewName", "Thomas", "Schwarz", 21));

        StudentDtoResponse result = studentService.updateStudent(1L, request);

        assertNotNull(result);
        assertEquals("NewName", result.getFirstName());
        verify(studentRepository).persist(student);
    }

    @Test
    void testUpdateStudent_notFound() {
        StudentDtoRequest request = new StudentDtoRequest();
        request.setFirstName("NewName");

        when(studentRepository.getByIdStudent(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.updateStudent(1L, request));
    }

    @Test
    void testDeleteStudent_success() {
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.deleteStudent(1L)).thenReturn(Optional.of(student));
        StudentDtoResponse responseDto = new StudentDtoResponse(1L, "Wagner", "Thomas", "Schwarz", 21);
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        StudentDtoResponse result = studentService.deleteStudent(1L);

        assertNotNull(result);
        assertEquals("Wagner", result.getFirstName());
        verify(studentRepository).deleteStudent(1L);
    }

    @Test
    void testDeleteStudent_notFound() {
        when(studentRepository.deleteStudent(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.deleteStudent(1L));
    }
}
