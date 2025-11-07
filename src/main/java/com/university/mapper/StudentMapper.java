package com.university.mapper;

import com.university.dto.request.StudentDtoRequest;
import com.university.dto.response.StudentDtoResponse;
import com.university.entity.Student;
import com.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "cdi")
public interface StudentMapper {

    StudentDtoResponse toResponseDto(Student entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Student toEntity(StudentDtoRequest dto);

}
