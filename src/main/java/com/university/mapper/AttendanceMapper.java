package com.university.mapper;

import com.university.dto.response.AttendanceDtoResponse;
import com.university.dto.request.AttendanceDtoRequest;
import com.university.dto.response.*;
import com.university.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "cdi")

// response Dto

public interface AttendanceMapper {

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName", expression = "java(entity.getStudent().getFirstName() + \" \" + entity.getStudent().getLastName())")
    @Mapping(target = "disciplineId", source = "discipline.id")
    @Mapping(target = "disciplineName", source = "discipline.disciplineName")
    AttendanceDtoResponse toResponseDto (Attendance entity);


// request Dto
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target= "discipline.id", source = "disciplineId")
    Attendance toEntity(AttendanceDtoRequest dto);
}
