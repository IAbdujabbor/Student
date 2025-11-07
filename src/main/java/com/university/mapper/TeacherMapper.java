package com.university.mapper;

import com.university.dto.request.TeacherDtoRequest;
import com.university.dto.response.TeacherDtoResponse;
import com.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "cdi")
public interface TeacherMapper {

    TeacherDtoResponse toResponseDto(Teacher teacher);

     @Mapping(target = "id", ignore = true)
     @Mapping(target = "students", ignore = true)
     @Mapping(target = "discipline", ignore = true)
     Teacher toEntity(TeacherDtoRequest dto);

}

