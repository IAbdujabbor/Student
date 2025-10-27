package com.university.mapper;

import com.university.dto.TeacherDto;
import com.university.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "cdi")
public interface TeacherMapper {


    //@Mapping(target = "students", ignore = true)
   // @Mapping(target = "teachers", ignore = true)
    //TeacherDto toDto(Teacher teacher);

    //@Mapping(target = "students", ignore = true)
    //@Mapping(target = "teachers", ignore = true)
   // Teacher toEntity(TeacherDto dto);
}
