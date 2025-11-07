package com.university.mapper;

import com.university.dto.request.StudentAndTeacherDtoRequest;
import com.university.dto.response.StudentAndTeacherDtoResponse;
import com.university.entity.StudentAndTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface StudentAndTeacherMapper {

    // Request -> Entity
    @Mapping(target = "id", ignore = true)
    StudentAndTeacher toEntity(StudentAndTeacherDtoRequest dto);

    // Entity -> Response
    StudentAndTeacherDtoResponse toResponseDto(StudentAndTeacher entity);

    List<StudentAndTeacherDtoResponse> toResponseDtoList(List<StudentAndTeacher> entities);
}
