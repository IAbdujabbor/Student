package com.university.service;

import com.university.dto.request.AttendanceDtoRequest;
import com.university.dto.response.AttendanceDtoResponse;
import com.university.entity.Attendance;
import com.university.entity.Discipline;
import com.university.entity.Student;
import com.university.exceptions.NotFoundException;
import com.university.mapper.AttendanceMapper;
import com.university.repository.AttendanceRepository;
import com.university.repository.DisciplineRepository;
import com.university.repository.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class AttendanceServiceTest {

    private AttendanceRepository attendanceRepository;
    private AttendanceMapper attendanceMapper;
    private DisciplineRepository disciplineRepository;
    private StudentRepository studentRepository;
    private AttendanceService attendanceService;

    @BeforeEach
    void setUp() {
        attendanceRepository = Mockito.mock(AttendanceRepository.class);
        attendanceMapper = Mockito.mock(AttendanceMapper.class);
        disciplineRepository = Mockito.mock(DisciplineRepository.class);
        studentRepository = Mockito.mock(StudentRepository.class);

        attendanceService = new AttendanceService(attendanceRepository, attendanceMapper, disciplineRepository);
        attendanceService.studentRepository = studentRepository; // because it's @Inject
    }

    @Test
    void testGetAll() {
        Attendance attendance = new Attendance();
        when(attendanceRepository.getAll()).thenReturn(List.of(attendance));

        AttendanceDtoResponse dto = new AttendanceDtoResponse();
        when(attendanceMapper.toResponseDto(attendance)).thenReturn(dto);

        List<AttendanceDtoResponse> result = attendanceService.getAll();

        assertEquals(1, result.size());
        verify(attendanceRepository).getAll();
    }

    @Test
    void testGetByIdAttendance_found() {
        Attendance attendance = new Attendance();
        when(attendanceRepository.getByIdAttendance(1L)).thenReturn(Optional.of(attendance));

        AttendanceDtoResponse dto = new AttendanceDtoResponse();
        when(attendanceMapper.toResponseDto(attendance)).thenReturn(dto);

        Optional<AttendanceDtoResponse> result = attendanceService.getByIdAttendance(1L);

        assertTrue(result.isPresent());
        verify(attendanceRepository).getByIdAttendance(1L);
    }

    @Test
    void testAddAttendance_success() throws NotFoundException {
        AttendanceDtoRequest request = new AttendanceDtoRequest();
        request.setStudentId(1L);
        request.setDisciplineId(2L);

        Student student = new Student();
        student.setId(1L);

        Discipline discipline = new Discipline();
        discipline.setId(2L);

        Attendance attendance = new Attendance();

        when(studentRepository.findByIdOptional(1L)).thenReturn(Optional.of(student));
        when(disciplineRepository.getByIdDiscipline(2L)).thenReturn(Optional.of(discipline));
        when(attendanceMapper.toEntity(request)).thenReturn(attendance);

        AttendanceDtoResponse responseDto = new AttendanceDtoResponse();
        when(attendanceMapper.toResponseDto(attendance)).thenReturn(responseDto);

        AttendanceDtoResponse result = attendanceService.addAttendance(request);

        assertNotNull(result);
        verify(attendanceRepository).add(attendance);
    }

    @Test
    void testAddAttendance_studentOrDisciplineNotFound() {
        AttendanceDtoRequest request = new AttendanceDtoRequest();
        request.setStudentId(1L);
        request.setDisciplineId(2L);

        when(studentRepository.findByIdOptional(1L)).thenReturn(Optional.empty());
        when(disciplineRepository.getByIdDiscipline(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> attendanceService.addAttendance(request));
    }

    @Test
    void testUpdateAttendance_success() {
        AttendanceDtoRequest request = new AttendanceDtoRequest();
        Attendance attendance = new Attendance();

        when(attendanceMapper.toEntity(request)).thenReturn(attendance);
        AttendanceDtoResponse responseDto = new AttendanceDtoResponse();
        when(attendanceRepository.updateAttendance(1L, attendance)).thenReturn(Optional.of(attendance));
        when(attendanceMapper.toResponseDto(attendance)).thenReturn(responseDto);

        Optional<AttendanceDtoResponse> result = attendanceService.updateAttendance(1L, request);

        assertTrue(result.isPresent());
        verify(attendanceRepository).updateAttendance(1L, attendance);
    }

    @Test
    void testUpdateAttendance_notFound() {
        AttendanceDtoRequest request = new AttendanceDtoRequest();
        Attendance attendance = new Attendance();

        when(attendanceMapper.toEntity(request)).thenReturn(attendance);
        when(attendanceRepository.updateAttendance(1L, attendance)).thenReturn(Optional.empty());

        Optional<AttendanceDtoResponse> result = attendanceService.updateAttendance(1L, request);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteById_success() {
        Attendance attendance = new Attendance();
        when(attendanceRepository.deleteAttendanceById(1L)).thenReturn(Optional.of(attendance));

        AttendanceDtoResponse dto = new AttendanceDtoResponse();
        when(attendanceMapper.toResponseDto(attendance)).thenReturn(dto);

        Optional<AttendanceDtoResponse> result = attendanceService.deleteById(1L);

        assertTrue(result.isPresent());
        verify(attendanceRepository).deleteAttendanceById(1L);
    }

    @Test
    void testDeleteById_notFound() {
        when(attendanceRepository.deleteAttendanceById(1L)).thenReturn(Optional.empty());

        Optional<AttendanceDtoResponse> result = attendanceService.deleteById(1L);

        assertTrue(result.isEmpty());
    }
}
