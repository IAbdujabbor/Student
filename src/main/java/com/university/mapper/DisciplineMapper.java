package com.university.mapper;

import com.university.dto.request.DisciplineDtoRequest;
import com.university.dto.response.DisciplineDtoResponse;
import com.university.entity.Discipline;
import com.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DisciplineMapper {

    // Request DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attendance", ignore = true)
    @Mapping(target = "teacher", source = "teacher") // teacher obyekt service orqali keladi
    Discipline toEntity(DisciplineDtoRequest dto, Teacher teacher);

    // Entity -> Response DTO
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "teacherFirstName", source = "teacher.firstName")
    @Mapping(target = "teacherLastName", source = "teacher.lastName")
    DisciplineDtoResponse toResponseDto(Discipline entity);

    List<DisciplineDtoResponse> toResponseDtoList(List<Discipline> entities);

    Discipline toEntity(DisciplineDtoRequest disciplineParameter);
}

